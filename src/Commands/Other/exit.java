package Commands.Other;

import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class exit extends Command {
    public exit() {
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
