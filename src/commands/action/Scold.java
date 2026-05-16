package commands.action;

import util.Resources;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class Scold extends Command {
    public Scold() {
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
            for(User user : event.getMessage().getMentions().getUsers()) {
                names.append("<@").append(user.getId()).append("> ");
            }
            message = "*Scolds*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), Resources.scold, message);
    }
}
