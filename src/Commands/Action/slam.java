package Commands.Action;

import Constants.BotConstants;
import Manager.EmbedManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class slam extends Command {
    public slam() {
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
            for(int i = 0; i < event.getMessage().getMentions().getUsers().size(); ++i) {
                names.append("<@").append(event.getMessage().getMentions().getUsers().get(i).getId()).append("> ");
            }
            message = "*Slams*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), BotConstants.slam, message);
    }
}
