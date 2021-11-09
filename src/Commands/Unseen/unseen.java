package Commands.Unseen;

import Commands.Unseen.Utility.unseenManager;
import Constants.BotConstants;
import Manager.EmbedManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class unseen extends Command {
    public unseen() {
        this.name = "Unseen";
        this.help = "Displays a Unseen";
        unseenManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentRaw().toLowerCase();
        boolean name = false;
        if(!message.equals(BotConstants.prefix + "unseen")) {
            message = message.substring(7 + BotConstants.prefix.length());
            name = true;
        }
        String link = unseenManager.callMe(message,name);
        String title = "Is this the Unseen you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), link, title);
    }
}
