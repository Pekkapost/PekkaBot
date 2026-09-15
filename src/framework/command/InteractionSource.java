package framework.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

/**
 * {@link CommandSource} backed by a slash interaction.
 *
 * Options arrive already parsed and type-checked by Discord, so the
 * accessors are thin lookups. Every reply goes through the interaction
 * hook — {@link CommandClient} deferred the reply before dispatching, and
 * the hook is the only sink that resolves that deferral.
 */
class InteractionSource implements CommandSource {
    private final SlashCommandInteractionEvent event;

    InteractionSource(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    @Override public User getAuthor() { return event.getUser(); }
    @Override public Guild getGuild() { return event.getGuild(); }
    @Override public JDA getJDA() { return event.getJDA(); }
    @Override public MessageChannelUnion getChannel() { return event.getChannel(); }

    @Override public void reply(String content) { event.getHook().sendMessage(content).queue(); }
    @Override public void replyEmbeds(MessageEmbed embed) { event.getHook().sendMessageEmbeds(embed).queue(); }
    @Override public void replyBlocking(String content) { event.getHook().sendMessage(content).complete(); }

    @Override
    public String getString(String option) {
        OptionMapping mapping = event.getOption(option);
        return mapping == null ? "" : mapping.getAsString();
    }

    @Override
    public User getUser(String option) {
        OptionMapping mapping = event.getOption(option);
        return mapping == null ? null : mapping.getAsUser();
    }
}
