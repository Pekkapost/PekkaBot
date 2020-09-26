package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fish extends Command {
    public fish() {
        this.name = "Fish";
        this.aliases = new String[]{"F"};
        this.help = "Fishes";
    }
    @Override
    protected void execute(CommandEvent event) {
        String id = event.getAuthor().getId();
        fishManager.callMe(event.getChannel(),event.getAuthor(),SQLManager.getLocation(id), SQLManager.getFishing(id));
}
}
