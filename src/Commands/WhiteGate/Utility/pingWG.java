package Commands.WhiteGate.Utility;

import Manager.SQLManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// Parses a White Gate result message sent by mentioning the bot.
// A full result walks 5 layers of the WG tree:
//   Layer 1 (entrance):   drawer / window / bed
//   Layer 2 (area):       lake / plant
//   Layer 3 (position):   left / middle / right
//   Layer 4 (path):       boat / door
//   Layer 5 (destination): element / balloon / well / varuo
// Each layer records a success ("lake") or failure ("lakeF") to the DB.
// An incomplete chain (missing a layer) marks the entrance as a failure and adds a Shion_point reaction.
public class pingWG {
    public static void addEmote(MessageReceivedEvent event) {
        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
            event.getMessage().addReaction(Emoji.fromCustom("Shion_point", 778018004075937803L, false)).queue();
        }
    }

    // Layer 1: identify the entrance (drawer / window / bed)
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

    // Layer 2: identify the area (lake / plant)
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

    // Layer 3: identify the position (left / middle / right)
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

    // Layer 4: identify the path (boat / door)
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

    // Layer 5: identify the destination (element / balloon / well / varuo)
    // Varuo is always a win; the others check for "win" in the message.
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

    // Returns true if the message contains "win" as a result indicator.
    // Removes all "window" occurrences first to prevent false positives.
    private static boolean isWin(String message) {
        return message.replace("window", "").contains("win");
    }
}
