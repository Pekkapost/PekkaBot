package Commands.Action;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class hug extends Command {
    static File hugFile = new File("assets/Pictures/Hug.gif");
    static StringBuilder names = new StringBuilder();

    public hug() {
        this.name = "Hug";
        this.help = "Hugs";
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getMessage().getMentionedUsers().isEmpty()) {
            event.getTextChannel().sendMessage("*Hugs* <@" + event.getAuthor().getId() + ">").addFile(hugFile).complete();
        } else {
            names.setLength(0);
            for(int hugs = 0; hugs < event.getMessage().getMentionedUsers().size(); ++hugs) {
                names.append("<@").append(event.getMessage().getMentionedUsers().get(hugs).getId()).append("> ");
            }
            event.getTextChannel().sendMessage("*Hugs*  " + names).addFile(hugFile).complete();
        }
    }
}
