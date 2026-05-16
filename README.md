# PekkaBot

<h4 align="center">A personal Discord bot for the Another Eden Discord server</h4>

## Layout

```
PekkaBot-Redesign/
├── src/                # library code; Connection.java is the entry point
│   ├── Connection.java
│   ├── Commands/       # bot commands, grouped by feature
│   ├── Discord/        # JDA setup + message event listener
│   ├── Framework/      # in-tree replacement for the archived jda-utilities
│   ├── Manager/        # embed + SQL helpers
│   ├── Structures/     # generic data structures
│   └── Constants/      # BotConstants.java (gitignored, host-local)
├── config/             # dependency manifest and reference docs
├── data/               # runtime state (gitignored except for GachaList.txt)
├── libs/               # JAR dependencies
└── assets/             # static binary content (currently empty)
```

## Tech Stack

| Dependency | Version | Purpose |
|---|---|---|
| [JDA](https://github.com/discord-jda/JDA) | 6.4.1 | Discord API wrapper |
| [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) | 3.49.1.0 | SQLite database driver |
| [jsoup](https://jsoup.org/) | 1.21.1 | HTML parsing |
| [SLF4J Simple](https://www.slf4j.org/) | 2.0.17 | Logging backend |
| Java | 17+ | Runtime |

The entry point is [`src/Connection.java`](src/Connection.java). All JARs live in [`libs/`](libs/) and are pinned in [`config/libs.txt`](config/libs.txt).

## Setup

### **1. Install dependencies**

Drop the four JARs listed in [`config/libs.txt`](config/libs.txt) into [`libs/`](libs/) at the repo root. Versions matter — older JDA releases use a different API.

### **2. BotConstants.java**

`src/Constants/BotConstants.java` is gitignored. Create it locally with at minimum:

```java
package Constants;

public class BotConstants {
    public static final String discordToken   = "YOUR_BOT_TOKEN";
    public static final String prefix         = "p!";

    public static final String helpText       = "";
    public static final String addMeUrl       = "";
}
```

`discordToken` is your bot's secret. Copy it from your application's *Bot → Token* tab. Never commit it.

`prefix` is the prefix every command uses (e.g. `p!gacha`).

`addMeUrl` is what `/addMe` posts. Generate it from your application's *OAuth2 → URL Generator* tab.

### **3. Discord Developer Portal**

In the [Discord Developer Portal](https://discord.com/developers/applications), under **Bot → Privileged Gateway Intents**, enable:
- **Message Content Intent** — required to read message text via `getContentRaw()`.

### **4. Build the JAR**

The whole source tree is packaged into a single executable JAR — the dependency JARs in [`libs/`](libs/) are unpacked and included alongside the project's own `.class` files. The JAR's manifest points at [`Connection`](src/Connection.java) as the entry point (see [`src/META-INF/MANIFEST.MF`](src/META-INF/MANIFEST.MF)).

Produce `PekkaBot.jar` at the repo root. In IntelliJ: *File → Project Structure → Artifacts → + → JAR → From modules with dependencies*, then *Build → Build Artifacts → PekkaBot:jar*.

### **5. Run**

From the repo root:

```bash
java -jar PekkaBot.jar
```

The bot writes SQLite state to [`data/PekkaBot.db`](data/) and reads gacha banner definitions from [`data/GachaList.txt`](data/GachaList.txt). Both are created on first run if missing. The working directory matters — `data/` is resolved relative to wherever you launch the JAR from, so launch it from the repo root.

## Architecture

| Module | Description |
|---|---|
| [src/Connection.java](src/Connection.java) | Bot entry point. Builds `DiscordManager` and triggers the initial gacha banner load. |
| [src/Constants/BotConstants.java](src/Constants/BotConstants.java) | Holds the Discord token, prefix, and other host-local constants. Gitignored. |
| [src/Discord/Discord.java](src/Discord/Discord.java) | Builds the JDA client, registers every command, and wires up the message listener. |
| [src/Discord/DiscordManager.java](src/Discord/DiscordManager.java) | Static accessor around the `Discord` instance so commands can look up user names. |
| [src/Discord/GuildMessageRespond.java](src/Discord/GuildMessageRespond.java) | JDA event listener that dispatches incoming messages into the command framework. |
| [src/Framework/Command/](src/Framework/Command/) | Drop-in replacement for the archived jda-utilities library. Provides `Command`, `CommandEvent`, `CommandClient`, `CommandClientBuilder`. |
| [src/Manager/EmbedManager.java](src/Manager/EmbedManager.java) | Helpers for building Discord embeds. |
| [src/Manager/SQLManager.java](src/Manager/SQLManager.java) | Application-level wrappers around `Utility/SQL.java`. |
| [src/Manager/Utility/SQL.java](src/Manager/Utility/SQL.java) | Raw SQLite access — connection, schema, and per-table queries. |
| [src/Structures/](src/Structures/) | `weightedRandomBag<T>` for gacha/bless random selection; `pair<A,B>` helper tuple. |
| [src/Commands/](src/Commands/) | All commands grouped by feature: Action, Ad, Currency, Gacha, Gary, Other, Timer, Unseen, WhiteGate. |
| [data/](data/) | Runtime state. Holds `PekkaBot.db` (gitignored) and `GachaList.txt` (banner definitions). |

## Adding a new command

Drop a class under the appropriate `src/Commands/<Feature>/` package that extends [`Command`](src/Framework/Command/Command.java), then add a `new YourCommand()` line to the `builder.addCommands(...)` block in [`src/Discord/Discord.java`](src/Discord/Discord.java). Minimum skeleton:

```java
package Commands.Other;

import Framework.Command.Command;
import Framework.Command.CommandEvent;

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

Recompile and restart. Files under `src/Commands/<Feature>/Utility/` are support modules, not commands themselves — they don't need to be registered.

## Commands

All commands use the configured `prefix` (e.g. `p!`).

### **White Gate**

| Command | Aliases | Description |
|---|---|---|
| `WhiteGate` | `WG`, `WGMy`, `MyWG` | Display your white gate data. |
| `WhiteGateRandom` | `RandomWG`, `WGRandom` | Return a random white gate. |
| `WGTotal` | `TotalWG`, `WGT` | Display total white gate data across all users. |

### **Gacha**

| Command | Aliases | Description |
|---|---|---|
| `Gacha` | `G` | Roll on the current banner. Uploads the result image. |
| `GachaBanner` | `BannerList`, `GBanner` | List all configured banners. |
| `Bless` | `B` | Bless the user. |

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
| `Gz` | Congratulate a user. |
| `Hug` | Hug a user. |
| `Pat` | Pat a user. |
| `Slap` | Slap a user. |
| `Slam` | Slam a user. |
| `Scold` | Scold a user. |
| `HighFive` | High-five a user. |
| `Wink` | Wink at a user. |

### **Other**

| Command | Aliases | Description |
|---|---|---|
| `Jokes` | — | List the joke commands. |
| `Whale` | — | Post a whale. |
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
| `BannerUpdate` | Reload gacha banner data from `data/GachaList.txt`. |
| `Update` | Admin update command. |
| `Clear` | Admin clear command. |
| `Exit` | Shut down the bot. |

## Limitations

- The bot relies on the *Message Content Intent* and the legacy prefix-command model. JDA's slash-command path isn't wired up.
- SQLite writes go straight to [`data/PekkaBot.db`](data/) without the tmp + rename atomic-write pattern described in [`DESIGN.md`](DESIGN.md) §15. SQLite's own WAL gives some crash safety, but the bot relies on it rather than enforcing atomic semantics at the application layer. The GachaList.txt write paths in [`src/Commands/Gacha/Utility/UrlParse.java`](src/Commands/Gacha/Utility/UrlParse.java) do use atomic-replace via `atomicReplace(...)`.
- The fishing sub-feature has been removed from earlier versions; no schema migration was needed because its tables were never live in this branch.

## Authors

- **@Pekkapost** — Bot Creator
