package Discord;

import Manager.SQLManager;
import Commands.WhiteGate.Utility.pingWG;
import Commands.Ad.Utility.pingAd;

import Constants.BotConstants;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//Message Respond
public class GuildMessageRespond extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) {
            event.getChannel().sendMessage("Please do not dm me").queue();
            return;
        }

        String message = event.getMessage().getContentRaw().toLowerCase();
        // Update Points
        if (!message.startsWith(BotConstants.prefix)) {
            SQLManager.updatePoints(event.getAuthor().getId());
        }
        // Check if message is a white gate response
        for (int i = 0; i < event.getMessage().getMentions().getUsers().size(); i++) {
            // If PekkaBot is mentioned (hard-coded bot ID)
            if (event.getMessage().getMentions().getUsers().get(i).getIdLong() == 379513566711119872L) {
                // Mobile Discord sends <@!id> while desktop sends <@id>, so strip both
                message = message.replace("<@!379513566711119872>", "");
                message = message.replace("<@379513566711119872>", "");
                System.out.println(message);
                System.out.println(event.getMessage().getContentRaw().toLowerCase());
                // If message is a white gate response
                if (message.contains("drawer") ||
                        message.contains("window") ||
                        message.contains("bed")) {
                    String output = pingWG.check(event, event.getAuthor().getId(), message);
                    // Try to add an emoji
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
                    }
                    event.getChannel().asTextChannel().sendMessage("Received(in reverse): " + output).queue();
                } else if (message.contains("5") || message.contains("1") || message.contains("2") || message.contains("g") || message.contains("r")) {
                    String output = pingAd.check(event.getAuthor().getId(), message);
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction(Emoji.fromCustom("KleeHugBomb", 783883423054823434L, false)).queue();
                    }
                    event.getChannel().asTextChannel().sendMessage("Received: " + output).queue();
                }
                break;
            }
        }
    }
}
