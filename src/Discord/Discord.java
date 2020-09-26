package Discord;

import Commands.Action.*;
import Commands.Currency.Chronos.chronosDisplay;
import Commands.Currency.Fishing.*;
import Commands.Gacha.Utility.Admin.bannerUpdate;
import Commands.Gacha.Utility.Admin.clear;
import Commands.Gacha.Utility.Admin.update;
import Commands.Gacha.gacha;
import Commands.Gacha.gachaBanner;
import Commands.Gary.gary;
import Commands.Other.*;
import Commands.Timer.timeCat;
import Commands.Timer.timeReset;
import Commands.WhiteGate.wgMy;
import Commands.WhiteGate.wgRandom;
import Commands.WhiteGate.wgTotal;
import Constants.BotConstants;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Discord {
    net.dv8tion.jda.api.JDA d;
    public Discord() {
        try {
            // Command Builder
            CommandClientBuilder builder = new CommandClientBuilder();
            builder.setActivity(Activity.listening("Pekka Bot | " + BotConstants.prefix));
            builder.setOwnerId(BotConstants.discordOwner);
            builder.setCoOwnerIds(BotConstants.discordCoOwner);
            builder.setPrefix(BotConstants.prefix);
            //builder.setHelpWord("pekka");
            builder.useHelpBuilder(false);
            //builder.setHelpConsumer();
            // Commands
            //TO DO:
            //1. make your classes follow naming conventions
            //2. you can use reflection to register all classes, commands in a package
            builder.addCommands(
                    //Jokes
                    new jokes(),
                    new slap(),
                    new gz(),
                    new hug(),
                    new scold(),
                    new highFive(),
                    new wink(),
                    new whale(),
                    new gimmie(),
                    new dango(),
                    new tiramisu(),
                    //Timers
                    new timeReset(),
                    new timeCat(),
                    //Gary
                    new gary(),
                    //White Gate
                    new wgMy(),
                    new wgRandom(),
                    new wgTotal(),
                    //Gacha
                    new gacha(),
                    new gachaBanner(),
                    //Currency
                    new chronosDisplay(),
                    new fish(),
                    new fishDisplay(),
                    new fishLeaderboard(),
                    new fishUpgrade(),
                    new fishLocation(),
                    new fishBuy(),
                    new fishGive(),
                    new fishPrestige(),
                    new fishDex(),
                    //Other
                    new addMe(),
                    new pekka(),
                    //Admin
                    new bannerUpdate(),
                    new update(),
                    new clear(),
                    new exit());
            CommandClient client = builder.build();
            // JDA Setup
            d = JDABuilder.create(
                    BotConstants.discordToken,
                    GatewayIntent.GUILD_EMOJIS,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MESSAGE_REACTIONS).disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.VOICE_STATE).build();
            // JDA Event Listeners
            d.addEventListener(
                    new GuildMessageRespond(),
                    client);
        }catch (LoginException e){
            e.printStackTrace();
        }
    }
    public net.dv8tion.jda.api.JDA getDiscord() {
        return d;
    }
    public String getUserName(String id) {
        try {
            User temp = d.retrieveUserById(id).complete();
            return temp.getName();
        } catch(NullPointerException e) {
            System.out.println("User cannot be found " + id);
            return id;
        } catch(Exception e) {
            System.out.println("    Error: Get User Name " + e);
            return id;
        }
    }
}
