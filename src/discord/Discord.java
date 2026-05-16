package discord;

import config.BotConstants;
import framework.command.Command;
import framework.command.CommandClient;
import framework.command.CommandClientBuilder;
import util.CommandLoader;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDA client builder + global user-name lookup.
 *
 * Constructs the bot's gateway connection (with the four intents the bot
 * actually uses), wires the message listener, and registers every command
 * found by {@link util.CommandLoader}. Reads its host-local config from
 * {@link config.BotConstants}; everything else (action GIF URLs, etc.)
 * comes from {@link util.Resources}.
 *
 * The single retained instance is exposed to the rest of the codebase
 * through {@link DiscordManager} so commands can look up Discord user
 * names by id without keeping their own JDA reference.
 */
public class Discord {
    private static final Logger logger = LoggerFactory.getLogger(Discord.class);

    net.dv8tion.jda.api.JDA d;
    public Discord() {
        try {
            CommandClientBuilder builder = new CommandClientBuilder();
            builder.setOwnerId(BotConstants.discordOwner);
            builder.setCoOwnerIds(BotConstants.discordCoOwner);
            builder.setPrefix(BotConstants.prefix);
            builder.useHelpBuilder(false);
            builder.addCommands(CommandLoader.discover().toArray(new Command[0]));
            CommandClient client = builder.build();
            // MESSAGE_CONTENT is a privileged intent — must be enabled in the Discord Developer Portal
            d = JDABuilder.create(
                    BotConstants.discordToken,
                    GatewayIntent.GUILD_EXPRESSIONS,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MESSAGE_REACTIONS,
                    GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.listening("Pekka Bot | " + BotConstants.prefix))
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.VOICE_STATE)
                    .build();
            d.addEventListener(
                    new GuildMessageRespond(),
                    client);
        } catch (Exception e) {
            logger.error("Failed to build JDA client", e);
        }
    }

    public String getUserName(String id) {
        try {
            User temp = d.retrieveUserById(id).complete();
            return temp.getName();
        } catch (NullPointerException e) {
            logger.warn("getUserName: user {} not found", id);
            return id;
        } catch (Exception e) {
            logger.error("getUserName failed for {}", id, e);
            return id;
        }
    }
}
