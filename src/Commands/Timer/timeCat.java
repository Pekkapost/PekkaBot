package Commands.Timer;

import Commands.Timer.Utility.TimerManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

import java.time.ZoneId;

public class timeCat extends Command {
    public timeCat() {
        this.name = "TimeCat";
        this.aliases = new String[]{"CatTime"};
        this.help = "Displays the times that cats spawn";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(TimerManager.checkCat1() + "\n" + TimerManager.checkCat2()).queue();
    }
}
