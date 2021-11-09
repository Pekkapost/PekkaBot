package Commands.Action;

import Manager.SQLManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;


import java.io.File;
import java.util.Scanner;

public class test extends Command {
    public test() {
        this.name = "Test";
        this.help = "Tests";
        this.ownerCommand = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        System.out.println("Trying");
        try {
            File temp = new File("Input.txt");
            Scanner scan = new Scanner(temp);

            String message = scan.nextLine();
            String output = "";
            String id = "386948934234538039";
            for (int i = 0; i < message.length(); i++) {
                if (message.charAt(i) == '5') {
                    SQLManager.updateAd(id, "CS5");
                    output += "5 ";
                }
                if (message.charAt(i) == '1') {
                    SQLManager.updateAd(id, "CS10");
                    output += "10 ";
                }
                if (message.charAt(i) == '2') {
                    SQLManager.updateAd(id, "CS20");
                    output += "20 ";
                }
                if (message.charAt(i) == 'g') {
                    SQLManager.updateAd(id, "Green");
                    output += "G ";
                }
                if (message.charAt(i) == 'r') {
                    SQLManager.updateAd(id, "Red");
                    output += "R ";
                }
            }
            System.out.println(output);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
