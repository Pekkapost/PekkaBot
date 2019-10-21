package Commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.awt.*;

public class embeds {
    public static void vote(MessageReceivedEvent event){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Voting: ");
        builder.setDescription(event.getMessage().getContent().substring(7));
        builder.setColor(Color.CYAN);
        builder.setAuthor(event.getAuthor().getName(), "https://discordapp.com", event.getAuthor().getAvatarUrl());
        event.getGuild().getTextChannelById(478777838741356554L).sendMessage(builder.build()).queue(
                m -> {m.addReaction(event.getGuild().getEmotesByName("Yes",true).get(0)).queue();
                m.addReaction(event.getGuild().getEmotesByName("No",true).get(0)).queue();});
    }
    public static void dango(MessageChannel channel){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Description");
        builder.setDescription("A pink blob");
        builder.setColor(Color.PINK);
        builder.setThumbnail("https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fstatic.zerochan.net%2FDango.%2528CLANNAD%2529.full.113863.jpg&f=1");
        builder.setAuthor("Dango", "https://discordapp.com", "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fstatic.zerochan.net%2FDango.%2528CLANNAD%2529.full.113863.jpg&f=1");
        builder.addField("Type", "Pink Blob", true);
        builder.addField("Weapon", "Blob", true);
        builder.addField("Main Attacks","```Being A Pink Blob```", false);
        builder.addField("Main Buffs", "```Being A Pink Blob```", false);
        channel.sendMessage(builder.build()).queue();
    }
}
