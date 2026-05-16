package commands.action;

import config.BotConstants;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class Hug extends Command {
    public Hug() {
        this.name = "Hug";
        this.help = "Hugs";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message;
        if(event.getMessage().getMentions().getUsers().isEmpty()) {
            message = "*Hugs* <@" + event.getAuthor().getId() + "> ";
        } else {
            StringBuilder names = new StringBuilder();
            for(User user : event.getMessage().getMentions().getUsers()) {
                names.append("<@").append(user.getId()).append("> ");
            }
            message = "*Hugs*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), BotConstants.hug, message);
    }
}
