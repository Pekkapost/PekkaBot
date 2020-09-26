package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class highFive extends Command {
    static File highFile = new File("assets/Pictures/HighFive.gif");
    static StringBuilder names = new StringBuilder();

    public highFive() {
        this.name = "HighFive";
        this.help = "HighFives";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*HighFives* <@" + event.getAuthor().getId() + ">").addFile(highFile).complete();
        } else {
            names.setLength(0);
            for(int highFives = 0; highFives < event.getMessage().getMentionedUsers().size(); ++highFives) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(highFives).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*HighFives*  " + names).addFile(highFile).complete();
        }
    }
}
