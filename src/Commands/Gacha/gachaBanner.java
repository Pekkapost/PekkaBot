package Commands.Gacha;

import Commands.Gacha.Utility.gachaRead;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class gachaBanner extends Command {
    public gachaBanner() {
        this.name = "GachaBanner";
        this.aliases = new String[]{"BannerList","GBanner"};
        this.help = "Displays a list of banners";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(gachaRead.checkList()).queue();
    }
}
