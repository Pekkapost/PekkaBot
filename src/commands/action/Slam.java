package commands.action;

import config.BotConstants;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class Slam extends Command {
    public Slam() {
        this.name = "Slam";
        this.help = "Slams";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message;
        if(event.getMessage().getMentions().getUsers().isEmpty()) {
            message = "*Slams* <@" + event.getAuthor().getId() + "> ";
        } else {
            StringBuilder names = new StringBuilder();
            for(User user : event.getMessage().getMentions().getUsers()) {
                names.append("<@").append(user.getId()).append("> ");
            }
            message = "*Slams*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), BotConstants.slam, message);
    }
}
