package framework.command;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Base class every slash command extends.
 *
 * Subclasses set the public fields in their constructor (name, help,
 * aliases, options, ownerCommand, hidden) and implement {@link #execute}.
 * The fields are read by {@link CommandClient} at registration time and by
 * {@link manager.EmbedManager#help} when rendering the dynamic help
 * embed, so changing them after construction has no effect.
 *
 * {@code name} and every entry in {@code aliases} are registered with
 * Discord as separate slash commands sharing this handler — the API has no
 * alias concept of its own. Discord lowercases command names, so the
 * casing here is only ever a display detail for the help embed.
 *
 * This file is the trimmed in-tree replacement for the archived
 * jda-utilities {@code com.jagrosh.jdautilities.command.Command} —
 * intentionally minimal so the framework stays drop-in-replaceable.
 */
public abstract class Command {
    public String name = "";
    public String help = "";
    public String[] aliases = new String[0];
    public OptionData[] options = new OptionData[0];
    public boolean ownerCommand = false;
    public boolean hidden = false;

    protected abstract void execute(CommandEvent event);
}
