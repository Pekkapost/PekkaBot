package Commands.Gacha.Utility;

import Structures.weightedRandomBag;

import java.io.*;

public class gachaRead {
    public static String checkList() {
        String output = "```Apache\n" +
                "[Banner Number]   : [Banner Name]";
        try {
            File inputFile = new File("Storage/GachaList.txt");

            if(inputFile.length() != 0) {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("Banner ")) {
                        output += "\n" + currentLine.substring(7);
                    }
                }
                reader.close();
            }
        }catch (IOException e){
            System.out.println("     Error: GachaRead IOException");
        }
        output += "\nDefault           : 5* Banner```";
        return output;
    }
    public static void updateBanners(int num, weightedRandomBag<String> bag, weightedRandomBag bag2) {
        try {
            File inputFile = new File("Storage/GachaList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String currentline;
            boolean flip = false;

            bag.purge();
            bag2.purge();

            while((currentline = reader.readLine()) != null){
                if(currentline.startsWith("Banner ")) {
                    if(Integer.valueOf(currentline.substring(7,8)) == num) {
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

                    double value = Double.valueOf(star3);
                    bag.addEntry(name+"3",value);
                    if(star4.contains("(")) {
                        value = Double.valueOf(star4.substring(0,star4.indexOf("(")));
                        bag.addEntry(name+"4",value);
                        value = Double.valueOf(star4.substring(star4.indexOf("(")+1,star4.indexOf(")")));
                        bag2.addEntry(name+"4",value);
                    } else {
                        value = Double.valueOf(star4);
                        bag.addEntry(name+"4",value);
                    }
                    if(star5.contains("(")) {
                        value = Double.valueOf(star5.substring(0,star5.indexOf("(")));
                        bag.addEntry(name+"5",value);
                        value = Double.valueOf(star5.substring(star5.indexOf("(")+1,star5.indexOf(")")));
                        bag2.addEntry(name+"5",value);
                    } else {
                        value = Double.valueOf(star5);
                        bag.addEntry(name+"5",value);
                    }
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("     Error: GachaRead IOException");
        }
    }
}
