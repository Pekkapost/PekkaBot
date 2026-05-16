package commands.other;

import framework.command.Command;
import framework.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Admin extends Command {
    private static final Logger logger = LoggerFactory.getLogger(Admin.class);

    public Admin() {
        this.name = "Admin";
        this.help = "Admin";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        boolean canRead = event.getGuild().getSelfMember()
                .hasPermission(event.getGuildChannel(), Permission.MESSAGE_HISTORY);
        logger.debug("Admin check in {}: MESSAGE_HISTORY={}", event.getGuildChannel(), canRead);
    }
}
