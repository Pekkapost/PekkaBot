package Manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.awt.*;

public class EmbedManager {
    public static void chronos(MessageChannel channel, User author, int points) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setDescription("You have " + points + " <:chronos:540404637581443095>");
        builder.setAuthor(author.getName(), author.getEffectiveAvatarUrl(), author.getEffectiveAvatarUrl());
        channel.sendMessageEmbeds(builder.build()).queue();
    }

    public static void dango(MessageChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("This is a smol little dango");
        builder.setDescription("It likes to bounce around");
        builder.setThumbnail("https://cdn.discordapp.com/avatars/218781547854168064/68474a1b67a8e27f5fafe296815771fe.png");
        channel.sendMessageEmbeds(builder.build()).queue();
    }

    public static void whiteGate(MessageChannel channel, User author, int[] temp) {
        if (temp.length == 27) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.PINK);
            builder.setTitle(author.getName() + "'s White Gates");
            builder.addField("Drawer",  temp[0]  + "/" + temp[14], true);
            builder.addField("Window",  temp[1]  + "/" + temp[15], true);
            builder.addField("Bed",     temp[2]  + "/" + temp[16], true);

            builder.addField("Lake",    temp[3]  + "/" + temp[17], true);
            builder.addField("Plant",   temp[4]  + "/" + temp[18], true);
            builder.addField("", "", true);

            builder.addField("Left",    temp[5]  + "/" + temp[19], true);
            builder.addField("Middle",  temp[6]  + "/" + temp[20], true);
            builder.addField("Right",   temp[7]  + "/" + temp[21], true);

            builder.addField("Boat",    temp[8]  + "/" + temp[22], true);
            builder.addField("Door",    temp[9]  + "/" + temp[23], true);
            builder.addField("", "", true);

            builder.addField("Element", temp[10] + "/" + temp[24], true);
            builder.addField("Balloon", temp[11] + "/" + temp[25], true);
            builder.addField("Well",    temp[12] + "/" + temp[26], true);

            builder.addField("Varuo",   String.valueOf(temp[13]), true);
            builder.setAuthor(author.getName(), author.getEffectiveAvatarUrl(), author.getEffectiveAvatarUrl());
            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }

    public static void ad(MessageChannel channel, User author, int[] temp) {
        if (temp.length == 5) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.PINK);
            builder.setTitle(author.getName() + "'s Ad Results");
            builder.addField("5 <:ChronosStone:719806042606665738>",  String.valueOf(temp[0]), true);
            builder.addField("10 <:ChronosStone:719806042606665738>", String.valueOf(temp[1]), true);
            builder.addField("20 <:ChronosStone:719806042606665738>", String.valueOf(temp[2]), true);
            builder.addField("G Key", String.valueOf(temp[3]), true);
            builder.addField("R Key", String.valueOf(temp[4]), true);
            builder.setAuthor(author.getName(), author.getEffectiveAvatarUrl(), author.getEffectiveAvatarUrl());
            channel.sendMessageEmbeds(builder.build()).queue();
        }
    }

    public static void action(MessageChannel channel, User author, String url, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setDescription(message);
        builder.setImage(url);
        builder.setAuthor(author.getName(), author.getEffectiveAvatarUrl(), author.getEffectiveAvatarUrl());
        channel.sendMessageEmbeds(builder.build()).queue();
    }

    public static void lookingfor(MessageChannel channel, User author, String url, String title) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle(title);
        builder.setImage(url);
        builder.setAuthor(author.getName(), author.getEffectiveAvatarUrl(), author.getEffectiveAvatarUrl());
        channel.sendMessageEmbeds(builder.build()).queue();
    }
}
