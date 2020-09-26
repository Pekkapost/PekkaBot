package Commands.WhiteGate;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class wgRandom extends Command {
    public wgRandom() {
        this.name = "WhiteGateRandom";
        this.aliases = new String[]{"RandomWG","WGRandom"};
        this.help = "Returns a random white gate";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(random()).queue();
    }
    public String random(){
        String str = "";
        Random r = new Random();
        int rand = r.nextInt(3) + 1;
        switch(rand) {
            case 1:
                str += "Drawer - ";
                break;
            case 2:
                str += "Window - ";
                break;
            case 3:
                str += "Bed - ";
                break;
        }
        rand = r.nextInt(2) + 1;
        switch(rand) {
            case 1:
                str += "Lake - ";
                break;
            case 2:
                str += "Plant - ";
                break;
        }
        rand = r.nextInt(3) + 1;
        switch(rand) {
            case 1:
                str += "Left - ";
                break;
            case 2:
                str += "Middle - ";
                break;
            case 3:
                str += "Right - ";
                break;
        }
        rand = r.nextInt(2) + 1;
        switch(rand) {
            case 1:
                str += "Boat - ";
                break;
            case 2:
                str += "Door - ";
                break;
        }
        rand = r.nextInt(3) + 1;
        switch(rand) {
            case 1:
                str += "Element";
                break;
            case 2:
                str += "Balloon";
                break;
            case 3:
                str += "Well";
                break;
        }
        return str;
    }
}
