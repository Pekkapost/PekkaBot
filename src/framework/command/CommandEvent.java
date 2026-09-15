package framework.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

/**
 * Argument bundle handed to every {@link Command#execute} call.
 *
 * Wraps the underlying JDA {@link SlashCommandInteractionEvent} so callers
 * don't import JDA's interaction types directly, exposes the invocation's
 * options through typed accessors, and carries a reference to the
 * dispatching {@link CommandClient} so commands can introspect the live
 * registry — used by {@link commands.other.Pekka} (the help command).
 *
 * All output goes through {@link #getHook()} (or the {@link #reply} shorthand)
 * rather than to the channel directly. {@link CommandClient} has already
 * deferred the reply by the time {@code execute} runs, so the hook is the
 * only sink that resolves the pending interaction — a bare channel send
 * would post the message but leave Discord showing "thinking…" forever.
 */
public class CommandEvent {
    private final SlashCommandInteractionEvent event;
    private final CommandClient client;

    public CommandEvent(SlashCommandInteractionEvent event, CommandClient client) {
        this.event = event;
        this.client = client;
    }

    public User getAuthor() { return event.getUser(); }
    public InteractionHook getHook() { return event.getHook(); }
    public GuildMessageChannel getGuildChannel() { return event.getChannel().asGuildMessageChannel(); }
    public Guild getGuild() { return event.getGuild(); }
    public JDA getJDA() { return event.getJDA(); }
    public Member getSelfMember() { return event.getGuild().getSelfMember(); }
    public CommandClient getClient() { return client; }

    public void reply(String content) {
        event.getHook().sendMessage(content).queue();
    }

    /** Returns the option's text, or {@code ""} when the caller omitted it. */
    public String getString(String option) {
        OptionMapping mapping = event.getOption(option);
        return mapping == null ? "" : mapping.getAsString();
    }

    /** Returns the selected user, or {@code null} when the caller omitted the option. */
    public User getUser(String option) {
        OptionMapping mapping = event.getOption(option);
        return mapping == null ? null : mapping.getAsUser();
    }
}
