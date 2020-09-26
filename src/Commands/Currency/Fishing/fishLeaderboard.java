package Commands.Currency.Fishing;

import Manager.EmbedManager;
import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fishLeaderboard extends Command {
    public fishLeaderboard() {
        this.name = "FishLeaderboard";
        this.aliases = new String[] {"FishLb","FLeaderboard","FLb"};
        this.help = "Displays top 10 <:PekkaCoin:719798142983340074> data";
    }
    @Override
    protected void execute(CommandEvent e) {
        String id = e.getAuthor().getId();
        EmbedManager.fishLeaderboard(e.getTextChannel(), e.getAuthor(),
                SQLManager.leaderboard(id), SQLManager.myLeaderboard(id));
    }
}
