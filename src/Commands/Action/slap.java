package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class slap extends Command {
    static File slapFile = new File("assets/Pictures/Slap.gif");
    static StringBuilder names = new StringBuilder();

    public slap() {
        this.name = "Slap";
        this.help = "Slaps";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*Slaps* <@" + event.getAuthor().getId() + ">").addFile(slapFile).complete();
        } else {
            names.setLength(0);
            for(int slaps = 0; slaps < event.getMessage().getMentionedUsers().size(); ++slaps) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(slaps).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*Slaps*  " + names).addFile(slapFile).complete();
        }
    }
}
