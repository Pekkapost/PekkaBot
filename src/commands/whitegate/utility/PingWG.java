package commands.whitegate.utility;

import manager.SQLManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Parses a White Gate result message posted by @-mentioning the bot.
 *
 * A full result walks 5 layers of the WG tree:
 * <pre>
 *   Layer 1 (entrance):    drawer / window / bed
 *   Layer 2 (area):        lake / plant
 *   Layer 3 (position):    left / middle / right
 *   Layer 4 (path):        boat / door
 *   Layer 5 (destination): element / balloon / well / varuo
 * </pre>
 * Each layer records a success ({@code "lake"}) or failure
 * ({@code "lakeF"}) to the WhiteGate table. An incomplete chain (missing
 * a layer) marks the entrance as a failure and triggers a Shion_point
 * reaction so the reporter knows to retry.
 *
 * Called from {@link discord.GuildMessageRespond} after substring-matching
 * a body-keyword like "drawer" / "window" / "bed".
 */
public class PingWG {
    public static void addEmote(MessageReceivedEvent event) {
        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
            event.getMessage().addReaction(Emoji.fromCustom("Shion_point", 778018004075937803L, false)).queue();
        }
    }

    public static String check(MessageReceivedEvent event, String id, String message) {
        StringBuilder output = new StringBuilder();
        String entrance = message.contains("drawer") ? "drawer"
                        : message.contains("window") ? "window"
                        : message.contains("bed")    ? "bed"
                        : null;
        if (entrance != null) {
            if (checkArea(id, message, output)) {
                SQLManager.updateWhiteGate(id, entrance);
                output.append(entrance).append(" ");
            } else {
                SQLManager.updateWhiteGate(id, entrance + "F");
                output.append(entrance).append("F ");
                addEmote(event);
            }
        }
        return output.toString();
    }

    private static boolean checkArea(String id, String message, StringBuilder output) {
        String area = (message.contains("lake") || message.contains("pond")) ? "lake"
                    : message.contains("plant") ? "plant"
                    : null;
        if (area == null) return false;
        if (checkPosition(id, message, output)) {
            SQLManager.updateWhiteGate(id, area);
            output.append(area).append(" ");
        } else {
            SQLManager.updateWhiteGate(id, area + "F");
            output.append(area).append("F ");
        }
        return true;
    }

    private static boolean checkPosition(String id, String message, StringBuilder output) {
        String pos = message.contains("left")                              ? "left"
                   : (message.contains("middle") || message.contains("center")) ? "middle"
                   : message.contains("right")                             ? "right"
                   : null;
        if (pos == null) return false;
        if (checkPath(id, message, output)) {
            SQLManager.updateWhiteGate(id, pos);
            output.append(pos).append(" ");
        } else {
            SQLManager.updateWhiteGate(id, pos + "F");
            output.append(pos).append("F ");
        }
        return true;
    }

    private static boolean checkPath(String id, String message, StringBuilder output) {
        String path = message.contains("boat") ? "boat"
                    : message.contains("door") ? "door"
                    : null;
        if (path == null) return false;
        if (checkDestination(id, message, output)) {
            SQLManager.updateWhiteGate(id, path);
            output.append(path).append(" ");
        } else {
            SQLManager.updateWhiteGate(id, path + "F");
            output.append(path).append("F ");
        }
        return true;
    }

    // Varuo is always a win — the rare layer-5 destination has no "fail" column
    // in the schema. The other three destinations check the message for "win".
    private static boolean checkDestination(String id, String message, StringBuilder output) {
        if (message.contains("element")) {
            String key = isWin(message) ? "element" : "elementF";
            SQLManager.updateWhiteGate(id, key);
            output.append(key).append(" ");
            return true;
        } else if (message.contains("balloon")) {
            String key = isWin(message) ? "balloon" : "balloonF";
            SQLManager.updateWhiteGate(id, key);
            output.append(key).append(" ");
            return true;
        } else if (message.contains("well")) {
            String key = isWin(message) ? "well" : "wellF";
            SQLManager.updateWhiteGate(id, key);
            output.append(key).append(" ");
            return true;
        } else if (message.contains("varuo")) {
            SQLManager.updateWhiteGate(id, "varuo");
            output.append("varuo ");
            return true;
        }
        return false;
    }

    // Strip "window" before checking — the layer-1 entrance keyword contains
    // "win" as a substring, which would otherwise mark every windowed gate
    // as a destination-win even when no actual "win" was reported.
    private static boolean isWin(String message) {
        return message.replace("window", "").contains("win");
    }
}
