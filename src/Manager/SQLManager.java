package Manager;

import Manager.Utility.SQL;

public class SQLManager {
    static SQL sql = new SQL();

    public static int getPoints(String id)      { return sql.getPoints(id); }
    public static void updatePoints(String id)  { sql.updatePoints(id); }

    // --- WhiteGate ---
    public static int[] getWhiteGate(String id)                          { return sql.getWhiteGate(id); }
    public static int[] getTotalWhiteGate()                              { return sql.getTotalWhiteGate(); }
    public static void updateWhiteGate(String id, String option)         { sql.updateWhiteGate(id, option); }
    public static void updateWhiteGateNumber(String id, String option, int num) { sql.updateWhiteGateNumber(id, option, num); }

    // --- Ad ---
    public static int[] getAd(String id)            { return sql.getAd(id); }
    public static int[] getTotalAd()                { return sql.getAdTotal(); }
    public static void updateAd(String id, String option) { sql.updateAd(id, option); }

    // --- Fishing ---
    public static int getRegion(String id)           { return sql.getRegion(id); }
    public static void updateRegion(String id, int region) { sql.updateRegion(id, region); }

    public static int getFishing(String id, int region) {
        return region == 1 ? getFishing2(id) : getFishing(id);
    }
    public static int getFishing(String id)          { return sql.getFishing(id); }
    public static int getFishing2(String id)         { return sql.getFishing2(id); }

    public static void updateFishing(String id, int points, int region) {
        if (getFishing(id) + points < 100000000) {
            if (region == 1) sql.updateFishing2(id, points);
            else             sql.updateFishing(id, points);
        }
    }
    public static void decreaseFishing(String id, int points, int region) {
        if (region == 1) sql.decreaseFishing2(id, points);
        else             sql.decreaseFishing(id, points);
    }

    public static String getLocation(String id)                  { return sql.getLocation(id); }
    public static void updateLocation(String id, String location) { sql.updateLocation(id, location); }
    public static String leaderboard(String id)                  { return sql.leaderboard(id); }
    public static String myLeaderboard(String id)                { return sql.myLeaderboard(id); }
    public static void close()                                   { sql.close(); }

    // --- Fishgrade ---
    public static int getUpgradeLocation(String id)    { return sql.getUpgradeLocation(id); }
    public static void updateUpgradeLocation(String id){ sql.updateUpgradeLocation(id); }
    public static int getUpgradeStorage(String id)     { return sql.getUpgradeStorage(id); }
    public static void updateUpgradeStorage(String id) { sql.updateUpgradeStorage(id); }

    // --- Pekkadex ---
    public static void updatePekkadex(String id, String fish) { sql.updatePekkadex(id, fish); }
    public static int getFish(String id, String fish)         { return sql.getFish(id, fish); }
    public static boolean ownedFish(String id, String fish)   { return sql.ownedFish(id, fish); }

    // --- Shion ---
    public static int updateShion() { return sql.updateShion(); }
}
