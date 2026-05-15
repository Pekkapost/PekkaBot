package Commands.Gary;

import Constants.BotConstants;
import Commands.Gary.Utility.GaryManager;
import Manager.EmbedManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class Gary extends Command {
    public Gary() {
        this.name = "Gary";
        this.help = "Displays a Gary";
        GaryManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentRaw().toLowerCase();
        boolean name = false;
        if(!message.equals(BotConstants.prefix + "gary")) {
            // Strip prefix + "gary " (5 chars) to isolate the character name argument
            message = message.substring(5 + BotConstants.prefix.length());
            name = true;
        }
        String link = GaryManager.callMe(message,name);
        String title = "Is this the Gariyu AS you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), link, title);
    }
}
