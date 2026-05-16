package commands.other;

import framework.command.Command;
import framework.command.CommandEvent;
import manager.EmbedManager;

public class Pekka extends Command {
    public Pekka() {
        this.name = "Pekka";
        this.aliases = new String[]{"Help"};
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.help(event.getTextChannel(), event.getClient().getCommands());
    }
}
