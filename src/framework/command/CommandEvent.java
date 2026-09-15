package framework.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

/**
 * Argument bundle handed to every {@link Command#execute} call.
 *
 * A command is invoked either as {@code p!name} or as {@code /name}, and
 * sees no difference: whatever varies between the two lives behind
 * {@link CommandSource}, and everything derived the same way from both
 * (self member, guild channel) is computed here. Commands therefore never
 * import JDA's event types, and never branch on how they were called.
 *
 * Also carries a reference to the dispatching {@link CommandClient} so
 * commands can introspect the live registry — used by
 * {@link commands.other.Pekka} (the help command).
 *
 * Output goes through {@link #reply} / {@link #replyEmbeds} rather than to
 * the channel directly. For a slash invocation those resolve the deferred
 * interaction; sending to the channel would post the message but leave
 * Discord showing "thinking…" forever.
 */
public class CommandEvent {
    private final CommandSource source;
    private final CommandClient client;

    CommandEvent(CommandSource source, CommandClient client) {
        this.source = source;
        this.client = client;
    }

    public User getAuthor() { return source.getAuthor(); }
    public Guild getGuild() { return source.getGuild(); }
    public JDA getJDA() { return source.getJDA(); }
    public MessageChannelUnion getChannel() { return source.getChannel(); }
    public GuildMessageChannel getGuildChannel() { return source.getChannel().asGuildMessageChannel(); }
    public Member getSelfMember() { return source.getGuild().getSelfMember(); }
    public CommandClient getClient() { return client; }

    public void reply(String content) { source.reply(content); }
    public void replyEmbeds(MessageEmbed embed) { source.replyEmbeds(embed); }

    /** Blocks until the reply is sent. Only for callers that shut down straight after. */
    public void replyBlocking(String content) { source.replyBlocking(content); }

    /** Returns the option's text, or {@code ""} when the caller omitted it. */
    public String getString(String option) { return source.getString(option); }

    /** Returns the selected user, or {@code null} when the caller omitted the option. */
    public User getUser(String option) { return source.getUser(option); }
}
