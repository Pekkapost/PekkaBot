package Commands.Other;

import Manager.EmbedManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class dango extends Command {
    public dango() {
        this.name = "Dango";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.dango(event.getTextChannel());
    }
}
