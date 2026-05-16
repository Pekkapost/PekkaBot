package commands.other;

import config.BotConstants;
import framework.command.Command;
import framework.command.CommandEvent;

public class Jokes extends Command {
    public Jokes(){
        this.name = "Jokes";
        this.help = "Displays all joke commands";
    }
    @Override
    protected void execute(CommandEvent event){
        event.getTextChannel().sendMessage(BotConstants.jokes).queue();
    }
}
