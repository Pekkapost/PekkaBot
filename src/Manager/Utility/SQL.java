package Manager.Utility;

import java.sql.*;

public class SQL {
    private Connection c = null;
    // DB tables:
    //   Chronos   — Id, Points
    //   WhiteGate — Id, Drawer, Window, Bed, Lake, Plant, Left, Middle, Right,
    //               Boat, Door, Element, Balloon, Well, Varuo (+ *F failure columns)
    //   Ad        — Id, CS5, CS10, CS20, Green, Red
    //   Shion     — count
    public SQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Storage/PekkaBot.db");
            System.out.println("SQLite Connected");
        } catch (Exception e) {
            System.out.println("    SQLite Error " + e);
        }
    }

    private void checkConnection() throws SQLException {
        if (c == null) throw new SQLException("Database connection is not available");
    }

    // --- Points ---
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
            System.out.println("    SQLite: Get Points Error " + e);
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
            String query = exists
                    ? "UPDATE Chronos SET Points = Points + 5 WHERE Id = ?"
                    : "INSERT INTO Chronos (Id, Points) VALUES (?,5)";
            try (PreparedStatement psUpdate = c.prepareStatement(query)) {
                psUpdate.setString(1, id);
                psUpdate.execute();
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update Points Error " + e);
        }
    }

    // --- WhiteGate ---
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
                        System.out.println("    SQLite: Get White Gate Cannot find user");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get White Gate Points Error " + e);
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
            System.out.println("    SQLite: Get White Gate Total Points Error " + e);
        }
        return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    // option must be a known WhiteGate column name — validated by callers in pingWG.java
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
            System.out.println("    SQLite: Update White Gate Error " + e);
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
            System.out.println("    SQLite: Update White Gate Error " + e);
        }
    }

    // --- Ads ---
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
                        System.out.println("    SQLite: Get Ad Cannot find user");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Ad Points Error " + e);
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
            System.out.println("    SQLite: Get Ad Total Points Error " + e);
        }
        return new int[]{0,0,0,0,0};
    }

    // option must be a known Ad column name — validated by callers in pingAd.java
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
            System.out.println("    SQLite: Update Ad Error " + e);
        }
    }

    // --- Shion ---
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
            System.out.println("    SQLite: Update Shion Error " + e);
        }
        return 0;
    }

    // --- Close ---
    public synchronized void close() {
        try {
            if (c != null) c.close();
        } catch (SQLException e) {
            System.out.println("    SQLite: Close Error " + e);
        }
    }
}
