package Commands.WhiteGate.Utility;

import Manager.SQLManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class pingWG {
    public static void addEmote(MessageReceivedEvent event) {
        if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
            event.getMessage().addReaction(Emoji.fromCustom("Shion_point", 778018004075937803L, false)).queue();
        }
    }
    public static String check(MessageReceivedEvent event, String id, String message) {
        StringBuilder output = new StringBuilder();
        if(message.contains("drawer")) {
            if(check2(id, message, output)) {
                SQLManager.updateWhiteGate(id, "drawer");
                output.append("drawer ");
            } else {
                SQLManager.updateWhiteGate(id, "drawerF");
                output.append("drawerF ");
                addEmote(event);
            }
        } else if(message.contains("window")) {
            if(check2(id, message, output)){
                SQLManager.updateWhiteGate(id, "window");
                output.append("window ");
            } else {
                SQLManager.updateWhiteGate(id, "windowF");
                output.append("windowF ");
                addEmote(event);
            }
        } else if(message.contains("bed")) {
            if(check2(id, message, output)){
                SQLManager.updateWhiteGate(id, "bed");
                output.append("bed ");
            } else {
                SQLManager.updateWhiteGate(id, "bedF");
                output.append("bedF ");
                addEmote(event);
            }
        }
        return output.toString();
    }
    public static boolean check2(String id, String message, StringBuilder output) {
        if(message.contains("lake") || message.contains("pond")) {
            if(check3(id, message, output)) {
                SQLManager.updateWhiteGate(id, "lake");
                output.append("lake ");
            } else {
                SQLManager.updateWhiteGate(id, "lakeF");
                output.append("lakeF ");
            }
            return true;
        } else if(message.contains("plant")) {
            if (check3(id, message, output)) {
                SQLManager.updateWhiteGate(id, "plant");
                output.append("plant ");
            } else {
                SQLManager.updateWhiteGate(id, "plantF");
                output.append("plantF ");
            }
            return true;
        }
        return false;
    }
    public static boolean check3(String id, String message, StringBuilder output) {
        if(message.contains("left")) {
            if(check4(id, message, output)) {
                SQLManager.updateWhiteGate(id, "left");
                output.append("left ");
            } else {
                SQLManager.updateWhiteGate(id, "leftF");
                output.append("leftF ");
            }
            return true;
        } else if(message.contains("middle") || message.contains("center")) {
            if(check4(id, message, output)){
                SQLManager.updateWhiteGate(id, "middle");
                output.append("middle ");
            } else {
                SQLManager.updateWhiteGate(id, "middleF");
                output.append("middleF ");
            }
            return true;
        } else if(message.contains("right")) {
            if(check4(id, message, output)){
                SQLManager.updateWhiteGate(id, "right");
                output.append("right ");
            } else {
                SQLManager.updateWhiteGate(id, "rightF");
                output.append("rightF ");
            }
            return true;
        }
        return false;
    }
    public static boolean check4(String id, String message, StringBuilder output) {
        if(message.contains("boat")) {
            if(check5(id, message, output)) {
                SQLManager.updateWhiteGate(id, "boat");
                output.append("boat ");
            } else {
                SQLManager.updateWhiteGate(id, "boatF");
                output.append("boatF ");
            }
            return true;
        } else if(message.contains("door")) {
            if (check5(id, message, output)) {
                SQLManager.updateWhiteGate(id, "door");
                output.append("door ");
            } else {
                SQLManager.updateWhiteGate(id, "doorF");
                output.append("doorF ");
            }
            return true;
        }
        return false;
    }
    public static boolean check5(String id, String message, StringBuilder output) {
        if(message.contains("element")) {
            if(check6(message)) {
                SQLManager.updateWhiteGate(id, "element");
                output.append("element ");
            } else {
                SQLManager.updateWhiteGate(id, "elementF");
                output.append("elementF ");
            }
            return true;
        } else if(message.contains("balloon")) {
            if(check6(message)){
                SQLManager.updateWhiteGate(id, "balloon");
                output.append("balloon ");
            } else {
                SQLManager.updateWhiteGate(id, "balloonF");
                output.append("balloonF ");
            }
            return true;
        } else if(message.contains("well")) {
            if(check6(message)){
                SQLManager.updateWhiteGate(id, "well");
                output.append("well ");
            } else {
                SQLManager.updateWhiteGate(id, "wellF");
                output.append("wellF ");
            }
            return true;
        } else if (message.contains("varuo")) {
            SQLManager.updateWhiteGate(id, "varuo");
            output.append("varuo ");
            return true;
        }
        return false;
    }
    public static boolean check6(String message) {
        // Strip "window" so "win" inside it doesn't false-positive
        if(message.contains("window")) {
            message = message.substring(message.indexOf("window") + 6);
        }
        if(message.contains("win")){
            return true;
        }
        return false;
    }
}
