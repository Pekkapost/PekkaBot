package Commands.Gacha.Utility;

import Structures.weightedRandomBag;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;


public class gachaManager {
    private static weightedRandomBag<String> bag1 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag2 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag3 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag4 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag5 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag6 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag7 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag8 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag110 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag210 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag310 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag410 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag510 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag610 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag710 = new weightedRandomBag<>();
    private static weightedRandomBag<String> bag810 = new weightedRandomBag<>();
    public static void banner1() {
        gachaRead.updateBanners(1,bag1,bag110);
    }
    public static void banner1(int pullNum, String[] gachalist) {
        if(pullNum == 9) {
            gachalist[pullNum] = bag110.getRandom();
            return;
        }
        gachalist[pullNum] = bag1.getRandom();
    }
    public static void banner2() {
        gachaRead.updateBanners(2,bag2,bag210);
    }
    public static void banner2(int pullNum, String[] gachalist) {
        if(pullNum == 9) {
            gachalist[pullNum] = bag210.getRandom();
            return;
        }
        gachalist[pullNum] = bag2.getRandom();
    }
    public static void banner3(){
        gachaRead.updateBanners(3,bag3,bag310);
    }
    public static void banner3(int pullNum, String[] gachalist) {
        if(pullNum == 9){
            gachalist[pullNum] = bag310.getRandom();
            return;
        }
        gachalist[pullNum] = bag3.getRandom();
    }
    public static void banner4() {
        gachaRead.updateBanners(4,bag4,bag410);
    }
    public static void banner4(int pullNum, String[] gachalist) {
        if(pullNum == 9) {
            gachalist[pullNum] = bag410.getRandom();
            return;
        }
        gachalist[pullNum] = bag4.getRandom();
    }
    public static void banner5() {
        gachaRead.updateBanners(5,bag5,bag510);
    }
    public static void banner5(int pullNum, String[] gachalist) {
        if(pullNum == 9) {
            gachalist[pullNum] = bag510.getRandom();
            return;
        }
        gachalist[pullNum] = bag5.getRandom();
    }
    public static void banner6() {
        gachaRead.updateBanners(6,bag6,bag610);
    }
    public static void banner6(int pullNum, String[] gachalist) {
        if(pullNum == 9){
            gachalist[pullNum] = bag610.getRandom();
            return;
        }
        gachalist[pullNum] = bag6.getRandom();
    }
    public static void banner7() {
        gachaRead.updateBanners(7,bag7,bag710);
    }
    public static void banner7(int pullNum, String[] gachalist) {
        if(pullNum == 9){
            gachalist[pullNum] = bag710.getRandom();
            return;
        }
        gachalist[pullNum] = bag7.getRandom();
    }
    public static void banner8() {
        gachaRead.updateBanners(8,bag8,bag810);
    }
    public static void banner8(int pullNum, String[] gachalist) {
        if(pullNum == 9){
            gachalist[pullNum] = bag810.getRandom();
            return;
        }
        gachalist[pullNum] = bag8.getRandom();
    }
    public static void update() {
        banner1();
        banner2();
        banner3();
        banner4();
        banner5();
        banner6();
        banner7();
        banner8();
    }
    public static File pickMe(int banner) {
        String[] gachalist = new String[] {
                "Failure", "Failure", "Failure", "Failure", "Failure", "Failure", "Failure", "Failure", "Failure", "Failure"};
        try {
            byte[] b = new byte[6000];
            FileInputStream fis = new FileInputStream(new File("assets/Gacha/Other/Base.png"));
            FileOutputStream fos = new FileOutputStream(new File("assets/Gacha/Other/Export.png"));
            int readbytes;
            while((readbytes=fis.read(b)) != -1) {
                fos.write(b,0,readbytes);
            }
            fis.close();
            fos.close();
         } catch (Exception e) {System.out.println("Error resetting Export File.");}

        for(int i=0;i<10;i++) {
            switch(banner) {
                case 1:
                    if(bag1.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner1(i,gachalist);
                    break;
                case 2:
                    if(bag2.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner2(i,gachalist);
                    break;
                case 3:
                    if(bag3.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner3(i,gachalist);
                    break;
                case 4:
                    if(bag4.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner4(i,gachalist);
                    break;
                case 5:
                    if(bag5.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner5(i,gachalist);
                    break;
                case 6:
                    if(bag6.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner6(i,gachalist);
                    break;
                case 7:
                    if(bag7.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner7(i,gachalist);
                    break;
                case 8:
                    if(bag8.isEmpty()) {
                        fiveBanner(i,gachalist);
                        break;
                    }
                    banner8(i,gachalist);
                    break;
                default:
                    fiveBanner(i,gachalist);
            }
        }
        drawMe(gachalist);
        System.out.println(Arrays.toString(gachalist));
        File export = new File("assets/Gacha/Other/Export.png");
        return export;
    }
    public static void drawMe(String[] gachalist) {
        try {
            BufferedImage img = ImageIO.read(new File("assets/Gacha/Other/Export.png"));
            Graphics g = img.createGraphics();

            BufferedImage character0 = ImageIO.read(new File("assets/Gacha/" + gachalist[0] + ".png"));
            BufferedImage character1 = ImageIO.read(new File("assets/Gacha/" + gachalist[1] + ".png"));
            BufferedImage character2 = ImageIO.read(new File("assets/Gacha/" + gachalist[2] + ".png"));
            BufferedImage character3 = ImageIO.read(new File("assets/Gacha/" + gachalist[3] + ".png"));
            BufferedImage character4 = ImageIO.read(new File("assets/Gacha/" + gachalist[4] + ".png"));
            BufferedImage character5 = ImageIO.read(new File("assets/Gacha/" + gachalist[5] + ".png"));
            BufferedImage character6 = ImageIO.read(new File("assets/Gacha/" + gachalist[6] + ".png"));
            BufferedImage character7 = ImageIO.read(new File("assets/Gacha/" + gachalist[7] + ".png"));
            BufferedImage character8 = ImageIO.read(new File("assets/Gacha/" + gachalist[8] + ".png"));
            BufferedImage character9 = ImageIO.read(new File("assets/Gacha/" + gachalist[9] + ".png"));

            g.drawImage(character0,169,28,null);
            g.drawImage(character1,367,28,null);
            g.drawImage(character2,565,28,null);
            g.drawImage(character3,763,28,null);
            g.drawImage(character4,961,28,null);
            g.drawImage(character5,169,374,null);
            g.drawImage(character6,367,374,null);
            g.drawImage(character7,565,374,null);
            g.drawImage(character8,763,374,null);
            g.drawImage(character9,961,374,null);

            File export = new File("assets/Gacha/Other/Export.png");
            ImageIO.write(img, "png", export);
        } catch(Exception e) {System.out.println("Issue exporting image.");}
    }

    public static void fiveBanner(int pullNum, String[] gachalist) {
        weightedRandomBag<String> bag = new weightedRandomBag<>();
        double Tier1 = 0.5,Tier2 = 1,Tier3 = 5;

        bag.addEntry("Myunfa5",Tier1);
        /*bag.addEntry("Dunarith5",Tier1);
        bag.addEntry("ProudBeast5",Tier1);
        bag.addEntry("Lovely5", Tier1);
        bag.addEntry("Philo5", Tier1);
        bag.addEntry("Tentamare5", Tier1);
        bag.addEntry("Mikahayahi5", Tier1);
        bag.addEntry("Rosetta5", Tier2);
        bag.addEntry("Tsubame5", Tier2);
        bag.addEntry("Shigure5", Tier2);
        bag.addEntry("Punishment5", Tier1);
        bag.addEntry("Biaka5", Tier2);
        bag.addEntry("Hozuki5", Tier2);
        bag.addEntry("BlancMeneur5",Tier1);
        bag.addEntry("Radica5",Tier2);
        bag.addEntry("Ilulu5",Tier2);
        bag.addEntry("Sunrise5",Tier1);
        bag.addEntry("Dewey5",Tier2);
        bag.addEntry("Veina5",Tier3);
        bag.addEntry("CursedBride5",Tier2);
        bag.addEntry("Elga5",Tier3);
        bag.addEntry("Shannon5",Tier3);
        bag.addEntry("Renri5",Tier3);
        bag.addEntry("Starlight5",Tier1);
        bag.addEntry("Claude5",Tier3);
        bag.addEntry("Hypnotist5",Tier1);
        bag.addEntry("Tsukiha5",Tier3);
        bag.addEntry("Felmina5",Tier3);
        bag.addEntry("Bertrand5",Tier3);*/
        bag.addEntry("ButterflyWarrior5",Tier1);
        /*bag.addEntry("Myrus5",Tier3);
        bag.addEntry("Shanie5",Tier3);
        bag.addEntry("Melina5",Tier3);
        bag.addEntry("Cetie5",Tier3);
        bag.addEntry("Ewan5",Tier3);
        bag.addEntry("Nagi5",Tier3);
        bag.addEntry("Yuna5",Tier3);
        bag.addEntry("Isuka5",Tier3);
        bag.addEntry("Laclair5",Tier3);
        bag.addEntry("Anabel5",Tier3);
        bag.addEntry("Mariel5",Tier3);
        bag.addEntry("Shion5",Tier3);
        bag.addEntry("Suzette5",Tier3);
        bag.addEntry("Mighty5",Tier3);
        bag.addEntry("Toova5",Tier3);
        bag.addEntry("Lokido5",Tier3);*/

        gachalist[pullNum] = bag.getRandom();
    }
}
