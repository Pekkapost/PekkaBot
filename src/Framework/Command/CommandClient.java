package Framework.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandClient extends ListenerAdapter {
    private final Map<String, Command> commands = new HashMap<>();
    private final String prefix;
    private final String ownerId;
    private final Set<String> coOwnerIds;

    public CommandClient(String prefix, String ownerId, String[] coOwnerIds, List<Command> commandList) {
        this.prefix = prefix.toLowerCase();
        this.ownerId = ownerId;
        this.coOwnerIds = new HashSet<>();
        for (String id : coOwnerIds) this.coOwnerIds.add(id);
        for (Command cmd : commandList) {
            commands.put(cmd.name.toLowerCase(), cmd);
            for (String alias : cmd.aliases) commands.put(alias.toLowerCase(), cmd);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (!event.isFromGuild()) return;

        String content = event.getMessage().getContentRaw();
        if (!content.toLowerCase().startsWith(prefix)) return;

        String withoutPrefix = content.substring(prefix.length()).trim();
        if (withoutPrefix.isEmpty()) return;
        String[] parts = withoutPrefix.split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        Command command = commands.get(commandName);
        if (command == null) return;

        String authorId = event.getAuthor().getId();
        if (command.ownerCommand && !ownerId.equals(authorId) && !coOwnerIds.contains(authorId)) return;

        command.execute(new CommandEvent(event, args));
    }
}
