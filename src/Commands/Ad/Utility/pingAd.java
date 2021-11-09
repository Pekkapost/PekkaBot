package Commands.Ad.Utility;

import Manager.SQLManager;

public class pingAd {
    public static String check(String id, String message) {
        System.out.println(id);
        System.out.println(message);
        String output = "";
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) == '5'){
                SQLManager.updateAd(id, "CS5");
                output += "5 ";
            }
            if(message.charAt(i) == '1'){
                SQLManager.updateAd(id, "CS10");
                output += "10 ";
            }
            if(message.charAt(i) == '2'){
                SQLManager.updateAd(id, "CS20");
                output += "20 ";
            }
            if(message.charAt(i) == 'g'){
                SQLManager.updateAd(id, "Green");
                output += "G ";
            }
            if(message.charAt(i) == 'r'){
                SQLManager.updateAd(id, "Red");
                output += "R ";
            }
        }
        System.out.println(output);
        System.out.println();
        return output;
    }
}
