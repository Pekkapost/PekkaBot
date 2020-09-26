package Commands.Gacha.Utility.Admin;

import Commands.Gacha.Utility.urlParse;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class clear extends Command {
    public clear() {
        this.name = "Clear";
        this.aliases = new String[]{"Clearbanner"};
        this.help = "Clears banners";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event){
        urlParse.clear();
        if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI)){
            event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
        }
    }
}
