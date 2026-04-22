package Commands.Gacha.Utility;

import Structures.weightedRandomBag;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Manages up to 8 named banners loaded from GachaList.txt.
// Each banner has a normal bag (pulls 1–9) and a guaranteed bag (pull 10).
// Banner 0 / unrecognized banners fall back to the default 5* banner.
public class gachaManager {
    private static final List<weightedRandomBag<String>> bags   = new ArrayList<>();
    private static final List<weightedRandomBag<String>> bags10 = new ArrayList<>();

    // Fallback banner: always available, fixed 5* pool
    private static final weightedRandomBag<String> defaultBag = new weightedRandomBag<>();

    // Pixel positions for 10 character slots: 5 per row, 2 rows
    private static final int[] DRAW_X = {169, 367, 565, 763, 961, 169, 367, 565, 763, 961};
    private static final int[] DRAW_Y = { 28,  28,  28,  28,  28, 374, 374, 374, 374, 374};

    static {
        for (int i = 0; i < 8; i++) {
            bags.add(new weightedRandomBag<>());
            bags10.add(new weightedRandomBag<>());
        }
        defaultBag.addEntry("Myunfa5", 0.5);
        defaultBag.addEntry("ButterflyWarrior5", 0.5);
    }

    public static synchronized void update() {
        for (int i = 0; i < 8; i++) {
            gachaRead.updateBanners(i + 1, bags.get(i), bags10.get(i));
        }
    }

    // Pull 10 (index 9) uses the guaranteed bag; all others use the normal bag
    private static synchronized void pull(int bannerIdx, int pullNum, String[] gachalist) {
        gachalist[pullNum] = (pullNum == 9)
                ? bags10.get(bannerIdx).getRandom()
                : bags.get(bannerIdx).getRandom();
    }

    public static synchronized File pickMe(int banner) {
        String[] gachalist = new String[10];
        Arrays.fill(gachalist, "Failure");

        try (FileInputStream fis = new FileInputStream("assets/Gacha/Other/Base.png");
             FileOutputStream fos = new FileOutputStream("assets/Gacha/Other/Export.png")) {
            byte[] b = new byte[6000];
            int readbytes;
            while ((readbytes = fis.read(b)) != -1) {
                fos.write(b, 0, readbytes);
            }
        } catch (Exception e) {
            System.out.println("Error resetting Export File.");
        }

        int idx = banner - 1;
        for (int i = 0; i < 10; i++) {
            if (idx >= 0 && idx < 8 && !bags.get(idx).isEmpty()) {
                pull(idx, i, gachalist);
            } else {
                fiveBanner(i, gachalist);
            }
        }

        drawMe(gachalist);
        System.out.println(Arrays.toString(gachalist));
        return new File("assets/Gacha/Other/Export.png");
    }

    public static void drawMe(String[] gachalist) {
        try {
            BufferedImage img = ImageIO.read(new File("assets/Gacha/Other/Export.png"));
            Graphics g = img.createGraphics();
            try {
                for (int i = 0; i < 10; i++) {
                    BufferedImage character = ImageIO.read(new File("assets/Gacha/" + gachalist[i] + ".png"));
                    g.drawImage(character, DRAW_X[i], DRAW_Y[i], null);
                    character.flush();
                }
                ImageIO.write(img, "png", new File("assets/Gacha/Other/Export.png"));
            } finally {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Issue exporting image.");
        }
    }

    public static void fiveBanner(int pullNum, String[] gachalist) {
        gachalist[pullNum] = defaultBag.getRandom();
    }
}
