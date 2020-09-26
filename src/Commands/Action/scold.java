package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class scold extends Command {
    static File scoldFile = new File("assets/Pictures/Scold.gif");
    static StringBuilder names = new StringBuilder();

    public scold() {
        this.name = "Scold";
        this.help = "Scolds";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*Scolds* <@" + event.getAuthor().getId() + ">").addFile(scoldFile).complete();
        } else {
            names.setLength(0);
            for(int scolds = 0; scolds < event.getMessage().getMentionedUsers().size(); ++scolds) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(scolds).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*Scolds*  " + names).addFile(scoldFile).complete();
        }
    }
}
