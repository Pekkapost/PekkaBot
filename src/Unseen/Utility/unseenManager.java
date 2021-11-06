package Unseen.Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class unseenManager {
    private static Map<String, String> unseenFile = new HashMap<>();

    public static void initialize() {
        unseenFile.put("???","https://cdn.discordapp.com/attachments/814291379194036245/906676284120248320/unseen.jpg");
        unseenFile.put("anabel","https://cdn.discordapp.com/attachments/814291379194036245/906673646548967434/Unabel.png");
        unseenFile.put("gary","https://cdn.discordapp.com/attachments/814291379194036245/906674315729190932/Ungary.jpg");
        unseenFile.put("moke","https://cdn.discordapp.com/attachments/814291379194036245/906674468104065065/unmoke.jpg");
        unseenFile.put("mryus","https://cdn.discordapp.com/attachments/814291379194036245/906674634588553237/Unmyrus.jpg");
        unseenFile.put("prai","https://cdn.discordapp.com/attachments/814291379194036245/906674745469173790/Unprai.jpg");
        unseenFile.put("premaya","https://cdn.discordapp.com/attachments/814291379194036245/906674843016110120/Unpremaya.jpg");
        unseenFile.put("riica","https://cdn.discordapp.com/attachments/814291379194036245/906674936821714974/Unriica.jpg");
        unseenFile.put("alter","https://cdn.discordapp.com/attachments/814291379194036245/906675062940237834/unseen_alter.png");
        unseenFile.put("clarte","https://cdn.discordapp.com/attachments/814291379194036245/906675182301761656/unseen_clarte.png");
        unseenFile.put("deirdre","https://cdn.discordapp.com/attachments/814291379194036245/906675349893578782/unseen_deirdre.png");
        unseenFile.put("feinne","https://cdn.discordapp.com/attachments/814291379194036245/906675545817907260/unseen_feinne.png");
        unseenFile.put("hozuki","https://cdn.discordapp.com/attachments/814291379194036245/906675808976920606/unseen_hozuki.png");
        unseenFile.put("melissa","https://cdn.discordapp.com/attachments/814291379194036245/906675995539554305/unseen_melissa.png");
        unseenFile.put("pexe","https://cdn.discordapp.com/attachments/814291379194036245/906676106122383460/unseen_pexe.png");
        unseenFile.put("tiramisu","https://cdn.discordapp.com/attachments/814291379194036245/906676195129700372/unseen_tiramisu.png");
        unseenFile.put("shion","https://cdn.discordapp.com/attachments/814291379194036245/906676384695459920/Unshion.jpg");
        unseenFile.put("suzette","https://cdn.discordapp.com/attachments/814291379194036245/906676489779576842/unsuzette.jpg");
        unseenFile.put("thilelille","https://cdn.discordapp.com/attachments/814291379194036245/906676608587423774/Unthilelille.jpg");
        unseenFile.put("toova","https://cdn.discordapp.com/attachments/814291379194036245/906676722672496701/Untoova.jpg");

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
