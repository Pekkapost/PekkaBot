package commands.other;

import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class Dango extends Command {
    public Dango() {
        this.name = "Dango";
        this.help = "Displays a dango";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.dango(event.getTextChannel());
    }
}
