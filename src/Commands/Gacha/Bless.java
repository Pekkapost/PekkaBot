package Commands.Gacha;

import Framework.Command.Command;
import Framework.Command.CommandEvent;

import java.util.Random;

public class Bless extends Command {
    public Bless() {
        this.name = "Bless";
        this.aliases = new String[]{"B"};
        this.help = "Blesses you";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(random()).queue();
    }
    private String random() {
        switch (new Random().nextInt(10) + 1) {
            case 1:          return "You have been blessed with great luck";
            case 2: case 3:  return "You have been blessed with good luck";
            case 4: case 5: case 6: return "You have been blessed with average luck";
            case 7: case 8: case 9: return "You have been cursed with bad luck";
            default:         return "You have been cursed with extremely bad luck";
        }
    }
}
