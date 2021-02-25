import Commands.Currency.Fishing.Utility.fishManager;
import Commands.Currency.Fishing.Utility.fishUpgradeManager;
import Commands.Gacha.Utility.gachaManager;
import Commands.Timer.Utility.TimerManager;
import Discord.DiscordManager;

public class Connection {
    public static void main(String[] args) {
        new DiscordManager();
        // Others
        //new TimerManager(DiscordManager.getDiscord());
        gachaManager.update();
        fishManager.initializeFish();
        fishUpgradeManager.initialize();
    }
}
