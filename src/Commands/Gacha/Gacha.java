package Commands.Gacha;

import Commands.Gacha.Utility.GachaManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.util.concurrent.TimeUnit;

public class Gacha extends Command {
    public Gacha() {
        this.name = "Gacha";
        this.aliases = new String[]{"G"};
        this.help = "Outputs a gacha roll";
    }
    @Override
    protected void execute(CommandEvent event) {
        int banner = 0;
        try {
            if (!event.getArgs().isEmpty()) {
                banner = Integer.parseInt(event.getArgs().trim());
            }
        } catch (NumberFormatException error) {
            event.getTextChannel().sendMessage("Please use the correct format.").queue();
            return;
        }
        event.getTextChannel().sendMessage("Generating " + event.getAuthor().getName() + "'s Gacha Pull").queue(
                (message2 -> message2.delete().queueAfter(10, TimeUnit.SECONDS)));
        event.getTextChannel().sendFiles(FileUpload.fromData(GachaManager.pickMe(banner)))
                .setContent(event.getAuthor().getName() + "'s 10x Gacha Roll").complete();
    }
}
