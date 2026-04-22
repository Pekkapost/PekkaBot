package Commands.Other;

import Constants.BotConstants;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class addMe extends Command {
    public addMe() {
        this.name = "AddMe";
        this.help = "Sends a link to add the bot";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(BotConstants.addME).queue();
    }
}
