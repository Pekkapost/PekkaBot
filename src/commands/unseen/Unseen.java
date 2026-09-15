package commands.unseen;

import commands.unseen.utility.UnseenManager;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Unseen extends Command {
    public Unseen() {
        this.name = "Unseen";
        this.help = "Displays a Unseen";
        this.options = new OptionData[]{
                new OptionData(OptionType.STRING, "name", "Character name, or leave blank for a random one", false)
        };
        UnseenManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String name = e.getString("name").toLowerCase();
        String link = UnseenManager.callMe(name, !name.isBlank());
        String title = "Is this the Unseen you're looking for?";
        EmbedManager.lookingfor(e, e.getAuthor(), link, title);
    }
}
