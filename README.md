# PekkaBot

A personal Discord bot for the *Another Eden Discord* server. Its primary purpose is tracking data from a game mechanic called **White Gates**, with additional fun commands for users.

## Tech Stack

| Dependency | Version | Purpose |
|---|---|---|
| [JDA](https://github.com/discord-jda/JDA) | 6.4.1 | Discord API wrapper |
| [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) | 3.49.1.0 | SQLite database driver |
| [jsoup](https://jsoup.org/) | 1.21.1 | HTML parsing |
| [SLF4J Simple](https://www.slf4j.org/) | 2.0.17 | Logging backend |

All JARs live in `libs/`. The entry point is `src/Connection.java`.

## Setup

### 1. BotConstants.java

`BotConstants.java` is gitignored. Create it at `src/Constants/BotConstants.java` with at minimum:

```java
package Constants;

public class BotConstants {
    public static final String discordToken  = "YOUR_BOT_TOKEN";
    public static final String discordOwner  = "YOUR_USER_ID";
    public static final String[] discordCoOwner = {};
    public static final String prefix        = "!";
    // ... other constants (help text, URLs, etc.)
}
```

### 2. Discord Developer Portal

In the [Discord Developer Portal](https://discord.com/developers/applications), under **Bot → Privileged Gateway Intents**, enable:
- **Message Content Intent** — required to read message text via `getContentRaw()`

### 3. Storage directory

The bot expects a `Storage/` directory at the working directory root:

```
Storage/
├── PekkaBot.db       # SQLite database (auto-created on first run)
└── GachaList.txt     # Gacha banner definitions
```

`GachaList.txt` format (one banner per block, blank line between banners):
```
Banner N: Banner Name
CharacterName|3star%|4star%|5star%
```
Rates use parentheses for 10th-pull guaranteed rates, e.g. `75(90)%`.

## Architecture

| Package | Description |
|---|---|
| `Commands` | All bot commands, grouped by feature (Action, Ad, Currency, Gacha, Gary, Other, Timer, Unseen, WhiteGate) |
| `Discord` | JDA setup (`Discord.java`), message event listener (`GuildMessageRespond.java`) |
| `Framework.Command` | Drop-in replacement for the archived jda-utilities library. Provides `Command`, `CommandEvent`, `CommandClient`, `CommandClientBuilder`. |
| `Manager` | `EmbedManager` (embed helpers), `SQLManager` (database queries), `Utility.SQL` (raw SQL + schema) |
| `Structures` | `weightedRandomBag<T>` for gacha/bless random selection |
| `Constants` | `BotConstants` (gitignored) |

## Commands

All commands use the configured `prefix` (e.g. `!`).

### White Gate

| Command | Aliases | Description |
|---|---|---|
| WhiteGate | WG, WGMy, MyWG | Displays your white gate data |
| WhiteGateRandom | RandomWG, WGRandom | Returns a random white gate |
| WGTotal | TotalWG, WGT | Displays total white gate data |

### Fishing

| Command | Aliases | Description |
|---|---|---|
| Fish | F | Go fishing |
| FishDisplay | MyFish, FD, FCoin, FishCoin, FBal | Displays your PekkaCoin balance |
| FishLocation | Fishl, Flocation, FL, FArea | Displays or sets your fishing location |
| FishUpgrade | Fishgrade, Fishu, FUpgrade, FUp, FShop, FishShop | Displays and purchases upgrades |
| FishBuy | Buy, FBuy, FB | Buys something |
| FishLeaderboard | FishLb, FLeaderboard, FLb | Displays top 10 PekkaCoin data |
| FishDex | FDex, PekkaDex, PDex | Fish Dex |
| FishPrestige | FP, FishP, FPrestige | Prestiges your fishing (at 10M PekkaCoins) |

Fishing uses a two-region prestige system: region 0 earns PekkaCoins; reaching 10M triggers prestige and switches to region 1 (PekkaPoints).

### Gacha

| Command | Aliases | Description |
|---|---|---|
| Gacha | G | Outputs a gacha roll (uploads image) |
| GachaBanner | BannerList, GBanner | Displays a list of banners |
| Bless | B | Blesses you |

### Ads

| Command | Aliases | Description |
|---|---|---|
| Ad | AdMy, MyAd, MyAds, AdsMy | Displays your ad data |
| ADTotal | TotalAd, ADT, ADsTotal, TotalAds | Displays total ad data |

### Currency

| Command | Aliases | Description |
|---|---|---|
| ChronosDisplay | Chronos, MyChronos | Displays your Chronos Stone balance |

### Timer

All times are in JST (Asia/Tokyo), which is Another Eden's server timezone.

| Command | Aliases | Description |
|---|---|---|
| Time | TimeReset, ResetTime | Displays reset time |
| TimeCat | CatTime | Displays the times that cats spawn |

### Actions

| Command | Description |
|---|---|
| Gz | Congratulates a user |
| Hug | Hugs a user |
| Pat | Pats a user |
| Slap | Slaps a user |
| Slam | Slams a user |
| Scold | Scolds a user |
| HighFive | High fives a user |
| Wink | Winks at a user |

### Other

| Command | Aliases | Description |
|---|---|---|
| Jokes | — | Displays all joke commands |
| Whale | — | Displays a whale |
| Dango | — | Displays a dango |
| Tiramisu | Tira | Displays a tiramisu |
| Gimmie | — | Gimmie |
| Gary | — | Displays a Gary (Gariyu AS) |
| Unseen | — | Displays an Unseen |
| AddMe | — | Sends a link to add the bot |
| Pekka | Help | Displays help |

### Admin (owner-only, hidden)

| Command | Description |
|---|---|
| BannerUpdate | Reloads gacha banner data from GachaList.txt |
| Update | Admin update command |
| Clear | Admin clear command |
| Exit | Shuts down the bot |
