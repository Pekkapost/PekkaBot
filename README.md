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
│   └── BotConstants.java  # gitignored — Discord token, prefix, ...
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
    public static final String   prefix         = "p!";
}
```

In IntelliJ, mark [`config/`](config/) as a source root (*right-click → Mark Directory as → Sources Root*) so the `config` package is compiled and bundled into the JAR.

`discordToken` is your bot's secret. Copy it from your application's *Bot → Token* tab. Never commit it.

`discordOwner` / `discordCoOwner` are Discord user IDs that bypass owner-only command checks.

`prefix` is the prefix every command uses (e.g. `p!hug`).

Everything else the bot ships with — invite URL, action-command GIF URLs, image URLs — lives in [`src/util/Resources.java`](src/util/Resources.java) instead. That file *is* tracked in git; populate it once and the values stay in sync with the repo.

### **3. Discord Developer Portal**

In the [Discord Developer Portal](https://discord.com/developers/applications), under **Bot → Privileged Gateway Intents**, enable:
- **Message Content Intent** — required to read message text via `getContentRaw()`.

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
| [config/BotConstants.java](config/BotConstants.java) | Discord token, owner ids, and prefix. Gitignored. |
| [src/util/Resources.java](src/util/Resources.java) | Tracked-in-git string content the bot ships with: invite URL, action GIFs, image URLs. |
| [src/discord/Discord.java](src/discord/Discord.java) | Builds the JDA client, auto-loads every command via `CommandLoader`, and wires up the message listener. |
| [src/discord/DiscordManager.java](src/discord/DiscordManager.java) | Static accessor around the `Discord` instance so commands can look up user names. |
| [src/discord/GuildMessageRespond.java](src/discord/GuildMessageRespond.java) | JDA event listener that dispatches incoming messages into the command framework. |
| [src/framework/command/](src/framework/command/) | Drop-in replacement for the archived jda-utilities library. Provides `Command`, `CommandEvent`, `CommandClient`, `CommandClientBuilder`. |
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

Rebuild and restart. The help embed picks up the new command from its `name` and `help` fields, bucketing it into the section that matches its package (`commands.other.Hello` → **Other**). To add a brand-new category with its own help-embed heading, add one line to [`EmbedManager.HELP_CATEGORY_DISPLAY`](src/manager/EmbedManager.java); otherwise commands in unknown packages fall into the trailing **Other** bucket.

Files under `src/commands/<feature>/utility/` are support modules — they don't extend `Command`, so `CommandLoader` ignores them.

## Commands

All commands use the configured `prefix` (e.g. `p!`).

### **White Gate**

| Command | Aliases | Description |
|---|---|---|
| `WhiteGate` | `WG`, `WGMy`, `MyWG` | Display your white gate data. |
| `WhiteGateRandom` | `RandomWG`, `WGRandom` | Return a random white gate. |
| `WGTotal` | `TotalWG`, `WGT` | Display total white gate data across all users. |

### **Ads**

| Command | Aliases | Description |
|---|---|---|
| `Ad` | `AdMy`, `MyAd`, `MyAds`, `AdsMy` | Display your ad data. |
| `ADTotal` | `TotalAd`, `ADT`, `ADsTotal`, `TotalAds` | Display total ad data across all users. |

### **Currency**

| Command | Aliases | Description |
|---|---|---|
| `ChronosDisplay` | `Chronos`, `MyChronos` | Display your Chronos Stone balance. |

### **Timer**

| Command | Aliases | Description |
|---|---|---|
| `Time` | `TimeReset`, `ResetTime` | Display the next reset time. |
| `TimeCat` | `CatTime` | Display the times that cats spawn. |

Times use JST (Asia/Tokyo) — Another Eden's server timezone.

### **Actions**

| Command | Description |
|---|---|
| `Hug` | Hug a user. |
| `Pat` | Pat a user. |
| `Slap` | Slap a user. |
| `Slam` | Slam a user. |
| `Scold` | Scold a user. |

### **Other**

| Command | Aliases | Description |
|---|---|---|
| `Dango` | — | Post a dango. |
| `Tiramisu` | `Tira` | Post a tiramisu. |
| `Gimmie` | — | Gimmie. |
| `Gary` | — | Post a Gary (Gariyu AS). |
| `Unseen` | — | Post an Unseen. |
| `Shion` | — | Shion counter. |
| `AddMe` | — | Post the bot's add-me OAuth2 URL. |

### **Admin (hidden)**

| Command | Description |
|---|---|
| `Exit` | Shut down the bot. |

## Limitations

- The bot relies on the *Message Content Intent* and the legacy prefix-command model. JDA's slash-command path isn't wired up.
- The fishing and gacha sub-features have been removed from earlier versions; no schema migration was needed because their tables were never live in this branch.

[`DESIGN.md`](DESIGN.md) §11 covers the SQLite contract: the tmp + rename atomic-write pattern common to JSON state files deliberately does **not** apply to [`data/PekkaBot.db`](data/) — SQLite's WAL already provides stronger crash semantics than tmp + rename, and overwriting a SQLite file via rename would corrupt the journal. See the class Javadoc in [`src/manager/utility/SQL.java`](src/manager/utility/SQL.java) for the reasoning.

## Authors

- **@Pekkapost** — Bot Creator
