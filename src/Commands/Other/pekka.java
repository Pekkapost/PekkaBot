package Commands.Other;

import Constants.BotConstants;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class pekka extends Command {
    public pekka() {
        this.name = "Pekka";
        this.aliases = new String[]{"Help"};
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event){
        event.getTextChannel().sendMessage(BotConstants.help).queue();
    }
}
