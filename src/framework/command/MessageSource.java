package framework.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

/**
 * {@link CommandSource} backed by a prefix message.
 *
 * A prefix invocation carries one blob of text and a mention list, where a
 * slash invocation carries named, typed options. This class bridges the
 * two: the text answers the command's first {@code STRING} option, and the
 * first mentioned user answers its first {@code USER} option. That is
 * enough for every command in the tree, each of which declares at most one
 * of either — a command that needs two options of the same type has to
 * parse {@code args} itself, or be slash-only.
 *
 * Only reachable when a prefix is configured, which in turn is the only
 * case where the bot requests MESSAGE_CONTENT and can read the text at
 * all; see {@link discord.Discord}.
 */
class MessageSource implements CommandSource {
    private final MessageReceivedEvent event;
    private final Command command;
    private final String args;

    MessageSource(MessageReceivedEvent event, Command command, String args) {
        this.event = event;
        this.command = command;
        this.args = args;
    }

    @Override public User getAuthor() { return event.getAuthor(); }
    @Override public Guild getGuild() { return event.getGuild(); }
    @Override public JDA getJDA() { return event.getJDA(); }
    @Override public MessageChannelUnion getChannel() { return event.getChannel(); }

    @Override public void reply(String content) { event.getChannel().sendMessage(content).queue(); }
    @Override public void replyEmbeds(MessageEmbed embed) { event.getChannel().sendMessageEmbeds(embed).queue(); }
    @Override public void replyBlocking(String content) { event.getChannel().sendMessage(content).complete(); }

    @Override
    public String getString(String option) {
        return option.equals(firstOptionNamed(OptionType.STRING)) ? args : "";
    }

    @Override
    public User getUser(String option) {
        if (!option.equals(firstOptionNamed(OptionType.USER))) return null;
        List<User> mentioned = event.getMessage().getMentions().getUsers();
        return mentioned.isEmpty() ? null : mentioned.get(0);
    }

    private String firstOptionNamed(OptionType type) {
        for (OptionData option : command.options) {
            if (option.getType() == type) return option.getName();
        }
        return null;
    }
}
