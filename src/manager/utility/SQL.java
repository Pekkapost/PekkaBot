package manager.utility;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Paths;

/**
 * Raw SQLite access layer — the only class that touches JDBC.
 *
 * Schema lives in this file (no migrations) and the bot expects the four
 * tables below to exist. The DB file itself is auto-created on first
 * connect; populating the schema is the operator's responsibility — a
 * one-time {@code sqlite3} step at deploy time.
 *
 * Tables:
 * <pre>
 *   Chronos   — Id, Points
 *   WhiteGate — Id, Drawer, Window, Bed, Lake, Plant, Left, Middle, Right,
 *               Boat, Door, Element, Balloon, Well, Varuo (+ *F failure columns)
 *   Ad        — Id, CS5, CS10, CS20, Green, Red
 *   Shion     — count
 * </pre>
 *
 * Higher-level callers go through {@link manager.SQLManager}, which holds
 * the singleton {@link SQL} instance. Every public method here is
 * {@code synchronized} so JDA listener threads can't collide — SQLite is
 * single-writer, so the monitor is the cheapest correct lock.
 *
 * Crash-safety is provided by SQLite's WAL; this layer deliberately does
 * not apply the tmp+rename pattern from {@link DESIGN.md} §15, which
 * would corrupt the WAL.
 */
public class SQL {
    private static final Logger logger = LoggerFactory.getLogger(SQL.class);
    private Connection c = null;

