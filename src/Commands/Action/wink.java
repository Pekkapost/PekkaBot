package Commands.Action;

import Constants.BotConstants;
import Manager.EmbedManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class wink extends Command {
    public wink() {
        this.name = "Wink";
        this.help = "Winks";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message;
        if(event.getMessage().getMentions().getUsers().isEmpty()) {
            message = "*Winks* <@" + event.getAuthor().getId() + "> ";
        } else {
            StringBuilder names = new StringBuilder();
            for(User user : event.getMessage().getMentions().getUsers()) {
                names.append("<@").append(user.getId()).append("> ");
            }
            message = "*Winks*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), BotConstants.wink, message);
    }
}
