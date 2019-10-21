package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.io.*;

public class ping {
    public static void drawerCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("lake") || event.getMessage().getContent().toLowerCase().contains("pond")){
            replaceSelected(event.getAuthor().getId() + " Drawer",whiteGate(event,"Drawer"));
            lakeCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("plant")){
            replaceSelected(event.getAuthor().getId() + " Drawer",whiteGate(event,"Drawer"));
            plantCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " DrawerF",whiteGate(event,"DrawerF"));
        }
    }
    public static void windowCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("lake") || event.getMessage().getContent().toLowerCase().contains("pond")){
            replaceSelected(event.getAuthor().getId() + " Window",whiteGate(event,"Window"));
            lakeCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("plant")){
            replaceSelected(event.getAuthor().getId() + " Window",whiteGate(event,"Window"));
            plantCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " WindowF",whiteGate(event,"WindowF"));
        }
    }
    public static void bedCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("lake")){
            replaceSelected(event.getAuthor().getId() + " Bed",whiteGate(event,"Bed"));
            lakeCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("plant")){
            replaceSelected(event.getAuthor().getId() + " Bed",whiteGate(event,"Bed"));
            plantCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " BedF",whiteGate(event,"BedF"));
        }
    }
    public static void lakeCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("left")){
            replaceSelected(event.getAuthor().getId() + " Lake",whiteGate(event,"Lake"));
            leftCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("middle") || event.getMessage().getContent().toLowerCase().contains("center")){
            replaceSelected(event.getAuthor().getId() + " Lake",whiteGate(event,"Lake"));
            middleCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("right")){
            replaceSelected(event.getAuthor().getId() + " Lake",whiteGate(event,"Lake"));
            rightCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " LakeF",whiteGate(event,"LakeF"));
        }
    }
    public static void plantCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("left")){
            replaceSelected(event.getAuthor().getId() + " Plant",whiteGate(event,"Plant"));
            leftCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("middle") || event.getMessage().getContent().toLowerCase().contains("center")){
            replaceSelected(event.getAuthor().getId() + " Plant",whiteGate(event,"Plant"));
            middleCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("right")){
            replaceSelected(event.getAuthor().getId() + " Plant",whiteGate(event,"Plant"));
            rightCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " PlantF",whiteGate(event,"PlantF"));
        }
    }
    public static void leftCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("boat")){
            replaceSelected(event.getAuthor().getId() + " LadderL",whiteGate(event,"LadderL"));
            boatCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("door")){
            replaceSelected(event.getAuthor().getId() + " LadderL",whiteGate(event,"LadderL"));
            doorCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " LadderLF",whiteGate(event,"LadderLF"));
        }
    }
    public static void middleCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("boat")){
            replaceSelected(event.getAuthor().getId() + " LadderM",whiteGate(event,"LadderM"));
            boatCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("door")){
            replaceSelected(event.getAuthor().getId() + " LadderM",whiteGate(event,"LadderM"));
            doorCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " LadderMF",whiteGate(event,"LadderMF"));
        }
    }
    public static void rightCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("boat")){
            replaceSelected(event.getAuthor().getId() + " LadderR",whiteGate(event,"LadderR"));
            boatCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("door")){
            replaceSelected(event.getAuthor().getId() + " LadderR",whiteGate(event,"LadderR"));
            doorCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " LadderRF",whiteGate(event,"LadderRF"));
        }
    }
    public static void boatCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("element")){
            replaceSelected(event.getAuthor().getId() + " Boat",whiteGate(event,"Boat"));
            elementCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("balloon")){
            replaceSelected(event.getAuthor().getId() + " Boat",whiteGate(event,"Boat"));
            balloonCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("well")){
            replaceSelected(event.getAuthor().getId() + " Boat",whiteGate(event,"Boat"));
            wellCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("varuo")){
            replaceSelected(event.getAuthor().getId() + " Boat",whiteGate(event,"Boat"));
            varuoCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " BoatF",whiteGate(event,"BoatF"));
        }
    }
    public static void doorCheck(MessageReceivedEvent event){
        if(event.getMessage().getContent().toLowerCase().contains("element")){
            replaceSelected(event.getAuthor().getId() + " Door",whiteGate(event,"Door"));
            elementCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("balloon")){
            replaceSelected(event.getAuthor().getId() + " Door",whiteGate(event,"Door"));
            balloonCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("well")){
            replaceSelected(event.getAuthor().getId() + " Door",whiteGate(event,"Door"));
            wellCheck(event);
        }else if(event.getMessage().getContent().toLowerCase().contains("varuo")){
            replaceSelected(event.getAuthor().getId() + " Door",whiteGate(event,"Door"));
            varuoCheck(event);
        }else{
            replaceSelected(event.getAuthor().getId() + " DoorF",whiteGate(event,"DoorF"));
        }
    }
    public static void elementCheck(MessageReceivedEvent event){
        if(!event.getMessage().getContent().toLowerCase().contains("dungeon") && !event.getMessage().getContent().toLowerCase().contains("dead") && !event.getMessage().getContent().toLowerCase().contains("jail") && !event.getMessage().getContent().toLowerCase().contains("fail")){
            replaceSelected(event.getAuthor().getId() + " Element",whiteGate(event,"Element"));
        }else{
            replaceSelected(event.getAuthor().getId() + " ElementF",whiteGate(event,"ElementF"));
        }
    }
    public static void balloonCheck(MessageReceivedEvent event){
        if(!event.getMessage().getContent().toLowerCase().contains("dungeon") && !event.getMessage().getContent().toLowerCase().contains("dead") && !event.getMessage().getContent().toLowerCase().contains("jail") && !event.getMessage().getContent().toLowerCase().contains("fail")){
            replaceSelected(event.getAuthor().getId() + " Balloon",whiteGate(event,"Balloon"));
        }else{
            replaceSelected(event.getAuthor().getId() + " BalloonF",whiteGate(event,"BalloonF"));
        }
    }
    public static void wellCheck(MessageReceivedEvent event){
        if(!event.getMessage().getContent().toLowerCase().contains("dungeon") && !event.getMessage().getContent().toLowerCase().contains("dead") && !event.getMessage().getContent().toLowerCase().contains("jail") && !event.getMessage().getContent().toLowerCase().contains("fail")){
            replaceSelected(event.getAuthor().getId() + " Well",whiteGate(event,"Well"));
        }else{
            replaceSelected(event.getAuthor().getId() + " WellF",whiteGate(event,"WellF"));
        }
    }
    public static void varuoCheck(MessageReceivedEvent event){
        replaceSelected(event.getAuthor().getId() + " Varuo",whiteGate(event,"Varuo"));
    }

    public static void check(MessageReceivedEvent event){
        String message = event.getMessage().getContent().toLowerCase();
        System.out.println(message);
        if(message.contains("drawer")){
            drawerCheck(event);
        }else if (message.contains("window")){
            windowCheck(event);
        }else if (message.contains("bed")){
            bedCheck(event);
        }
    }

    public static String whiteGate(MessageReceivedEvent event,String message) {
        String fileName = "Storage.txt";
        String line;
        try {
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
            add(event.getAuthor().getId());
            bufferedReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("     Unable to open file '" + fileName + "'");
        }catch (IOException ex){
            System.out.println("     Error reading file '" + fileName + "'");
        }
        return "";
    }
    public static void add(String UserID){
        try{
            Writer output;
            output = new BufferedWriter(new FileWriter("Storage.txt",true));
            output.append("\n"+UserID+" Drawer0");
            output.append("\n"+UserID+" DrawerF0");
            output.append("\n"+UserID+" Window0");
            output.append("\n"+UserID+" WindowF0");
            output.append("\n"+UserID+" Bed0");
            output.append("\n"+UserID+" BedF0");
            output.append("\n"+UserID+" Lake0");
            output.append("\n"+UserID+" LakeF0");
            output.append("\n"+UserID+" Plant0");
            output.append("\n"+UserID+" PlantF0");
            output.append("\n"+UserID+" LadderL0");
            output.append("\n"+UserID+" LadderLF0");
            output.append("\n"+UserID+" LadderM0");
            output.append("\n"+UserID+" LadderMF0");
            output.append("\n"+UserID+" LadderR0");
            output.append("\n"+UserID+" LadderRF0");
            output.append("\n"+UserID+" Boat0");
            output.append("\n"+UserID+" BoatF0");
            output.append("\n"+UserID+" Door0");
            output.append("\n"+UserID+" DoorF0");
            output.append("\n"+UserID+" Element0");
            output.append("\n"+UserID+" ElementF0");
            output.append("\n"+UserID+" Balloon0");
            output.append("\n"+UserID+" BalloonF0");
            output.append("\n"+UserID+" Well0");
            output.append("\n"+UserID+" WellF0");
            output.append("\n"+UserID+" Varuo0");
            output.close();
        }
        catch(Exception e){
            System.out.println("     Problem initializing data.");
        }
}
    public static void replaceSelected(String replaceWith, String type) {
        try{
            BufferedReader file = new BufferedReader(new FileReader("Storage.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while((line = file.readLine()) != null){
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(replaceWith + type, replaceWith + String.valueOf(Integer.parseInt(type) + 1));

            FileOutputStream fileOut = new FileOutputStream("Storage.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println("     Problem replacing choice.");
        }
    }
    public static void mywg(MessageReceivedEvent event){
        String fileName = "Storage.txt";
        String line;
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp = "```";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(event.getAuthor().getId())) {
                    temp = temp + line.substring(event.getAuthor().getId().length())+"\r\n";
                }
            }
            temp=temp+"```";
            bufferedReader.close();
            event.getTextChannel().sendMessage(temp).queue();
        }catch(FileNotFoundException ex) {
            System.out.println("     Unable to open file '" + fileName + "'");
        }catch(IOException ex) {
            System.out.println("     Error reading file '" + fileName + "'");
        }catch(NullPointerException ex){
            System.out.println("     Null Pointer Exception?");
        }
    }
}