package Commands.Gacha.Utility;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class urlParse {
    public static void callMe(String num, String name, String currentBanner) throws Exception {
        try {
            parseMe(num,name,currentBanner);
        }catch (Exception e){
            throw e;
        }
    }
    public static void checkBanner(String num) {
        try {
            if (Integer.parseInt(num) > 9) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("    Error: Check Banner");
        }
        try {
            File inputFile = new File("Storage/GachaList.txt");
            File tempFile = new File("temp.txt");

            if(inputFile.length() == 0) {
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            boolean onoff = false;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("Banner ")) {
                    if (currentLine.startsWith("Banner " + num)) {
                        onoff = true;
                    } else {
                        onoff = false;
                    }
                }
                if (onoff) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }catch (IOException e){
            System.out.println("     Error: UrlParse IOException Check Banner");
        }
    }
    public static void sort() {
        try {
            File inputFile = new File("Storage/GachaList.txt");
            File tempFile = new File("temp.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            boolean onoff = false;
            String currentLine;
            for(int i=1;i<10;++i) {
                reader.close();
                reader = new BufferedReader(new FileReader(inputFile));
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("Banner ")) {
                        if (currentLine.startsWith("Banner " + i)) {
                            onoff = true;
                        } else {
                            onoff = false;
                        }
                    }
                    if (!onoff) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }catch (IOException e){
            System.out.println("     Error: UrlParse IOException Sort");
        }
    }
    public static void parseMe(String num, String name, String currentBanner) throws Exception {
        try {
            URL url = new URL(currentBanner);
            URLConnection conn = url.openConnection();

            checkBanner(num);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String input,output;
            int counter = 0;
            writeMe("Banner " + num + "                 : " + name + "\n");
            while((input = br.readLine()) != null){
                output = "";
                input = input.replaceAll("\\s+","");
                if(input.startsWith("<td>")) {
                    output += input.substring(4, input.length() - 5) + "|";
                }
                else if(input.startsWith("<tdclass=\"new\"")) {
                    output += input.substring(15, input.length() - 5) + "|";
                }else if(input.startsWith("<tdclass=\"pickup\">")) {
                    output += input.substring(18, input.length() - 5) + "|";
                }else if(input.startsWith("<tdclass=\"style\">")) {
                    output += input.substring(17, input.length() - 5) + "|";
                }else if(input.startsWith("<tdclass=\"newstyle\">")) {
                    output += input.substring(20, input.length() - 5) + "|";
                }else if(input.startsWith("<tdclass=\"none\">")){
                        output += "0%" + "|";
                }
                if(!output.equals("")) {
                    counter++;
                    if(counter == 4){
                        output += "\n";
                        counter = 0;
                    }
                    writeMe(output);
                }
            }
            writeMe("\n");
            br.close();
            sort();
        }
        catch (MalformedURLException e) {
            System.out.println("     Error: UrlParse MalformedURL");
            throw e;
        }
        catch (IOException e) {
            System.out.println("     Error: UrlParse IOException ???");
        }
    }
    public static void clear(){
        File inputFile = new File("Storage/GachaList.txt");
        inputFile.delete();
        File newFile = new File("Storage/GachaList.txt");
        try {
            newFile.createNewFile();
        } catch(IOException e) {
            System.out.println("    Error: Clear");
        }
    }
    public static void writeMe(String write){
        try{
            Writer output;
            output = new BufferedWriter(new FileWriter("Storage/GachaList.txt",true));
            output.append(write);
            output.close();
        } catch (Exception e) {
            System.out.println("     Error: UrlParse ???");
        }
    }
}
