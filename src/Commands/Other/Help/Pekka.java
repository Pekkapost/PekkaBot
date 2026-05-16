package Commands.Other.Help;

import Framework.Command.Command;
import Framework.Command.CommandEvent;
import Manager.EmbedManager;

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
