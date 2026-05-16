package discord;

public class DiscordManager {
    static Discord d;
    public DiscordManager() {
        d = new Discord();
    }
    public static String getUserName(String id) {
        if (d == null) return id;
        return d.getUserName(id);
    }
}
