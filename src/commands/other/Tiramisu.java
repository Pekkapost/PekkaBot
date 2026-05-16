package commands.other;

import util.Resources;
import manager.EmbedManager;
import commands.gary.utility.GaryManager;
import framework.command.Command;
import framework.command.CommandEvent;

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
            url = Resources.tiramisuCharacter;
        } else {
            url = Resources.tiramisuCake;
        }
        String title = "Is this the Tiramisu you're looking for?";
        EmbedManager.lookingfor(e.getTextChannel(), e.getAuthor(), url, title);
    }
}
