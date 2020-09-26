package Commands.Other;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class gimmie extends Command {
    public gimmie() {
        this.name = "Gimmie";
        this.help = "Gimmie";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("<a:Gimmie:468234791943143424>").queue();
    }
}
