package discord;

import manager.SQLManager;
import commands.whitegate.utility.PingWG;
import commands.ad.utility.PingAd;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * The bot's only message handler; commands arrive as interactions instead
 * and are routed by {@link framework.command.CommandClient}.
 *
 * Two side-channels live here:
 *
 * 1. Activity tracking — every guild message increments the sender's
 *    Chronos Stone count via {@link SQLManager#updatePoints}. Needs only
 *    the author, never the body.
 * 2. Stat ingestion — when the bot is @-mentioned, the message body is
 *    parsed for white-gate or ad data and persisted through {@link PingWG}
 *    / {@link PingAd}.
 *
 * Without the MESSAGE_CONTENT intent, {@code getContentRaw()} is empty for
 * every message Discord doesn't exempt — and "the bot was mentioned" is
 * exactly one of those exemptions. So the mention check below is not just
 * a filter on which messages are interesting, it is the boundary of what
 * this listener may read at all, and nothing above it may touch the body.
 */
public class GuildMessageRespond extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) {
            event.getChannel().sendMessage("Please do not dm me").queue();
            return;
        }

        SQLManager.updatePoints(event.getAuthor().getId());

        // Read nothing out of the message until we know the bot was mentioned.
        // That mention is what makes Discord populate the body at all, and JDA
        // logs a "attempting to access message content without
        // GatewayIntent.MESSAGE_CONTENT" warning for any other guild message
        // whose content is touched. This is the same predicate JDA itself uses
        // to decide the access was legitimate, so keep the two in step.
        User self = event.getJDA().getSelfUser();
        if (!event.getMessage().getMentions().getUsers().contains(self)) return;

        // Mobile Discord sends <@!id> while desktop sends <@id>; strip both.
        String message = event.getMessage().getContentRaw().toLowerCase()
                .replace("<@!" + self.getId() + ">", "")
                .replace("<@" + self.getId() + ">", "");

        if (message.contains("drawer") ||
                message.contains("window") ||
                message.contains("bed")) {
            String output = PingWG.check(event, event.getAuthor().getId(), message);
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                    event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                event.getMessage().addReaction(Emoji.fromCustom("ShibaHeart", 666864728110530591L, false)).queue();
            }
            event.getChannel().sendMessage("Received(in reverse): " + output).queue();
        // Ad shorthand alphabet, kept in sync with PingAd.check: digits 5/1/2
        // are Chronos-Stone tiers, g/r are Green/Red key drops, whitespace
        // is allowed between tokens.
        } else if (!message.isBlank() && message.matches("[512gr\\s]*")) {
            String output = PingAd.check(event.getAuthor().getId(), message);
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                    event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
                event.getMessage().addReaction(Emoji.fromCustom("KleeHugBomb", 783883423054823434L, false)).queue();
            }
            event.getChannel().sendMessage("Received: " + output).queue();
        }
    }
}
