import Commands.*;
import Constants.BotConstants;
import Constants.GachaConstants;
import Constants.TimeConstants;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.Permission;
import java.io.File;
import javax.script.ScriptException;
import java.util.concurrent.TimeUnit;

public class MessageRespond extends ListenerAdapter {
    //Message Received
    File slap = new File("pictures/Slap.gif");
    File gz = new File("pictures/Gz.gif");
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()){return;}
        currency.add(event);
        //Variables
        String message = event.getMessage().getContent();
        for (int i = 0; i < event.getMessage().getMentionedUsers().size(); i++) {
            if (event.getMessage().getMentionedUsers().get(i).getIdLong() == 218781547854168064L || event.getMessage().getMentionedUsers().get(i).getIdLong() == 379513566711119872L || event.getMessage().getMentionedUsers().get(i).getIdLong() == 432672348273901571L) {
                if (message.toLowerCase().contains("drawer") || message.toLowerCase().contains("window") || message.toLowerCase().contains("bed"))
                    ping.check(event);
                break;
            }
        }
        if(!event.getChannelType().equals(ChannelType.PRIVATE) && event.getTextChannel().canTalk()) {
            //Current Bot Commands
            if (message.equals("p!bannerlist")) {
                event.getTextChannel().sendMessage(GachaConstants.bannerList).queue();
            }
            else if (message.startsWith("p!gacha")) {
                gacha.pickMe(event);
            }
            else if (message.startsWith("p!mygacha")){
                gacha.gachaMeList(event);
            }
            else if (message.startsWith("p!roll")) {
                if (message.length() >= 7 && message.charAt(6) == ' ')
                    gachaSpecific.pickMe(event);
            }
            else if (message.startsWith("p!fish")){
                //Do stuff
            }
            else if (message.equals("p!mywg")) {
                ping.mywg(event);
            }
            else if(message.equals("p!chronos")) {
                currency.chronos(event);
            }
            else if(message.equals("p!time")){
                resetTime.callMe(event);
            }
            else if (message.equals("p!timechart")){
                event.getTextChannel().sendMessage(TimeConstants.timeSheet).queue();
            }

            //Joke Commands
            if (message.startsWith("p!slap")) {
                if (message.equals("p!slap")) {
                    event.getTextChannel().sendFile(slap, new MessageBuilder().append("*Slaps*  <@" + event.getAuthor().getId() + ">").build()).queue();
                } else if (!event.getMessage().getMentionedUsers().isEmpty() && event.getMessage().getMentionedUsers().size() == 1) {
                    event.getTextChannel().sendFile(slap, new MessageBuilder().append("*Slaps*  <@" + event.getMessage().getMentionedUsers().get(0).getId() + ">").build()).queue();
                }
            }
            else if (message.startsWith("p!gz")){
                if (message.equals("p!gz")) {
                    event.getTextChannel().sendFile(gz, new MessageBuilder().append("*Congratulates*  <@" + event.getAuthor().getId() + ">").build()).queue();
                } else if (!event.getMessage().getMentionedUsers().isEmpty() && event.getMessage().getMentionedUsers().size() == 1) {
                    event.getTextChannel().sendFile(gz, new MessageBuilder().append("*Congratulates*  <@" + event.getMessage().getMentionedUsers().get(0).getId() + ">").build()).queue();
                }
            }
            else  if (message.equals("p!gimmie")) {
                event.getTextChannel().sendMessage("<a:Gimmie:468234791943143424>").queue();
            }
            else if (message.equals("p!whale")) {
                event.getTextChannel().sendMessage(BotConstants.whale).queue();
            }

            //Help and Bot related
            if (message.startsWith("p!help")) {
                if (message.equals("p!help"))
                    event.getTextChannel().sendMessage(BotConstants.help).queue();
                else if (message.equals("p!help gacha")) {
                    event.getTextChannel().sendMessage(BotConstants.gacha).queue();
                }else if (message.equals("p!help bannerlist")) {
                    event.getTextChannel().sendMessage(BotConstants.bannerlisthelp).queue();
                }else if (message.equals("p!help roll")) {
                    event.getTextChannel().sendMessage(BotConstants.roll).queue();
                }else if (message.equals("p!help mywg")) {
                    event.getTextChannel().sendMessage(BotConstants.mywg).queue();
                }else if (message.equals("p!help math")) {
                    event.getTextChannel().sendMessage(BotConstants.math).queue();
                }else if (message.equals("p!help addme")) {
                    event.getTextChannel().sendMessage(BotConstants.addmehelp).queue();
                }else if (message.equals("p!help chronos")) {
                    event.getTextChannel().sendMessage(BotConstants.chronos).queue();
                }else if (message.equals("p!help fish")) {
                    event.getTextChannel().sendMessage(BotConstants.fish).queue();
                }else if (message.equals("p!help slap") || message.equals("p!help whale") || message.equals("p!help dango") || message.equals("p!help gimmie") || message.equals("p!help gz")) {
                    event.getTextChannel().sendMessage(BotConstants.joke).queue();
                }else{
                    event.getTextChannel().sendMessage("Unknown command").queue();
                }
            }

            if (message.startsWith("p!purge")) {
                if (message.length() >= 8 && message.charAt(7) == ' ') {
                    for (int r = 0; r < event.getMember().getRoles().size(); r++) {
                        event.getTextChannel().sendMessage(Boolean.toString(event.getMember().getRoles().get(r).hasPermission(Permission.ADMINISTRATOR))).queue();
                        if (event.getMember().getRoles().get(r).hasPermission(Permission.ADMINISTRATOR) || event.getMember().getRoles().get(r).hasPermission(Permission.MANAGE_SERVER) || event.getMember().getRoles().get(r).hasPermission(Permission.MANAGE_CHANNEL)) {
                            purge.delete(event, Integer.valueOf(message.substring(8)));
                            break;
                        }
                    }
                }
            }

            //Information
            if (message.equals("p!dango")) {
                embeds.dango(event.getTextChannel());
            }
            else if (message.startsWith("p!math")) {
                if (message.length() >= 7) {
                    try {
                        math.callMe(event, message.substring(7));
                    } catch (ScriptException e) {
                        event.getTextChannel().sendMessage("You wrote `" + message.substring(7) + "`\nPlease do better math").queue();
                    }
                }
            }
            //Personal Commands
            if (message.equals("p!addme")) {
                event.getTextChannel().sendMessage(BotConstants.addME).queue();
            }
            //End of Commands List
        }
    }
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event){
        if(event.getAuthor().isBot()){return;}
        event.getChannel().sendMessage("Please do not dm me").queue();
    }
}
