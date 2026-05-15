package Commands.Gacha;

import Commands.Gacha.Utility.GachaRead;
import Framework.Command.Command;
import Framework.Command.CommandEvent;

public class GachaBanner extends Command {
    public GachaBanner() {
        this.name = "GachaBanner";
        this.aliases = new String[]{"BannerList","GBanner"};
        this.help = "Displays a list of banners";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage(GachaRead.checkList()).queue();
    }
}
