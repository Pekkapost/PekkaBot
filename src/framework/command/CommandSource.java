package framework.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

/**
 * The half of a {@link CommandEvent} that differs between the two ways a
 * command can be invoked: a prefix message, or a slash interaction.
 *
 * Commands never see this type — they go through {@link CommandEvent},
 * which owns everything both paths compute the same way (self member,
 * guild channel) and delegates the rest here. Keeping the fork behind one
 * small interface is what lets a single {@code execute} body serve both
 * front doors without branching on how it was called.
 *
 * The replies differ in destination, not just in style: a deferred
 * interaction has to be answered through its hook or Discord leaves the
 * caller on "thinking…", while a prefix invocation has no interaction to
 * resolve and simply posts to the channel.
 */
interface CommandSource {
    User getAuthor();
    Guild getGuild();
    JDA getJDA();
    MessageChannelUnion getChannel();

    void reply(String content);
    void replyEmbeds(MessageEmbed embed);

    /** Blocks until the reply is sent. Only for callers that shut down straight after. */
    void replyBlocking(String content);

    /** The option's text, or {@code ""} when the caller omitted it. */
    String getString(String option);

    /** The selected user, or {@code null} when the caller omitted the option. */
    User getUser(String option);
}
