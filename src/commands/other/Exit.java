package commands.other;

import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class Exit extends Command {
    public Exit() {
        this.name = "Exit";
        this.aliases = new String[]{"Shutdown"};
        this.help = "Shuts the bot down";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        // complete(), not queue() — the shutdown below would otherwise race the
        // REST call and the caller would be left with a dead "thinking…" prompt.
        event.getHook().sendMessage("Shutting down.").complete();
        event.getJDA().shutdown();
        SQLManager.close();
        System.exit(0);
    }
}
