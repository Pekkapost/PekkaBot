package Commands.Other;

import Constants.BotConstants;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

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
