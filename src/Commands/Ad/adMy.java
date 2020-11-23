package Commands.Ad;

import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class adMy extends Command {
    public adMy() {
        this.name = "Ad";
        this.aliases = new String[]{"AdMy","MyAd","MyAds","AdsMy"};
        this.help = "Displays your ad data";
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.ad(event.getTextChannel(),event.getAuthor(), SQLManager.getAd(event.getAuthor().getId()));
    }
}
