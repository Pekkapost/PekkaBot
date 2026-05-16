package framework.command;

/**
 * Base class every prefix command extends.
 *
 * Subclasses set the public fields in their constructor (name, help,
 * aliases, ownerCommand, hidden) and implement {@link #execute}. The
 * fields are read by {@link CommandClient} at registration time and by
 * {@link manager.EmbedManager#help} when rendering the dynamic help
 * embed, so changing them after construction has no effect.
 *
 * This file is the trimmed in-tree replacement for the archived
 * jda-utilities {@code com.jagrosh.jdautilities.command.Command} —
 * intentionally minimal so the framework stays drop-in-replaceable.
 */
public abstract class Command {
    public String name = "";
    public String help = "";
    public String[] aliases = new String[0];
    public String arguments = "";
    public boolean ownerCommand = false;
    public boolean hidden = false;

    protected abstract void execute(CommandEvent event);
}
