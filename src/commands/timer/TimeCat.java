package commands.timer;

import commands.timer.utility.TimerManager;
import framework.command.Command;
import framework.command.CommandEvent;

import java.time.ZoneId;

public class TimeCat extends Command {
    public TimeCat() {
        this.name = "TimeCat";
        this.aliases = new String[]{"CatTime"};
        this.help = "Displays the times that cats spawn";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(TimerManager.checkCat1() + "\n" + TimerManager.checkCat2()).queue();
    }
}
