package commands.currency;

import util.Resources;
import manager.EmbedManager;
import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class Shion extends Command {
    public Shion() {
        this.name = "shion";
        this.help = "Shions";
    }
    @Override
    protected void execute(CommandEvent event) {
        int count = SQLManager.updateShion();
        event.getTextChannel().sendMessage(count + " Shions have been shioned " + Resources.shion).queue();
    }
}
