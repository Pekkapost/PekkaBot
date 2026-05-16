package manager;

import framework.command.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Centralised Discord embed (and a couple of plain-message) builders.
 *
 * Every method here owns the full {@link EmbedBuilder} → {@code send}
 * round trip, so callers stay one-liners. New canned responses go here
 * rather than in the command file so the embed style (colour, layout)
 * stays consistent across commands.
 *
 * {@link #help} is the only dynamic builder — it introspects the live
 * command registry, buckets by package name, and emits one field per
 * category. The bucket-to-display map below is the only thing to touch
 * when adding a new top-level package under {@code commands/}.
 */
public class EmbedManager {
    // Maps the bucket name (the segment right after "commands." in a command's
    // package) to the display heading shown in the help embed. The map's
    // insertion order also dictates the field order in the embed. Anything
    // not listed here falls into the trailing "Other" bucket.
    private static final Map<String, String> HELP_CATEGORY_DISPLAY = new LinkedHashMap<>();
    static {
        HELP_CATEGORY_DISPLAY.put("whitegate", "White Gate");
        HELP_CATEGORY_DISPLAY.put("ad",        "Ads");
        HELP_CATEGORY_DISPLAY.put("currency",  "Currency");
        HELP_CATEGORY_DISPLAY.put("timer",     "Timer (JST)");
        HELP_CATEGORY_DISPLAY.put("action",    "Actions");
    }
    private static final String HELP_FALLBACK_CATEGORY = "Other";

    public static void help(MessageChannel channel, Collection<Command> commands) {
        LinkedHashMap<String, List<Command>> grouped = new LinkedHashMap<>();
        for (String display : HELP_CATEGORY_DISPLAY.values()) grouped.put(display, new ArrayList<>());
        grouped.put(HELP_FALLBACK_CATEGORY, new ArrayList<>());

        for (Command cmd : commands) {
            if (cmd.hidden || cmd.ownerCommand) continue;
            String[] parts = cmd.getClass().getPackage().getName().split("\\.");
            String bucket = parts.length >= 2 ? parts[1] : "";
            String display = HELP_CATEGORY_DISPLAY.getOrDefault(bucket, HELP_FALLBACK_CATEGORY);
            grouped.get(display).add(cmd);
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("PekkaBot Commands");
        for (Map.Entry<String, List<Command>> entry : grouped.entrySet()) {
            List<Command> bucket = entry.getValue();
            if (bucket.isEmpty()) continue;
            bucket.sort(Comparator.comparing(c -> c.name.toLowerCase()));
            StringBuilder body = new StringBuilder();
            for (Command cmd : bucket) {
                String h = cmd.help.isEmpty() ? "—" : cmd.help;
                body.append("`").append(cmd.name).append("` — ").append(h).append("\n");
            }
            builder.addField(entry.getKey(), body.toString().stripTrailing(), false);
        }
        channel.sendMessageEmbeds(builder.build()).queue();
    }

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
            // Empty inline field acts as a row-break: Discord lays inline
            // fields out 3-per-row, so injecting blanks keeps the WG board
            // visually grouped as 3/3/3/3/3 instead of reflowing.
            builder.addField("", "", true);

            builder.addField("Left",    temp[5]  + "/" + temp[19], true);
            builder.addField("Middle",  temp[6]  + "/" + temp[20], true);
            builder.addField("Right",   temp[7]  + "/" + temp[21], true);

            builder.addField("Boat",    temp[8]  + "/" + temp[22], true);
            builder.addField("Door",    temp[9]  + "/" + temp[23], true);
            // Empty inline field acts as a row-break: Discord lays inline
            // fields out 3-per-row, so injecting blanks keeps the WG board
            // visually grouped as 3/3/3/3/3 instead of reflowing.
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
            builder.addField("5 <:chronos:540404637581443095>",  String.valueOf(temp[0]), true);
            builder.addField("10 <:chronos:540404637581443095>", String.valueOf(temp[1]), true);
            builder.addField("20 <:chronos:540404637581443095>", String.valueOf(temp[2]), true);
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
