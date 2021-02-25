package Manager;

import Commands.Currency.Fishing.Utility.fishManager;
import Constants.BotConstants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class EmbedManager {
    public static void chronos(MessageChannel channel, User author, int points) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setDescription("You have  " + points + " <:ChronosStone:719806042606665738>");
        builder.setAuthor(author.getName(),author.getAvatarUrl(),author.getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
    public static void pekkaCoin(MessageChannel channel, User author, int points, int region) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.BLUE);
        String currency = fishManager.getCurrency(region);
        builder.setDescription("You have  " + points + currency);
        builder.setAuthor(author.getName(),author.getAvatarUrl(),author.getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
    public static void dango(MessageChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("This is a smol little dango");
        builder.setDescription("It likes to bounce around");
        builder.setThumbnail("https://cdn.discordapp.com/avatars/218781547854168064/68474a1b67a8e27f5fafe296815771fe.png");
        channel.sendMessage(builder.build()).queue();
    }
    public static void fish(MessageChannel channel, User author, String catchType, String fish, String points, String location, int total, int region) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("Caught " + catchType);
        builder.setDescription("+ " + points);
        builder.setAuthor(author.getName(),author.getAvatarUrl(),author.getAvatarUrl());
        builder.setThumbnail(fish);
        String currency = fishManager.getCurrency(region).replaceAll("[^A-Za-z]","");;
        builder.setFooter(location + " | " + total + " " + currency);
        channel.sendMessage(builder.build()).queue();
    }
    public static void fishLeaderboard(MessageChannel channel, User author, String output, String userOuput) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("Fish Leaderboard");
        builder.setDescription(output + "\n" + userOuput);
        builder.setAuthor(author.getName(),author.getAvatarUrl(),author.getAvatarUrl());
        //builder.setFooter();
        channel.sendMessage(builder.build()).queue();
    }
    public static void fishLocation(MessageChannel channel, User author, String currentLocation, String totalLocation) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("You are fishing at: " + currentLocation);
        builder.setDescription("You can fish at: " + totalLocation);
        builder.setAuthor(author.getName(),author.getAvatarUrl(),author.getAvatarUrl());
        builder.setFooter("Please use Proper Capitalization");
        channel.sendMessage(builder.build()).queue();
    }
    public static void fishgrade(MessageChannel channel, User author, String location, String rod, String boat, String storage, String bait) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.addField("Location", location, false);
        builder.addField("Rod", rod, false);
        builder.addField("Boat", boat, false);
        builder.addField("Storage", storage, false);
        builder.addField("Bait", bait, false);
        builder.setAuthor(author.getName(),author.getAvatarUrl(),author.getAvatarUrl());
        builder.setFooter(BotConstants.prefix + "fishbuy [catagory]");
        channel.sendMessage(builder.build()).queue();
    }
    public static void fishdex(MessageChannel channel, User author, String[] fishes,int myFish, int totalFish, int pageNum) {
        if(fishes.length > 0) {
            String id = author.getId();
            String fishName;
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.PINK);
            builder.setTitle("Pekka Dex");
            builder.setDescription("You caught " + myFish + "/" + totalFish + " fish");
            for (int i = 0; i < fishes.length; i++) {
                if(fishes[i] == null) {
                    builder.addField("",
                            "", true);
                } else {
                    fishName = fishes[i].replaceAll("[^A-Za-z]", "");
                    String temp;
                    if (SQLManager.ownedFish(id, fishName)) {
                        temp =  "\uD83C\uDFA3";
                    } else {
                        temp = "❌";
                    }
                    builder.addField(temp + " " + fishes[i],
                            BotConstants.blank + String.valueOf(SQLManager.getFish(id, fishName)), true);
                }
            }
            builder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
            builder.setFooter("Page: " + pageNum + "/" + (int)(Math.ceil(Double.valueOf(totalFish)/12)));
            channel.sendMessage(builder.build()).queue();
        } else {
            channel.sendMessage("This page does not exist");
        }
    }
    public static void whiteGate(MessageChannel channel, User author, int[] temp){
        if(temp.length == 27) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.PINK);
            builder.setTitle(author.getName() + "'s White Gates");
            builder.addField("Drawer", temp[0] + "/" + temp[14], true);
            builder.addField("Window", temp[1] + "/" + temp[15], true);
            builder.addField("Bed", temp[2] + "/" + temp[16], true);

            builder.addField("Lake", temp[3] + "/" + temp[17], true);
            builder.addField("Plant", temp[4] + "/" + temp[18], true);
            builder.addField("", "", true);

            builder.addField("Left", temp[5] + "/" + temp[19], true);
            builder.addField("Middle", temp[6] + "/" + temp[20], true);
            builder.addField("Right", temp[7] + "/" + temp[21], true);

            builder.addField("Boat", temp[8] + "/" + temp[22], true);
            builder.addField("Door", temp[9] + "/" + temp[23], true);
            builder.addField("", "", true);

            builder.addField("Element", temp[10] + "/" + temp[24], true);
            builder.addField("Balloon", temp[11] + "/" + temp[25], true);
            builder.addField("Well", temp[12] + "/" + temp[26], true);

            builder.addField("Varuo", String.valueOf(temp[13]), true);
            builder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
            channel.sendMessage(builder.build()).queue();
        }
    }
    public static void ad(MessageChannel channel, User author, int[] temp){
        if(temp.length == 5) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.PINK);
            builder.setTitle(author.getName() + "'s Ad Results");
            builder.addField("5 <:ChronosStone:719806042606665738>", String.valueOf(temp[0]), true);
            builder.addField("10 <:ChronosStone:719806042606665738>", String.valueOf(temp[1]), true);
            builder.addField("20 <:ChronosStone:719806042606665738>", String.valueOf(temp[2]), true);

            builder.addField("G Key", String.valueOf(temp[3]), true);
            builder.addField("R Key", String.valueOf(temp[4]), true);

            builder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
            channel.sendMessage(builder.build()).queue();
        }
    }
    public static void action(MessageChannel channel, User author, String url, String message){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setDescription(message);
        builder.setImage(url);

        builder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
    public static void lookingfor(MessageChannel channel, User author, String url, String title){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle(title);
        builder.setImage(url);

        builder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
        channel.sendMessage(builder.build()).queue();
    }
}
