package Commands.Action;

import Constants.BotConstants;
import Manager.EmbedManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class highFive extends Command {
    public highFive() {
        this.name = "HighFive";
        this.help = "HighFives";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message;
        if(event.getMessage().getMentions().getUsers().isEmpty()) {
            message = "*HighFives* <@" + event.getAuthor().getId() + "> ";
        } else {
            StringBuilder names = new StringBuilder();
            for(int i = 0; i < event.getMessage().getMentions().getUsers().size(); ++i) {
                names.append("<@").append(event.getMessage().getMentions().getUsers().get(i).getId()).append("> ");
            }
            message = "*HighFives*  " + names + " ";
        }
        EmbedManager.action(event.getTextChannel(), event.getAuthor(), BotConstants.highfive, message);
    }
}
