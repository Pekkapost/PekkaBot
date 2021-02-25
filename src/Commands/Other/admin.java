package Commands.Other;

import Commands.Ad.Utility.pingAd;
import Commands.WhiteGate.Utility.pingWG;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;

import java.util.List;

import static java.lang.Thread.sleep;

public class admin extends Command {
    public admin() {
        this.name = "Admin";
        this.help = "Admin";
        this.ownerCommand = true;
        this.hidden = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        if(event.getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_HISTORY)) {
            System.out.println("Has Permission");
        } else {
            System.out.println("Doesnt Have Permission");
        }
        String messageID = event.getMessage().getContentRaw().substring(8);
        run(event, messageID);
    }
    public void run(CommandEvent event, String messageID) {
        while(true) {
            try {
                MessageHistory his = MessageHistory.getHistoryAfter(event.getChannel(), messageID).limit(100).complete();
                System.out.println(his.size() + " Size");
                List<Message> temp = his.getRetrievedHistory();
                for (Message var : temp) {
                    check(var);
                    System.out.println(var);
                }
                //Success
                if (his.size() == 100) {
                    Message last = temp.get(0);
                    System.out.println();
                    sleep(1000);
                    messageID = last.getId();
                    //run(event, last.getId());
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("Interupted");
                try {sleep(10000);} catch (Exception e2){}
            }
        }
    }
    public void check(Message temp){
        String message = temp.getContentRaw().toLowerCase();
        for(int i = 0; i < temp.getMentionedUsers().size(); i++) {
            // If PekkaBot or Pekkapost is mentioned
            if(temp.getMentionedUsers().get(i).getIdLong() == 218781547854168064L ||
                    temp.getMentionedUsers().get(i).getIdLong() == 379513566711119872L) {
                message = message.replace("<@!" + temp.getMentionedUsers().get(i).getId() + ">", "");
                // If message is a white gate response
                if(message.contains("drawer") ||
                        message.contains("window") ||
                        message.contains("bed")){
                    pingWG.check(temp.getAuthor().getId(),message);
                    //What is this check for?
                    pingWG.check6(message);     //???
                } else if(temp.getMentionedUsers().get(i).getIdLong() == 379513566711119872L &&
                        (message.contains("5") || message.contains("1") || message.contains("2") || message.contains("g") || message.contains("r"))) {
                    pingAd.check(temp.getAuthor().getId(), message);
                }
                break;
            }
        }
    }
}
