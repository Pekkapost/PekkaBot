package Commands.WhiteGate;

import Manager.EmbedManager;
import Manager.SQLManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class wgMy extends Command {
    public wgMy() {
        this.name = "WhiteGate";
        this.aliases = new String[]{"WG","WGMy","MyWG"};
        this.help = "Displays your white gate data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.whiteGate(event.getTextChannel(),event.getAuthor(), SQLManager.getWhiteGate(event.getAuthor().getId()));
    }
}
