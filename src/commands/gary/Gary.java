package commands.gary;

import commands.gary.utility.GaryManager;
import manager.EmbedManager;
import framework.command.Command;
import framework.command.CommandEvent;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Gary extends Command {
    public Gary() {
        this.name = "Gary";
        this.help = "Displays a Gary";
        this.options = new OptionData[]{
                new OptionData(OptionType.STRING, "name", "Character name, or leave blank for a random one", false)
        };
        GaryManager.initialize();
    }
    @Override
    protected void execute(CommandEvent e) {
        String name = e.getString("name").toLowerCase();
        String link = GaryManager.callMe(name, !name.isBlank());
        String title = "Is this the Gariyu AS you're looking for?";
        EmbedManager.lookingfor(e.getHook(), e.getAuthor(), link, title);
    }
}
