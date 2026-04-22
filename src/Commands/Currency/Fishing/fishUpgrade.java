package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishManager;
import Commands.Currency.Fishing.Utility.fishUpgradeManager;
import Manager.EmbedManager;
import Manager.SQLManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class fishUpgrade extends Command {
    public fishUpgrade() {
        this.name = "FishUpgrade";
        this.aliases = new String[]{"Fishgrade","Fishu","FUpgrade","FUp","FUpgrades","FShop","FishUp","FishShop"};
        this.help = "Displays and purchases fish upgrades";
    }
    @Override
    protected void execute(CommandEvent event) {
        String id = event.getAuthor().getId();
        int region = SQLManager.getRegion(id);
        // Location
        int upgradeLocation = SQLManager.getUpgradeLocation(id) + 1;
        int fishRegionLocation = fishUpgradeManager.getLocationDisplayRegion(upgradeLocation, region);
        if(region > fishRegionLocation) {
            fishRegionLocation = region;
        }
        String currencyLocation = fishManager.getCurrency(fishRegionLocation);
        // Storage
        int upgradeStorage = SQLManager.getUpgradeStorage(id);
        int fishRegionStorage = fishUpgradeManager.getStorageRegion(upgradeStorage);
        if(region > fishRegionStorage) {
            fishRegionStorage = region;
        }
        String currencyStorage = fishManager.getCurrency(fishRegionStorage);

        EmbedManager.fishgrade(event.getChannel(),event.getAuthor(),
                fishUpgradeManager.getDisplayLocation(upgradeLocation, region)
                        + " " + fishUpgradeManager.getLocationDisplayPrice(upgradeLocation, fishRegionLocation) + currencyLocation,
                fishUpgradeManager.getRod(0)
                        + " " + fishUpgradeManager.getRodPrice(0) + " <:PekkaCoin:719798142983340074>\n",
                fishUpgradeManager.getBoat(0)
                        + " " + fishUpgradeManager.getBoatPrice(0) + " <:PekkaCoin:719798142983340074>\n",
                fishUpgradeManager.getStorage(upgradeStorage)
                        + " " + fishUpgradeManager.getStoragePrice(upgradeStorage, fishRegionStorage) + currencyStorage,
                fishUpgradeManager.getBait(0)
                        + " " + fishUpgradeManager.getBaitPrice(0) + " <:PekkaCoin:719798142983340074>\n");
    }
}
