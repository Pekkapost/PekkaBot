package Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.*;

public class resetTime {
    public static void callMe(MessageReceivedEvent event){
        if(java.time.LocalTime.now().getHour()>=0) {
            if(java.time.LocalTime.now().getHour()>=2){
                if (java.time.LocalTime.now().getHour()>=8){
                    if (java.time.LocalTime.now().getHour()>=14){
                        if(java.time.LocalTime.now().getHour()>=20){
                            if(!(java.time.LocalTime.now().getMinute()==0)) {
                                if(java.time.LocalTime.now().getMinute() > 50){
                                    event.getTextChannel().sendMessage("There is `" + (26 - java.time.LocalTime.now().getHour() - 1) + ":0" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                                }else{
                                    event.getTextChannel().sendMessage("There is `" + (26 - java.time.LocalTime.now().getHour() - 1) + ":" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                                }
                            }else{
                                event.getTextChannel().sendMessage("There is `" + (26 - java.time.LocalTime.now().getHour()) + ":00` left until the next ticket reset").queue();
                            }
                            return;
                        }
                        if(!(java.time.LocalTime.now().getMinute()==0)) {
                            if(java.time.LocalTime.now().getMinute() > 50){
                                event.getTextChannel().sendMessage("There is `" + (20 - java.time.LocalTime.now().getHour() - 1) + ":0" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                            }else{
                                event.getTextChannel().sendMessage("There is `" + (20 - java.time.LocalTime.now().getHour() - 1) + ":" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                            }
                        }else{
                            event.getTextChannel().sendMessage("There is `" + (20 - java.time.LocalTime.now().getHour()) + ":00` left until the next ticket reset").queue();
                        }
                        return;
                    }
                    if(!(java.time.LocalTime.now().getMinute()==0)) {
                        if(java.time.LocalTime.now().getMinute() > 50){
                            event.getTextChannel().sendMessage("There is `" + (14 - java.time.LocalTime.now().getHour() - 1) + ":0" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                        }else{
                            event.getTextChannel().sendMessage("There is `" + (14 - java.time.LocalTime.now().getHour() - 1) + ":" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                        }
                    }else{
                        event.getTextChannel().sendMessage("There is `" + (14 - java.time.LocalTime.now().getHour()) + ":00` left until the next ticket reset").queue();
                    }
                    return;
                }
                if(!(java.time.LocalTime.now().getMinute()==0)) {
                    if(java.time.LocalTime.now().getMinute() > 50){
                        event.getTextChannel().sendMessage("There is `" + (8 - java.time.LocalTime.now().getHour() - 1) + ":0" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                    }else{
                        event.getTextChannel().sendMessage("There is `" + (8 - java.time.LocalTime.now().getHour() - 1) + ":" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                    }
                }else{
                    event.getTextChannel().sendMessage("There is `" + (8 - java.time.LocalTime.now().getHour()) + ":00` left until the next ticket reset").queue();
                }
                return;
            }
            if(!(java.time.LocalTime.now().getMinute()==0)) {
                if(java.time.LocalTime.now().getMinute() > 50){
                    event.getTextChannel().sendMessage("There is `" + (2 - java.time.LocalTime.now().getHour() - 1) + ":0" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                }else{
                    event.getTextChannel().sendMessage("There is `" + (2 - java.time.LocalTime.now().getHour() - 1) + ":" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
                }
            }else{
                event.getTextChannel().sendMessage("There is `" + (2 - java.time.LocalTime.now().getHour()) + ":00` left until the next ticket reset").queue();
            }
            return;
        }
        if(!(java.time.LocalTime.now().getMinute()==0)) {
            if(java.time.LocalTime.now().getMinute() > 50){
                event.getTextChannel().sendMessage("There is `" + (26 - java.time.LocalTime.now().getHour() - 1) + ":0" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
            }else{
                event.getTextChannel().sendMessage("There is `" + (26 - java.time.LocalTime.now().getHour() - 1) + ":" + (60 - java.time.LocalTime.now().getMinute()) + "` left until the next ticket reset").queue();
            }
        }else{
            event.getTextChannel().sendMessage("There is `" + (26 - java.time.LocalTime.now().getHour()) + ":00` left until the next ticket reset").queue();
        }
        return;
    }
}
