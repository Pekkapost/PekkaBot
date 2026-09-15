package framework.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Slash-command registrar and dispatcher.
 *
 * Holds the registered command list (built by {@link CommandClientBuilder},
 * populated from {@link util.CommandLoader#discover}), pushes it to Discord
 * on {@code READY}, and routes each incoming interaction to its handler
 * after gating on {@code ownerCommand}.
 *
 * Discord has no alias concept, so a command's {@code name} and every entry
 * in its {@code aliases} are registered as separate slash commands pointing
 * at the same handler. Names are lowercased because Discord rejects
 * anything else.
 *
 * The {@code commandList} field is kept separate from the name-keyed
 * {@code commands} map so the help embed can iterate commands
 * deterministically — iterating the map's values would yield duplicates,
 * once per alias.
 */
public class CommandClient extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CommandClient.class);

    // Discord rejects a description that is empty or over 100 characters.
    private static final int MAX_DESCRIPTION_LENGTH = 100;

    private final Map<String, Command> commands = new HashMap<>();
    // Separate registration-ordered list so the help command can iterate
    // commands deterministically. The `commands` map is keyed by name and
    // alias, so iterating its values would yield duplicates.
    private final List<Command> commandList;
    private final String ownerId;
    private final Set<String> coOwnerIds;

    public CommandClient(String ownerId, String[] coOwnerIds, List<Command> commandList) {
        this.ownerId = ownerId;
        this.coOwnerIds = new HashSet<>();
        for (String id : coOwnerIds) this.coOwnerIds.add(id);
        this.commandList = List.copyOf(commandList);
        for (Command cmd : this.commandList) {
            register(cmd.name, cmd);
            for (String alias : cmd.aliases) register(alias, cmd);
        }
    }

    // Discord 400s the whole registration batch on a duplicate name, which
    // would take every command down — log and keep the first binding instead.
    private void register(String name, Command cmd) {
        String key = name.toLowerCase();
        Command existing = commands.putIfAbsent(key, cmd);
        if (existing != null && existing != cmd) {
            logger.error("Duplicate command name '{}': {} collides with {}, ignoring the latter",
                    key, existing.getClass().getName(), cmd.getClass().getName());
        }
    }

    public List<Command> getCommands() {
        return commandList;
    }

    @Override
    public void onReady(ReadyEvent event) {
        List<SlashCommandData> payload = new ArrayList<>();
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            Command cmd = entry.getValue();
            SlashCommandData data = Commands.slash(entry.getKey(), description(cmd))
                    // The bot only ever operated in guilds — the old dispatcher
                    // dropped non-guild messages outright, and commands reach for
                    // getGuild() freely, which is null in a DM.
                    .setContexts(InteractionContextType.GUILD)
                    .addOptions(cmd.options);
            if (cmd.ownerCommand) {
                // Hides owner-only commands from the picker for everyone but
                // guild admins. The runtime owner check below is still the real
                // gate — this is presentation only.
                data.setDefaultPermissions(DefaultMemberPermissions.DISABLED);
            }
            payload.add(data);
        }
        event.getJDA().updateCommands().addCommands(payload).queue(
                registered -> logger.info("Registered {} slash commands", registered.size()),
                error -> logger.error("Failed to register slash commands", error));
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Command command = commands.get(event.getName());
        if (command == null) return;

        String authorId = event.getUser().getId();
        if (command.ownerCommand && !ownerId.equals(authorId) && !coOwnerIds.contains(authorId)) {
            event.reply("That command is owner-only.").setEphemeral(true).queue();
            return;
        }

        // Deferring first turns the 3-second interaction deadline into 15
        // minutes, which covers the blocking user lookups in
        // discord.Discord#getUserName. Every command must then produce output
        // through the hook, or the interaction shows "thinking…" forever.
        event.deferReply().queue();
        command.execute(new CommandEvent(event, this));
    }

    // Discord shows this verbatim in the command picker, where custom-emoji
    // markup would render as raw "<:Name:id>" text — strip it back to the
    // emoji's name. Commands with no help text fall back to their own name.
    private static String description(Command cmd) {
        String text = cmd.help.replaceAll("<a?:(\\w+):\\d+>", "$1").trim();
        if (text.isEmpty()) text = cmd.name;
        if (text.length() > MAX_DESCRIPTION_LENGTH) text = text.substring(0, MAX_DESCRIPTION_LENGTH);
        return text;
    }
}
