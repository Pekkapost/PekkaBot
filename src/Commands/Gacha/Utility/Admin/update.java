package Commands.Gacha.Utility.Admin;

import Commands.Gacha.Utility.gachaManager;
import Commands.Gacha.Utility.urlParse;
import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class update extends Command {
    public update() {
        this.name = "Update";
        this.help = "Updates banner entry";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event){
        String message = event.getMessage().getContentRaw();
        try {
            urlParse.callMe(
                    message.substring(
                            message.indexOf(" ") + 1, message.indexOf(" ", message.indexOf(" ") + 1)),
                    message.substring(
                            message.indexOf(" ", message.indexOf(" ") + 1) + 1, message.lastIndexOf(" ")),
                    message.substring(
                            message.lastIndexOf(" ") + 1));
            if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI)){
                event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
            }
            gachaManager.update();
        } catch (Exception e) {
            event.getTextChannel().sendMessage("Please use the correct format").queue();
        }
    }
}
