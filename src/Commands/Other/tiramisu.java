package Commands.Other;

import Commands.Gary.Utility.garyManager;
import Constants.BotConstants;
import Manager.EmbedManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;
import java.util.Random;

public class tiramisu extends Command {
    public tiramisu() {
        this.name = "Tiramisu";
        this.help = "Displays a Tiramisu";
        this.aliases = new String[]{"Tira"};
    }
    @Override
    protected void execute(CommandEvent e) {
        String url;
        int rand = new Random().nextInt(100) + 1;
        if(rand <= 33) {
            url = garyManager.findMe("tiramisu");
        } else if(rand <= 66) {
            url = BotConstants.tiramisuCharacter;
        } else {
            url = BotConstants.tiramisuCake;
        }
        String title = "Is this the Tiramisu you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), url, title);
    }
}
