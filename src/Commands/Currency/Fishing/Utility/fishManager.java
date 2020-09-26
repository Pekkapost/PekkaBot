package Commands.Currency.Fishing.Utility;

import Manager.SQLManager;
import Structures.triple;
import Structures.weightedRandomBag;
import Manager.EmbedManager;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fishManager {
    private static Map<String, weightedRandomBag<String>> fishLocation = new HashMap<>();
    private static weightedRandomBag<String> kiraBag = new weightedRandomBag<>();
    private static weightedRandomBag<String> dragonBag = new weightedRandomBag<>();
    private static weightedRandomBag<String> baruokiBag = new weightedRandomBag<>();

    private static weightedRandomBag<String> pekkaBag = new weightedRandomBag<>();
    private static weightedRandomBag<String> acteulBag = new weightedRandomBag<>();
    private static weightedRandomBag<String> tiilenBag = new weightedRandomBag<>();
    private static weightedRandomBag<String> vasuBag = new weightedRandomBag<>();

    private static Map<String, triple> fishValue = new HashMap<>();
    private static Map<String, ZonedDateTime> playerTime = new HashMap<>();
    private static Map<String, Double> rareFish = new HashMap<>();
    private static ArrayList<String> fishList = new ArrayList<>();
    public static void callMe(MessageChannel channel, User author, String location, int points) {
        String id = author.getId();
        if(playerTime.containsKey(author.getId())){
            if(!author.getId().equals("218781547854168064")) {
                ZonedDateTime currentTime = java.time.ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
                ZonedDateTime setTime = playerTime.get(id).plusSeconds(5l);
                if (setTime.isAfter(currentTime)) {
                    channel.sendMessage("Please wait `" + (setTime.getSecond() - currentTime.getSecond()) + "` seconds").queue();
                    return;
                }
                playerTime.replace(id, currentTime);
            }
        } else {
            playerTime.put(id,java.time.ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));
        }

        String fishName = getRandom(location);
        if(fishName.equals("ERROR")) {
            channel.sendMessage("Please contact <@218781547854168064> if you receive this message").queue();
            System.out.println("    Error: " + location);
            return;
        }

        int region = SQLManager.getRegion(id);
        double fishPoint = getPoint(fishName) * getUpgrades(SQLManager.getUpgradeStorage(id));
        int tempFishPoint, tempFishPoint2, fishRegion = getRegion(fishName);
        String fishStorage = fishName.replaceAll("[^A-Za-z]","");
        SQLManager.updatePekkadex(id,fishStorage);
        // Update Points
        tempFishPoint = (int)fishPoint / 1;
        SQLManager.updateFishing(id,tempFishPoint,fishRegion);
        if(fishRegion > 0) {
            tempFishPoint2 = (int)(fishPoint % 1 * 10000000);
            SQLManager.updateFishing(id, tempFishPoint2, fishRegion - 1);
        }
        // Convert Points
        if(region > 0) {
            updatePoints(id);
        }
        points = SQLManager.getFishing(id,region);
        EmbedManager.fish(channel, author, fishName, getEmote(fishName),
                tempFishPoint + getCurrency(fishRegion), location, points, region);
    }
    public static String getCurrency(int region) {
        switch(region) {
            case 1:
                return " <:PekkaPoint:725177206279503883>";
            default:
                return " <:PekkaCoin:719798142983340074>";
        }
    }
    public static double getUpgrades(int num) {
        switch (num) {
            case 1:
                return 1.5;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 1;
        }
    }
    public static void updatePoints(String id) {
        int tempPoints = SQLManager.getFishing(id);
        if(tempPoints >= 10000000) {
            int updatePoints = tempPoints / 10000000;
            if(SQLManager.getRegion(id) < 1) {
                SQLManager.updateRegion(id, 1);
            }
            SQLManager.decreaseFishing(id,updatePoints * 10000000,0);
            SQLManager.updateFishing(id,updatePoints,1);
        }
    }
    public static String getRandom(String location) {
        if(fishLocation.containsKey(location) != true){
            System.out.println("    Error: Fish Manager Get Random Location");
            return "ERROR";
        }
        return fishLocation.get(location).getRandom();
    }
    public static void initializeFish() {
        kiraBeach();
        fishLocation.put("Kira Beach", kiraBag);
        dragonPalace();
        fishLocation.put("Dragon Palace",dragonBag);
        baruokiVillage();
        fishLocation.put("Baruoki Village",baruokiBag);
        pekka();
        fishLocation.put("Pekka",pekkaBag);
        acteul();
        fishLocation.put("Acteul",acteulBag);
        lakeTiilen();
        fishLocation.put("Lake Tiilen",tiilenBag);
        vasuMountain();
        fishLocation.put("Vasu Mountain", vasuBag);
    }
    public static void kiraBeach() {
        kiraBag.addEntry("Empty Pot",20);
        fishValue.put("Empty Pot",new triple("https://static.miraheze.org/anotheredenwiki/thumb/1/11/870000089.png/120px-870000089.png",1,0));
        fishList.add("Empty Pot");
        kiraBag.addEntry("Three Lid Clam",20);
        fishValue.put("Three Lid Clam",new triple("https://cdn.discordapp.com/emojis/615744790734045184.png?v=1",2,0));
        fishList.add("Three Lid Clam");
        kiraBag.addEntry("Kid Kamasu",30);
        fishValue.put("Kid Kamasu",new triple("https://static.miraheze.org/anotheredenwiki/thumb/4/42/870000102.png/120px-870000102.png",1,0));
        fishList.add("Kid Kamasu");
        kiraBag.addEntry("Legacy Kamasu",15);
        fishValue.put("Legacy Kamasu",new triple("https://static.miraheze.org/anotheredenwiki/thumb/4/49/870000060.png/120px-870000060.png",5,0));
        fishList.add("Legacy Kamasu");
        kiraBag.addEntry("Gold Silver Fish",15);
        fishValue.put("Gold Silver Fish",new triple("https://static.miraheze.org/anotheredenwiki/thumb/3/34/870000070.png/120px-870000070.png",5,0));
        fishList.add("Gold Silver Fish");
    }
    public static void dragonPalace() {
        dragonBag.addEntry("Empty Pot",4.9);
        dragonBag.addEntry("Kid Kamasu",5);
        dragonBag.addEntry("Surprised Clione",25);
        fishValue.put("Surprised Clione",new triple("https://static.miraheze.org/anotheredenwiki/thumb/d/da/870000099.png/120px-870000099.png",2,0));
        fishList.add("Surprised Clione");
        dragonBag.addEntry("Rock-Paper Axolotl",15);
        fishValue.put("Rock-Paper Axolotl",new triple("https://static.miraheze.org/anotheredenwiki/thumb/0/0c/870000097.png/120px-870000097.png",5,0));
        fishList.add("Rock-Paper Axolotl");
        dragonBag.addEntry("Three Bean Monkfish",15);
        fishValue.put("Three Bean Monkfish",new triple("https://static.miraheze.org/anotheredenwiki/thumb/d/d7/870000098.png/120px-870000098.png",10,0));
        fishList.add("Three Bean Monkfish");
        dragonBag.addEntry("Cockatiel Conch",10);
        fishValue.put("Cockatiel Conch",new triple("https://static.miraheze.org/anotheredenwiki/thumb/7/76/870000101.png/120px-870000101.png",5,0));
        fishList.add("Cockatiel Conch");
        dragonBag.addEntry("Parrot Conch",10);
        fishValue.put("Parrot Conch",new triple("https://static.miraheze.org/anotheredenwiki/thumb/9/90/870000100.png/120px-870000100.png",5,0));
        fishList.add("Parrot Conch");
        dragonBag.addEntry("Giant Coelacanth",10);
        fishValue.put("Giant Coelacanth",new triple("https://static.miraheze.org/anotheredenwiki/thumb/7/7b/870000064.png/120px-870000064.png",20,0));
        fishList.add("Giant Coelacanth");
        dragonBag.addEntry("Pair Ribbonfish",5);
        fishValue.put("Pair Ribbonfish",new triple("https://static.miraheze.org/anotheredenwiki/thumb/c/c5/870000077.png/120px-870000077.png",30,0));
        fishList.add("Pair Ribbonfish");
        dragonBag.addEntry("Levia", 0.1);
        fishValue.put("Levia",new triple("https://static.miraheze.org/anotheredenwiki/6/6e/104000201_sprite.png",1000000,0));
        fishList.add("Levia");
        rareFish.put("Levia",0.1);
    }
    public static void baruokiVillage() {
        baruokiBag.addEntry("Glass Slipper",9);
        fishValue.put("Glass Slipper",new triple("https://static.miraheze.org/anotheredenwiki/thumb/8/8a/870000084.png/120px-870000084.png",10,0));
        fishList.add("Glass Slipper");
        baruokiBag.addEntry("Baruoki Kamasu",20);
        fishValue.put("Baruoki Kamasu",new triple("https://static.miraheze.org/anotheredenwiki/thumb/d/d6/870000001.png/120px-870000001.png",25,0));
        fishList.add("Baruoki Kamasu");
        baruokiBag.addEntry("Long Face Mackerel",20);
        fishValue.put("Long Face Mackerel",new triple("https://static.miraheze.org/anotheredenwiki/thumb/c/c4/870000002.png/120px-870000002.png",25,0));
        fishList.add("Long Face Mackerel");
        baruokiBag.addEntry("Red Sahuagin",5);
        fishValue.put("Red Sahuagin",new triple("https://static.miraheze.org/anotheredenwiki/thumb/a/ae/870000103.png/120px-870000103.png",50,0));
        fishList.add("Red Sahuagin");
        baruokiBag.addEntry("Rad Grunt",15);
        fishValue.put("Rad Grunt",new triple("https://static.miraheze.org/anotheredenwiki/thumb/b/bf/870000003.png/120px-870000003.png",40,0));
        fishList.add("Rad Grunt");
        baruokiBag.addEntry("Beat Mackerel",15);
        fishValue.put("Beat Mackerel",new triple("https://static.miraheze.org/anotheredenwiki/thumb/c/ca/870000004.png/120px-870000004.png",40,0));
        fishList.add("Beat Mackerel");
        baruokiBag.addEntry("Bonito of Iso",15);
        fishValue.put("Bonito of Iso",new triple("https://static.miraheze.org/anotheredenwiki/thumb/2/28/870000005.png/120px-870000005.png",40,0));
        fishList.add("Bonito of Iso");
        baruokiBag.addEntry("Barunessie",1);
        fishValue.put("Barunessie",new triple("https://static.miraheze.org/anotheredenwiki/thumb/c/c7/870000006.png/120px-870000006.png",10000,0));
        fishList.add("Barunessie");
    }
    public static void pekka() {
        pekkaBag.addEntry("Empty Pot",99.8);
        pekkaBag.addEntry("Dango",0.2);
        fishValue.put("Dango",new triple("https://cdn.discordapp.com/avatars/218781547854168064/68474a1b67a8e27f5fafe296815771fe.png",1000000,0));
        fishList.add("Dango");
    }
    public static void acteul() {
        acteulBag.addEntry("Geta",20);
        fishValue.put("Geta",new triple("https://static.miraheze.org/anotheredenwiki/thumb/b/b3/870000086.png/120px-870000086.png",10000,0));
        fishList.add("Geta");
        acteulBag.addEntry("Empty Pot",10);
        acteulBag.addEntry("Three Lid Clam",10);
        acteulBag.addEntry("Legacy Kamasu",10);
        acteulBag.addEntry("Acteul Shrimp",10);
        fishValue.put("Acteul Shrimp",new triple("https://static.miraheze.org/anotheredenwiki/thumb/b/bd/870000061.png/120px-870000061.png",40000,0));
        fishList.add("Acteul Shrimp");
        acteulBag.addEntry("Marsh Coelus",20);
        fishValue.put("Marsh Coelus",new triple("https://static.miraheze.org/anotheredenwiki/thumb/4/4c/870000104.png/120px-870000104.png",100000,0));
        fishList.add("Marsh Coelus");
        acteulBag.addEntry("How Much Tuna",10);
        fishValue.put("How Much Tuna",new triple("https://static.miraheze.org/anotheredenwiki/thumb/0/0c/870000066.png/120px-870000066.png",111111,0));
        fishList.add("How Much Tuna");
        acteulBag.addEntry("Neoceratodus",5);
        fishValue.put("Neoceratodus",new triple("https://static.miraheze.org/anotheredenwiki/thumb/b/b9/870000063.png/120px-870000063.png",200000,0));
        fishList.add("Neoceratodus");
        acteulBag.addEntry("Trap Moray Eel",4.9);
        fishValue.put("Trap Moray Eel",new triple("https://static.miraheze.org/anotheredenwiki/thumb/5/58/870000065.png/120px-870000065.png",400000,0));
        fishList.add("Trap Moray Eel");
        acteulBag.addEntry("Kracken",0.1);
        fishValue.put("Kracken",new triple("https://static.miraheze.org/anotheredenwiki/thumb/0/07/870000079.png/120px-870000079.png",1000000,0));
        fishList.add("Kracken");
    }
    public static void lakeTiilen() {
        tiilenBag.addEntry("Empty Pot",20);
        tiilenBag.addEntry("Legacy Kamasu",20);
        tiilenBag.addEntry("Gold Silver Fish",20);
        tiilenBag.addEntry("Marsh Coelus",20);
        tiilenBag.addEntry("Gala Palsifa",10);
        fishValue.put("Gala Palsifa",new triple("https://static.miraheze.org/anotheredenwiki/thumb/1/12/870000068.png/120px-870000068.png",1,1));
        fishList.add("Gala Palsifa");
        tiilenBag.addEntry("Giant White Snail",5);
        fishValue.put("Giant White Snail",new triple("https://static.miraheze.org/anotheredenwiki/thumb/5/50/870000069.png/120px-870000069.png",1,1));
        fishList.add("Giant White Snail");
        tiilenBag.addEntry("Eternal Eel",2);
        fishValue.put("Eternal Eel",new triple("https://static.miraheze.org/anotheredenwiki/thumb/d/df/870000071.png/120px-870000071.png",1,1));
        fishList.add("Eternal Eel");
        tiilenBag.addEntry("Crabby Fish",2);
        fishValue.put("Crabby Fish",new triple("https://static.miraheze.org/anotheredenwiki/thumb/b/b8/870000072.png/120px-870000072.png",1,1));
        fishList.add("Crabby Fish");
        tiilenBag.addEntry("Muscle Mantra",1);
        fishValue.put("Muscle Mantra",new triple("https://static.miraheze.org/anotheredenwiki/thumb/b/b9/870000073.png/120px-870000073.png",3,1));
        fishList.add("Muscle Mantra");
    }
    public static void vasuMountain() {
        vasuBag.addEntry("Empty Pot",10);
        vasuBag.addEntry("Burnt Rockfish",30);
        fishValue.put("Burnt Rockfish",new triple("https://static.miraheze.org/anotheredenwiki/thumb/3/32/870000057.png/120px-870000057.png",1,1));
        fishList.add("Burnt Rockfish");
        vasuBag.addEntry("Fire Escargot",30);
        fishValue.put("Fire Escargot",new triple("https://static.miraheze.org/anotheredenwiki/thumb/3/32/870000057.png/120px-870000057.png",1,1));
        fishList.add("Fire Escargot");
        vasuBag.addEntry("Bright Red Goby",14);
        fishValue.put("Bright Red Goby",new triple("https://static.miraheze.org/anotheredenwiki/thumb/2/2a/870000058.png/120px-870000058.png",1,1));
        fishList.add("Bright Red Goby");
        vasuBag.addEntry("Magma Slime",14);
        fishValue.put("Magma Slime",new triple("https://static.miraheze.org/anotheredenwiki/thumb/1/1c/870000112.png/120px-870000112.png",1,1));
        fishList.add("Magma Slime");
        vasuBag.addEntry("Blazing Pagrus",1);
        fishValue.put("Blazing Pagrus",new triple("https://static.miraheze.org/anotheredenwiki/thumb/c/cc/870000054.png/120px-870000054.png",3,1));
        fishList.add("Blazing Pagrus");
        vasuBag.addEntry("Magmyne",1);
        fishValue.put("Magmyne",new triple("https://static.miraheze.org/anotheredenwiki/thumb/6/67/870000114.png/120px-870000114.png",3,1));
        fishList.add("Magmyne");
    }
    public static String[] getFishDex(int pageNum) {
        int fishAmount = 12;
        String[] temp = new String[fishAmount];
        for(int i = 0; i < fishAmount; i++) {
            if(i + (pageNum -1) * fishAmount >= fishList.size()) {
                return temp;
            }
            temp[i] = fishList.get(i  + (pageNum - 1) * fishAmount);
        }
        return temp;
    }
    public static int getMyFishDex(String id) {
        int temp = 0;
        for(int i = 0; i < fishList.size(); i++) {
            if(SQLManager.ownedFish(id, fishList.get(i).replaceAll("[^A-Za-z]", ""))) {
                temp++;
            }
        }
        return temp;
    }
    public static int getAllFishDex() {
        return fishList.size();
    }
    public static String getEmote(String fishName) {
        return fishValue.get(fishName).getLeft();
    }
    public static int getPoint(String fishName) {
        return fishValue.get(fishName).getMiddle();
    }
    public static int getRegion(String fishName) {
        return fishValue.get(fishName).getRight();
    }
}

