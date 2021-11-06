package Gary;

import Constants.BotConstants;
import Gary.Utility.garyManager;
import Manager.EmbedManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class gary extends Command {
    public gary() {
        this.name = "Gary";
        this.help = "Displays a Gary";
        garyManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentRaw().toLowerCase();
        boolean name = false;
        if(!message.equals(BotConstants.prefix + "gary")) {
            message = message.substring(5 + BotConstants.prefix.length());
            name = true;
        }
        String link = garyManager.callMe(message,name);
        String title = "Is this the Gariyu AS you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), link, title);
    }
}
