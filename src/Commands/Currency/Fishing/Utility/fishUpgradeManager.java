package Commands.Currency.Fishing.Utility;

import Structures.triple;

import java.util.HashMap;
import java.util.Map;

public class fishUpgradeManager {
    private static Map<Integer, triple> fishLocation = new HashMap<>();
    private static Map<Integer, triple> fishRod = new HashMap<>();
    private static Map<Integer, triple> fishBoat = new HashMap<>();
    private static Map<Integer, triple> fishStorage = new HashMap<>();
    private static Map<Integer, triple> fishBait = new HashMap<>();
    public static void initialize() {
        location();
        rod();
        boat();
        storage();
        bait();
    }
    public static void location() {
        fishLocation.put(0, new triple("Kira Beach",0, 0));
        fishLocation.put(1, new triple("Dragon Palace", 100, 0));
        fishLocation.put(2, new triple("Baruoki Village", 1000, 0));
        fishLocation.put(3, new triple("Pekka", 10000, 0));

        fishLocation.put(4, new triple("Acteul",1000000, 0));
        fishLocation.put(5, new triple("Lake Tiilen",20, 1));
        fishLocation.put(6, new triple("Vasu Mountain",1000, 1));
        fishLocation.put(7, new triple("TBA",100000, 1));
    }
    public static String getLocation(int loc) {
        return fishLocation.get(loc).getLeft();
    }
    public static String getDisplayLocation(int loc, int region) {
        if(loc == 5 && region == 0) {
            return "Try p!fishprestige";
        }
        return fishLocation.get(loc).getLeft();
    }
    public static int getLocationPrice(int loc, int region) {
        if(region > getLocationRegion(loc)) {
            return 0;
        }
        return fishLocation.get(loc).getMiddle();
    }
    public static int getLocationDisplayPrice(int loc, int region) {
        if(loc == 5 && region == 0) {
            return 10000000;
        }
        return fishLocation.get(loc).getMiddle();
    }
    public static int getLocationRegion(int loc) {
        return fishLocation.get(loc).getRight();
    }
    public static int getLocationDisplayRegion(int loc, int region) {
        if(loc == 5 && region == 0) {
            return 0;
        }
        return fishLocation.get(loc).getRight();
    }
    public static void rod() {
        fishRod.put(0, new triple("TBA",0,0));
    }
    public static String getRod(int loc) {
        return fishRod.get(loc).getLeft();
    }
    public static int getRodPrice(int loc) {
        return fishRod.get(loc).getMiddle();
    }
    public static void boat() {
        fishBoat.put(0, new triple("TBA",0,0));
    }
    public static String getBoat(int loc) {
        return fishBoat.get(loc).getLeft();
    }
    public static int getBoatPrice(int loc) {
        return fishBoat.get(loc).getMiddle();
    }
    public static void storage() {
        fishStorage.put(0, new triple("+50% Selling Price",10,1));
        fishStorage.put(1, new triple("+33% Selling Price",25,1));
        fishStorage.put(2, new triple("+50% Selling Price",100,1));
        fishStorage.put(3, new triple("TBA",0,0));
    }
    public static String getStorage(int loc) {
        return fishStorage.get(loc).getLeft();
    }
    public static String getDisplayStorage(int loc, int region) {
        return fishStorage.get(loc).getLeft();
    }
    public static int getStoragePrice(int loc, int region) {
        if(region > getStorageRegion(loc)) {
            return 0;
        }
        return fishStorage.get(loc).getMiddle();
    }
    public static int getStorageDisplayPrice(int loc, int region) {
        if(region > getStorageRegion(loc)) {
            return 0;
        }
        return fishStorage.get(loc).getMiddle();
    }
    public static int getStorageRegion(int loc) {
        return fishStorage.get(loc).getRight();
    }
    public static int getStorageDisplayRegion(int loc, int region) {
        return fishStorage.get(loc).getRight();
    }
    public static void bait() {
        fishBait.put(0, new triple("TBA",0,0));
    }
    public static String getBait(int loc) {
        return fishBait.get(loc).getLeft();
    }
    public static int getBaitPrice(int loc) {
        return fishBait.get(loc).getMiddle();
    }
}
