package Commands.Action;

import Constants.BotConstants;
import Manager.EmbedManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class scold extends Command {
    public scold() {
        this.name = "Scold";
        this.help = "Scolds";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message;
        if(event.getMessage().getMentions().getUsers().isEmpty()) {
            message = "*Scolds* <@" + event.getAuthor().getId() + "> ";
        } else {
            StringBuilder names = new StringBuilder();
            for(int i = 0; i < event.getMessage().getMentions().getUsers().size(); ++i) {
                names.append("<@").append(event.getMessage().getMentions().getUsers().get(i).getId()).append("> ");
            }
            message = "*Scolds*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), BotConstants.scold, message);
    }
}
