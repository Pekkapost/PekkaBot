package discord;

import config.BotConstants;
import manager.SQLManager;
import commands.whitegate.utility.PingWG;
import commands.ad.utility.PingAd;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Non-command message handler. Prefix invocations are routed by
 * {@link framework.command.CommandClient}, which listens separately.
 *
 * Two side-channels live here:
 *
 * 1. Activity tracking — every non-command guild message increments the
 *    sender's Chronos Stone count via {@link SQLManager#updatePoints}.
 * 2. Stat ingestion — when the bot is @-mentioned, the message body is
 *    parsed for white-gate or ad data and persisted through {@link PingWG}
 *    / {@link PingAd}.
 *
 * Whether the body is readable at all depends on configuration. With a
 * prefix set the bot holds MESSAGE_CONTENT and everything is legible; with
 * a blank prefix it holds no such intent and {@code getContentRaw()}
 * returns "" for every message Discord doesn't exempt — "the bot was
 * mentioned" being the exemption this listener lives on. Reading the body
 * outside those two cases is not merely useless, it makes JDA log a
 * warning, so the guard below is load-bearing rather than an optimisation.
 */
public class GuildMessageRespond extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        if (!event.isFromGuild()) {
            event.getChannel().sendMessage("Please do not dm me").queue();
            return;
        }

        // The body is only legible in two situations, and reading it outside
        // them makes JDA log an "attempting to access message content without
        // GatewayIntent.MESSAGE_CONTENT" warning: when the bot was mentioned
        // (Discord exempts those regardless of intent), or when a prefix is
        // configured, which is exactly when the bot holds the intent.
        User self = event.getJDA().getSelfUser();
        boolean mentioned = event.getMessage().getMentions().getUsers().contains(self);
        boolean prefixCommands = !BotConstants.prefix.isBlank();
        String message = mentioned || prefixCommands
                ? event.getMessage().getContentRaw().toLowerCase()
                : "";

        // Command invocations don't count — otherwise users could farm Chronos
        // Stones by spamming any cheap command (`p!hug`, `p!shion`, ...). Slash
        // invocations aren't messages at all, so they never reach this listener.
        if (!(prefixCommands && message.startsWith(BotConstants.prefix.toLowerCase()))) {
            SQLManager.updatePoints(event.getAuthor().getId());
        }

        if (!mentioned) return;

        // Mobile Discord sends <@!id> while desktop sends <@id>; strip both.
        message = message
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
