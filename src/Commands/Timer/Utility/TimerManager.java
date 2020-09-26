package Commands.Timer.Utility;

import net.dv8tion.jda.api.JDA;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
    Timer timer;
    JDA discord;

    public TimerManager(JDA input) {
        int timeleft = 60 - java.time.ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).getMinute();
        discord = input;
        timer = new Timer();
        timer.schedule(new RemindTask(), timeleft * 60 * 1000,1800 * 1000 * 2);
    }

    class RemindTask extends TimerTask {
        public void run() {
            try {
                discord.getGuildById(468234463386533888L).getTextChannelById(678879605272215552L).sendMessage(
                        "Online " + java.time.ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).getHour() + ":00").queue();
                //timer.cancel(); //Terminate the timer thread
            } catch(java.lang.NullPointerException e) {
                System.out.println("Error: " + e);
            }
        }
    }
    //--------------------
    //Reset Time
    //--------------------
    //--------------------
    public static String checkTime(){
        int currentTimeHour = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getHour();
        int currentTimeMinute = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getMinute();

        int ticketHour, ticketMinute = 0;
        if(currentTimeHour < 6) {
            if(currentTimeMinute == 0) {
                ticketHour = 6 - currentTimeHour;
            } else {
                ticketHour = 5 - currentTimeHour;
                ticketMinute = 60 - currentTimeMinute;
            }
        } else if(currentTimeHour < 12) {
            if(currentTimeMinute == 0) {
                ticketHour = 12 - currentTimeHour;
            } else {
                ticketHour = 11 - currentTimeHour;
                ticketMinute = 60 - currentTimeMinute;
            }
        } else if(currentTimeHour < 18) {
            if(currentTimeMinute == 0) {
                ticketHour = 18 - currentTimeHour;
            } else {
                ticketHour = 17 - currentTimeHour;
                ticketMinute = 60 - currentTimeMinute;
            }
        } else {
            if(currentTimeMinute == 0) {
                ticketHour = 24 - currentTimeHour;
            } else {
                ticketHour = 23 - currentTimeHour;
                ticketMinute = 60 - currentTimeMinute;
            }
        }
        String output;
        if(ticketMinute < 10) {
            output = "There is `" + ticketHour + ":0" + ticketMinute + "` until the next ticket reset";
        }else{
            output = "There is `" + ticketHour + ":" + ticketMinute + "` until the next ticket reset";
        }
        return output;
    }
    //--------------------
    //Cat Time
    //--------------------
    //--------------------
    public static String checkCat1() {
        int currentTimeHour = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getHour();
        int currentTimeMinute = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getMinute();

        int catHour = 0, catMinute = 0;
        String output;
        boolean now = false;
        //Time Check
        if(currentTimeHour < 12) {
            if(currentTimeMinute == 0) {
                catHour = 12 - currentTimeHour;
            } else {
                catHour = 11 - currentTimeHour;
                catMinute = 60 - currentTimeMinute;
            }
        } else if(currentTimeHour < 18) {
            if(currentTimeMinute == 0) {
                catHour = 18 - currentTimeHour;
            } else {
                catHour = 17 - currentTimeHour;
                catMinute = 60 - currentTimeMinute;
            }
            now = true;
        } else {
            if(currentTimeMinute == 0) {
                catHour = 24 - currentTimeHour;
            } else {
                catHour = 23 - currentTimeHour;
                catMinute = 60 - currentTimeMinute;
            }
        }
        //Output Parsing
        if(!now) {
            if(catMinute < 10) {
                output = "There is `" + catHour + ":0" + catMinute + "` left until Langelo arrives";
            } else {
                output = "There is `" + catHour + ":" + catMinute + "` left until Langelo arrives";
            }
        }else{
            if(catMinute < 10) {
                output = "Now! There is `" + catHour + ":0" + catMinute + "` left until Langelo leaves";
            }else{
                output = "Now! There is `" + catHour + ":" + catMinute + "` left until Langelo leaves";
            }
        }
        return output;
    }
    public static String checkCat2() {
        int currentTimeHour = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getHour();
        int currentTimeMinute = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getMinute();
        String currentTimeDay = java.time.ZonedDateTime.now(ZoneId.of("Asia/Tokyo")).getDayOfWeek().name();

        int catHour, catMinute;
        String output;
        boolean now = false;
        //Math Calculations
        int totalTime = 0;
        switch(currentTimeDay) {
            case "MONDAY":
                totalTime = 0;
                break;
            case "TUESDAY":
                totalTime = 24 * 60;
                break;
            case "WEDNESDAY":
                totalTime = 2 * 24 * 60;
                break;
            case "THURSDAY":
                totalTime = 3 * 24 * 60;
                break;
            case "FRIDAY":
                totalTime = 4 * 24 * 60;
                break;
            case "SATURDAY":
                totalTime = 5 * 24 * 60;
                break;
            case "SUNDAY":
                totalTime = 6 * 24 * 60;
                break;
            default:
                break;
        }
        totalTime += currentTimeHour * 60 + currentTimeMinute;
        if(totalTime < 19 * 60) {
            catMinute = 19 * 60 - totalTime;
        } else if(totalTime < 24 * 60) {
            catMinute = 24 * 60 - totalTime;
            now = true;
        } else if(totalTime < 19 * 60 + 24 * 2 * 60) {
            catMinute = 19 * 60 + 24 * 2 * 60 - totalTime;
        } else if(totalTime < 24 * 60 + 24 * 2 * 60) {
            catMinute = 24 * 60 + 24 * 2 * 60 - totalTime;
            now = true;
        } else if(totalTime < 19 * 60 + 24 * 4 * 60) {
            catMinute = 19 * 60 + 24 * 4 * 60 - totalTime;
        } else if(totalTime < 24 * 60 + 24 * 4 * 60) {
            catMinute = 24 * 60 + 24 * 4 * 60 - totalTime;
            now = true;
        } else {
            catMinute = 24 * 7 * 60 - totalTime + 19 * 60;
        }
        catHour = catMinute / 60;
        catMinute = catMinute % 60;
        //Output Parsing
        if(!now) {
            if(catMinute < 10) {
                output = "There is `" + catHour + ":0" + catMinute + "` left until Peasuke arrives";
            } else {
                output = "There is `" + catHour + ":" + catMinute + "` left until Peasuke arrives";
            }
        } else {
            if(catMinute < 10) {
                output = "Now! There is `" + catHour + ":0" + catMinute + "` left until Peasuke leaves";
            } else {
                output = "Now! There is `" + catHour + ":" + catMinute + "` left until Peasuke leaves";
            }
        }
        return output;
    }
}
