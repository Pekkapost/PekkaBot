package Commands.Gacha;

import Commands.Gacha.Utility.gachaManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class bless extends Command {
    public bless() {
        this.name = "Bless";
        this.aliases = new String[]{"B"};
        this.help = "Blesses you";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(random()).queue();
    }
    public String random(){
        Random r = new Random();
        String output = "";
        int rand = r.nextInt(10) + 1;
        switch (rand) {
            case 1:
                output = "You have been blessed with great luck";
                break;
            case 2:
            case 3:
                output = "You have been blessed with good luck";
                break;
            case 4:
            case 5:
            case 6:
                output = "You have been blessed with average luck";
                break;
            case 7:
            case 8:
            case 9:
                output = "You have been cursed with bad luck";
                break;
            case 10:
                output = "You have been cursed with extremely bad luck";
                break;
        }
        return output;
    }
}
