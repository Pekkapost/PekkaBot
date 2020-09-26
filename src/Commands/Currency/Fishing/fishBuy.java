package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishManager;
import Commands.Currency.Fishing.Utility.fishUpgradeManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fishBuy extends Command {
    public fishBuy() {
        this.name = "FishBuy";
        this.aliases = new String[]{"Buy","FBuy","FB"};
        this.help = "Buys something";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message = event.getMessage().getContentRaw();
        if(message.contains(" ")) {
            String id = event.getAuthor().getId();
            int region = SQLManager.getRegion(id);
            String currency = fishManager.getCurrency(region);
            // Location
            if (message.substring(message.indexOf(" ") + 1).equals("location")) {
                int loc = SQLManager.getUpgradeLocation(id) + 1;
                int fishRegion = fishUpgradeManager.getLocationRegion(loc);
                int point = fishUpgradeManager.getLocationPrice(loc, region);
                int userPoint = SQLManager.getFishing(id, region);
                if(fishUpgradeManager.getLocation(loc).equals("TBA")) {
                    event.getTextChannel().sendMessage("This feature is currently not implemented yet").queue();
                } else if(userPoint >= point && region >= fishRegion) {
                    SQLManager.updateLocation(id, fishUpgradeManager.getLocation(loc));
                    SQLManager.decreaseFishing(id, point, region);
                    SQLManager.updateUpgradeLocation(id);
                    event.getTextChannel().sendMessage("Thank you for your purchase!").queue();
                } else {
                    String fishCurrency = fishManager.getCurrency(fishRegion);
                    event.getTextChannel().sendMessage(
                            "You have `" + userPoint + "` " + currency + " but need `" +
                                    point + "` " + fishCurrency).queue();
                }
            } else if (message.substring(message.indexOf(" ") + 1).equals("storage")) {
                int loc = SQLManager.getUpgradeStorage(id);
                int fishRegion = fishUpgradeManager.getStorageRegion(loc);
                int point = fishUpgradeManager.getStoragePrice(loc, region);
                int userPoint = SQLManager.getFishing(id, region);
                if(fishUpgradeManager.getStorage(loc).equals("TBA")) {
                    event.getTextChannel().sendMessage("This feature is currently not implemented yet").queue();
                } else if(userPoint >= point && region >= fishRegion) {
                    SQLManager.decreaseFishing(id, point, region);
                    SQLManager.updateUpgradeStorage(id);
                    event.getTextChannel().sendMessage("Thank you for your purchase!").queue();
                } else {
                    String fishCurrency = fishManager.getCurrency(fishRegion);
                    event.getTextChannel().sendMessage(
                            "You have `" + userPoint + "` " + currency + " but need `" +
                                    point + "` " + fishCurrency).queue();
                }
            }
        }
    }
}
