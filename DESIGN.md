# Design Document — Conventions & Style

This document captures the structural and syntactic conventions used in this codebase. It is project-agnostic: every rule applies to small-to-medium Python projects regardless of what they do.

The driving idea: **make it easy for a future reader (or future-you) to walk through the codebase top-to-bottom and never be surprised**. Surprise costs more than verbosity.

---

## 1. Project Layout

```
<repo>/
├── <Entrypoint>.py     # Single-file launcher at the repo root
├── README.md           # User-facing docs
├── DESIGN.md           # This file
├── pyproject.toml      # Tooling config (ruff, formatter, ...)
├── .gitignore
├── .gitattributes      # `* text=auto` for cross-platform line-ending normalization
├── src/                # Library code; never run directly
│   └── <feature>/      # Feature modules
├── config/             # Host-local config + dependency manifests + internal docs
│   ├── <App>Settings.py  # Gitignored — secrets and per-host values
│   └── requirements.txt
└── data/               # Runtime state (gitignored, auto-created)
```

Optional but commonly useful directories:

- `assets/` — static binary content the program reads but doesn't modify (images, fonts, sample data files).
- `tests/` — test suite, mirroring `src/` structure.
- `docs/` — extended documentation if `README.md` and `DESIGN.md` aren't enough.

### Why this split?

- **Entry point at the repo root.** A new developer's first instinct is `python <entrypoint>.py` from the project root. Putting it anywhere else creates friction.
- **`src/` is library code.** The entry point imports from it, but it is never the working directory. Library files shouldn't assume any particular CWD.
- **`config/` is host-local.** Secrets and per-machine settings live here, gitignored. The directory itself is tracked so the structure is visible to a fresh clone, but the secret-bearing files are not.
- **`data/` is machine-generated.** Whole directory gitignored. Auto-created on first write so a fresh clone doesn't crash.

### Path resolution

Anchor every path on `__file__`, never on the process CWD:

```python
ROOT = os.path.dirname(os.path.realpath(__file__))
SRC = os.path.join(ROOT, "src")
CONFIG = os.path.join(ROOT, "config")
```

This guarantees the program runs the same regardless of where `python` is invoked from. For files deeper in the tree reaching back to a shared directory:

```python
_DATA_DIR = os.path.join(os.path.dirname(os.path.realpath(__file__)), "..", "..", "data")
PRIMARY_PATH = os.path.join(_DATA_DIR, "primary.json")
SECONDARY_PATH = os.path.join(_DATA_DIR, "secondary.json")
```

Extract the directory anchor into a single constant when multiple paths share the same root. Don't repeat the `..`-chain.

---

## 2. File & Module Naming

- **Module files use PascalCase** when they primarily define one class, mirroring the class name.
- **All-lowercase** for utility modules with no dominant class (e.g. `utils.py`, `helpers.py`).
- **Match the file name to the primary export.** A reader scanning the directory should be able to predict what's inside.

---

## 3. Identifier Naming Conventions

| Identifier kind | Convention | Example |
|---|---|---|
| Function / method | `snake_case` | `get_secret`, `_load_state` |
| Variable / parameter | `snake_case` | `event_time`, `record_id` |
| Module-level constant | `UPPER_SNAKE_CASE` | `DEFAULT_TIMEOUT`, `MAX_PAYLOAD_LEN` |
| Class | `PascalCase` | `Worker`, `Record` |
| Module-private (any kind) | `_leading_underscore` | `_atomic_write_json`, `_DATA_DIR` |
| Loop variable | `snake_case`, descriptive | `for record in records:` (not `for r in ...`) |

### Rules of thumb

