package Commands.WhiteGate;

import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

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
