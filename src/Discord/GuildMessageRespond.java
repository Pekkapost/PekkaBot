package Discord;

import Manager.SQLManager;
import Commands.WhiteGate.Utility.pingWG;
import Commands.Ad.Utility.pingAd;

import Constants.BotConstants;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//Message Respond
public class GuildMessageRespond extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        // Check for Bot
        if(event.getAuthor().isBot()) { return; }
        String message = event.getMessage().getContentRaw().toLowerCase();
        // Update Points
        if(!message.startsWith(BotConstants.prefix)) {
            SQLManager.updatePoints(event.getAuthor().getId());
        }
        // Check if message is a white gate response
        for(int i = 0; i < event.getMessage().getMentionedUsers().size(); i++) {
            // If PekkaBot or Pekkapost is mentioned
            if(event.getMessage().getMentionedUsers().get(i).getIdLong() == 218781547854168064L ||
                    event.getMessage().getMentionedUsers().get(i).getIdLong() == 379513566711119872L) {
                message = message.replace("<@!" + event.getMessage().getMentionedUsers().get(i).getId() + ">", "");
                // If message is a white gate response
                if(message.contains("drawer") ||
                        message.contains("window") ||
                        message.contains("bed")){
                    pingWG.check(event.getAuthor().getId(),message);
                    //What is this check for?
                    pingWG.check6(message);     //???
                    // Try to add an emoji
                    if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
                    }
                } else if(event.getMessage().getMentionedUsers().get(i).getIdLong() == 379513566711119872L &&
                        (message.contains("5") || message.contains("1") || message.contains("2") || message.contains("g") || message.contains("r"))) {
                    pingAd.check(event.getAuthor().getId(), message);
                    if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction("ShibaHeart:666864728110530591").queue();
                    }
                }
                break;
            }
        }
    }

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) { return; }
        event.getChannel().sendMessage("Please do not dm me").queue();
    }
}