- **Never single-letter variables outside of `i`/`j` indices in tight numeric loops.** Even there, `idx` is preferable.
- **Don't shadow Python builtins.** `list`, `id`, `type`, `input`, `dict`, `str` are off-limits as variable or parameter names. When the obvious name shadows a builtin, append a trailing underscore (`list_`, `id_`) or rename to a descriptive alternative (`spec` instead of `input`).
- **For external-facing names that must collide with a builtin** (e.g. an API parameter that users see as `id`), use the builtin-shadowing alternative as the Python identifier and rely on whatever your framework provides to expose a different external name. Internally use the safe name; externally keep the convenient one.
- **Use the same name for the same concept across functions.** If two functions accept the same value, they use the same parameter name. Mixed naming (`r` here, `record` there; `now` vs `event_time`) makes the relationship harder to see.
- **Prefer concrete domain words over generics.** `record` beats `r`. `event_time` beats `dt`. Keep names short *only* when the scope is small enough that the abbreviation is unambiguous.

---

## 4. Imports

Three groups, in this order, with a blank line between groups:

1. **Standard library** (`os`, `sys`, `json`, `datetime`)
2. **Third-party** (anything from PyPI)
3. **Local** (modules from this project)

Within each group, alphabetize. Use `import X` for whole modules and `from X import Y` for specific names; pick whichever reads more clearly at the call site.

```python
import json
import logging
import os
from datetime import datetime, timedelta
from typing import TypedDict

import third_party_lib
from another_lib import helper

from local_module import do_thing
```

### When to violate the order

Only when a `sys.path` injection forces it:

```python
import os
import sys

ROOT = os.path.dirname(os.path.realpath(__file__))
sys.path.insert(0, os.path.join(ROOT, "src"))

# Local import depends on the sys.path tweak above.
from project_settings import get_secret

# Now back to the normal three-group order.
import asyncio
import logging
```

When you have to do this, leave a comment explaining why the order is split.

---

## 5. Type Hints

### Always annotate

- All function parameters except `self` and `cls`.
- Public function and method return types.
- Module-level constants whose type isn't obvious from the literal (e.g. `_LOOKUP: dict[str, int] = {}`).

### Trivial helpers can skip the return type

If the body is one line and the return is obviously a string, int, or bool, the return-type hint adds noise. Trust the reader.

### Use modern syntax (Python 3.10+)

```python
TIMEOUT: int | None = None              # not Optional[int]
def f(xs: list[int]) -> dict[str, int]:  # not List/Dict from typing
```

### TypedDict for structured dicts

When a dict has a fixed schema that flows through several functions, define a `TypedDict`:

```python
class Record(TypedDict):
    id: int
    name: str
    created_at: str
    payload: dict
```

Then use `Record` as the type for parameters and return values. The IDE / lint will catch typos in keys.

### When `dict` (untyped) is fine

Top-level state with mixed-shape contents. Adding a deeply nested TypedDict for these is more noise than help. Annotate the leaves, not the container.

### `object` for "any JSON-serializable value"

```python
def _atomic_write_json(path: str, payload: object) -> None:
```

`Any` invites trouble; `object` says "I won't introspect this; the JSON encoder will."

---

## 6. Module Structure

Top-to-bottom, every module follows this skeleton:

```python
"""
Module docstring.

What this module is, why it exists, and any non-obvious load-bearing
behavior a maintainer needs to know on first read.
"""

# ---- imports (stdlib → third-party → local) ----

# ---- module-level logger ----
logger = logging.getLogger(__name__)


# ---- type definitions (TypedDict, NamedTuple, dataclass) ----

class Record(TypedDict):
    ...


# ---------------------------------------------------------------------------
# Constants
# ---------------------------------------------------------------------------

DEFAULT_X = "..."
_PRIVATE_PATTERN = re.compile(...)


# ---------------------------------------------------------------------------
# Generic helpers (no module-state dependencies)
# ---------------------------------------------------------------------------

def _atomic_write_json(...): ...
def _normalize(...): ...


# ---------------------------------------------------------------------------
# Persistence (touches the data layer)
# ---------------------------------------------------------------------------

def _load_state(): ...
def _save_state(): ...


# ---------------------------------------------------------------------------
# Main class
# ---------------------------------------------------------------------------

class Worker:
    ...


# ---- entry point ----

def setup():
    ...
```

Helpers come **before** the class that uses them. A reader walking down the file always has the context for what they're about to encounter.

