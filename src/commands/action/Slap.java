package commands.action;

import util.Resources;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class Slap extends Command {
    public Slap() {
        this.name = "Slap";
        this.help = "Slaps";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message;
        if(event.getMessage().getMentions().getUsers().isEmpty()) {
            message = "*Slaps* <@" + event.getAuthor().getId() + "> ";
        } else {
            StringBuilder names = new StringBuilder();
            for(User user : event.getMessage().getMentions().getUsers()) {
                names.append("<@").append(user.getId()).append("> ");
            }
            message = "*Slaps*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), Resources.slap, message);
    }
}
