package Discord;

import Commands.Action.*;
import Commands.Currency.Chronos.*;
import Commands.Currency.Shion;
import Commands.Other.*;
import Commands.Other.Help.Pekka;
import Commands.Timer.*;
import Commands.WhiteGate.*;
import Commands.Ad.*;
import config.BotConstants;
import Commands.Gary.*;
import Commands.Unseen.*;

import Framework.Command.CommandClient;
import Framework.Command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            builder.addCommands(
                    new Jokes(),
                    new Slap(),
                    new Gz(),
                    new Hug(),
                    new Pat(),
                    new Scold(),
                    new HighFive(),
                    new Wink(),
                    new Slam(),
                    new Whale(),
                    new Gimmie(),
                    new Dango(),
                    new Tiramisu(),
                    new TimeReset(),
                    new TimeCat(),
                    new Gary(),
                    new Unseen(),
                    new WgMy(),
                    new WgRandom(),
                    new WgTotal(),
                    new AdMy(),
                    new AdTotal(),
                    new ChronosDisplay(),
                    new Shion(),
                    new AddMe(),
                    new Pekka(),
                    new Admin(),
                    new Exit()
                    );
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
