package Commands.Other;

import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class Gimmie extends Command {
    public Gimmie() {
        this.name = "Gimmie";
        this.help = "Gimmie";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("<a:Gimmie:468234791943143424>").queue();
    }
}
