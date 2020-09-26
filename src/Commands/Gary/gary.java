package Commands.Gary;

import Commands.Gary.Utility.garyManager;
import Constants.BotConstants;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;

public class gary extends Command {
    public gary() {
        this.name = "Gary";
        this.help = "Displays a Gary";
        garyManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentRaw().toLowerCase();
        boolean name = false;
        if(!message.equals(BotConstants.prefix + "gary")) {
            message = message.substring(5 + BotConstants.prefix.length());
            name = true;
        }
        File file = garyManager.callMe(message,name);
        e.getChannel().sendMessage("Is this the Gariyu AS you're looking for?").addFile(file).complete();
    }
}
