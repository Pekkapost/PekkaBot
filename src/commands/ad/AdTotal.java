package commands.ad;

import manager.EmbedManager;
import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class AdTotal extends Command {
    public AdTotal() {
        this.name = "ADTotal";
        this.aliases = new String[]{"TotalAd","ADT","ADsTotal","TotalAds"};
        this.help = "Displays total ad data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.ad(event.getTextChannel(),event.getSelfMember().getUser(),SQLManager.getTotalAd());
    }
}
