package Commands.Other;

import Constants.BotConstants;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class admin extends Command {
    public admin() {
        this.name = "Admin";
        this.help = "Admin";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(BotConstants.addME).queue();
    }
}
