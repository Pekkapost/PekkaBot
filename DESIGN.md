# Design Document — Conventions & Style

This document captures the structural and syntactic conventions used in this codebase. It is project-specific to PekkaBot (a Java + JDA Discord bot), but the conventions generalise to any small-to-medium single-process Java application.

The driving idea: **make it easy for a future reader to walk through the codebase top-to-bottom and never be surprised.** Surprise costs more than verbosity.

---

## 1. Project Layout

```
PekkaBot/
├── Connection.java           # Entry point (default package, runs from here)
├── README.md
├── DESIGN.md
├── META-INF/MANIFEST.MF      # Main-Class points at Connection
├── src/                      # Library code; never run directly
│   ├── discord/              # JDA setup, listener, name-lookup facade
│   ├── framework/            # In-tree replacement for archived jda-utilities
│   │   └── command/          # Command base class, CommandClient, CommandEvent
│   ├── commands/             # Bot commands, grouped by feature
│   │   ├── action/           # Hug, Pat, Slap, Scold, Slam
│   │   ├── ad/               # Ad-tracking commands
│   │   ├── currency/         # Points / Shion counter
│   │   ├── gary/             # Random Gary image
│   │   ├── other/            # Misc one-offs (AddMe, etc.)
│   │   ├── timer/            # Time / TimeCat
│   │   ├── unseen/           # Unseen-message ledger
│   │   └── whitegate/        # White Gate puzzle tracker
│   ├── manager/              # Static facades over singletons
│   │   ├── DiscordManager.java  # (lives in src/discord/ — sibling, see §10)
│   │   ├── EmbedManager.java    # All embed construction lives here
│   │   ├── SQLManager.java      # Static facade in front of the SQL singleton
│   │   └── utility/SQL.java     # The only class that touches JDBC
│   ├── structures/           # Generic data structures (Pair)
│   └── util/                 # Cross-cutting utilities
│       ├── CommandLoader.java  # Reflection-based command discovery
│       ├── Paths.java          # Project-root-anchored path resolution
│       └── Resources.java      # Tracked-in-git string constants
├── config/                   # Host-local config + dependency manifest
│   ├── BotConstants.java     # Gitignored — token, owner ids, prefix
│   └── libs.txt              # Pinned JAR versions
├── data/                     # Runtime state (gitignored, auto-created)
│   └── PekkaBot.db
└── libs/                     # JAR dependencies (not on a Maven Central path)
```

### Why this split?

- **Entry point at the repo root, default package.** `java -jar PekkaBot.jar` finds `Main-Class: Connection` in the manifest. Default package keeps the entry point trivially discoverable.
- **`src/` is library code.** Imported by `Connection`, never run directly. Library files never assume the process CWD.
- **`config/` is host-local.** `BotConstants.java` is gitignored — secrets and per-host knobs only. The directory is tracked so the structure is visible after a fresh clone. In IntelliJ, mark `config/` as a *Sources Root* so the `config` package compiles.
- **`data/` is machine-generated.** Whole directory gitignored. The SQLite file is auto-created on first connect.
- **`libs/` holds JAR dependencies.** No Gradle/Maven — pinned versions live in `config/libs.txt`. Drop matching JARs in `libs/` on first checkout.

### Path resolution

Anchor every path on the class's containing JAR (or compile-output directory), never on the process CWD. The repo-wide implementation lives in [`util/Paths.java`](src/util/Paths.java):

```java
URL location = Paths.class.getProtectionDomain().getCodeSource().getLocation();
File codeSource = new File(location.toURI());
File candidate = codeSource.isFile() ? codeSource.getParentFile() : codeSource;
// Walk up until data/ or libs/ appears alongside.
```

Then all callers use `Paths.data("PekkaBot.db")` or `Paths.dataPath(...)` — no `new File("data/...")` anywhere else in the codebase. This guarantees the bot behaves identically whether launched from IntelliJ or as a packaged JAR.

---

## 2. File & Class Naming

- **One top-level public class per file.** Filename matches the class name.
- **PascalCase for class files** (`SQLManager.java`, `TimeCat.java`).
- **Package names are lowercase, single word** (`commands`, `framework.command`, `manager.utility`).
- **Command class names mirror the user-facing command name** (e.g. `Slap.java` defines the `Slap` command). The reflection-based loader doesn't care, but it makes greppable navigation effortless.

---

## 3. Identifier Naming

| Identifier kind | Convention | Example |
|---|---|---|
| Class / interface | `PascalCase` | `CommandLoader`, `EmbedManager` |
| Method / field / parameter | `camelCase` | `getUserName`, `discordToken` |
| `static final` constant | `UPPER_SNAKE_CASE` | `COMMANDS_PACKAGE`, `PROJECT_ROOT` |
| Local variable | `camelCase`, descriptive | `String message`, `List<Command> commands` |
| Loop variable | descriptive, not single-letter (except `i`/`idx` in tight numeric loops) | `for (Command cmd : commands)` |

