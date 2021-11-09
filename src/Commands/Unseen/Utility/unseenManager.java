package Commands.Unseen.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class unseenManager {
    private static Map<String, String> unseenFile = new HashMap<>();

    public static void initialize() {
        unseenFile.put("mistrare","https://cdn.discordapp.com/attachments/720886089535193109/844811275330125834/image0.png");
        unseenFile.put("???","https://cdn.discordapp.com/attachments/720886089535193109/844811275330125834/image0.png");
    }
    public static String callMe(String message, boolean name){
        String link;
        if(name){
            link = findMe(message);
        } else {
            Random rand = new Random();
            Object[] links = unseenFile.values().toArray();
            link = (String) links[rand.nextInt(links.length)];
        }
        //System.out.println("Debug: " + link);
        return link;
    }
    public static String findMe(String unit){
        if(unseenFile.containsKey(unit)){
            return unseenFile.get(unit);
        }
        return unseenFile.get("???");
    }
}
