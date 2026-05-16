package Commands.Other.Help;

import config.BotConstants;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class Pekka extends Command {
    public Pekka() {
        this.name = "Pekka";
        this.aliases = new String[]{"Help"};
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event){
        String message = event.getMessage().getContentRaw().toLowerCase();
        if(message.contains("wg") || message.contains("white gate"))
            event.getTextChannel().sendMessage(BotConstants.whiteGate).queue();
        else if(message.contains("ad"))
            event.getTextChannel().sendMessage(BotConstants.ad).queue();
        else
            event.getTextChannel().sendMessage(BotConstants.help).queue();
    }
}
