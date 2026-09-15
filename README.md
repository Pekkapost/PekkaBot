# PekkaBot

<h4 align="center">A personal Discord bot for the Another Eden Discord server</h4>

## Layout

```
PekkaBot/
├── Connection.java     # entry point — run from here
├── src/                # library code (never run directly)
│   ├── commands/       # bot commands, grouped by feature
│   ├── discord/        # JDA setup + message event listener
│   ├── framework/      # in-tree replacement for the archived jda-utilities
│   ├── manager/        # embed + SQL helpers
│   ├── structures/     # generic data structures
│   └── util/           # cross-cutting utilities (e.g. Paths anchoring)
├── config/             # host-local config + dependency manifest
│   └── BotConstants.java  # gitignored — Discord token, owner ids
├── data/               # runtime state (gitignored)
└── libs/               # JAR dependencies
```

## Tech Stack

| Dependency | Version | Purpose |
|---|---|---|
| [JDA](https://github.com/discord-jda/JDA) | 6.4.1 | Discord API wrapper |
| [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) | 3.49.1.0 | SQLite database driver |
| [SLF4J Simple](https://www.slf4j.org/) | 2.0.17 | Logging backend |
| Java | 17+ | Runtime |

The entry point is [`Connection.java`](Connection.java) at the repo root (default package). All JARs live in [`libs/`](libs/) and are pinned in [`config/libs.txt`](config/libs.txt).

## Setup

### **1. Install dependencies**

Drop the three JARs listed in [`config/libs.txt`](config/libs.txt) into [`libs/`](libs/) at the repo root. Versions matter — older JDA releases use a different API.

### **2. BotConstants.java**

`config/BotConstants.java` is gitignored — it holds the secrets and per-host knobs. Create it locally with exactly these four fields:

```java
package config;

public class BotConstants {
    public static final String   discordToken   = "YOUR_BOT_TOKEN";
    public static final String   discordOwner   = "YOUR_DISCORD_USER_ID";
    public static final String[] discordCoOwner = {};
    public static final String   prefix         = "p!";   // "" for slash-only
}
```

In IntelliJ, mark [`config/`](config/) as a source root (*right-click → Mark Directory as → Sources Root*) so the `config` package is compiled and bundled into the JAR.

`discordToken` is your bot's secret. Copy it from your application's *Bot → Token* tab. Never commit it.

`discordOwner` / `discordCoOwner` are Discord user IDs that bypass owner-only command checks.

`prefix` turns prefix commands on and off, and is the **single switch for the privileged intent** — see [step 3](#3-discord-developer-portal). Set it to `p!` and the bot answers both `p!hug` and `/hug`; set it to `""` and the bot is slash-only and requests no privileged intent. There is deliberately no second flag to keep in sync.

Everything else the bot ships with — invite URL, action-command GIF URLs, image URLs — lives in [`src/util/Resources.java`](src/util/Resources.java) instead. That file *is* tracked in git; populate it once and the values stay in sync with the repo.

### **3. Discord Developer Portal**

What you need here depends on the `prefix` you just set.

**Slash-only (`prefix = ""`)** — nothing to do. `GUILD_EXPRESSIONS`, `GUILD_MESSAGES` and `GUILD_MESSAGE_REACTIONS` are all unprivileged.

**Prefix commands (`prefix = "p!"`)** — the bot additionally requests `MESSAGE_CONTENT`, which is privileged. Enable it under **Bot → Privileged Gateway Intents → Message Content Intent**. For an app in fewer than 100 servers this is a self-serve toggle; past that threshold the app must be verified and approved for the intent.

> **Requesting the intent without enabling it here fails the login.** Discord closes the gateway with code 4014 and the bot never connects — it does not silently fall back. If `prefix` is non-blank and the bot won't start, this is the first thing to check; the startup log says so explicitly.

Either way, make sure the invite URL in [`src/util/Resources.java`](src/util/Resources.java) grants the `applications.commands` scope alongside `bot` — without it Discord won't surface the bot's slash commands in that guild.

### **4. Build the JAR**

The whole source tree is packaged into a single executable JAR — the dependency JARs in [`libs/`](libs/) are unpacked and included alongside the project's own `.class` files. The JAR's manifest points at [`Connection`](Connection.java) as the entry point (see [`META-INF/MANIFEST.MF`](META-INF/MANIFEST.MF)).

Produce `PekkaBot.jar` at the repo root. In IntelliJ: *File → Project Structure → Artifacts → + → JAR → From modules with dependencies*, then *Build → Build Artifacts → PekkaBot:jar*. Make sure the project root and [`config/`](config/) are both marked as source roots so `Connection.java` and `BotConstants.java` get compiled in.

### **5. Run**

```bash
java -jar PekkaBot.jar
```

[`data/`](data/) is resolved relative to the JAR's own location (see [`src/util/Paths.java`](src/util/Paths.java)), not the process working directory, so the bot can be launched from anywhere as long as the JAR sits next to it. SQLite writes go to [`data/PekkaBot.db`](data/), which is created on first run if missing.

## Architecture

| Module | Description |
|---|---|
| [Connection.java](Connection.java) | Bot entry point at the repo root. Builds `DiscordManager`. |
| [config/BotConstants.java](config/BotConstants.java) | Discord token, owner ids, and the command prefix. Gitignored. |
| [src/util/Resources.java](src/util/Resources.java) | Tracked-in-git string content the bot ships with: invite URL, action GIFs, image URLs. |
| [src/discord/Discord.java](src/discord/Discord.java) | Builds the JDA client, auto-loads every command via `CommandLoader`, and wires up the message listener. |
| [src/discord/DiscordManager.java](src/discord/DiscordManager.java) | Static accessor around the `Discord` instance so commands can look up user names. |
| [src/discord/GuildMessageRespond.java](src/discord/GuildMessageRespond.java) | Non-command message listener: awards Chronos Stones per message, and parses white gate / ad reports out of messages that @-mention the bot. |
| [src/framework/command/](src/framework/command/) | Drop-in replacement for the archived jda-utilities library. Provides `Command`, `CommandEvent`, `CommandClient`, `CommandClientBuilder`; registers slash commands and dispatches both slash and prefix invocations. |
| [src/framework/command/CommandSource.java](src/framework/command/CommandSource.java) | The seam that lets one `execute` body serve both invocation styles — implemented by `MessageSource` (prefix) and `InteractionSource` (slash). |
| [src/manager/EmbedManager.java](src/manager/EmbedManager.java) | Helpers for building Discord embeds, including the dynamically-generated help embed. |
| [src/manager/SQLManager.java](src/manager/SQLManager.java) | Application-level wrappers around `utility/SQL.java`. |
| [src/manager/utility/SQL.java](src/manager/utility/SQL.java) | Raw SQLite access — connection, schema, and per-table queries. |
| [src/util/CommandLoader.java](src/util/CommandLoader.java) | Reflection-based command discovery — walks the `commands` package and instantiates every concrete `Command` subclass. |
| [src/util/Paths.java](src/util/Paths.java) | Resolves `data/` paths against the JAR's own location, not the process CWD. |
| [src/structures/](src/structures/) | `Pair` helper tuple. |
| [src/commands/](src/commands/) | All commands grouped by feature: action, ad, currency, gary, other, timer, unseen, whitegate. Auto-loaded on startup. |
| [data/](data/) | Runtime state. Holds `PekkaBot.db` (gitignored). |

## Adding a new command

Drop a class under the appropriate `src/commands/<feature>/` package that extends [`Command`](src/framework/command/Command.java) and has a no-arg constructor — [`CommandLoader`](src/util/CommandLoader.java) finds it on startup. No registration line anywhere. Minimum skeleton:

```java
package commands.other;

import framework.command.Command;
import framework.command.CommandEvent;

public class Hello extends Command {
    public Hello() {
        this.name = "Hello";
        this.help = "Say hi.";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Hi!");
    }
}
```

`help` doubles as the slash command's description in Discord's picker, so it can't be blank — a command that leaves it empty is registered under its own name instead.

The command is reachable as both `/hello` and `p!hello` with no extra work; `event` hides which one was used.

To take arguments, declare them as `options` and read them back by name:

```java
this.options = new OptionData[]{
        new OptionData(OptionType.USER,   "user", "Who to greet",     false),
        new OptionData(OptionType.STRING, "note", "Something to add", false)
};
// ...
User user = event.getUser("user");     // null when omitted
String note = event.getString("note"); // "" when omitted
```

Options are named only on the slash path. A prefix invocation fills the **first** `STRING` option from all the text after the command name, and the **first** `USER` option from the first mention. The two overlap: `p!hello @bob hi there` yields `user=@bob` **and** `note="<@1234> hi there"`, because the text is passed through verbatim, mention markup included. Declare at most one option of each type, and don't rely on a `STRING` option being clean when a `USER` option is also declared.

Rebuild and restart. The help embed picks up the new command from its `name` and `help` fields, bucketing it into the section that matches its package (`commands.other.Hello` → **Other**). To add a brand-new category with its own help-embed heading, add one line to [`EmbedManager.HELP_CATEGORY_DISPLAY`](src/manager/EmbedManager.java); otherwise commands in unknown packages fall into the trailing **Other** bucket.

Files under `src/commands/<feature>/utility/` are support modules — they don't extend `Command`, so `CommandLoader` ignores them.

## Commands

Every command below works two ways: as a slash command (always), and as `<prefix>name` when a `prefix` is configured — `/hug` and `p!hug` reach the same handler. Type `/` in any channel to browse them.

Discord has no alias concept, so each alias is registered as its own slash command sharing the same handler (46 registrations in total, against Discord's cap of 100). Names are lowercased on registration; the casing here is display only.

Arguments differ slightly between the two. A slash invocation passes them by name (`/gary name:shion`); a prefix invocation passes the text after the command name, and mentions a user by @-ing them (`p!gary shion`, `p!hug @bob`). Each command takes at most one of each kind, so the two always line up.

### **White Gate**

| Command | Aliases | Description |
|---|---|---|
| `/whitegate` | `/wg`, `/wgmy`, `/mywg` | Display your white gate data. |
| `/whitegaterandom` | `/randomwg`, `/wgrandom` | Return a random white gate. |
| `/wgtotal` | `/totalwg`, `/wgt` | Display total white gate data across all users. |

White gate *results* aren't logged with a command — @-mention the bot with the run instead:

```
@PekkaBot drawer lake left boat well win
```

The bot reacts and echoes back what it recorded. This still works without the Message Content Intent because Discord delivers message content when the app is mentioned (see [Limitations](#limitations)).

### **Ads**

| Command | Aliases | Description |
|---|---|---|
| `/ad` | `/admy`, `/myad`, `/myads`, `/adsmy` | Display your ad data. |
| `/adtotal` | `/totalad`, `/adt`, `/adstotal`, `/totalads` | Display total ad data across all users. |

Ad results are logged by @-mentioning the bot with the shorthand — `5`/`1`/`2` for the Chronos Stone tiers, `g`/`r` for key drops:

```
@PekkaBot 5 5 1 g 2 r
```

### **Currency**

| Command | Aliases | Description |
|---|---|---|
| `/chronosdisplay` | `/chronos`, `/mychronos` | Display your Chronos Stone balance. |

Chronos Stones accrue automatically — one per message sent in a guild.

### **Timer**

| Command | Aliases | Description |
|---|---|---|
| `/time` | `/timereset`, `/resettime` | Display the next reset time. |
| `/timecat` | `/cattime` | Display the times that cats spawn. |

Times use JST (Asia/Tokyo) — Another Eden's server timezone.

### **Actions**

| Command | Options | Description |
|---|---|---|
| `/hug` | `user` | Hug a user. |
| `/pat` | `user` | Pat a user. |
| `/slap` | `user` | Slap a user. |
| `/slam` | `user` | Slam a user. |
| `/scold` | `user` | Scold a user. |

`user` is optional — omit it and the bot targets you instead. Prefix form: `p!hug @bob`. Only the first mentioned user is targeted.

### **Other**

| Command | Aliases | Options | Description |
|---|---|---|---|
| `/pekka` | `/help` | — | List every command. |
| `/dango` | — | — | Post a dango. |
| `/tiramisu` | `/tira` | — | Post a tiramisu. |
| `/gimmie` | — | — | Gimmie. |
| `/gary` | — | `name` | Post a Gary (Gariyu AS). |
| `/unseen` | — | `name` | Post an Unseen. |
| `/shion` | — | — | Shion counter. |
| `/addme` | — | — | Post the bot's add-me OAuth2 URL. |

`name` is optional on `/gary` and `/unseen` — omit it for a random one, or pass a character name (`/gary name:shion`, or `p!gary shion`). An unrecognised name returns the `???` placeholder image.

### **Admin (hidden)**

Owner-only, and registered with default permissions disabled so they don't clutter the picker for regular members.

| Command | Aliases | Description |
|---|---|---|
| `/exit` | `/shutdown` | Shut down the bot. |
| `/admin` | — | Report whether the bot has MESSAGE_HISTORY in the current channel. |

## Limitations

- **Slash commands register globally**, which Discord can take up to an hour to propagate after a name, description or option changes. Existing commands keep working in the meantime. A guild the bot joined *without* the `applications.commands` scope won't show them at all — re-invite with the URL from `/addme`.
- **Prefix commands depend on a privileged intent.** They need `MESSAGE_CONTENT`, which is self-serve only below 100 servers; past that the app must be verified and approved, and Discord commonly declines "so I can have prefix commands" as a rationale. The slash path has no such dependency, which is why it stays wired up even when a prefix is set — if the intent is ever lost, blanking `prefix` leaves the bot fully working.
- **Stat logging always works via @-mention**, in both configurations. Discord delivers message content when the app is mentioned regardless of intent, which is the shape the white gate / ad reporting already used.
- **One argument of each kind per command.** A prefix invocation carries one blob of text and a mention list, so it can fill at most one `STRING` option and one `USER` option. A command needing two of the same kind has to parse the text itself, or be slash-only.
- **Aliases cost registrations.** Every alias is a separate slash command; 46 of Discord's 100-command budget are in use.
- The fishing and gacha sub-features have been removed from earlier versions; no schema migration was needed because their tables were never live in this branch.

[`DESIGN.md`](DESIGN.md) §11 covers the SQLite contract: the tmp + rename atomic-write pattern common to JSON state files deliberately does **not** apply to [`data/PekkaBot.db`](data/) — SQLite's WAL already provides stronger crash semantics than tmp + rename, and overwriting a SQLite file via rename would corrupt the journal. See the class Javadoc in [`src/manager/utility/SQL.java`](src/manager/utility/SQL.java) for the reasoning.

## Authors

- **@Pekkapost** — Bot Creator
