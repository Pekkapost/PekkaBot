package discord;

import manager.SQLManager;
import commands.whitegate.utility.PingWG;
import commands.ad.utility.PingAd;

import config.BotConstants;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Non-command message handler.
 *
 * Most user input flows through the {@link framework.command.CommandClient}
 * dispatcher, but this listener handles two side-channels that don't fit
 * the prefix-command model:
 *
 * 1. Activity tracking — every non-command guild message increments the
 *    sender's Chronos Stone count via {@link SQLManager#updatePoints}.
 * 2. Stat ingestion — when the bot is @-mentioned, the message body is
 *    parsed for white-gate or ad data and persisted through {@link PingWG}
 *    / {@link PingAd}.
 *
 * The hard-coded bot user id (379513566711119872L) is PekkaBot's own —
 * a substitution would only matter if forking onto a different account.
 */
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
                // If message is a white gate response
                if (message.contains("drawer") ||
                        message.contains("window") ||
                        message.contains("bed")) {
                    String output = PingWG.check(event, event.getAuthor().getId(), message);
                    // Try to add an emoji
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
                    }
                    event.getChannel().sendMessage("Received(in reverse): " + output).queue();
                } else if (!message.isBlank() && message.matches("[512gr\\s]*")) {
                    String output = PingAd.check(event.getAuthor().getId(), message);
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                            event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                        event.getMessage().addReaction(Emoji.fromCustom("KleeHugBomb", 783883423054823434L, false)).queue();
                    }
                    event.getChannel().sendMessage("Received: " + output).queue();
                }
                break;
            }
        }
    }
}
