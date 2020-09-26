package Commands.Gacha;

import Commands.Gacha.Utility.gachaManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.concurrent.TimeUnit;

public class gacha extends Command {
    public gacha() {
        this.name = "Gacha";
        this.aliases = new String[]{"G"};
        this.help = "Outputs a gacha roll";
    }
    @Override
    protected void execute(CommandEvent event) {
        int banner = 0;
        try {
            if(event.getMessage().getContentRaw().length() >= 8) {
                banner = Integer.parseInt(event.getMessage().getContentRaw().substring(8));
            }
        } catch (NumberFormatException error) {
            event.getTextChannel().sendMessage("Please use the correct format.").queue();
            return;
        }
        event.getTextChannel().sendMessage("Generating " + event.getAuthor().getName() + "'s Gacha Pull").queue(
                (message2 -> message2.delete().queueAfter(10, TimeUnit.SECONDS)));
        event.getTextChannel().sendMessage(event.getAuthor().getName() + "'s 10x Gacha Roll").addFile(
                gachaManager.pickMe(banner)).complete();
    }
}
