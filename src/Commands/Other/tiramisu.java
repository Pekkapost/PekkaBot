package Commands.Other;

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
        File file;
        int rand = new Random().nextInt(100) + 1;
        if(rand == 1) {
            file = new File("assets/Gary/gariyu96.png");
        } else if(rand <= 33) {
            file = new File("assets/Pictures/TiramisuSprite.png");
        } else if(rand <= 66) {
            file = new File("assets/Pictures/TiramisuCharacter.png");
        } else {
            file = new File("assets/Pictures/TiramisuCake.png");
        }
        e.getChannel().sendMessage("Is this the Tiramisu you're looking for?").addFile(file).complete();
    }
}
