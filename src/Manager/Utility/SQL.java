package Manager.Utility;

import Commands.Currency.Fishing.Utility.fishManager;
import Discord.DiscordManager;

import java.sql.*;

public class SQL {
    Connection c = null;
    // DB tables:
    //   Chronos   — Id, Points
    //   WhiteGate — Id, Drawer, Window, Bed, Lake, Plant, Left, Middle, Right,
    //               Boat, Door, Element, Balloon, Well, Varuo (+ *F failure columns)
    //   Ad        — Id, CS5, CS10, CS20, Green, Red
    //   Fishing   — Id, Points, Points2, Location, Region
    //   Fishgrade — Id, Location, Rod, Boat, Storage, Bait
    //   Pekkadex  — Id, <one column per fish name>
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

    // --- Points ---
    public int getPoints(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Chronos WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Points");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Points Error " + e);
        }
        return 0;
    }

    public void updatePoints(String id) {
        try {
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
    public int[] getWhiteGate(String id) {
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
        } catch (SQLException e) {
            System.out.println("    SQLite: Get White Gate Points Error " + e);
        }
        return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    public int[] getTotalWhiteGate() {
        try {
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

    public void updateWhiteGate(String id, String option) {
        try {
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

    public void updateWhiteGateNumber(String id, String option, int num) {
        try {
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
    public int[] getAd(String id) {
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
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Ad Points Error " + e);
        }
        return new int[]{0,0,0,0,0};
    }

    public int[] getAdTotal() {
        try {
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

    public void updateAd(String id, String option) {
        try {
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

    // --- Fishing ---
    public int getRegion(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Region");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return 0;
    }

    public void updateRegion(String id, int region) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishing SET Region = ? WHERE Id = ?")) {
                    psUpdate.setString(1, String.valueOf(region));
                    psUpdate.setString(2, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }

    public int getFishing(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Points");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return 0;
    }

    public void updateFishing(String id, int points) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "INSERT INTO Fishing (Id, Points) VALUES (?,?)")) {
                    psUpdate.setString(1, id);
                    psUpdate.setString(2, String.valueOf(points));
                    psUpdate.execute();
                }
            } else {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishing SET Points = Points + ? WHERE Id = ?")) {
                    psUpdate.setString(1, String.valueOf(points));
                    psUpdate.setString(2, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }

    public void decreaseFishing(String id, int points) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "INSERT INTO Fishing (Id, Points) VALUES (?,?)")) {
                    psUpdate.setString(1, id);
                    psUpdate.setString(2, String.valueOf(points));
                    psUpdate.execute();
                }
            } else {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishing SET Points = Points - ? WHERE Id = ?")) {
                    psUpdate.setString(1, String.valueOf(points));
                    psUpdate.setString(2, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }

    public int getFishing2(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Points2");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return 0;
    }

    public void updateFishing2(String id, int points) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishing SET Points2 = Points2 + ? WHERE Id = ?")) {
                    psUpdate.setString(1, String.valueOf(points));
                    psUpdate.setString(2, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update FishPoints2 Error " + e);
        }
    }

    public void decreaseFishing2(String id, int points) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishing SET Points2 = Points2 - ? WHERE Id = ?")) {
                    psUpdate.setString(1, String.valueOf(points));
                    psUpdate.setString(2, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update FishPoints2 Error " + e);
        }
    }

    public String getLocation(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("Location");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return "Kira Beach";
    }

    public void updateLocation(String id, String location) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishing WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "INSERT INTO Fishing (Id, Location) VALUES (?,?)")) {
                    psUpdate.setString(1, id);
                    psUpdate.setString(2, "Kira Beach");
                    psUpdate.execute();
                }
            } else {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishing SET Location = ? WHERE Id = ?")) {
                    psUpdate.setString(1, location);
                    psUpdate.setString(2, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }

    public String leaderboard(String id) {
        String output = "";
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM Fishing ORDER BY Region DESC, Points2 DESC, Points DESC");
             ResultSet rs = ps.executeQuery()) {
            int rank = 0;
            while (rs.next()) {
                String temp = rs.getString("Id");
                int region = rs.getInt("Region");
                int points = (region + 1) >= 2 ? rs.getInt("Points" + (region + 1)) : rs.getInt("Points");
                rank++;
                if (temp.equals(id)) output += "**";
                output += rank + ". " + DiscordManager.getUserName(temp) + " has " + points + fishManager.getCurrency(region) + "\n";
                if (temp.equals(id)) output += "**";
                if (rank == 10) break;
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Leaderboard Error " + e);
        }
        return output;
    }

    public String myLeaderboard(String id) {
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM Fishing ORDER BY Region DESC, Points2 DESC, Points DESC");
             ResultSet rs = ps.executeQuery()) {
            int rank = 0;
            while (rs.next()) {
                rank++;
                String temp = rs.getString("Id");
                if (id.equals(temp)) {
                    int region = rs.getInt("Region");
                    int points = (region + 1) >= 2 ? rs.getInt("Points" + (region + 1)) : rs.getInt("Points");
                    return "**" + rank + ". " + DiscordManager.getUserName(temp) + " has " + points + fishManager.getCurrency(region) + "**\n";
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get My Leaderboard Error " + e);
        }
        return "";
    }

    // --- Fishgrade ---
    public int getUpgradeLocation(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishgrade WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Location");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Location Error");
        }
        return 0;
    }

    public void updateUpgradeLocation(String id) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishgrade WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "INSERT INTO Fishgrade (Id, Location) VALUES (?,1)")) {
                    psUpdate.setString(1, id);
                    psUpdate.execute();
                }
            } else {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishgrade SET Location = Location + 1 WHERE Id = ?")) {
                    psUpdate.setString(1, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update Location Error " + e);
        }
    }

    public int getUpgradeStorage(String id) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishgrade WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Storage");
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Location Error");
        }
        return 0;
    }

    public void updateUpgradeStorage(String id) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Fishgrade WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "INSERT INTO Fishgrade (Id, Storage) VALUES (?,1)")) {
                    psUpdate.setString(1, id);
                    psUpdate.execute();
                }
            } else {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Fishgrade SET Storage = Storage + 1 WHERE Id = ?")) {
                    psUpdate.setString(1, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update Location Error " + e);
        }
    }

    // --- Pekkadex ---
    public void updatePekkadex(String id, String fish) {
        try {
            boolean exists;
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Pekkadex WHERE Id = ?")) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) { exists = rs.next(); }
            }
            if (!exists) {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "INSERT INTO Pekkadex (Id) VALUES (?)")) {
                    psUpdate.setString(1, id);
                    psUpdate.execute();
                }
            } else {
                try (PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE Pekkadex SET " + fish + " = " + fish + " + 1 WHERE Id = ?")) {
                    psUpdate.setString(1, id);
                    psUpdate.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Update Pekkadex Error " + e);
        }
    }

    public int getFish(String id, String fish) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Pekkadex WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int temp = rs.getInt(fish);
                    if (temp > 0) return temp;
                }
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Fish Error " + e);
        }
        return 0;
    }

    public boolean ownedFish(String id, String fish) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM Pekkadex WHERE Id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(fish) > 0;
            }
        } catch (SQLException e) {
            System.out.println("    SQLite: Get Fish Error " + e);
        }
        return false;
    }

    // --- Shion ---
    public int updateShion() {
        try {
            int count;
            try (PreparedStatement ps = c.prepareStatement("SELECT count FROM Shion");
                 ResultSet rs = ps.executeQuery()) {
                count = rs.next() ? rs.getInt("count") : 0;
            }
            try (PreparedStatement psUpdate = c.prepareStatement("UPDATE Shion SET count = count + 1")) {
                psUpdate.execute();
            }
            return count;
        } catch (SQLException e) {
            System.out.println("    SQLite: Update Shion Error " + e);
        }
        return 0;
    }

    // --- Close ---
    public void close() {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("    SQLite: Close Error " + e);
        }
    }
}
