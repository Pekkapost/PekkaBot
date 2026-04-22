package Commands.Other;

import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class admin extends Command {
    public admin() {
        this.name = "Admin";
        this.help = "Admin";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_HISTORY)) {
            System.out.println("Has Permission");
        } else {
            System.out.println("Doesnt Have Permission");
        }
    }
}
