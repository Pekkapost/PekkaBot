package Commands.Ad;

import Manager.EmbedManager;
import Manager.SQLManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

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
