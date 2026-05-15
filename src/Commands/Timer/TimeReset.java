package Commands.Timer;

import Commands.Timer.Utility.TimerManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

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