---

## 7. Section Dividers

Use 75-character dashed comment blocks to separate logical sections in any file longer than ~150 lines:

```python
# ---------------------------------------------------------------------------
# Section name
# ---------------------------------------------------------------------------
```

Mirror this pattern *inside* large classes too:

```python
class Worker:
    def __init__(self, ...): ...
    def setup(self, ...): ...
    def teardown(self, ...): ...

    # -----------------------------------------------------------------------
    # Public API
    # -----------------------------------------------------------------------

    def process(self, ...): ...
    def query(self, ...): ...

    # -----------------------------------------------------------------------
    # Background tasks
    # -----------------------------------------------------------------------

    def _poll(self, ...): ...
    def _refresh(self, ...): ...
```

Inner section dividers use 71 dashes (account for the 4-space indent inside the class). Pick *one* width per file and stay with it.

---

## 8. Comments

### Default: write no comments

If the code reads as English, comments add nothing. Identifiers that explain themselves are worth more than comments that paraphrase them.

### When to add a comment

Add a comment only when **the why is non-obvious**:

- A hidden constraint or invariant the code relies on.
- A workaround for a specific bug, with a link or one-line explanation.
- A surprising design choice that a maintainer would otherwise "fix" by accident.
- A non-obvious side effect or ordering requirement.

### What NOT to comment

- The "what" (the code already says it).
- The current task ("added for the X feature") — that belongs in the commit message.
- Specific callers ("used by Y") — those rot as the codebase changes.
- Things obviously inferable from types.

### Style

- Full sentences, ending with a period.
- Leading capital.
- Wrapped to fit the line length.
- Stay on the same indent level as the code being commented.

```python
# Snapshot the list so concurrent mutations during one of the awaits below
# can't desync iteration or skip / duplicate entries.
for record in list(self.records):
    ...
```

---

## 9. Docstrings

### Single-line for trivial functions

```python
def _safe_call(name: str) -> Result:
    """Look up `name`, falling back to the default if not found."""
```

### Multi-line for non-trivial

Summary sentence on its own line, blank line, then explanation:

```python
def _resolve(name: str) -> str:
    """
    Resolve a user-typed string to a canonical internal name.

    Accepts known shorthand (case-insensitively) or a fully-qualified name.
    Raises ValueError with a helpful message on unknown input.
    """
```

### Conventions

- Triple double quotes (`"""`).
- Imperative mood for the summary ("Resolve…", not "Resolves…").
- Document raises, side effects, and any non-obvious return semantics.
- Don't restate the type signature.

### Module docstring

Every module gets one. It should answer:

- What is this module?
- Why does it exist (what role does it play in the broader system)?
- Anything load-bearing a future reader needs to know on the first scroll?

---

## 10. Logging

### Setup

A module-level logger keyed on `__name__`:

```python
import logging
logger = logging.getLogger(__name__)
```

The entry-point file configures the root handler:

```python
logging.basicConfig(
    filename="output.log",
    filemode="w",
    format="%(name)s - %(levelname)s - %(message)s",
)
```

### Use format-string placeholders, not f-strings

```python
# Good — message string built lazily, only if the level is enabled.
logger.error("Value %r is invalid; falling back to default", value)

# Bad — string formatted even if the level is disabled.
logger.error(f"Value {value!r} is invalid; falling back to default")
```

### Pick the right level

| Method | When |
|---|---|
| `logger.debug` | Verbose tracing for development. |
| `logger.info` | Noteworthy state changes (startup events, recovery actions). |
| `logger.warning` | Recoverable issue worth surfacing. |
| `logger.error` | Handled error with context (e.g. fallback used). |
| `logger.exception` | **Only inside `except:` blocks.** Records traceback. |

### `print` is for the operator, `logger` is for forensics

`print` writes to stdout — visible to whoever launched the process. `logger` writes to the log file. They serve different audiences:

- Use `print` for fatal startup messages a developer needs to see immediately and the "I'm ready" signal.
- Use `logger` for anything you want recorded for later inspection.
- Don't `print` and `logger.error` the same thing — pick one based on the audience.

