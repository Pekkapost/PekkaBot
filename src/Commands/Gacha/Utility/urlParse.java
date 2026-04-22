package Commands.Gacha.Utility;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class urlParse {
    public static void callMe(String num, String name, String currentBanner) throws Exception {
        parseMe(num, name, currentBanner);
    }

    public static void checkBanner(String num) {
        try {
            if (Integer.parseInt(num) > 9) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("    Error: Check Banner");
        }

        File inputFile = new File("Storage/GachaList.txt");
        if (inputFile.length() == 0) return;

        File tempFile = new File("temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            boolean onoff = false;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("Banner ")) {
                    onoff = currentLine.startsWith("Banner " + num);
                }
                if (onoff) continue;
                writer.write(currentLine + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("     Error: UrlParse IOException Check Banner");
            return;
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public static void sort() {
        File inputFile = new File("Storage/GachaList.txt");
        File tempFile = new File("temp.txt");

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
        } catch (IOException e) {
            System.out.println("     Error: UrlParse IOException Sort");
            return;
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public static void parseMe(String num, String name, String currentBanner) throws Exception {
        try {
            URL url = new URL(currentBanner);
            URLConnection conn = url.openConnection();
            checkBanner(num);

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
