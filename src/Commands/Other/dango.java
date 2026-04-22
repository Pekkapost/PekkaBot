package Commands.Other;

import Manager.EmbedManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class dango extends Command {
    public dango() {
        this.name = "Dango";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.dango(event.getTextChannel());
    }
}
