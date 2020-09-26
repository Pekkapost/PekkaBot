package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishManager;
import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fishDisplay extends Command {
    public fishDisplay() {
        this.name = "FishDisplay";
        this.aliases = new String[]{"MyFish","FDisplay","FD","FCoin","FishCoin","FBal"};
        this.help = "Displays your <:PekkaCoin:719798142983340074> data";
    }
    @Override
    protected void execute(CommandEvent event) {
        String id = event.getAuthor().getId();
        fishManager.updatePoints(id);
        int region = SQLManager.getRegion(id);
        EmbedManager.pekkaCoin(event.getTextChannel(),event.getAuthor(), SQLManager.getFishing(id,region),region);
    }
}
