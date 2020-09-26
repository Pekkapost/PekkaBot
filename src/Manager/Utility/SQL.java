package Manager.Utility;

import Commands.Currency.Fishing.Utility.fishManager;
import Discord.DiscordManager;
import Manager.EmbedManager;
import net.dv8tion.jda.api.EmbedBuilder;

import java.sql.*;

public class SQL {
    Connection c = null;
    public SQL() {
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Storage/PekkaBot.db");
            //Points - Id, Points
            //White Gate - Id, Drawer, Window, Bed, Lake, Plant, Left, Middle, Right, Boat, Door, Element, Balloon, Well, Varuo
            //Fishing - Id, Points, Location
            //Fishgrade - Id, Location, Rod, Boat, Storage, Bait
            System.out.println("SQLite Connected");
        } catch(Exception e) {
            System.out.println("    SQLite Error " + e);
        }
    }
    //--------------------
    //Points
    //--------------------
    //--------------------
    public int getPoints(String id) {
        try {
            final String queryCheck = "SELECT * FROM Chronos WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getInt("Points");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get Points Error " + e);
        }
        return 0;
    }
    public void updatePoints(String id) {
        try {
            final String queryCheck = "SELECT * FROM Chronos WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            final String query;
            if(rs.isClosed()) {
                query = "INSERT INTO Chronos (Id, Points) VALUES (?,5)";
            } else {
                query = "UPDATE Chronos SET Points = Points + 5 Where Id = ?";
            }
            final PreparedStatement psUpdate = c.prepareStatement(query);
            psUpdate.setString(1, id);
            psUpdate.execute();
        } catch(SQLException e) {
            System.out.println("    SQLite: Update Points Error " + e);
        }
    }
    //--------------------
    //WhiteGate
    //--------------------
    //--------------------
    public int[] getWhiteGate(String id){
        try {
            final String queryCheck = "SELECT * FROM WhiteGate WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                int drawer = rs.getInt("Drawer");
                int window = rs.getInt("Window");
                int bed = rs.getInt("Bed");
                int lake = rs.getInt("Lake");
                int plant = rs.getInt("Plant");
                int left = rs.getInt("Left");
                int middle = rs.getInt("Middle");
                int right = rs.getInt("Right");
                int boat = rs.getInt("Boat");
                int door = rs.getInt("Door");
                int element = rs.getInt("Element");
                int balloon = rs.getInt("Balloon");
                int well = rs.getInt("Well");
                int varuo = rs.getInt("Varuo");
                int drawerF = rs.getInt("DrawerF");
                int windowF = rs.getInt("WindowF");
                int bedF = rs.getInt("BedF");
                int lakeF = rs.getInt("LakeF");
                int plantF = rs.getInt("PlantF");
                int leftF = rs.getInt("LeftF");
                int middleF = rs.getInt("MiddleF");
                int rightF = rs.getInt("RightF");
                int boatF = rs.getInt("BoatF");
                int doorF = rs.getInt("DoorF");
                int elementF = rs.getInt("ElementF");
                int balloonF = rs.getInt("BalloonF");
                int wellF = rs.getInt("WellF");
                return new int[]{drawer,window,bed,lake,plant,left,middle,right,boat,door,element,balloon,well,varuo,
                        drawerF,windowF,bedF,lakeF,plantF,leftF,middleF,rightF,boatF,doorF,elementF,balloonF,wellF};
            } else {
                System.out.println("    SQLite: Get White Gate Cant find user");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get White Gate Points Error " + e);
        }
        return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }
    public int[] getTotalWhiteGate(){
        try {
            final String queryCheck = "SELECT SUM(Drawer) as 'Drawer', SUM(Window) as 'Window', SUM(Bed) as 'Bed'," +
                    "SUM(Lake) as 'Lake', SUM(Plant) as 'Plant', SUM(Left) as 'Left', SUM(Middle) as 'Middle', SUM(Right) as 'Right'," +
                    "SUM(Boat) as 'Boat', SUM(Door) as 'Door', SUM(Element) as 'Element', SUM(Balloon) as 'Balloon'," +
                    "SUM(Well) as 'Well', SUM(Varuo) as 'Varuo', SUM(DrawerF) as 'DrawerF', SUM(WindowF) as 'WindowF'," +
                    "SUM(BedF) as 'BedF', SUM(LakeF) as 'LakeF', SUM(PlantF) as 'PlantF', SUM(LeftF) as 'LeftF'," +
                    "SUM(MiddleF) as 'MiddleF', SUM(RightF) as 'RightF', SUM(BoatF) as 'BoatF', SUM(DoorF) as 'DoorF'," +
                    "SUM(ElementF) as 'ElementF', SUM(BalloonF) as 'BalloonF', SUM(WellF) as 'WellF' FROM WhiteGate";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            int drawer = 0, window = 0, bed = 0, lake = 0, plant = 0, left = 0, middle = 0, right = 0, boat = 0,
                    door = 0, element = 0, balloon = 0, well = 0, varuo = 0,
                    drawerF = 0, windowF = 0, bedF = 0, lakeF = 0, plantF = 0, leftF = 0, middleF = 0, rightF = 0, boatF = 0,
                    doorF = 0, elementF = 0, balloonF = 0, wellF = 0;
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                drawer = rs.getInt("Drawer");
                window = rs.getInt("Window");
                bed = rs.getInt("Bed");
                lake = rs.getInt("Lake");
                plant = rs.getInt("Plant");
                left = rs.getInt("Left");
                middle = rs.getInt("Middle");
                right = rs.getInt("Right");
                boat = rs.getInt("Boat");
                door = rs.getInt("Door");
                element = rs.getInt("Element");
                balloon = rs.getInt("Balloon");
                well = rs.getInt("Well");
                varuo = rs.getInt("Varuo");
                drawerF = rs.getInt("DrawerF");
                windowF = rs.getInt("WindowF");
                bedF = rs.getInt("BedF");
                lakeF = rs.getInt("LakeF");
                plantF = rs.getInt("PlantF");
                leftF = rs.getInt("LeftF");
                middleF = rs.getInt("MiddleF");
                rightF = rs.getInt("RightF");
                boatF = rs.getInt("BoatF");
                doorF = rs.getInt("DoorF");
                elementF = rs.getInt("ElementF");
                balloonF = rs.getInt("BalloonF");
                wellF = rs.getInt("WellF");
            }
            return new int[]{drawer,window,bed,lake,plant,left,middle,right,boat,door,element,balloon,well,varuo,
                    drawerF,windowF,bedF,lakeF,plantF,leftF,middleF,rightF,boatF,doorF,elementF,balloonF,wellF};
        } catch(SQLException e) {
            System.out.println("    SQLite: Get White Gate Points Error " + e);
        }
        return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }
    public void updateWhiteGate(String id, String option){
        try {
            final String queryCheck = "SELECT * FROM WhiteGate WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            final String query;
            if(rs.isClosed()) {
                query = "INSERT INTO WhiteGate (Id," +
                        option + ") VALUES (?,1)";
            } else {
                query = "UPDATE WhiteGate SET " +
                        option + " = " + option + " + 1 WHERE Id = ?";
            }
            final PreparedStatement psInsert = c.prepareStatement(query);
            psInsert.setString(1, id);
            psInsert.execute();
        } catch(SQLException e) {
            System.out.println("    SQLite: Update White Gate Error " + e);
        }
    }
    public void updateWhiteGateNumber(String id, String option,int num){
        try {
            final String queryCheck = "SELECT * FROM WhiteGate WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            final String query;
            if(rs.isClosed()) {
                query = "INSERT INTO WhiteGate (Id, " +
                        option + ")" + " VALUES (?,?)";
                final PreparedStatement psInsert = c.prepareStatement(query);
                psInsert.setString(1, id);
                psInsert.setString(2, String.valueOf(num));
                psInsert.execute();
            } else {
                query = "UPDATE WhiteGate SET " +
                        option + " = ? WHERE Id = ?";
                final PreparedStatement psInsert = c.prepareStatement(query);
                psInsert.setString(1, String.valueOf(num));
                psInsert.setString(2, id);
                psInsert.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update White Gate Error " + e);
        }
    }
    //--------------------
    //Fishing
    //--------------------
    //--------------------
    public int getRegion(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getInt("Region");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return 0;
    }
    public void updateRegion(String id, int region) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                final String queryUpdate = "UPDATE Fishing SET Region = ? Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, String.valueOf(region));
                psUpdate.setString(2, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }
    public int getFishing(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getInt("Points");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return 0;
    }
    public void updateFishing(String id, int Points) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) {
                final String queryInsert = "INSERT INTO Fishing (Id, Points) VALUES (?,?)";
                final PreparedStatement psUpdate = c.prepareStatement(queryInsert);
                psUpdate.setString(1, id);
                psUpdate.setString(2, String.valueOf(Points));
                psUpdate.execute();
            } else {
                final String queryUpdate = "UPDATE Fishing SET Points = Points + ? Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, String.valueOf(Points));
                psUpdate.setString(2, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }
    public void decreaseFishing(String id, int Points) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) {
                final String queryInsert = "INSERT INTO Fishing (Id, Points) VALUES (?,?)";
                final PreparedStatement psUpdate = c.prepareStatement(queryInsert);
                psUpdate.setString(1, id);
                psUpdate.setString(2, String.valueOf(Points));
                psUpdate.execute();
            } else {
                final String queryUpdate = "UPDATE Fishing SET Points = Points - ? Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, String.valueOf(Points));
                psUpdate.setString(2, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }
    //Points 2
    public int getFishing2(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getInt("Points2");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return 0;
    }
    public void updateFishing2(String id, int Points) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                final String queryUpdate = "UPDATE Fishing SET Points2 = Points2 + ? Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, String.valueOf(Points));
                psUpdate.setString(2, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update FishPoints2 Error " + e);
        }
    }
    public void decreaseFishing2(String id, int Points) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                final String queryUpdate = "UPDATE Fishing SET Points2 = Points2 - ? Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, String.valueOf(Points));
                psUpdate.setString(2, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update FishPoints2 Error " + e);
        }
    }
    public String getLocation(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getString("Location");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get FishPoints Error");
        }
        return "Kira Beach";
    }
    public void updateLocation(String id, String location) {
        try {
            final String queryCheck = "SELECT * FROM Fishing WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) {
                final String queryInsert = "INSERT INTO Fishing (Id, Location) VALUES (?,?)";
                final PreparedStatement psUpdate = c.prepareStatement(queryInsert);
                psUpdate.setString(1, id);
                psUpdate.setString(2, "Kira Beach");
                psUpdate.execute();
            } else {
                final String queryUpdate = "UPDATE Fishing SET Location = ? Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, location);
                psUpdate.setString(2, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update FishPoints Error " + e);
        }
    }
    public String leaderboard(String id) {
        String output = "";
        try {
            final String queryCheck = "SELECT * FROM Fishing ORDER BY Region DESC, Points2 DESC, Points DESC";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ResultSet rs = ps.executeQuery();
            int region, points, rank = 0;
            String name, temp;
            while(rs.next()){
                temp = rs.getString("Id");
                region = rs.getInt("Region");
                if((region + 1) >= 2) {
                    points = rs.getInt("Points" + (region + 1));
                } else {
                    points = rs.getInt("Points");
                }
                rank++;
                if(temp.equals(id)) {
                    output += "**";
                }
                name = DiscordManager.getUserName(rs.getString("Id"));
                output += rank + ". " + name + " has " + points + fishManager.getCurrency(region) + "\n";
                if(temp.equals(id)) {
                    output += "**";
                }
                if(rank == 10) {
                    break;
                }
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get Leaderboard Error " + e);
        }
        return output;
    }
    public String myLeaderboard(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishing ORDER BY Region DESC, Points2 DESC, Points DESC";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ResultSet rs = ps.executeQuery();
            int region, points, rank = 0;
            String name, temp;
            while(rs.next()){
                rank++;
                temp = rs.getString("Id");
                if(id.equals(temp)) {
                    region = rs.getInt("Region");
                    if((region + 1) >= 2) {
                        points = rs.getInt("Points" + (region + 1));
                    } else {
                        points = rs.getInt("Points");
                    }
                    name = DiscordManager.getUserName(rs.getString("Id"));
                    return "**" + rank + ". " + name + " has " + points + fishManager.getCurrency(region) + "**\n";
                }
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get My Leaderboard Error " + e);
        }
        return "";
    }
    //--------------------
    //Fishgrade
    //--------------------
    //--------------------
    public int getUpgradeLocation(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishgrade WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getInt("Location");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get Location Error");
        }
        return 0;
    }
    public void updateUpgradeLocation(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishgrade WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) {
                final String queryInsert = "INSERT INTO Fishgrade (Id, Location) VALUES (?,1)";
                final PreparedStatement psUpdate = c.prepareStatement(queryInsert);
                psUpdate.setString(1, id);
                psUpdate.execute();
            } else {
                final String queryUpdate = "UPDATE Fishgrade SET Location = Location + 1 Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update Location Error " + e);
        }
    }
    public int getUpgradeStorage(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishgrade WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                return rs.getInt("Storage");
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get Location Error");
        }
        return 0;
    }
    public void updateUpgradeStorage(String id) {
        try {
            final String queryCheck = "SELECT * FROM Fishgrade WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) {
                final String queryInsert = "INSERT INTO Fishgrade (Id, Storage) VALUES (?,1)";
                final PreparedStatement psUpdate = c.prepareStatement(queryInsert);
                psUpdate.setString(1, id);
                psUpdate.execute();
            } else {
                final String queryUpdate = "UPDATE Fishgrade SET Storage = Storage + 1 Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update Location Error " + e);
        }
    }
    //--------------------
    //Pekkadex
    //--------------------
    //--------------------
    public void updatePekkadex(String id, String fish) {
        try {
            final String queryCheck = "SELECT * FROM Pekkadex WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) {
                final String queryInsert = "INSERT INTO Pekkadex (Id) VALUES (?)";
                final PreparedStatement psUpdate = c.prepareStatement(queryInsert);
                psUpdate.setString(1, id);
                psUpdate.execute();
            } else {
                final String queryUpdate = "UPDATE Pekkadex SET " + fish + " = " + fish + "  + 1 Where Id = ?";
                final PreparedStatement psUpdate = c.prepareStatement(queryUpdate);
                psUpdate.setString(1, id);
                psUpdate.execute();
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Update Location Error " + e);
        }
    }
    public int getFish(String id, String fish) {
        try {
            final String queryCheck = "SELECT * FROM Pekkadex WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                int temp = rs.getInt(fish);
                if(temp > 0) {
                    return temp;
                }
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get Fish Error " + e);
        }
        return 0;
    }
    public boolean ownedFish(String id, String fish) {
        try {
            final String queryCheck = "SELECT * FROM Pekkadex WHERE Id = ?";
            final PreparedStatement ps = c.prepareStatement(queryCheck);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.isClosed()) {
                int temp = rs.getInt(fish);
                if(temp > 0) {
                    return true;
                }
            }
        } catch(SQLException e) {
            System.out.println("    SQLite: Get Fish Error " + e);
        }
        return false;
    }
    //--------------------
    //Close
    //--------------------
    //--------------------
    public void close() {
        try {
            c.close();
        } catch (SQLException e){
            System.out.println("    SQLite: Close Error " + e);
        }
    }
}
