package Commands;

import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.Message;
import java.util.List;
public class purge {
    public static void delete (MessageReceivedEvent event, int repeat){
        repeat = repeat + 1;
        if(repeat<=21){
            MessageHistory history = new MessageHistory(event.getTextChannel());
            List<Message> msgs;
            try{
                msgs = history.retrievePast(repeat).complete();
                for(int i = msgs.size()-1; i >= 0; i--) {
                    msgs.get(i).delete().queue();
                }
            }catch(Exception ex){System.out.println("     Error in purge command.");}
        }
        else
            event.getTextChannel().sendMessage("Maximum purges are 20 messages").queue();
    }
}
