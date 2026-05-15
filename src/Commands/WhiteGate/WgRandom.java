package Commands.WhiteGate;

import Framework.Command.Command;
import Framework.Command.CommandEvent;

import java.util.Random;

public class WgRandom extends Command {
    private static final String[] ENTRANCES  = {"Drawer", "Window", "Bed"};
    private static final String[] AREAS      = {"Lake", "Plant"};
    private static final String[] POSITIONS  = {"Left", "Middle", "Right"};
    private static final String[] PATHS      = {"Boat", "Door"};
    private static final String[] ENDINGS    = {"Element", "Balloon", "Well"};

    public WgRandom() {
        this.name = "WhiteGateRandom";
        this.aliases = new String[]{"RandomWG", "WGRandom"};
        this.help = "Returns a random white gate";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(random()).queue();
    }
    private String random() {
        Random r = new Random();
        return pick(r, ENTRANCES) + " - " +
               pick(r, AREAS)     + " - " +
               pick(r, POSITIONS) + " - " +
               pick(r, PATHS)     + " - " +
               pick(r, ENDINGS);
    }
    private String pick(Random r, String[] options) {
        return options[r.nextInt(options.length)];
    }
}
