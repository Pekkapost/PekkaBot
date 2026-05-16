import discord.DiscordManager;

/**
 * Bot entry point.
 *
 * Lives at the repo root so the bot can be launched directly via
 * {@code java -jar PekkaBot.jar} with the manifest's {@code Main-Class}
 * pointing here. Default package — no {@code package} declaration —
 * which is why this file sits outside {@code src/}.
 *
 * Responsibility is intentionally tiny: hand off to {@link DiscordManager},
 * which builds the JDA client and auto-discovers every registered command.
 */
public class Connection {
    public static void main(String[] args) {
        new DiscordManager();
    }
}
