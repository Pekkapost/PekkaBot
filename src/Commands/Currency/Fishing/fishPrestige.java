package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fishPrestige extends Command {
    public fishPrestige() {
        this.name = "FishPrestige";
        this.aliases = new String[]{"FP","FishP","FPrestige"};
        this.help = "Prestiges your fishing";
    }
    @Override
    protected void execute(CommandEvent event) {
        String id = event.getAuthor().getId();
        int tempPoints = SQLManager.getFishing(id);
        int region = SQLManager.getRegion(id);
        if(!(region == 1)) {
            if(tempPoints >= 10000000) {
                fishManager.updatePoints(id);
                event.getTextChannel().sendMessage("Congradulations on your Prestige! Every 10 million" +
                        fishManager.getCurrency(0) + " will be converted to" + fishManager.getCurrency(1)).queue();
            } else {
                event.getTextChannel().sendMessage("You need `" + (10000000 - tempPoints) + "` more to prestige").queue();
            }
        } else {
            event.getTextChannel().sendMessage("There is no next prestige...*yet*").queue();
        }
    }
}
