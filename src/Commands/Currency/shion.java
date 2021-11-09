package Commands.Currency;

import Constants.BotConstants;
import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

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
