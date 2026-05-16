package Commands.Other;

import config.BotConstants;
import Manager.EmbedManager;
import Commands.Gary.Utility.GaryManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

import java.util.Random;

public class Tiramisu extends Command {
    public Tiramisu() {
        this.name = "Tiramisu";
        this.help = "Displays a Tiramisu";
        this.aliases = new String[]{"Tira"};
    }
    @Override
    protected void execute(CommandEvent e) {
        String url;
        int rand = new Random().nextInt(100) + 1;
        if(rand <= 33) {
            url = GaryManager.findMe("tiramisu");
        } else if(rand <= 66) {
            url = BotConstants.tiramisuCharacter;
        } else {
            url = BotConstants.tiramisuCake;
        }
        String title = "Is this the Tiramisu you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), url, title);
    }
}
