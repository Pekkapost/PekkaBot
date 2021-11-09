package Commands.WhiteGate.Utility;

import Manager.SQLManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class pingWG {
    static String output;

    public static void addEmote(GuildMessageReceivedEvent event) {
        if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION) &&
                event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EXT_EMOJI)) {
            event.getMessage().addReaction("Shion_point:778018004075937803").queue();
        }
    }
    public static String check(GuildMessageReceivedEvent event, String id, String message) {
        output = "";
        if(message.contains("drawer")) {
            if(check2(id, message)) {
                SQLManager.updateWhiteGate(id, "drawer");
                output += "drawer ";
            } else {
                SQLManager.updateWhiteGate(id, "drawerF");
                output += "drawerF ";
                addEmote(event);
            }
        } else if(message.contains("window")) {
            if(check2(id, message)){
                SQLManager.updateWhiteGate(id, "window");
                output += "window ";
            } else {
                SQLManager.updateWhiteGate(id, "windowF");
                output += "windowF ";
                addEmote(event);
            }
        } else if(message.contains("bed")) {
            if(check2(id, message)){
                SQLManager.updateWhiteGate(id, "bed");
                output += "bed ";
            } else {
                SQLManager.updateWhiteGate(id, "bedF");
                output += "bedF ";
                addEmote(event);
            }
        }
        return output;
    }
    public static boolean check2(String id, String message) {
        if(message.contains("lake") || message.contains("pond")) {
            if(check3(id, message)) {
                SQLManager.updateWhiteGate(id, "lake");
                output += "lake ";
            } else {
                SQLManager.updateWhiteGate(id, "lakeF");
                output += "lakeF ";
            }
            return true;
        } else if(message.contains("plant")) {
            if (check3(id, message)) {
                SQLManager.updateWhiteGate(id, "plant");
                output += "plant ";
            } else {
                SQLManager.updateWhiteGate(id, "plantF");
                output += "plantF ";
            }
            return true;
        }
        return false;
    }
    public static boolean check3(String id, String message) {
        if(message.contains("left")) {
            if(check4(id, message)) {
                SQLManager.updateWhiteGate(id, "left");
                output += "left ";
            } else {
                SQLManager.updateWhiteGate(id, "leftF");
                output += "leftF ";
            }
            return true;
        } else if(message.contains("middle") || message.contains("center")) {
            if(check4(id, message)){
                SQLManager.updateWhiteGate(id, "middle");
                output += "middle ";
            } else {
                SQLManager.updateWhiteGate(id, "middleF");
                output += "middleF ";
            }
            return true;
        } else if(message.contains("right")) {
            if(check4(id, message)){
                SQLManager.updateWhiteGate(id, "right");
                output += "right ";
            } else {
                SQLManager.updateWhiteGate(id, "rightF");
                output += "rightF ";
            }
            return true;
        }
        return false;
    }
    public static boolean check4(String id, String message) {
        if(message.contains("boat")) {
            if(check5(id, message)) {
                SQLManager.updateWhiteGate(id, "boat");
                output += "boat ";
            } else {
                SQLManager.updateWhiteGate(id, "boatF");
                output += "boatF ";
            }
            return true;
        } else if(message.contains("door")) {
            if (check5(id, message)) {
                SQLManager.updateWhiteGate(id, "door");
                output += "door ";
            } else {
                SQLManager.updateWhiteGate(id, "doorF");
                output += "doorF ";
            }
            return true;
        }
        return false;
    }
    public static boolean check5(String id, String message) {
        if(message.contains("element")) {
            if(check6(message)) {
                SQLManager.updateWhiteGate(id, "element");
                output += "element ";
            } else {
                SQLManager.updateWhiteGate(id, "elementF");
                output += "elementF ";
            }
            return true;
        } else if(message.contains("balloon")) {
            if(check6(message)){
                SQLManager.updateWhiteGate(id, "balloon");
                output += "balloon ";
            } else {
                SQLManager.updateWhiteGate(id, "balloonF");
                output += "balloonF ";
            }
            return true;
        } else if(message.contains("well")) {
            if(check6(message)){
                SQLManager.updateWhiteGate(id, "well");
                output += "well ";
            } else {
                SQLManager.updateWhiteGate(id, "wellF");
                output += "wellF ";
            }
            return true;
        } else if (message.contains("varuo")) {
            SQLManager.updateWhiteGate(id, "varuo");
            output += "varuo ";
            return true;
        }
        return false;
    }
    public static boolean check6(String message) {
        //Make sure window not counted as "win"
        if(message.contains("window")) {
            message = message.substring(message.indexOf("window") + 6);
        }
        if(message.contains("win")){
            output += "win ";
            return true;
        }
        return false;
    }
}