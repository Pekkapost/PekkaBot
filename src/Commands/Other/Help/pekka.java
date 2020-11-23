package Commands.Other.Help;

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
        String message = event.getMessage().getContentRaw().toLowerCase();
        if(message.contains("wg") || message.contains("white gate"))
            event.getTextChannel().sendMessage(BotConstants.whiteGate).queue();
        if(message.contains("ad"))
            event.getTextChannel().sendMessage(BotConstants.ad).queue();
        else
            event.getTextChannel().sendMessage(BotConstants.help).queue();
    }
}
