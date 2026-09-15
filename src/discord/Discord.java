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

import java.util.EnumSet;

/**
 * JDA client builder + global user-name lookup.
 *
 * Constructs the bot's gateway connection (with only the intents the bot
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

            // A blank prefix is the slash-command-only configuration, and the
            // switch for the privileged intent as well. Asking for
            // MESSAGE_CONTENT without enabling it in the Developer Portal makes
            // Discord refuse the connection outright (close code 4014), so the
            // request has to be conditional rather than always-on.
            //
            // Without it, message bodies arrive empty except in the cases
            // Discord exempts — one of which is "the bot was mentioned". That
            // exemption is what keeps GuildMessageRespond's white-gate and ad
            // reporting working; nothing else may assume readable content.
            EnumSet<GatewayIntent> intents = EnumSet.of(
                    GatewayIntent.GUILD_EXPRESSIONS,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MESSAGE_REACTIONS);
            boolean prefixCommands = !BotConstants.prefix.isBlank();
            if (prefixCommands) intents.add(GatewayIntent.MESSAGE_CONTENT);
            logger.info("Prefix commands {}", prefixCommands
                    ? "enabled with prefix '" + BotConstants.prefix + "'"
                    : "disabled (blank prefix); slash commands only");

            d = JDABuilder.create(BotConstants.discordToken, intents)
                    .setActivity(Activity.listening(prefixCommands
                            ? "Pekka Bot | " + BotConstants.prefix
                            : "Pekka Bot | /pekka"))
                    // The bot doesn't read presence, client-status, or voice state — disable
                    // the caches so JDA doesn't keep them populated per-guild.
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.VOICE_STATE)
                    .build();
            d.addEventListener(
                    new GuildMessageRespond(),
                    client);
        } catch (Exception e) {
            if (!BotConstants.prefix.isBlank()) {
                logger.error("Failed to build JDA client. Prefix commands are on, so the bot "
                        + "requested the privileged MESSAGE_CONTENT intent — if Discord refused the "
                        + "connection, enable Message Content Intent in the Developer Portal "
                        + "(Bot -> Privileged Gateway Intents), or blank out BotConstants.prefix "
                        + "to run slash-command-only.", e);
            } else {
                logger.error("Failed to build JDA client", e);
            }
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
