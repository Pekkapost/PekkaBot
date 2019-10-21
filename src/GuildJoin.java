import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter{
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        if(event.getGuild().getId().equals("608338330492600320")){
            event.getGuild().getController().addRolesToMember(event.getMember(),event.getGuild().getRolesByName("Admin",true)).complete();
        }
    }
}
