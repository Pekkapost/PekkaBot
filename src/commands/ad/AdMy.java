package commands.ad;

import manager.EmbedManager;
import manager.SQLManager;
import framework.command.Command;
import framework.command.CommandEvent;

public class AdMy extends Command {
    public AdMy() {
        this.name = "Ad";
        this.aliases = new String[]{"AdMy","MyAd","MyAds","AdsMy"};
        this.help = "Displays your ad data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.ad(event.getTextChannel(),event.getAuthor(), SQLManager.getAd(event.getAuthor().getId()));
    }
}
