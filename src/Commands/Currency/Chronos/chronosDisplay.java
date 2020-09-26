package Commands.Currency.Chronos;

import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class chronosDisplay extends Command {
    public chronosDisplay() {
        this.name = "ChronosDisplay";
        this.aliases = new String[]{"Chronos","MyChronos"};
        this.help = "Displays your <:ChronosStone:719806042606665738> data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.chronos(event.getTextChannel(),event.getAuthor(), SQLManager.getPoints(event.getAuthor().getId()));
    }
}
