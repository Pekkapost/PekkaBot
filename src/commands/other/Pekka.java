package commands.other;

import framework.command.Command;
import framework.command.CommandEvent;
import manager.EmbedManager;

/**
 * The {@code /Pekka} (alias {@code /Help}) command.
 *
 * Delegates the entire rendering to
 * {@link EmbedManager#help(net.dv8tion.jda.api.entities.channel.middleman.MessageChannel,
 * java.util.Collection)}, passing the live command registry pulled from
 * {@link CommandEvent#getClient()}. This means the help embed reflects
 * whatever commands are currently registered — no separate help-text
 * source of truth to keep in sync.
 *
 * {@code hidden=true} so the help command doesn't list itself.
 */
public class Pekka extends Command {
    public Pekka() {
        this.name = "Pekka";
        this.aliases = new String[]{"Help"};
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedManager.help(event.getTextChannel(), event.getClient().getCommands());
    }
}
