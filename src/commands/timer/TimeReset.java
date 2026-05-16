package commands.timer;

import commands.timer.utility.TimerManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class TimeReset extends Command {
    public TimeReset() {
        this.name = "Time";
        this.aliases = new String[]{"TimeReset", "ResetTime"};
        this.help = "Displays reset time";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(TimerManager.checkTime()).queue();
    }
}
