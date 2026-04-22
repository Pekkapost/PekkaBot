package Discord;

public class DiscordManager {
    static Discord d;
    public DiscordManager() {
        d = new Discord();
    }
    public static String getUserName(String id) {
        return d.getUserName(id);
    }
}
