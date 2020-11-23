package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class slam extends Command {
    static File slamFile = new File("assets/Pictures/Slam.gif");
    static StringBuilder names = new StringBuilder();

    public slam() {
        this.name = "Slam";
        this.help = "Slams";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*Slams* <@" + event.getAuthor().getId() + ">").addFile(slamFile).complete();
        } else {
            names.setLength(0);
            for(int gzs = 0; gzs < event.getMessage().getMentionedUsers().size(); ++gzs) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(gzs).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*Slams*  " + names).addFile(slamFile).complete();
        }
    }
}
