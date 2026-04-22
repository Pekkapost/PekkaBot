import Commands.Gacha.Utility.gachaManager;
import Discord.DiscordManager;

public class Connection {
    public static void main(String[] args) {
        new DiscordManager();
        gachaManager.update();
    }
}
