package Commands.Gacha.Utility.Admin;

import Commands.Gacha.Utility.GachaManager;
import Framework.Command.Command;
import Framework.Command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class BannerUpdate extends Command {
    public BannerUpdate() {
        this.name = "BannerUpdate";
        this.help = "Updates banner";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event){
        GachaManager.update();
        if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI)){
            event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
        }
    }
}
