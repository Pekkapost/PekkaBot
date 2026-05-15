package Commands.Other;

import Constants.BotConstants;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class Whale extends Command {
    public Whale() {
        this.name = "Whale";
        this.help = "Displays a whale";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(BotConstants.whale).queue();
    }
}
