package framework.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Argument bundle handed to every {@link Command#execute} call.
 *
 * Wraps the underlying JDA {@link MessageReceivedEvent} so callers don't
 * import JDA's event types directly, exposes the parsed command arguments
 * (everything after the command name), and carries a reference to the
 * dispatching {@link CommandClient} so commands can introspect the live
 * registry — used by {@link commands.other.Pekka} (the help command).
 */
public class CommandEvent {
    private final MessageReceivedEvent event;
    private final String args;
    private final CommandClient client;

    public CommandEvent(MessageReceivedEvent event, String args, CommandClient client) {
        this.event = event;
        this.args = args;
        this.client = client;
    }

    public User getAuthor() { return event.getAuthor(); }
    public Message getMessage() { return event.getMessage(); }
    public MessageChannel getChannel() { return event.getChannel(); }
    public MessageChannel getTextChannel()    { return event.getChannel(); }
    public GuildMessageChannel getGuildChannel() { return event.getChannel().asGuildMessageChannel(); }
    public Guild getGuild() { return event.getGuild(); }
    public JDA getJDA() { return event.getJDA(); }
    public Member getSelfMember() { return event.getGuild().getSelfMember(); }
    public String getArgs() { return args; }
    public CommandClient getClient() { return client; }
}
