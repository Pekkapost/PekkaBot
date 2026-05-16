package commands.whitegate;

import manager.EmbedManager;
import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class WgMy extends Command {
    public WgMy() {
        this.name = "WhiteGate";
        this.aliases = new String[]{"WG","WGMy","MyWG"};
        this.help = "Displays your white gate data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.whiteGate(event.getTextChannel(),event.getAuthor(), SQLManager.getWhiteGate(event.getAuthor().getId()));
    }
}