### Message style

- Sentence case, no leading caps unless naming a thing.
- End with a period if it's a sentence.
- Include identifying context (`%s`, `%r`) for any "which one" — id, path, name.

---

## 11. Error Handling

### Catch specific exceptions

```python
# Good
try:
    data = json.load(f)
except json.JSONDecodeError:
    ...

# Avoid (unless you log+re-raise or have a deliberate reason)
except Exception:
    ...
```

A bare `except Exception:` is OK in two cases:

1. Inside a long-running loop where one failure shouldn't kill everything (log via `logger.exception` and continue).
2. At the very top of the entry point as a last-resort handler.

### Validate at boundaries

User input, external API responses, and file contents are *boundaries*. Validate at the boundary; trust internal callers.

```python
# User-typed string — validate.
try:
    value = parse_value(raw)
except ValueError:
    notify_user("Could not parse input.")
    return

# Internal helpers receive validated data — no defensive guards.
def _process(value: Value) -> None:
    ...  # trust caller
```

### Defensive `.get()` only for legacy / optional fields

Use `dict.get("key")` only for fields that *might legitimately not be set* (e.g. older persisted records that pre-date the field). For required fields, use direct `dict["key"]` access — a `KeyError` is the right failure mode.

```python
# Required field — direct access.
record["id"]

# Optional / legacy-friendly — .get() with a sensible default in the call.
record.get("opt_in_to_new_thing")
```

### User-facing error messages

- Tell the user what went wrong **and what to do about it**.
- Use sentence case, end with a period.
- Quote user input back with backticks so they can spot typos:

```python
f"Could not parse `{value}` — expected an integer."
```

---

## 12. String Style

- **Double quotes by default.** Single quotes only inside double-quoted strings to avoid escaping (`"don't"`).
- **f-strings for interpolation.** No `%` formatting outside of `logger.*` calls (where lazy formatting matters).
- **Adjacent literals for multi-line strings.** Python concatenates them at compile time:

  ```python
  message = (
      f"Saved record `#{rid}` — created at {created_at} "
      f"with {item_count} items."
  )
  ```

- **Triple-quoted strings only for docstrings or genuine multi-line content.** Don't use them as a substitute for adjacent-literal concatenation.

---

## 13. Line Length & Formatting

### 100 characters

Wide enough that most lines don't need wrapping; narrow enough for side-by-side diffs.

### Wrapping multi-arg calls

One argument per line, trailing comma:

```python
worker = Worker(
    source=source,
    sink=sink,
    batch_size=BATCH_SIZE,
    retry_policy=DEFAULT_POLICY,
    metrics=metrics,
)
```

The trailing comma keeps future diffs minimal — adding a new arg only touches its own line.

### Wrapping conditionals

```python
strategy = (
    PRIORITY_STRATEGY if record.get("priority") else DEFAULT_STRATEGY
)
```

Parens to enable wrapping; ternary on its own line.

### Two blank lines between top-level definitions, one between methods

Standard PEP 8.

---

## 14. Class Organization

Inside a class, methods appear in this order:

1. `__init__`
2. Lifecycle hooks (setup, teardown, framework-specific load/unload).
3. Predicate / gate methods (validation, permission checks).
4. Class-level attribute declarations that aren't simple constants.
5. Public methods (in narrative order — what users invoke first goes first).
6. Private helpers prefixed with `_`.

Within a long class, use section dividers to group related methods.

---

## 15. Persistence Patterns

Whenever the app reads / writes state to disk:

### Atomic writes via tmp + rename

```python
def _atomic_write_json(path: str, payload: object) -> None:
    os.makedirs(os.path.dirname(path), exist_ok=True)
    tmp_path = path + ".tmp"
    with open(tmp_path, "w") as f:
        json.dump(payload, f, indent=2)
    os.replace(tmp_path, path)  # atomic on POSIX and Windows
