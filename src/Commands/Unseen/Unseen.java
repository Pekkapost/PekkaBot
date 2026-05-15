package Commands.Unseen;

import Constants.BotConstants;
import Manager.EmbedManager;
import Commands.Unseen.Utility.UnseenManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class Unseen extends Command {
    public Unseen() {
        this.name = "Unseen";
        this.help = "Displays a Unseen";
        UnseenManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentRaw().toLowerCase();
        boolean name = false;
        if(!message.equals(BotConstants.prefix + "unseen")) {
            // Strip prefix + "unseen " (7 chars) to isolate the character name argument
            message = message.substring(7 + BotConstants.prefix.length());
            name = true;
        }
        String link = UnseenManager.callMe(message,name);
        String title = "Is this the Unseen you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), link, title);
    }
}
