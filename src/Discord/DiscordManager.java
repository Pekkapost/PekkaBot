package Discord;

public class DiscordManager {
    static Discord d;
    public DiscordManager() {
        d = new Discord();
    }
    /*public static net.dv8tion.jda.api.JDA getDiscord() {
        return d.getDiscord();
    }*/
    public static String getUserName(String id) {
        return d.getUserName(id);
    }
}
