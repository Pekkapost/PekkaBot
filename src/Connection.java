import Constants.BotConstants;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import javax.security.auth.login.LoginException;

public class Connection {
    public static void main(String[] args) {
        JDA discord = null;
        try {
            discord = new JDABuilder(AccountType.BOT).setToken(BotConstants.discordToken).buildBlocking();
        }catch (LoginException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (RateLimitedException e){
            e.printStackTrace();
        }


        //Schedule scheudle = new Schedule(discord);
        discord.getPresence().setGame(Game.of(Game.GameType.DEFAULT, "Pekka Bot | p!"));
        discord.addEventListener(new MessageRespond());
        discord.addEventListener(new GuildJoin());
        System.out.println(discord.getGuilds());
        System.out.println("Pekka Bot Another Eden v1.7");
    }
}
