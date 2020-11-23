package Commands.Ad.Utility;

import Manager.SQLManager;

public class pingAd {
    public static void check(String id, String message) {
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) == '5'){
                SQLManager.updateAd(id, "CS5");
            }
            if(message.charAt(i) == '1'){
                SQLManager.updateAd(id, "CS10");
            }
            if(message.charAt(i) == '2'){
                SQLManager.updateAd(id, "CS20");
            }
            if(message.charAt(i) == 'g'){
                SQLManager.updateAd(id, "Green");
            }
            if(message.charAt(i) == 'r'){
                SQLManager.updateAd(id, "Red");
            }
        }
    }
}
