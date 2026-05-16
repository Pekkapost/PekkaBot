package commands.other;

import config.BotConstants;
import framework.command.Command;
import framework.command.CommandEvent;

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
