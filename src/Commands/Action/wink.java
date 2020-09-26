package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class wink extends Command {
    static File winkFile = new File("assets/Pictures/Wink.gif");
    static StringBuilder names = new StringBuilder();

    public wink() {
        this.name = "Wink";
        this.help = "Winks";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*Winks* <@" + event.getAuthor().getId() + ">").addFile(winkFile).complete();
        } else {
            names.setLength(0);
            for(int winks = 0; winks < event.getMessage().getMentionedUsers().size(); ++winks) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(winks).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*Winks*  " + names).addFile(winkFile).complete();
        }
    }
}
