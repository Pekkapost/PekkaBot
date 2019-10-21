package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.io.*;

public class currency {
    public static void add(MessageReceivedEvent event){
        if(!event.getMessage().getContent().startsWith("p!"))
            replaceSelected(event.getAuthor().getId() + " Points",points(event,"Points"));
    }
    public static String points(MessageReceivedEvent event,String message){
        String fileName = "Storage2.txt";
        String line;
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null){
                if(line.startsWith(event.getAuthor().getId())){
                    if(line.startsWith(event.getAuthor().getId()+" "+message)){
                        int leng = event.getAuthor().getId().length() + message.length()+1;
                        bufferedReader.close();
                        return line.substring(leng);
                    }
                }
            }
            add2(event.getAuthor().getId());
            bufferedReader.close();
        }catch(FileNotFoundException ex){
            System.out.println("     Unable to open file '" + fileName + "'");
        }catch(IOException ex){
            System.out.println("     Error reading file '" + fileName + "'");
        }
        return "";
    }
    public static void add2(String UserID){
        try{
            Writer output;
            output = new BufferedWriter(new FileWriter("Storage2.txt", true));
            output.append("\n" + UserID + " Points5");
            output.close();
        }catch(Exception e){
            System.out.println("     Problem initializing data.");
        }
    }
    public static void replaceSelected(String replaceWith, String type){
        try{
            BufferedReader file = new BufferedReader(new FileReader("Storage2.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null){
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(replaceWith + type, replaceWith + String.valueOf(Integer.parseInt(type) + 5));
            //System.out.println(inputStr.substring(1,inputStr.length()));
            //inputStr = inputStr.replace(replaceWith + (Integer.parseInt(inputStr.substring(inputStr.indexOf(0),inputStr.length()))),replaceWith + (Integer.parseInt(inputStr.substring(inputStr.indexOf(0),inputStr.length()))+1));

            FileOutputStream fileOut = new FileOutputStream("Storage2.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("     New User Speaking.");
        }
    }
    public static void chronos(MessageReceivedEvent event){
        String fileName = "Storage2.txt";
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp = "You have `";
            while ((line = bufferedReader.readLine()) != null){
                if (line.startsWith(event.getAuthor().getId())){
                    temp = temp + line.substring(event.getAuthor().getId().length() + 7);
                }
            }
            temp=temp+"` chronos";
            bufferedReader.close();
            event.getTextChannel().sendMessage(temp).queue();
        }catch(FileNotFoundException ex){
            System.out.println("     Unable to open file '" + fileName + "'");
        }catch(IOException ex){
            System.out.println("     Error reading file '" + fileName + "'");
        }catch(NullPointerException ex){
            System.out.println("     Null Pointer Exception?");
        }
    }
}
