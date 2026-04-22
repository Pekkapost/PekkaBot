package Commands.Timer;
import Commands.Timer.Utility.TimerManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.time.*;

public class timeReset extends Command {
    public timeReset() {
        this.name = "Time";
        this.aliases = new String[]{"TimeReset", "ResetTime"};
        this.help = "Displays reset time";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getChannel().sendMessage(TimerManager.checkTime()).queue();
    }
}