    public SQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Paths.dataPath("PekkaBot.db"));
            logger.info("SQLite connected");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("SQLite connection error", e);
        }
    }

    private void checkConnection() throws SQLException {
        if (c == null) throw new SQLException("Database connection is not available");
    }

    // -----------------------------------------------------------------------
    // Points (Chronos table)
    // -----------------------------------------------------------------------

    public synchronized int getPoints(String id) {
        try {
            checkConnection();
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Chronos WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getInt("Points");
                }
            }
        } catch (SQLException e) {
            logger.error("SQLite getPoints error", e);
        }
        return 0;
    }

    public synchronized void updatePoints(String id) {
        try {
            checkConnection();
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Chronos WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            // 5 points per qualifying message — the Chronos economy is balanced
            // around this rate. Changing it would inflate every historical row.
            String query = exists
                    ? "UPDATE Chronos SET Points = Points + 5 WHERE Id = ?"
                    : "INSERT INTO Chronos (Id, Points) VALUES (?,5)";
            try (PreparedStatement psUpdate = c.prepareStatement(query)) {
                psUpdate.setString(1, id);
                psUpdate.execute();
            }
        } catch (SQLException e) {
            logger.error("SQLite updatePoints error", e);
        }
    }

    // -----------------------------------------------------------------------
    // WhiteGate (per-user + totals)
    // -----------------------------------------------------------------------

    public synchronized int[] getWhiteGate(String id) {
        try {
            checkConnection();
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM WhiteGate WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new int[]{
                            rs.getInt("Drawer"),   rs.getInt("Window"),   rs.getInt("Bed"),
                            rs.getInt("Lake"),     rs.getInt("Plant"),
                            rs.getInt("Left"),     rs.getInt("Middle"),   rs.getInt("Right"),
                            rs.getInt("Boat"),     rs.getInt("Door"),
                            rs.getInt("Element"),  rs.getInt("Balloon"),  rs.getInt("Well"),  rs.getInt("Varuo"),
                            rs.getInt("DrawerF"),  rs.getInt("WindowF"),  rs.getInt("BedF"),
                            rs.getInt("LakeF"),    rs.getInt("PlantF"),
                            rs.getInt("LeftF"),    rs.getInt("MiddleF"),  rs.getInt("RightF"),
                            rs.getInt("BoatF"),    rs.getInt("DoorF"),
                            rs.getInt("ElementF"), rs.getInt("BalloonF"), rs.getInt("WellF")
                        };
                    } else {
                        logger.warn("SQLite getWhiteGate: user not found");
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLite getWhiteGate error", e);
        }
        return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    public synchronized int[] getTotalWhiteGate() {
        try {
            checkConnection();
            final String query =
                "SELECT SUM(Drawer) as 'Drawer', SUM(Window) as 'Window', SUM(Bed) as 'Bed'," +
                "SUM(Lake) as 'Lake', SUM(Plant) as 'Plant', SUM(Left) as 'Left', SUM(Middle) as 'Middle', SUM(Right) as 'Right'," +
                "SUM(Boat) as 'Boat', SUM(Door) as 'Door', SUM(Element) as 'Element', SUM(Balloon) as 'Balloon'," +
                "SUM(Well) as 'Well', SUM(Varuo) as 'Varuo', SUM(DrawerF) as 'DrawerF', SUM(WindowF) as 'WindowF'," +
                "SUM(BedF) as 'BedF', SUM(LakeF) as 'LakeF', SUM(PlantF) as 'PlantF', SUM(LeftF) as 'LeftF'," +
                "SUM(MiddleF) as 'MiddleF', SUM(RightF) as 'RightF', SUM(BoatF) as 'BoatF', SUM(DoorF) as 'DoorF'," +
                "SUM(ElementF) as 'ElementF', SUM(BalloonF) as 'BalloonF', SUM(WellF) as 'WellF' FROM WhiteGate";
            try (PreparedStatement ps = c.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new int[]{
                        rs.getInt("Drawer"),   rs.getInt("Window"),   rs.getInt("Bed"),
                        rs.getInt("Lake"),     rs.getInt("Plant"),
                        rs.getInt("Left"),     rs.getInt("Middle"),   rs.getInt("Right"),
                        rs.getInt("Boat"),     rs.getInt("Door"),
                        rs.getInt("Element"),  rs.getInt("Balloon"),  rs.getInt("Well"),  rs.getInt("Varuo"),
                        rs.getInt("DrawerF"),  rs.getInt("WindowF"),  rs.getInt("BedF"),
                        rs.getInt("LakeF"),    rs.getInt("PlantF"),
                        rs.getInt("LeftF"),    rs.getInt("MiddleF"),  rs.getInt("RightF"),
                        rs.getInt("BoatF"),    rs.getInt("DoorF"),
                        rs.getInt("ElementF"), rs.getInt("BalloonF"), rs.getInt("WellF")
                    };
                }
            }
        } catch (SQLException e) {
            logger.error("SQLite getTotalWhiteGate error", e);
        }
        return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    // option must be a known WhiteGate column name — validated by callers in PingWG.java
    public synchronized void updateWhiteGate(String id, String option) {
        try {
            checkConnection();
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM WhiteGate WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            String query = exists
                    ? "UPDATE WhiteGate SET " + option + " = " + option + " + 1 WHERE Id = ?"
                    : "INSERT INTO WhiteGate (Id," + option + ") VALUES (?,1)";
            try (PreparedStatement psInsert = c.prepareStatement(query)) {
                psInsert.setString(1, id);
                psInsert.execute();
            }
        } catch (SQLException e) {
            logger.error("SQLite updateWhiteGate error", e);
        }
    }

    // option must be a known WhiteGate column name — validated by callers
    public synchronized void updateWhiteGateNumber(String id, String option, int num) {
        try {
            checkConnection();
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM WhiteGate WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psInsert = c.prepareStatement(
                        "INSERT INTO WhiteGate (Id, " + option + ") VALUES (?,?)")) {
                    psInsert.setString(1, id);
                    psInsert.setString(2, String.valueOf(num));
                    psInsert.execute();
                }
            } else {
                try (PreparedStatement psInsert = c.prepareStatement(
                        "UPDATE WhiteGate SET " + option + " = ? WHERE Id = ?")) {
                    psInsert.setString(1, String.valueOf(num));
                    psInsert.setString(2, id);
                    psInsert.execute();
                }
            }
        } catch (SQLException e) {
            logger.error("SQLite updateWhiteGate error", e);
        }
    }

    // -----------------------------------------------------------------------
    // Ads (per-user + totals)
    // -----------------------------------------------------------------------

    public synchronized int[] getAd(String id) {
        try {
            checkConnection();
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Ad WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new int[]{
                            rs.getInt("CS5"), rs.getInt("CS10"), rs.getInt("CS20"),
                            rs.getInt("Green"), rs.getInt("Red")
                        };
                    } else {
                        logger.warn("SQLite getAd: user not found");
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLite getAd error", e);
        }
        return new int[]{0,0,0,0,0};
    }

    public synchronized int[] getAdTotal() {
        try {
            checkConnection();
            final String query =
                "SELECT SUM(CS5) as 'CS5', SUM(CS10) as 'CS10', SUM(CS20) as 'CS20'," +
                "SUM(Red) as 'Red', SUM(Green) as 'Green' FROM Ad";
            try (PreparedStatement ps = c.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new int[]{
                        rs.getInt("CS5"), rs.getInt("CS10"), rs.getInt("CS20"),
                        rs.getInt("Green"), rs.getInt("Red")
                    };
                }
            }
        } catch (SQLException e) {
            logger.error("SQLite getAdTotal error", e);
        }
        return new int[]{0,0,0,0,0};
    }

    // option must be a known Ad column name — validated by callers in PingAd.java
    public synchronized void updateAd(String id, String option) {
        try {
            checkConnection();
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Ad WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            String query = exists
                    ? "UPDATE Ad SET " + option + " = " + option + " + 1 WHERE Id = ?"
                    : "INSERT INTO Ad (Id," + option + ") VALUES (?,1)";
            try (PreparedStatement psInsert = c.prepareStatement(query)) {
                psInsert.setString(1, id);
                psInsert.execute();
            }
        } catch (SQLException e) {
            logger.error("SQLite updateAd error", e);
        }
    }

    // -----------------------------------------------------------------------
    // Shion (single-row counter)
    // -----------------------------------------------------------------------

    // Increments the Shion counter atomically and returns the new (post-increment) value.
    public synchronized int updateShion() {
        try {
            checkConnection();
            c.setAutoCommit(false);
            try {
                int count;
                try (PreparedStatement ps = c.prepareStatement("SELECT count FROM Shion");
                     ResultSet rs = ps.executeQuery()) {
                    count = rs.next() ? rs.getInt("count") : 0;
                }
                try (PreparedStatement psUpdate = c.prepareStatement("UPDATE Shion SET count = count + 1")) {
                    psUpdate.execute();
                }
                c.commit();
                return count + 1;
            } catch (SQLException e) {
                c.rollback();
                throw e;
            } finally {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("SQLite updateShion error", e);
        }
        return 0;
    }

    // -----------------------------------------------------------------------
    // Lifecycle
    // -----------------------------------------------------------------------

    public synchronized void close() {
        try {
            if (c != null) c.close();
        } catch (SQLException e) {
            logger.error("SQLite close error", e);
        }
    }
}