- Avoid one-letter names except in math-heavy or genuinely trivial loop scopes.
- Mutable static fields are package-private when they're the lone owners of their resource (e.g. `static SQL sql = new SQL();` in `SQLManager`). Not shared mutable state — sole-ownership state with a static accessor.

---

## 4. Imports

Group imports with a blank line between groups, in this order:

1. Local project imports (`config.*`, `framework.*`, `manager.*`, `util.*`)
2. Third-party — JDA (`net.dv8tion.jda.*`)
3. Third-party — other (`org.slf4j.*`, `org.xerial.*`)
4. JDK (`java.*`, `javax.*`)

Don't fight the IDE's optimizer past this — the goal is "grouped, blank lines between", not pixel-perfect ordering. No wildcard imports.

---

## 5. JavaDoc & Comments

**Default for inline comments: write none.** Identifiers should explain themselves.

Add a comment only when the **why** is non-obvious:

- A hidden invariant or ordering constraint (e.g. "MESSAGE_CONTENT is a privileged intent — must be enabled in the Developer Portal" in `Discord.java`).
- A workaround for a framework/library bug or quirk.
- A design choice a maintainer would otherwise "fix" by accident (e.g. why `SQL` doesn't apply the tmp+rename pattern).

**JavaDoc (`/** ... */`) goes on every non-trivial public class.** Lead with one summary sentence, then a paragraph or two on:

- What the class is responsible for.
- How it fits into the rest of the codebase (which classes call it, which classes it calls).
- Any non-obvious operational concerns (thread-safety, startup ordering, gitignored dependencies).

The existing top-of-file JavaDoc in [`Discord.java`](src/discord/Discord.java), [`CommandLoader.java`](src/util/CommandLoader.java), [`Paths.java`](src/util/Paths.java), [`SQL.java`](src/manager/utility/SQL.java), and [`EmbedManager.java`](src/manager/EmbedManager.java) are the reference examples — match their density and tone.

Do **not** document:
- What the code does (the code already says it).
- The current task or commit context.
- Specific callers (rots as the codebase evolves).
- Trivial getters / single-line wrappers.

---

## 6. Logging

```java
private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
```

- **SLF4J via `slf4j-simple`.** One `Logger` per class, `private static final`, named after the class.
- **Levels:**
  - `info` — noteworthy startup / lifecycle events (`"SQLite connected"`).
  - `warn` — recoverable issues that callers should know about (`"getUserName: user {} not found"`).
  - `error(msg, throwable)` — handled exceptions with the full stack trace as the last arg.
- Use SLF4J's `{}` placeholders, not string concatenation — they avoid the `toString()` call when the level is disabled and read more cleanly.
- Do **not** use `System.out.println` for diagnostics. The one exception is intentional user-facing CLI output (currently none).

---

## 7. Error Handling

- **Validate at boundaries.** The command framework's `execute(CommandEvent)` is the boundary; user-facing input checks belong there.
- **Internal helpers trust their callers** and do not defensively guard. `SQL.getPoints` doesn't validate that `id` is non-empty — the caller is expected to have done so (or to accept the SQLite-level behaviour).
- **Catch-and-log at top-level seams** so one failure doesn't kill the bot:
  - `Discord()` constructor wraps the entire JDA build in a try/catch.
  - `CommandLoader.discover()` catches per-class reflection failures so one broken command can't prevent the rest from loading.
  - Every public method on `SQL` catches `SQLException` and logs it — a transient DB error returns a sentinel (`0`, empty array) rather than propagating.
- **Never swallow with a bare `catch (Exception e) {}`.** If a catch block has no `logger.error`, it's a bug.

---

## 8. Configuration vs Resources

Two distinct stores, both intentionally hand-edited Java rather than property files:

- **[`config/BotConstants.java`](config/BotConstants.java)** — host-local, gitignored. Discord token, owner IDs, prefix. Created per-developer; never committed.
- **[`src/util/Resources.java`](src/util/Resources.java)** — tracked in git. Shared strings the bot ships with: invite URL, action GIF URLs, the Shion suffix. Anything that should be the same across every clone goes here.

The split exists so a new developer cloning the repo gets all the canned strings for free, and only has to create `BotConstants.java` with their own token.

---

## 9. Reflection-based Command Discovery

Adding a new command is a single-file affair:

1. Drop a class under `src/commands/<feature>/` that extends `framework.command.Command`.
2. Set `this.name`, `this.help`, optionally `this.aliases`, in the constructor.
3. Implement `protected void execute(CommandEvent event)`.

That's it — [`util/CommandLoader.java`](src/util/CommandLoader.java) scans the `commands` package (in the running JAR or compile-output dir), instantiates every concrete `Command` subclass with a no-arg constructor, and hands the list to the `CommandClient`. No registration line, no manifest edit, no annotation.

Per-class instantiation failures are logged and swallowed so one broken command can't prevent startup. The order is alphabetised by command name to keep help-embed output stable.

When adding a new **top-level feature package** under `commands/`, also add it to the `HELP_CATEGORY_DISPLAY` map in [`EmbedManager.java`](src/manager/EmbedManager.java) so the help embed groups the new commands under a readable heading instead of falling into "Other".

---

## 10. The Manager / Static-Facade Pattern

Three classes follow the same shape — a singleton wrapped in a static facade:

| Facade | Singleton | Purpose |
|---|---|---|
| `DiscordManager` | `Discord` | JDA client lifetime + global user-id → name lookup |
| `SQLManager` | `SQL` | The lone JDBC `Connection` + every SQL operation |
| (No facade) `EmbedManager` | itself | All embed construction; pure-static, no state |

The pattern exists so commands don't have to thread JDA / SQL references through their constructors — they call `DiscordManager.getUserName(id)` or `SQLManager.getPoints(id)` directly. The cost is that adding a new SQL method means touching both `SQL.java` and `SQLManager.java`; the one-liner per method in `SQLManager` keeps that indirection cheap.

If a fourth singleton appears, consider whether the static-facade pattern still pays for itself or whether a small DI seam would be cleaner. Don't introduce a DI framework for the fourth singleton; reconsider at the seventh.

---

## 11. Persistence (SQLite)

- **Single connection, held for the bot's lifetime** in [`SQL`](src/manager/utility/SQL.java).
- **Every public method is `synchronized`.** SQLite is single-writer; the monitor on `SQL` is the cheapest correct lock. JDA dispatches listeners on a thread pool, so collisions are possible without it.
- **Schema lives in `SQL.java`'s JavaDoc**, not in a migration system. Adding a table or column means editing the file *and* running a one-time `sqlite3` `ALTER TABLE` at deploy time. There is no automatic migration — if the schema drifts, the bot crashes on the next query that hits the missing column. Accept the operator burden; the alternative (a migration framework) is overkill for one DB file.
- **No `fallbackToDestructiveMigration` equivalent.** Losing user data is not an upgrade path.
- **Use `PreparedStatement` everywhere.** No string concatenation into SQL — that's the only way to be SQL-injection-safe long-term, and it reads no worse.
- **Connection failure is logged, not thrown.** The bot starts even with a dead DB; every subsequent query short-circuits via `checkConnection()`.
- **Crash safety comes from SQLite's WAL.** Do **not** apply a tmp-file + rename pattern around the `.db` file — that would corrupt the WAL.

---

## 12. Dependencies

- Pinned in [`config/libs.txt`](config/libs.txt). The actual JARs are *not* committed; they live in `libs/` which is gitignored.
- Three deps total: JDA (Discord), sqlite-jdbc (DB), slf4j-simple (logging). Resist the urge to add a fourth without a clear reason — every JAR is something that has to be re-downloaded on a fresh clone.
- Versions in `libs.txt` are authoritative. If a JAR version mismatch is suspected, compare `libs/*.jar` filenames against the file.

---

## 13. JDA Specifics

- **Intents are explicit.** Only enable what's actually consumed: `GUILD_EXPRESSIONS`, `GUILD_MESSAGES`, `GUILD_MESSAGE_REACTIONS`, `MESSAGE_CONTENT`. The last is privileged and must be enabled in the Developer Portal.
- **Disable unused caches.** `JDABuilder.disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.VOICE_STATE)` — the bot doesn't read these, so JDA shouldn't keep them populated per-guild.
- **`event.getChannel().sendMessage(...).queue()`** is the default send pattern. `queue()` enqueues asynchronously so listener threads don't block on the REST call.
- **`retrieveUserById(...).complete()`** is used in `Discord.getUserName` deliberately — that lookup is rare, happens off the gateway thread (in command-`execute` context), and the caller needs the result synchronously.
- **No slash commands yet.** Everything is prefix commands routed through the framework's `CommandClient` listener. If slash commands are added, extend the framework rather than bypassing it.

---

## 14. Anti-patterns to Avoid

- **`System.out.println` for diagnostics.** Use SLF4J.
- **String-concatenated SQL.** Use `PreparedStatement` placeholders.
- **`new File("data/...")` or any CWD-relative path.** Use `util.Paths`.
- **Adding a registration line somewhere for a new command.** The reflection loader picks it up — don't introduce a manual registry.
- **Catching `Throwable` to silence a real bug.** If the catch has no `logger.error`, it's a bug.
- **Sleeping / blocking on the gateway thread.** Use `queue()`, not `complete()`, in event listeners. The exception is `Discord.getUserName`, which is called from command-execution context.
- **Feature flags or backwards-compatibility shims for code we control end-to-end.** Change the code instead.
- **Repeated `BotConstants` reads inside hot loops.** They're constants; read once into a local.
- **Putting GIF / image URLs in command files.** Add them to `Resources.java` so the embed style is consistent and the URLs are swappable in one place.

---

## 15. When to Break These Rules

When a specific reader experience benefits, when JDA forces an idiom, or when strict adherence would obscure intent. The goal is to be **predictable** — predictability beats personal preference.
