package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishUpgradeManager;
import Manager.EmbedManager;
import Manager.SQLManager;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class fishLocation extends Command {
    public fishLocation() {
        this.name = "FishLocation";
        this.aliases = new String[]{"Fishl","Flocation","FL", "FArea"};
        this.help = "Displays your fishing location";
    }
    @Override
    protected void execute(CommandEvent event) {
        String message = event.getMessage().getContentRaw();
        String id = event.getAuthor().getId();
        int cur = SQLManager.getUpgradeLocation(id);
        if(!message.contains(" ")) {
            String totalLocation = fishUpgradeManager.getLocation(cur);
            for (int i = cur - 1; i >= 0; i--) {
                totalLocation += ", " + fishUpgradeManager.getLocation(i);
            }
            EmbedManager.fishLocation(event.getChannel(), event.getAuthor(), SQLManager.getLocation(id), totalLocation);
        } else {
            for(int i = cur; i >= 0; i--) {
                if(fishUpgradeManager.getLocation(i).equals(message.substring(message.indexOf(" ") + 1))) {
                    SQLManager.updateLocation(id, message.substring(message.indexOf(" ") + 1));
                    if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
                    }
                    return;
                }
            }
            event.getTextChannel().sendMessage("Unable to find `" + message.substring(message.indexOf(" ") + 1) + "`").queue();
        }
    }
}
