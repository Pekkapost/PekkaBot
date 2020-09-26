package Commands.Gacha.Utility.Admin;

import Commands.Gacha.Utility.gachaManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class bannerUpdate extends Command {
    public bannerUpdate() {
        this.name = "BannerUpdate";
        this.help = "Updates banner";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event){
        gachaManager.update();
        if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI)){
            event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
        }
    }
}
