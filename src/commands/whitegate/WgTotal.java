package commands.whitegate;

import manager.EmbedManager;
import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class WgTotal extends Command {
    public WgTotal() {
        this.name = "WGTotal";
        this.aliases = new String[]{"TotalWG","WGT"};
        this.help = "Displays total white gate data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.whiteGate(event.getTextChannel(),event.getSelfMember().getUser(),SQLManager.getTotalWhiteGate());
    }
}