```

A crash mid-write can never leave a half-written file behind.

### Tolerate corrupted state

If the file exists but is unreadable, **quarantine it and start fresh** rather than crash forever:

```python
try:
    with open(STATE_PATH) as f:
        return json.load(f)
except json.JSONDecodeError:
    bad_path = f"{STATE_PATH}.bad-{datetime.now(timezone.utc).strftime('%Y%m%dT%H%M%SZ')}"
    os.replace(STATE_PATH, bad_path)
    logger.error("Corrupted file quarantined to %s; starting with empty state", bad_path)
    return _empty_state()
```

### In-memory cache + disk on save

Read once at startup; mutate the in-memory copy; persist after each modification. Avoid re-reading the file on every operation unless you specifically need to pick up external edits.

### Migration logic in load

Old data files should keep working. Backfill new fields and migrate renamed ones inside the load function:

```python
for record in data.get("records", []):
    if "new_field" not in record and "old_field" in record:
        record["new_field"] = transform(record.pop("old_field"))
    record.setdefault("added_field", DEFAULT_VALUE)
```

Migration is one-way and cumulative. Once it runs, the file is in the new shape.

### Auto-create directories

`os.makedirs(os.path.dirname(path), exist_ok=True)` inside the writer means a fresh clone never has to be told to create the data directory.

---

## 16. Configuration & Secrets

### Secrets live in a gitignored Python file

A small module under `config/`, gitignored, exposes getters:

```python
SECRET_TOKEN = ""
SOME_URL = ""

def get_secret_token():
    return SECRET_TOKEN

def get_some_url():
    return SOME_URL
```

The `README.md` documents the template so a fresh clone knows what to create.

### Why getters and not just constants?

- Allows lazy / re-readable access (a hot reload can pick up edits without restarting).
- Lets the implementation swap to env vars / a vault later without touching callers.

### Other config

- `requirements.txt`: use compatible-release pins (`~=2.7`).
- `pyproject.toml`: tooling config (linter, formatter, build system).
- Application config (defaults like `MAX_PAYLOAD_LEN`, `RETRY_LIMIT`, `WINDOW_SECONDS`) lives as module-level constants near the top of the relevant file, not in a separate config file. Constants that operators commonly tune go in `config/`.

---

## 17. Documentation Files

### `README.md` at the repo root

The README is for *users* of the project (people who clone, install, and run it), not maintainers. Section order is fixed; each section has a specific shape.

#### Heading hierarchy

| Level | Used for | Style |
|---|---|---|
| `h1` | Project title (one only, at the very top) | Plain markdown `#`. Centered if you want a stylized title (`<h1 align="center">…</h1>`). |
| `h2` | Top-level sections (`## Layout`, `## Setup`, …) | One per major section, in the fixed order below. |
| `h3` | Subsections inside a section. Always bold. | `### **Subsection name**` |

#### Tagline (optional, between title and first h2)

A one-line descriptor under the title, smaller than the h1, centered:

```markdown
<h4 align="center">A one-line description of the project</h4>
```

Use `<h4>` (not `<p>` / `<em>`) so emoji and small text stay crisp at any zoom level. Keep it under ~60 chars.

#### Section order

```
# Title

(optional tagline)

## Layout
## Tech Stack
## Setup
## Architecture
## Adding a new <thing>          (only for plugin-extensible projects)
## <Public surface>              (commands, API, etc.)
## Limitations
## Authors / Credits
```

Every README has Layout, Tech Stack, Setup, Architecture, public surface, Limitations, and Authors. The "Adding a new <thing>" section appears only when the project is extensible by users.

#### `## Layout`

A code block containing a small file tree. Use the box-drawing characters (`├── └── │`) for the tree, and inline trailing comments to explain non-obvious entries:

````markdown
```
<repo>/
├── <Entrypoint>.py     # entry point — run this
├── src/
│   └── <feature>/      # feature modules (auto-loaded on startup)
├── config/             # secrets, deps, internal docs
└── data/               # runtime state (gitignored)
```
````

Don't list every file — only the directories and entry points a reader needs to orient themselves. Keep the tree under ~10 lines.

