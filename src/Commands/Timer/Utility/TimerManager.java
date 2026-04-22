package Commands.Timer.Utility;

import java.time.ZoneId;
import java.time.ZonedDateTime;

// All times use JST (Asia/Tokyo) because that is Another Eden's server timezone.
public class TimerManager {

    // Ticket resets happen at 06:00, 12:00, 18:00, and 24:00 JST.
    public static String checkTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        int hour = now.getHour();
        int minute = now.getMinute();

        int ticketHour, ticketMinute = 0;
        if(hour < 6) {
            if(minute == 0) {
                ticketHour = 6 - hour;
            } else {
                ticketHour = 5 - hour;
                ticketMinute = 60 - minute;
            }
        } else if(hour < 12) {
            if(minute == 0) {
                ticketHour = 12 - hour;
            } else {
                ticketHour = 11 - hour;
                ticketMinute = 60 - minute;
            }
        } else if(hour < 18) {
            if(minute == 0) {
                ticketHour = 18 - hour;
            } else {
                ticketHour = 17 - hour;
                ticketMinute = 60 - minute;
            }
        } else {
            if(minute == 0) {
                ticketHour = 24 - hour;
            } else {
                ticketHour = 23 - hour;
                ticketMinute = 60 - minute;
            }
        }
        if(ticketMinute < 10) {
            return "There is `" + ticketHour + ":0" + ticketMinute + "` until the next ticket reset";
        }
        return "There is `" + ticketHour + ":" + ticketMinute + "` until the next ticket reset";
    }

    // Langelo (cat 1) appears daily from 12:00 to 18:00 JST.
    public static String checkCat1() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        int hour = now.getHour();
        int minute = now.getMinute();

        int catHour = 0, catMinute = 0;
        boolean active = false;

        if(hour < 12) {
            if(minute == 0) {
                catHour = 12 - hour;
            } else {
                catHour = 11 - hour;
                catMinute = 60 - minute;
            }
        } else if(hour < 18) {
            if(minute == 0) {
                catHour = 18 - hour;
            } else {
                catHour = 17 - hour;
                catMinute = 60 - minute;
            }
            active = true;
        } else {
            if(minute == 0) {
                catHour = 24 - hour;
            } else {
                catHour = 23 - hour;
                catMinute = 60 - minute;
            }
        }

        String timeStr = catMinute < 10
                ? catHour + ":0" + catMinute
                : catHour + ":" + catMinute;
        if(!active) {
            return "There is `" + timeStr + "` left until Langelo arrives";
        }
        return "Now! There is `" + timeStr + "` left until Langelo leaves";
    }

    // Peasuke (cat 2) appears Mon 19:00–24:00, Wed 19:00–24:00, Fri 19:00–24:00 JST.
    public static String checkCat2() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        int hour = now.getHour();
        int minute = now.getMinute();
        String day = now.getDayOfWeek().name();

        int totalMinutes = 0;
        switch(day) {
            case "MONDAY":    totalMinutes = 0;           break;
            case "TUESDAY":   totalMinutes = 24 * 60;     break;
            case "WEDNESDAY": totalMinutes = 2 * 24 * 60; break;
            case "THURSDAY":  totalMinutes = 3 * 24 * 60; break;
            case "FRIDAY":    totalMinutes = 4 * 24 * 60; break;
            case "SATURDAY":  totalMinutes = 5 * 24 * 60; break;
            case "SUNDAY":    totalMinutes = 6 * 24 * 60; break;
        }
        totalMinutes += hour * 60 + minute;

        boolean active = false;
        int catMinute;
        if(totalMinutes < 19 * 60) {
            catMinute = 19 * 60 - totalMinutes;
        } else if(totalMinutes < 24 * 60) {
            catMinute = 24 * 60 - totalMinutes;
            active = true;
        } else if(totalMinutes < 19 * 60 + 2 * 24 * 60) {
            catMinute = 19 * 60 + 2 * 24 * 60 - totalMinutes;
        } else if(totalMinutes < 24 * 60 + 2 * 24 * 60) {
            catMinute = 24 * 60 + 2 * 24 * 60 - totalMinutes;
            active = true;
        } else if(totalMinutes < 19 * 60 + 4 * 24 * 60) {
            catMinute = 19 * 60 + 4 * 24 * 60 - totalMinutes;
        } else if(totalMinutes < 24 * 60 + 4 * 24 * 60) {
            catMinute = 24 * 60 + 4 * 24 * 60 - totalMinutes;
            active = true;
        } else {
            catMinute = 7 * 24 * 60 - totalMinutes + 19 * 60;
        }

        int catHour = catMinute / 60;
        catMinute = catMinute % 60;

        String timeStr = catMinute < 10
                ? catHour + ":0" + catMinute
                : catHour + ":" + catMinute;
        if(!active) {
            return "There is `" + timeStr + "` left until Peasuke arrives";
        }
        return "Now! There is `" + timeStr + "` left until Peasuke leaves";
    }
}
