package Commands.Other;

import config.BotConstants;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class AddMe extends Command {
    public AddMe() {
        this.name = "AddMe";
        this.help = "Sends a link to add the bot";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(BotConstants.addME).queue();
    }
}