#### `## Tech Stack`

A 3-column table (`Dependency | Version | Purpose`). Link each dependency to its homepage. Keep purposes to one short phrase.

```markdown
| Dependency | Version | Purpose |
|---|---|---|
| [<Library>](https://example.com) | 2.7+ | One-line role in this project |
| Python | 3.11+ | Runtime |
```

Below the table, one sentence pointing at the entry point and noting where library code lives.

#### `## Setup`

Numbered subsections, each `h3`, bold, with the step number in the heading itself:

```markdown
### **1. Install dependencies**
### **2. <App>Settings.py**
### **3. <External service config>**
### **4. Run**
```

Each step contains:

- One short paragraph of context (or zero if the step is self-explanatory).
- A code block (bash for commands, python/jsonc/etc. for templates).
- Optional follow-up sentences explaining placeholders or gotchas.

The Run step always includes the launch command and the log file location.

If a config template (e.g. `Settings.py`) needs explanation, put the template in a fenced `python` code block, then explain each placeholder in one-line paragraphs immediately below.

#### `## Architecture`

A 2-column table (`Module | Description`) mapping path → role. Each `Module` cell is a markdown link to the path. Each description is one short sentence:

```markdown
| Module | Description |
|---|---|
| [<Entrypoint>.py](<Entrypoint>.py) | Bot/app entry point at the repo root. |
| [config/<App>Settings.py](config/<App>Settings.py) | Holds secrets and exposes getters. |
| [src/<feature>/<File>.py](src/<feature>/<File>.py) | One sentence per feature module. |
| [data/](data/) | Auto-created at runtime. Holds <list of runtime files>. |
```

Skip rows for trivial files. Group related files into a single row when describing a directory.

#### `## Adding a new <thing>` *(only for extensible projects)*

One paragraph explaining the auto-discovery / plugin convention, followed by a minimal skeleton code block users can copy:

````markdown
Every `.py` file under `<dir>/` is auto-loaded on startup by [`<Function>`](<Path>). To add a new <thing>, drop a file in that directory with this skeleton:

```python
# minimal viable skeleton for the new <thing>
```

Restart the application and the new <thing> takes effect. <Any propagation note.> Files starting with `_` (e.g. `__init__.py`, `_helpers.py`) are skipped, so you can keep support modules alongside the <thing>s.
````

#### `## <Public surface>`

Whatever surface the project exposes — commands, REST endpoints, CLI flags, plugin API. Group with `h3` subsections (bold) when there are multiple categories. Each category is a table.

##### Command / endpoint table convention

- 2 columns: `<Surface> | Description`.
- The `<Surface>` cell shows the literal invocation, with **inputs in `[brackets]`**: `/command [param1] [param2]`. Brackets visually separate the literal command from its arguments.
- The description cell is concise. Repeated cross-cutting constraints (e.g. permissions, timezone defaults, format conventions) belong in a single sentence below the table, not in every row.

```markdown
| Command | Description |
|---|---|
| `/<command> [<arg>]` | Short description. |
| `/<command-2>` | Short description. |

Cross-cutting note about permissions, defaults, or behavior that applies to all rows above.
```

##### `**Sample output**` subsection

When the public surface produces output that's worth showing, add a `**Sample output**` block (bold paragraph header, not an `h4`) below the table. Use a fenced code block for the literal output and an indented blockquote (`>`) for the rendered version when relevant:

````markdown
**Sample output**

When <action> happens, <thing> emits:

```
<literal output>
```

…which renders as:

> <rendered version>
````

#### `## Limitations`

Bullet list. Each bullet is one self-contained sentence describing a known gotcha, edge case, missing feature, or scaling limit. Reference the relevant constant or function inline (`CATCHUP_WINDOW_MINUTES`, `[`Connection.py`](Connection.py)`) so a reader can jump to the implementation.

```markdown
- One-sentence limitation. Reference `CONSTANT_NAME` or [path](path) inline.
- Another limitation, with the *why* if non-obvious.
```

Don't write a "Future work" section — limitations are observable behavior; future work is speculation that rots.

