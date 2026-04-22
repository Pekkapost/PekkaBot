package Commands.Other;

import Constants.BotConstants;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class jokes extends Command {
    public jokes(){
        this.name = "Jokes";
        this.help = "Displays all joke commands";
    }
    @Override
    protected void execute(CommandEvent event){
        event.getTextChannel().sendMessage(BotConstants.jokes).queue();
    }
}
