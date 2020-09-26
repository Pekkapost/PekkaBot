package Commands.WhiteGate.Utility;

import Manager.SQLManager;

public class ping {
    public static void check(String id, String message) {
        if(message.contains("drawer")) {
            if(check2(id, message)) {
                SQLManager.updateWhiteGate(id, "drawer");
            } else {
                SQLManager.updateWhiteGate(id, "drawerF");
            }
        } else if(message.contains("window")) {
            if(check2(id, message)){
                SQLManager.updateWhiteGate(id, "window");
            } else {
                SQLManager.updateWhiteGate(id, "windowF");
            }
        } else if(message.contains("bed")) {
            if(check2(id, message)){
                SQLManager.updateWhiteGate(id, "bed");
            } else {
                SQLManager.updateWhiteGate(id, "bedF");
            }
        }
    }
    public static boolean check2(String id, String message) {
        if(message.contains("lake") || message.contains("pond")) {
            if(check3(id, message)) {
                SQLManager.updateWhiteGate(id, "lake");
            } else {
                SQLManager.updateWhiteGate(id, "lakeF");
            }
            return true;
        } else if(message.contains("plant")) {
            if (check3(id, message)) {
                SQLManager.updateWhiteGate(id, "plant");
            } else {
                SQLManager.updateWhiteGate(id, "plantF");
            }
            return true;
        }
        return false;
    }
    public static boolean check3(String id, String message) {
        if(message.contains("left")) {
            if(check4(id, message)) {
                SQLManager.updateWhiteGate(id, "left");
            } else {
                SQLManager.updateWhiteGate(id, "leftF");
            }
            return true;
        } else if(message.contains("middle") || message.contains("center")) {
            if(check4(id, message)){
                SQLManager.updateWhiteGate(id, "middle");
            } else {
                SQLManager.updateWhiteGate(id, "middleF");
            }
            return true;
        } else if(message.contains("right")) {
            if(check4(id, message)){
                SQLManager.updateWhiteGate(id, "right");
            } else {
                SQLManager.updateWhiteGate(id, "rightF");
            }
            return true;
        }
        return false;
    }
    public static boolean check4(String id, String message) {
        if(message.contains("boat")) {
            if(check5(id, message)) {
                SQLManager.updateWhiteGate(id, "boat");
            } else {
                SQLManager.updateWhiteGate(id, "boatF");
            }
            return true;
        } else if(message.contains("door")) {
            if (check5(id, message)) {
                SQLManager.updateWhiteGate(id, "door");
            } else {
                SQLManager.updateWhiteGate(id, "doorF");
            }
            return true;
        }
        return false;
    }
    public static boolean check5(String id, String message) {
        if(message.contains("element")) {
            if(check6(message)) {
                SQLManager.updateWhiteGate(id, "element");
            } else {
                SQLManager.updateWhiteGate(id, "elementF");
            }
            return true;
        } else if(message.contains("balloon")) {
            if(check6(message)){
                SQLManager.updateWhiteGate(id, "balloon");
            } else {
                SQLManager.updateWhiteGate(id, "balloonF");
            }
            return true;
        } else if(message.contains("well")) {
            if(check6(message)){
                SQLManager.updateWhiteGate(id, "well");
            } else {
                SQLManager.updateWhiteGate(id, "wellF");
            }
            return true;
        } else if (message.contains("varuo")) {
            SQLManager.updateWhiteGate(id, "varuo");
            return true;
        }
        return false;
    }
    public static boolean check6(String message) {
        if(message.contains("window")) {
            message = message.substring(message.indexOf("window") + 6);
        }
        if(message.contains("win")){
            return true;
        }
        return false;
    }
}