#### `## Authors / Credits`

Bulleted list of contributors (one line each), followed by any fork attribution as a separate paragraph below:

```markdown
- **@Username** — Role
- **@Other** — Role

Forked from the original [<Project>](<URL>) framework.
```

Keep it short. Don't list every minor contributor here — git history is canonical.

#### What does NOT belong in the README

- Changelogs (use `CHANGELOG.md` or git log).
- Version-specific migration notes (use release notes).
- Internal architecture deep-dives (use `DESIGN.md` or `docs/`).
- API-reference dumps (let the source be the source of truth).
- Roadmap / future work (rots).
- Auto-generated content of any kind (drifts).

### `DESIGN.md` (this file)

Project-wide conventions and "how we do things". Aimed at maintainers, not users.

### `config/*.txt` or `docs/`

Long-form internal walkthroughs that don't belong in the README (because they go too deep) or in code (because they describe *the whole module*). Reference these from the file they describe.

### Don't auto-generate docs from code

API references that drift out of sync are worse than no docs. Keep the README's tables short and let the source be the source of truth.

---

## 18. Repo Hygiene

### `.gitignore`

Everything that's machine-generated or host-local:

```
__pycache__/
.venv/
.env
*.egg-info/
config/<App>Settings.py
data/
output.log
```

Plus any IDE / editor / tooling directories that hold per-developer state.

### `.gitattributes`

Cross-platform line endings:

```
* text=auto
```

### Branch naming

Hyphenated kebab-case. Capitalization is fine if you prefer it (`Design-Restructure`), but spaces are not allowed. Avoid underscores in branch names — they round-trip awkwardly through URL bars.

### Commit messages

- Subject line in imperative mood, ≤ ~70 chars.
- Body explains the *why* and any non-obvious context, wrapped at 72 chars.
- One logical change per commit.
- Don't commit secrets, machine-specific paths, or generated artifacts.

---

## 19. Linter & Formatter

### Use `ruff` for both linting and formatting

`pyproject.toml`:

```toml
[tool.ruff]
line-length = 100
target-version = "py311"

[tool.ruff.lint]
select = ["E", "W", "F", "I", "N"]

[tool.ruff.format]
quote-style = "double"
indent-style = "space"
```

Rule sets:

- **E / W**: pycodestyle errors and warnings (PEP 8 mechanics).
- **F**: pyflakes (unused imports, undefined names).
- **I**: isort (import ordering).
- **N**: pep8-naming (snake_case enforcement, no camelCase).

Run `ruff check` and `ruff format` before every commit.

---

## 20. Anti-patterns to Avoid

- **camelCase in Python.** PEP 8 and the `N` ruff rules forbid it. Existing camelCase from external sources gets renamed at the boundary.
- **Catch-all `except Exception:` without log + re-raise.** Hides real bugs. Either narrow the exception type or log the traceback before swallowing.
- **`print` instead of `logger`** for anything that should be persisted. Print statements vanish into stdout buffers.
- **String formatting in logger calls.** `logger.info(f"...")` defeats lazy formatting. Use `logger.info("...", arg)`.
- **Global mutable singletons.** State lives on object instances, not at module level. If you need shared state across components, pass it explicitly.
- **Hard-coded paths.** Always anchor on `__file__` or accept a path parameter.
- **Re-reading config on every operation** when the value is stable for the process lifetime. Read once at startup. (Re-read only when you specifically want hot-reload semantics — and document that intent in a comment.)
- **Adding tests / abstractions / configurability for hypothetical needs.** Add them when the second use case shows up.

---

## 21. When to Break These Rules

These conventions are defaults, not laws. Break them when:

- A specific reader experience would benefit from the deviation, *and you say so in a comment*.
- A library or framework forces a different idiom (e.g. lifecycle hooks named by the framework override the "_-prefix means private" intuition).
- Strict adherence would obscure intent (a one-line `lambda` may be clearer than a named function for a trivial callback).

The point is to be **predictable**. Predictability beats personal preference.
