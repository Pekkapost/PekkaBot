package Commands.Other;

import Manager.SQLManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class Exit extends Command {
    public Exit() {
        this.name = "Exit";
        this.aliases = new String[]{"Shutdown"};
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getJDA().shutdown();
        SQLManager.close();
        System.exit(0);
    }
}
