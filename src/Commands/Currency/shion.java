package Commands.Currency;

import Constants.BotConstants;
import Manager.EmbedManager;
import Manager.SQLManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class shion extends Command {
    static StringBuilder names = new StringBuilder();

    public shion() {
        this.name = "shion";
        this.help = "Shions";
    }
    @Override
    protected void execute(CommandEvent event) {
        int count = SQLManager.updateShion();
        event.getTextChannel().sendMessage(count + " Shions have been shioned " + BotConstants.shion).queue();
    }
}
