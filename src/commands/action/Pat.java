package commands.action;

import util.Resources;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Pat extends Command {
    public Pat() {
        this.name = "Pat";
        this.help = "Pats";
        this.options = new OptionData[]{
                new OptionData(OptionType.USER, "user", "Who to pat", false)
        };
    }
    @Override
    protected void execute(CommandEvent event) {
        User target = event.getUser("user");
        String recipient = target == null ? event.getAuthor().getId() : target.getId();
        EmbedManager.action(event, event.getAuthor(), Resources.pat,
                "*Pats* <@" + recipient + "> ");
    }
}
