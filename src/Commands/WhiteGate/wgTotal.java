package Commands.WhiteGate;

import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class wgTotal extends Command {
    public wgTotal() {
        this.name = "WGTotal";
        this.aliases = new String[]{"TotalWG","WGT"};
        this.help = "Displays total white gate data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.whiteGate(event.getTextChannel(),event.getSelfMember().getUser(),SQLManager.getTotalWhiteGate());
    }
}
