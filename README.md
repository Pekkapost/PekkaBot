# PekkaBot

A personal Discord bot for the *Another Eden Discord* server. Its primary purpose is tracking data from a game mechanic called **White Gates**, with additional fun commands for users.

## Tech Stack

- Java with [JDA 6](https://github.com/discord-jda/JDA)
- SQLite (via sqlite-jdbc)
- jsoup (HTML parsing)
- SLF4J (logging)

## Setup

`BotConstants.java` is required at `src/Constants/BotConstants.java` but is not committed to the repository. It must be created locally with the bot token, owner ID, prefix, and other constants.

## Commands

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
| FishPrestige | FP, FishP, FPrestige | Prestiges your fishing |

### Gacha
| Command | Aliases | Description |
|---|---|---|
| Gacha | G | Outputs a gacha roll |
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
| Gary | — | Displays a Gary |
| Unseen | — | Displays an Unseen |
| AddMe | — | Sends a link to add the bot |
| Pekka | Help | Displays help |
