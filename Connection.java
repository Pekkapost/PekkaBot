import Commands.Gacha.Utility.GachaManager;
import Discord.DiscordManager;

public class Connection {
    public static void main(String[] args) {
        new DiscordManager();
        GachaManager.update();
    }
}
