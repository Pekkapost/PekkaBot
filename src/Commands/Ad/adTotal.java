package Commands.Ad;

import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class adTotal extends Command {
    public adTotal() {
        this.name = "ADTotal";
        this.aliases = new String[]{"TotalAd","ADT","ADsTotal","TotalAds"};
        this.help = "Displays total ad data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.ad(event.getTextChannel(),event.getSelfMember().getUser(),SQLManager.getTotalAd());
    }
}
