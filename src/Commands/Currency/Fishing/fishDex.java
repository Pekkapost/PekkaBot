package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishManager;
import Manager.EmbedManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fishDex extends Command {
    public fishDex() {
        this.name = "FishDex";
        this.aliases = new String[]{"FishDex","FDex","PekkaDex","PDex"};
        this.help = "Fish Dex";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message = event.getMessage().getContentRaw();
        int page = 1;
        if(message.contains(" ")) {
            try {
                page = Integer.valueOf(message.substring(message.lastIndexOf(" ") + 1));
            } catch(NumberFormatException error) {
                event.getTextChannel().sendMessage("Please use the correct format.").queue();
                return;
            }
        }
        if(page <= Math.ceil(fishManager.getAllFishDex()/12)) {
            if (event.getMessage().getMentionedUsers().size() > 0) {
                EmbedManager.fishdex(event.getTextChannel(), event.getMessage().getMentionedUsers().get(0), fishManager.getFishDex(page),
                        fishManager.getMyFishDex(event.getAuthor().getId()), fishManager.getAllFishDex(), page);
            } else {
                EmbedManager.fishdex(event.getTextChannel(), event.getAuthor(), fishManager.getFishDex(page),
                        fishManager.getMyFishDex(event.getAuthor().getId()), fishManager.getAllFishDex(), page);
            }
        } else {
            event.getTextChannel().sendMessage("There are only " + Math.ceil(fishManager.getAllFishDex()/12) + " pages");
        }
    }
}
