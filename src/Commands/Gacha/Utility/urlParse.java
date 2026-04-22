package Commands.Gacha.Utility;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class urlParse {
    public static void callMe(String num, String name, String currentBanner) throws Exception {
        parseMe(num, name, currentBanner);
    }

    // Removes the specified banner block from GachaList.txt.
    // Returns false without modifying the file if num is invalid.
    public static boolean checkBanner(String num) {
        int n;
        try {
            n = Integer.parseInt(num);
            if (n < 1 || n > 9) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("    Error: Check Banner — invalid banner number: " + num);
            return false;
        }

        File inputFile = new File("Storage/GachaList.txt");
        if (inputFile.length() == 0) return true;

        try {
            File tempFile = File.createTempFile("gacha_check", ".tmp", new File("Storage"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                boolean onoff = false;
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("Banner ")) {
                        onoff = currentLine.startsWith("Banner " + n);
                    }
                    if (onoff) continue;
                    writer.write(currentLine + System.lineSeparator());
                }
            }
            inputFile.delete();
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("     Error: UrlParse checkBanner — rename failed");
                return false;
            }
        } catch (IOException e) {
            System.out.println("     Error: UrlParse IOException Check Banner");
            return false;
        }
        return true;
    }

    // Rewrites GachaList.txt with all banners sorted in numeric order (1–9).
    public static void sort() {
        File inputFile = new File("Storage/GachaList.txt");
        try {
            File tempFile = File.createTempFile("gacha_sort", ".tmp", new File("Storage"));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                for (int i = 1; i < 10; ++i) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                        boolean onoff = false;
                        String currentLine;
                        while ((currentLine = reader.readLine()) != null) {
                            if (currentLine.startsWith("Banner ")) {
                                onoff = currentLine.startsWith("Banner " + i);
                            }
                            if (!onoff) continue;
                            writer.write(currentLine + System.lineSeparator());
                        }
                    }
                }
            }
            inputFile.delete();
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("     Error: UrlParse sort — rename failed");
            }
        } catch (IOException e) {
            System.out.println("     Error: UrlParse IOException Sort");
        }
    }

    public static void parseMe(String num, String name, String currentBanner) throws Exception {
        try {
            URL url = new URL(currentBanner);
            URLConnection conn = url.openConnection();

            if (!checkBanner(num)) {
                throw new IllegalArgumentException("Invalid banner number: " + num);
            }

            // Open GachaList.txt once for the entire parse, then close it.
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new FileWriter("Storage/GachaList.txt", true))) {
                out.write("Banner " + num + "                 : " + name + "\n");
                String input;
                int counter = 0;
                while ((input = br.readLine()) != null) {
                    String output = "";
                    input = input.replaceAll("\\s+", "");
                    if      (input.startsWith("<td>"))                  output = input.substring(4,  input.length() - 5) + "|";
                    else if (input.startsWith("<tdclass=\"new\""))       output = input.substring(15, input.length() - 5) + "|";
                    else if (input.startsWith("<tdclass=\"pickup\">"))   output = input.substring(18, input.length() - 5) + "|";
                    else if (input.startsWith("<tdclass=\"style\">"))    output = input.substring(17, input.length() - 5) + "|";
                    else if (input.startsWith("<tdclass=\"newstyle\">")) output = input.substring(20, input.length() - 5) + "|";
                    else if (input.startsWith("<tdclass=\"none\">"))     output = "0%|";

                    if (!output.isEmpty()) {
                        counter++;
                        if (counter == 4) {
                            output += "\n";
                            counter = 0;
                        }
                        out.write(output);
                    }
                }
                out.write("\n");
            }
            sort();
        } catch (MalformedURLException e) {
            System.out.println("     Error: UrlParse MalformedURL");
            throw e;
        } catch (IOException e) {
            System.out.println("     Error: UrlParse IOException");
        }
    }

    public static void clear() {
        File inputFile = new File("Storage/GachaList.txt");
        inputFile.delete();
        try {
            new File("Storage/GachaList.txt").createNewFile();
        } catch (IOException e) {
            System.out.println("    Error: Clear");
        }
    }
}
