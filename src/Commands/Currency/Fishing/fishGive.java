package Commands.Currency.Fishing;

import Manager.SQLManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;

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
        if(!event.getMessage().getMentions().getUsers().isEmpty()) {
            String id = event.getMessage().getMentions().getMembers().get(0).getId();
            if (message.contains(" ")) {
                int add = Integer.parseInt(message.substring(message.lastIndexOf(" ") + 1));
                SQLManager.updateFishing(id,add,1);
                if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                        event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                    event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
                }
            }
        }
    }
}
