package Commands.Timer;
import Commands.Timer.Utility.TimerManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.MessageChannel;

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
