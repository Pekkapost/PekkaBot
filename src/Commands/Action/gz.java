package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class gz extends Command {
    static File gzFile = new File("assets/Pictures/Gz.gif");
    static StringBuilder names = new StringBuilder();

    public gz() {
        this.name = "Gz";
        this.help = "Gzs";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*Gzs* <@" + event.getAuthor().getId() + ">").addFile(gzFile).complete();
        } else {
            names.setLength(0);
            for(int gzs = 0; gzs < event.getMessage().getMentionedUsers().size(); ++gzs) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(gzs).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*Gzs*  " + names).addFile(gzFile).complete();
        }
    }
}
