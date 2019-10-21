import net.dv8tion.jda.core.JDA;

public class Schedule {
    public Schedule(JDA discord) {
        /*
        int waitTime = 0;
        if(12>ZonedDateTime.now().getHour())
            waitTime = (11-ZonedDateTime.now().getHour())*60*60+(59-ZonedDateTime.now().getMinute())*60+(60-ZonedDateTime.now().getSecond());
        else
            waitTime = (23-ZonedDateTime.now().getHour()+12)*60*60+(59-ZonedDateTime.now().getMinute())*60+(60-ZonedDateTime.now().getSecond());
        int waitTime2 = 0;
        if(13>ZonedDateTime.now().getHour())
            waitTime2 = (12-ZonedDateTime.now().getHour())*60*60+(59-ZonedDateTime.now().getMinute())*60+(60-ZonedDateTime.now().getSecond());
        else
            waitTime2 = (23-ZonedDateTime.now().getHour()+13)*60*60+(59-ZonedDateTime.now().getMinute())*60+(60-ZonedDateTime.now().getSecond());
        int waitTime3 = 0;
        if(21>ZonedDateTime.now().getHour())
            waitTime3 = (20-ZonedDateTime.now().getHour())*60*60+(59-ZonedDateTime.now().getMinute())*60+(60-ZonedDateTime.now().getSecond());
        else
            waitTime3 = (23-ZonedDateTime.now().getHour()+21)*60*60+(59-ZonedDateTime.now().getMinute())*60+(60-ZonedDateTime.now().getSecond());
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture =
                scheduledExecutorService.scheduleAtFixedRate(() -> discord.getGuildById(471923372180111381L).getTextChannelById(477250834426626059L).sendMessage("Banquet Time\n<@&476994028588498955>").queue()
                        ,waitTime
                        , 86400
                        , TimeUnit.SECONDS);

        ScheduledFuture scheduledFuture2 =
                scheduledExecutorService.scheduleAtFixedRate(() -> discord.getGuildById(471923372180111381L).getTextChannelById(477250834426626059L).sendMessage("Expedition Time\n<@&476994028588498955>").queue()
                        ,waitTime2
                        , 86400
                        , TimeUnit.SECONDS);

        ScheduledFuture scheduledFuture3 =
                scheduledExecutorService.scheduleAtFixedRate(() -> discord.getGuildById(471923372180111381L).getTextChannelById(477250834426626059L).sendMessage("Expedition Time\n<@&476994028588498955>").queue()
                        ,waitTime3
                        , 86400
                        , TimeUnit.SECONDS);
    */
    }
}
