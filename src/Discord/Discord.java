package Discord;

import Commands.Action.*;
import Commands.Currency.Chronos.*;
import Commands.Currency.shion;
import Commands.Gacha.Utility.Admin.*;
import Commands.Gacha.*;
import Commands.Other.*;
import Commands.Other.Help.pekka;
import Commands.Timer.*;
import Commands.WhiteGate.*;
import Commands.Ad.*;
import Constants.BotConstants;
import Commands.Gary.*;
import Commands.Unseen.*;

import Framework.Command.CommandClient;
import Framework.Command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Discord {
    net.dv8tion.jda.api.JDA d;
    public Discord() {
        try {
            CommandClientBuilder builder = new CommandClientBuilder();
            builder.setOwnerId(BotConstants.discordOwner);
            builder.setCoOwnerIds(BotConstants.discordCoOwner);
            builder.setPrefix(BotConstants.prefix);
            builder.useHelpBuilder(false);
            builder.addCommands(
                    new jokes(),
                    new slap(),
                    new gz(),
                    new hug(),
                    new pat(),
                    new scold(),
                    new highFive(),
                    new wink(),
                    new slam(),
                    new whale(),
                    new gimmie(),
                    new dango(),
                    new tiramisu(),
                    new timeReset(),
                    new timeCat(),
                    new gary(),
                    new unseen(),
                    new wgMy(),
                    new wgRandom(),
                    new wgTotal(),
                    new adMy(),
                    new adTotal(),
                    new bless(),
                    new gacha(),
                    new gachaBanner(),
                    new chronosDisplay(),
                    new shion(),
                    new addMe(),
                    new pekka(),
                    new admin(),
                    new bannerUpdate(),
                    new update(),
                    new clear(),
                    new exit()
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
            e.printStackTrace();
        }
    }

    public String getUserName(String id) {
        try {
            User temp = d.retrieveUserById(id).complete();
            return temp.getName();
        } catch (NullPointerException e) {
            System.out.println("User cannot be found " + id);
            return id;
        } catch (Exception e) {
            System.out.println("    Error: Get User Name " + e);
            return id;
        }
    }
}
