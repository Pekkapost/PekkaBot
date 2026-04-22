package Commands.Gacha.Utility;

import Structures.weightedRandomBag;

import java.io.*;

// Reads and writes Storage/GachaList.txt.
// File format per banner:
//   Banner N                 : Banner Name
//   CharacterName|3star%|4star%|5star%
//   (blank line separates banners; 10th-pull rates use parentheses: e.g. 75(90)%)
public class gachaRead {
    public static String checkList() {
        String output = "```Apache\n" +
                "[Banner Number]   : [Banner Name]";
        try {
            File inputFile = new File("Storage/GachaList.txt");

            if (inputFile.length() != 0) {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String currentLine;
                    while ((currentLine = reader.readLine()) != null) {
                        if (currentLine.startsWith("Banner ")) {
                            output += "\n" + currentLine.substring(7);
                        }
                    }
                }
            }
        }catch (IOException e){
            System.out.println("     Error: GachaRead IOException");
        }
        output += "\nDefault           : 5* Banner```";
        return output;
    }
    public static void updateBanners(int num, weightedRandomBag<String> bag, weightedRandomBag bag2) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Storage/GachaList.txt"))) {
            bag.purge();
            bag2.purge();
            String currentline;
            boolean flip = false;
            while ((currentline = reader.readLine()) != null) {
                if(currentline.startsWith("Banner ")) {
                    if(Integer.parseInt(currentline.substring(7,8)) == num) {
                        flip = true;
                        continue;
                    }
                    flip = false;
                }
                if(flip) {
                    if(currentline.equals("")) {
                        break;
                    }
                    String name = currentline.substring(0,currentline.indexOf("|"));
                    currentline = currentline.substring(currentline.indexOf("|")+1);
                    String star3 = currentline.substring(0,currentline.indexOf("|")).replace("%", "");
                    currentline = currentline.substring(currentline.indexOf("|")+1);
                    String star4 = currentline.substring(0,currentline.indexOf("|")).replace("%", "");
                    currentline = currentline.substring(currentline.indexOf("|")+1);
                    String star5 = currentline.substring(0,currentline.indexOf("|")).replace("%", "");

                    double value = Double.parseDouble(star3);
                    bag.addEntry(name+"3",value);
                    if(star4.contains("(")) {
                        value = Double.parseDouble(star4.substring(0,star4.indexOf("(")));
                        bag.addEntry(name+"4",value);
                        value = Double.parseDouble(star4.substring(star4.indexOf("(")+1,star4.indexOf(")")));
                        bag2.addEntry(name+"4",value);
                    } else {
                        value = Double.parseDouble(star4);
                        bag.addEntry(name+"4",value);
                    }
                    if(star5.contains("(")) {
                        value = Double.parseDouble(star5.substring(0,star5.indexOf("(")));
                        bag.addEntry(name+"5",value);
                        value = Double.parseDouble(star5.substring(star5.indexOf("(")+1,star5.indexOf(")")));
                        bag2.addEntry(name+"5",value);
                    } else {
                        value = Double.parseDouble(star5);
                        bag.addEntry(name+"5",value);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("     Error: GachaRead IOException");
        }
    }
}
