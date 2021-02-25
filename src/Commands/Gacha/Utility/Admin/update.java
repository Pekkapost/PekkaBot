package Commands.Gacha.Utility.Admin;

import Commands.Gacha.Utility.gachaManager;
import Commands.Gacha.Utility.urlParse;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

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
                event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
            }
            gachaManager.update();
        } catch (Exception e) {
            event.getTextChannel().sendMessage("Please use the correct format").queue();
        }
    }
}
