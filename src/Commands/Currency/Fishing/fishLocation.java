package Commands.Currency.Fishing;

import Commands.Currency.Fishing.Utility.fishUpgradeManager;
import Manager.EmbedManager;
import Manager.SQLManager;

import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;

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
                        event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
                    }
                    return;
                }
            }
            event.getTextChannel().sendMessage("Unable to find `" + message.substring(message.indexOf(" ") + 1) + "`").queue();
        }
    }
}
