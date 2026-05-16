package commands.currency.chronos;

import manager.EmbedManager;
import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class ChronosDisplay extends Command {
    public ChronosDisplay() {
        this.name = "ChronosDisplay";
        this.aliases = new String[]{"Chronos","MyChronos"};
        this.help = "Displays your <:ChronosStone:719806042606665738> data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.chronos(event.getTextChannel(),event.getAuthor(), SQLManager.getPoints(event.getAuthor().getId()));
    }
}
