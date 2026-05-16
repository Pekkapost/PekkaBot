package framework.command;

import net.dv8tion.jda.api.entities.Activity;

import java.util.ArrayList;
import java.util.List;

public class CommandClientBuilder {
    private String prefix = "!";
    private String ownerId = "";
    private String[] coOwnerIds = new String[0];
    private final List<Command> commands = new ArrayList<>();

    public CommandClientBuilder setPrefix(String prefix) { this.prefix = prefix; return this; }
    public CommandClientBuilder setOwnerId(String ownerId) { this.ownerId = ownerId; return this; }
    public CommandClientBuilder setCoOwnerIds(String... coOwnerIds) { this.coOwnerIds = coOwnerIds; return this; }
    public CommandClientBuilder setActivity(Activity a) { return this; }
    public CommandClientBuilder useHelpBuilder(boolean b) { return this; }
    public CommandClientBuilder addCommands(Command... cmds) {
        for (Command c : cmds) commands.add(c);
        return this;
    }

    public CommandClient build() {
        return new CommandClient(prefix, ownerId, coOwnerIds, commands);
    }
}
