package discord;

/**
 * Static accessor around the single {@link Discord} instance.
 *
 * Commands that need to resolve a Discord user id to a display name (e.g.
 * total-stats outputs that list contributors) call {@link #getUserName}
 * without having to plumb a JDA reference through every constructor.
 * Returns the id unchanged if the {@link Discord} instance isn't built
 * yet, so static initialisation order can't crash a command.
 */
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
