package Manager;

import Manager.Utility.SQL;

public class SQLManager {
    static SQL sql = new SQL();

    public static int getPoints(String id)      { return sql.getPoints(id); }
    public static void updatePoints(String id)  { sql.updatePoints(id); }

    // --- WhiteGate ---
    public static int[] getWhiteGate(String id)                                    { return sql.getWhiteGate(id); }
    public static int[] getTotalWhiteGate()                                        { return sql.getTotalWhiteGate(); }
    public static void updateWhiteGate(String id, String option)                   { sql.updateWhiteGate(id, option); }
    public static void updateWhiteGateNumber(String id, String option, int num)    { sql.updateWhiteGateNumber(id, option, num); }

    // --- Ad ---
    public static int[] getAd(String id)                 { return sql.getAd(id); }
    public static int[] getTotalAd()                     { return sql.getAdTotal(); }
    public static void updateAd(String id, String option){ sql.updateAd(id, option); }

    // --- Shion ---
    public static int updateShion() { return sql.updateShion(); }

    public static void close() { sql.close(); }
}
