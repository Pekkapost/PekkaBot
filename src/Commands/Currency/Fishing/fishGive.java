package Commands.Currency.Fishing;

import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class fishGive extends Command {
    public fishGive() {
        this.name = "FGive";
        this.aliases = new String[]{"FG"};
        this.help = "Gives money to someone else";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        String message = event.getMessage().getContentRaw();
        if(event.getMessage().getMentionedUsers().size() > 0) {
            String id = event.getMessage().getMentionedMembers().get(0).getId();
            if (message.contains(" ")) {
                int add = Integer.valueOf(message.substring(message.lastIndexOf(" ") + 1));
                SQLManager.updateFishing(id,add,1);
                if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                        event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                    event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
                }
            }
        }
    }
}
