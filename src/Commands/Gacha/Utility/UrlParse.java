package Commands.Gacha.Utility;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.Paths;

public class UrlParse {
    private static final Logger logger = LoggerFactory.getLogger(UrlParse.class);

    // Atomic replace: ATOMIC_MOVE when the filesystem supports it (POSIX / NTFS),
    // otherwise fall back to REPLACE_EXISTING. A crash mid-replace can never leave
    // a half-written GachaList.txt behind.
    private static void atomicReplace(File tmp, File target) throws IOException {
        try {
            Files.move(tmp.toPath(), target.toPath(),
                    StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(tmp.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

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
            logger.error("checkBanner: invalid banner number {}", num);
            return false;
        }

        File inputFile = Paths.data("GachaList.txt");
        if (inputFile.length() == 0) return true;

        File tempFile = null;
        try {
            tempFile = File.createTempFile("gacha_check", ".tmp", Paths.data(""));
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
            atomicReplace(tempFile, inputFile);
            tempFile = null;
        } catch (IOException e) {
            logger.error("checkBanner I/O error", e);
            return false;
        } finally {
            if (tempFile != null && tempFile.exists()) tempFile.delete();
        }
        return true;
    }

    // Rewrites GachaList.txt with all banners sorted in numeric order (1–9).
    public static void sort() {
        File inputFile = Paths.data("GachaList.txt");
        File tempFile = null;
        try {
            tempFile = File.createTempFile("gacha_sort", ".tmp", Paths.data(""));
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
            atomicReplace(tempFile, inputFile);
            tempFile = null;
        } catch (IOException e) {
            logger.error("sort I/O error", e);
        } finally {
            if (tempFile != null && tempFile.exists()) tempFile.delete();
        }
    }

    public static void parseMe(String num, String name, String currentBanner) throws Exception {
        File target = Paths.data("GachaList.txt");
        File tempFile = null;
        try {
            URL url = new URL(currentBanner);
            URLConnection conn = url.openConnection();

            if (!checkBanner(num)) {
                throw new IllegalArgumentException("Invalid banner number: " + num);
            }

            // Build the new GachaList content in a temp file: existing contents
            // first, then the freshly-parsed banner block. The atomic-replace at
            // the end means a crash mid-write can never leave the live file in a
            // half-written state.
            tempFile = File.createTempFile("gacha_parse", ".tmp", Paths.data(""));
            try (BufferedWriter out = new BufferedWriter(new FileWriter(tempFile))) {
                if (target.exists()) {
                    try (BufferedReader existing = new BufferedReader(new FileReader(target))) {
                        String line;
                        while ((line = existing.readLine()) != null) {
                            out.write(line);
                            out.write(System.lineSeparator());
                        }
                    }
                }
                out.write("Banner " + num + "                 : " + name + "\n");
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
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
                }
                out.write("\n");
            }
            atomicReplace(tempFile, target);
            tempFile = null;
            sort();
        } catch (MalformedURLException e) {
            logger.error("parseMe: malformed banner URL", e);
            throw e;
        } catch (IOException e) {
            logger.error("parseMe I/O error", e);
        } finally {
            if (tempFile != null && tempFile.exists()) tempFile.delete();
        }
    }

    public static void clear() {
        File inputFile = Paths.data("GachaList.txt");
        inputFile.delete();
        try {
            Paths.data("GachaList.txt").createNewFile();
        } catch (IOException e) {
            logger.error("clear: failed to recreate GachaList.txt", e);
        }
    }
}
