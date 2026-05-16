package commands.other;

import util.Resources;
import framework.command.Command;
import framework.command.CommandEvent;

public class AddMe extends Command {
    public AddMe() {
        this.name = "AddMe";
        this.help = "Sends a link to add the bot";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(Resources.addME).queue();
    }
}
