package Commands.Other;

import Constants.BotConstants;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class whale extends Command {
    public whale() {
        this.name = "Whale";
        this.help = "Displays a whale";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(BotConstants.whale).queue();
    }
}
