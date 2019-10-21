package Commands;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class gacha {
    public static void pickMe(MessageReceivedEvent event){
        String[] gachalist = new String[]{"Failure","Failure","Failure","Failure","Failure","Failure","Failure","Failure","Failure","Failure"};
        try{
            byte[] b = new byte[6000];
            FileInputStream fis=new FileInputStream(new File("Gacha/Other/Base.png"));
            FileOutputStream fos=new FileOutputStream(new File("Gacha/Other/Export.png"));
            int readbytes;
            while((readbytes=fis.read(b))!=-1){
                fos.write(b,0,readbytes);
            }
        }catch (Exception e){System.out.println("Error resetting Export File.");}

        int banner = 0;
        try{
            if(event.getMessage().getContent().equals("p!gacha")){
                banner = 0;
            }else if(event.getMessage().getContent().length() >= 7){
                banner = Integer.valueOf(event.getMessage().getContent().substring(8));
            }
        }catch (NumberFormatException e){event.getTextChannel().sendMessage("Please use the correct format.").queue();return;}
        event.getTextChannel().sendMessage("Generating " + event.getAuthor().getName() + "'s Gacha Pull").queue((message2 -> message2.delete().queueAfter(10, TimeUnit.SECONDS)));

        for(int i=0;i<10;i++){
            switch(banner){
                case 1:
                    MightyASBanner(i,gachalist);
                    break;
                case 2:
                    ClaudeBanner(i,gachalist);
                    break;
                case 3:
                    IsukaASBanner(i,gachalist);
                    break;
                case 4:
                    ShannonBanner(i,gachalist);
                    break;
                case 5:
                    RenriBanner(i,gachalist);
                    break;
                case 6:
                    ElgaBanner(i,gachalist);
                    break;
                case 7:
                    ToovaASBanner(i,gachalist);
                    break;
                case 10:
                    jpBanner(i,gachalist);
                    break;
                default:
                    fiveBanner(event,i,gachalist);
            }
        }
        drawMe(gachalist);
        System.out.println(Arrays.toString(gachalist));
        File export = new File("Gacha/Other/Export.png");
        event.getTextChannel().sendFile(export, new MessageBuilder().append(event.getAuthor().getName()+"'s 10x Gacha Roll").build()).complete();
    }
    public static void drawMe(String[] gachalist){
        int locationX0 = 169;
        int locationY0 = 28;
        int locationX1 = 367;
        int locationY1 = 28;
        int locationX2 = 565;
        int locationY2 = 28;
        int locationX3 = 763;
        int locationY3 = 28;
        int locationX4 = 961;
        int locationY4 = 28;
        int locationX5 = 169;
        int locationY5 = 374;
        int locationX6 = 367;
        int locationY6 = 374;
        int locationX7 = 565;
        int locationY7 = 374;
        int locationX8 = 763;
        int locationY8 = 374;
        int locationX9 = 961;
        int locationY9 = 374;
        try{
            BufferedImage img = ImageIO.read(new File("Gacha/Other/Export.png"));
            Graphics g = img.createGraphics();

            BufferedImage character0 = ImageIO.read(new File("Gacha/" + gachalist[0] +".png"));
            BufferedImage character1 = ImageIO.read(new File("Gacha/" + gachalist[1] +".png"));
            BufferedImage character2 = ImageIO.read(new File("Gacha/" + gachalist[2] +".png"));
            BufferedImage character3 = ImageIO.read(new File("Gacha/" + gachalist[3] +".png"));
            BufferedImage character4 = ImageIO.read(new File("Gacha/" + gachalist[4] +".png"));
            BufferedImage character5 = ImageIO.read(new File("Gacha/" + gachalist[5] +".png"));
            BufferedImage character6 = ImageIO.read(new File("Gacha/" + gachalist[6] +".png"));
            BufferedImage character7 = ImageIO.read(new File("Gacha/" + gachalist[7] +".png"));
            BufferedImage character8 = ImageIO.read(new File("Gacha/" + gachalist[8] +".png"));
            BufferedImage character9 = ImageIO.read(new File("Gacha/" + gachalist[9] +".png"));

            g.drawImage(character0,locationX0,locationY0,null);
            g.drawImage(character1,locationX1,locationY1,null);
            g.drawImage(character2,locationX2,locationY2,null);
            g.drawImage(character3,locationX3,locationY3,null);
            g.drawImage(character4,locationX4,locationY4,null);
            g.drawImage(character5,locationX5,locationY5,null);
            g.drawImage(character6,locationX6,locationY6,null);
            g.drawImage(character7,locationX7,locationY7,null);
            g.drawImage(character8,locationX8,locationY8,null);
            g.drawImage(character9,locationX9,locationY9,null);

            File export = new File("Gacha/Other/Export.png");
            ImageIO.write(img, "png", export);
        }catch(Exception e){System.out.println("Issue exporting image.");}
    }
    public static void ToovaASBanner(int pullNum, String[] gachalist) {
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Toova","ToovaAS"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Toova"};
        int UnitMain4 = ListMain4.length;


        //Rate Up Main Unit 5*
        double RateMain50 = 0;
        double RateMain5100 = 0;
        String[] ListMain50 = new String[]{""};
        int UnitMain50 = ListMain50.length;
        //Rate Up Main Unit 4*
        double RateMain40 = 0;
        double RateMain4100 = 0;
        String[] ListMain40 = new String[]{""};
        int UnitMain40 = ListMain40.length;


        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .07;
        double RateSecondary510 = .08;
        String[] ListSecondary5 = new String[]{"Shion", "Suzette", "Anabel", "Isuka", "Bertrand", "Felmina", "Nagi", "Myrus", "Melina", "Tsukiha", "Claude"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .12;
        double RateSecondary410 = .44;
        String[] ListSecondary4 = new String[]{"Shion", "Suzette", "Anabel", "Isuka", "Bertrand", "Felmina", "Nagi", "Myrus", "Melina", "Tsukiha", "Claude"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .11;
        double Rate510 = .13;
        String[] ListRate5 = new String[]{"Mighty", "Mariel", "Lokido", "Laclair", "Yuna", "Ewan", "Cetie", "Shanie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .16;
        double Rate410 = .54;
        String[] ListRate4 = new String[]{"Mighty", "Mariel", "Lokido", "Laclair", "Yuna", "Ewan", "Cetie", "Shanie"};
        int Unit4 = ListRate4.length;

        //Rate Up 5 AS
        double Rate5AS = .02;
        double Rate5AS10 = .02;
        String[] ListRate5AS = new String[]{"SuzetteAS","MightyAS"};
        int Unit5AS = ListRate5AS.length;
        //Rate Up 4 Special
        double Rate4Special = 0;
        double Rate4Special10 = 0;
        String[] List4Special = new String[]{""};
        int Unit4Special = List4Special.length;
        //Rate Up 3 Special
        double Rate3Special = 0;
        String[] List3Special = new String[]{""};
        int Unit3Special = List3Special.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = 0;
        double RateSubMain410 = 0;
        String[] ListSubMain4 = new String[]{""};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 0;
        String[] ListSubMain3 = new String[]{""};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .60;
        double RateSubSecondary410 = 2.33;
        String[] ListSubSecondary4 = new String[]{"Kreevo", "Pom", "Prai", "Soira", "Nero", "Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.20;
        String[] ListSubSecondary3 = new String[]{"Kreevo", "Pom", "Prai", "Soira", "Nero", "Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .43;
        double RateSub410 = 2.25;
        String[] ListSub4 = new String[]{"Raven","Myron","Yio","Nikeh", "Otoha", "Akane", "Komachi", "Lingli", "Bivette", "Lele", "Sevyn", "Breeno", "Benedict", "Denny", "Miranda", "Cyuca", "Nomar", "Foran", "Ciel", "Parisa", "Darunis", "Rovella", "Yazuki", "Rufus", "May", "Samora", "Ruina", "Lovinia", "Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = 1.03;
        String[] ListSub3 = new String[]{"Raven","Myron","Yio","Nikeh", "Otoha", "Akane", "Komachi", "Lingli", "Raven", "Bivette", "Lele", "Sevyn", "Breeno", "Myron", "Yio", "Benedict", "Denny", "Miranda", "Cyuca", "Nomar", "Foran", "Ciel", "Parisa", "Darunis", "Rovella", "Yazuki", "Rufus", "May", "Samora", "Ruina", "Lovinia", "Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia", "Naylia", "Menesia", "Rodeia", "Sauria", "Asia", "Urania", "Pasia", "Yssis", "Natore", "Tohme", "Esta", "Bria", "Nixa", "Emms", "Ctos", "Noome", "Uuo", "Koot", "Oor", "Een", "Saithe", "Tayme", "Toole"};
        int UnitStandard3 = ListStandard3.length;


        double pick = (Math.random()*100);
        int pick2;
        if(pullNum == 9){
            if((pick = pick-Rate5AS10*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            }else if((pick = pick-RateMain510*UnitMain5)<=0) {
                pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate4Special10*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if((pick = pick-Rate5AS*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            } else if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            }else if((pick = pick-Rate4Special*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate3Special*Unit3Special)<=0) {
                pick2 = (int)(Math.random()*Unit3Special);
                gachalist[pullNum] = List3Special[pick2]+"3";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void ElgaBanner(int pullNum, String[] gachalist) {
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Elga"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Elga"};
        int UnitMain4 = ListMain4.length;


        //Rate Up Main Unit 5*
        double RateMain50 = .4;
        double RateMain5100 = .4;
        String[] ListMain50 = new String[]{"Lokido", "Myrus"};
        int UnitMain50 = ListMain50.length;
        //Rate Up Main Unit 4*
        double RateMain40 = .2;
        double RateMain4100 = .72;
        String[] ListMain40 = new String[]{"Lokido", "Myrus"};
        int UnitMain40 = ListMain40.length;


        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .07;
        double RateSecondary510 = .09;
        String[] ListSecondary5 = new String[]{"Shion", "Suzette", "Anabel", "Isuka", "Bertrand", "Felmina", "Nagi", "Melina", "Tsukiha", "Claude"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .1;
        double RateSecondary410 = .39;
        String[] ListSecondary4 = new String[]{"Shion", "Suzette", "Anabel", "Isuka", "Bertrand", "Felmina", "Nagi", "Melina", "Tsukiha", "Claude"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .11;
        double Rate510 = .13;
        String[] ListRate5 = new String[]{"Mighty", "Mariel", "Toova", "Laclair", "Yuna", "Ewan", "Cetie", "Shanie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .16;
        double Rate410 = .48;
        String[] ListRate4 = new String[]{"Mighty", "Mariel", "Toova", "Laclair", "Yuna", "Ewan", "Cetie", "Shanie"};
        int Unit4 = ListRate4.length;

        //Rate Up 5 AS
        double Rate5AS = .02;
        double Rate5AS10 = .02;
        String[] ListRate5AS = new String[]{"SuzetteAS","MightyAS"};
        int Unit5AS = ListRate5AS.length;
        //Rate Up 4 Special
        double Rate4Special = 0;
        double Rate4Special10 = 0;
        String[] List4Special = new String[]{""};
        int Unit4Special = List4Special.length;
        //Rate Up 3 Special
        double Rate3Special = 0;
        String[] List3Special = new String[]{""};
        int Unit3Special = List3Special.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Raven", "Myron", "Yio"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Raven", "Myron", "Yio"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .60;
        double RateSubSecondary410 = 2.33;
        String[] ListSubSecondary4 = new String[]{"Kreevo", "Pom", "Prai", "Soira", "Nero", "Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.21;
        String[] ListSubSecondary3 = new String[]{"Kreevo", "Pom", "Prai", "Soira", "Nero", "Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .40;
        double RateSub410 = 2.15;
        String[] ListSub4 = new String[]{"Nikeh", "Otoha", "Akane", "Komachi", "Lingli", "Bivette", "Lele", "Sevyn", "Breeno", "Benedict", "Denny", "Miranda", "Cyuca", "Nomar", "Foran", "Ciel", "Parisa", "Darunis", "Rovella", "Yazuki", "Rufus", "May", "Samora", "Ruina", "Lovinia", "Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = .99;
        String[] ListSub3 = new String[]{"Nikeh", "Otoha", "Akane", "Komachi", "Lingli", "Raven", "Bivette", "Lele", "Sevyn", "Breeno", "Myron", "Yio", "Benedict", "Denny", "Miranda", "Cyuca", "Nomar", "Foran", "Ciel", "Parisa", "Darunis", "Rovella", "Yazuki", "Rufus", "May", "Samora", "Ruina", "Lovinia", "Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia", "Naylia", "Menesia", "Rodeia", "Sauria", "Asia", "Urania", "Pasia", "Yssis", "Natore", "Tohme", "Esta", "Bria", "Nixa", "Emms", "Ctos", "Noome", "Uuo", "Koot", "Oor", "Een", "Saithe", "Tayme", "Toole"};
        int UnitStandard3 = ListStandard3.length;


        double pick = (Math.random()*100);
        int pick2;
        if(pullNum == 9){
            if((pick = pick-Rate5AS10*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            }else if((pick = pick-RateMain510*UnitMain5)<=0) {
                pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate4Special10*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if((pick = pick-Rate5AS*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            } else if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            }else if((pick = pick-Rate4Special*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate3Special*Unit3Special)<=0) {
                pick2 = (int)(Math.random()*Unit3Special);
                gachalist[pullNum] = List3Special[pick2]+"3";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void RenriBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Renri","Cetie"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Renri","Cetie"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .08;
        double RateSecondary510 = .09;
        String[] ListSecondary5 = new String[]{"Isuka","Shion","Suzette","Anabel","Nagi","Myrus","Melina","Bertrand","Felmina"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .10;
        double RateSecondary410 = .42;
        String[] ListSecondary4 = new String[]{"Isuka","Shion","Suzette","Anabel","Nagi","Myrus","Melina","Bertrand","Felmina"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .12;
        double Rate510 = .14;
        String[] ListRate5 = new String[]{"Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Mariel","Shanie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .16;
        double Rate410 = .51;
        String[] ListRate4 = new String[]{"Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Mariel","Shanie"};
        int Unit4 = ListRate4.length;

        //Rate Up 5 AS
        double Rate5AS = .02;
        double Rate5AS10 = .02;
        String[] ListRate5AS = new String[]{"SuzetteAS"};
        int Unit5AS = ListRate5AS.length;
        //Rate Up 4 Special
        double Rate4Special = 5;
        double Rate4Special10 = 15;
        String[] List4Special = new String[]{"Ciel"};
        int Unit4Special = List4Special.length;
        //Rate Up 3 Special
        double Rate3Special = 10;
        String[] List3Special = new String[]{"Ciel"};
        int Unit3Special = List3Special.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Bivette","Lele","Otoha"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Bivette","Lele","Otoha"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .58;
        double RateSubSecondary410 = 2.02;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.16;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .24;
        double RateSub410 = 1.76;
        String[] ListSub4 = new String[]{"Raven","Sevyn","Breeno","Myron","Nikeh","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo","Zilva","Nonold"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = .68;
        String[] ListSub3 = new String[]{"Raven","Sevyn","Breeno","Myron","Nikeh","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo","Zilva","Nonold"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        int pick2;
        if(pullNum == 9){
            if((pick = pick-Rate5AS10*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            }else if((pick = pick-RateMain510*UnitMain5)<=0) {
                pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate4Special10*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if((pick = pick-Rate5AS*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            } else if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            }else if((pick = pick-Rate4Special*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate3Special*Unit3Special)<=0) {
                pick2 = (int)(Math.random()*Unit3Special);
                gachalist[pullNum] = List3Special[pick2]+"3";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void ShannonBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Shannon","Mariel"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Shannon","Mariel"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .08;
        double RateSecondary510 = .09;
        String[] ListSecondary5 = new String[]{"Isuka","Shion","Suzette","Anabel","Nagi","Myrus","Melina","Bertrand","Felmina"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .10;
        double RateSecondary410 = .42;
        String[] ListSecondary4 = new String[]{"Isuka","Shion","Suzette","Anabel","Nagi","Myrus","Melina","Bertrand","Felmina"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .12;
        double Rate510 = .14;
        String[] ListRate5 = new String[]{"Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie","Shanie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .16;
        double Rate410 = .51;
        String[] ListRate4 = new String[]{"Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie","Shanie"};
        int Unit4 = ListRate4.length;

        //Rate Up 5 AS
        double Rate5AS = .02;
        double Rate5AS10 = .02;
        String[] ListRate5AS = new String[]{"SuzetteAS"};
        int Unit5AS = ListRate5AS.length;
        //Rate Up 4 Special
        double Rate4Special = 5;
        double Rate4Special10 = 15;
        String[] List4Special = new String[]{"Ciel"};
        int Unit4Special = List4Special.length;
        //Rate Up 3 Special
        double Rate3Special = 10;
        String[] List3Special = new String[]{"Ciel"};
        int Unit3Special = List3Special.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Bivette","Lele","Otoha"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Bivette","Lele","Otoha"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .58;
        double RateSubSecondary410 = 2.02;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.16;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .24;
        double RateSub410 = 1.76;
        String[] ListSub4 = new String[]{"Raven","Sevyn","Breeno","Myron","Nikeh","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo","Zilva","Nonold"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = .68;
        String[] ListSub3 = new String[]{"Raven","Sevyn","Breeno","Myron","Nikeh","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo","Zilva","Nonold"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        int pick2;
        if(pullNum == 9){
            if((pick = pick-Rate5AS10*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            }else if((pick = pick-RateMain510*UnitMain5)<=0) {
                pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate4Special10*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if((pick = pick-Rate5AS*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            } else if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            }else if((pick = pick-Rate4Special*Unit4Special)<=0) {
                pick2 = (int)(Math.random()*Unit4Special);
                gachalist[pullNum] = List4Special[pick2]+"4";
            }else if((pick = pick-Rate3Special*Unit3Special)<=0) {
                pick2 = (int)(Math.random()*Unit3Special);
                gachalist[pullNum] = List3Special[pick2]+"3";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void IsukaASBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Isuka","IsukaAS"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Isuka"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .09;
        double RateSecondary510 = .10;
        String[] ListSecondary5 = new String[]{"Shion","Suzette","Anabel","Nagi","Myrus","Melina"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .15;
        double RateSecondary410 = .55;
        String[] ListSecondary4 = new String[]{"Shion","Suzette","Anabel","Nagi","Myrus","Melina"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .12;
        double Rate510 = .15;
        String[] ListRate5 = new String[]{"Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie","Shanie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .18;
        double Rate410 = .65;
        String[] ListRate4 = new String[]{"Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie","Shanie"};
        int Unit4 = ListRate4.length;
        //Rate Up 5 AS
        double Rate5AS = .02;
        double Rate5AS10 = .02;
        String[] ListRate5AS = new String[]{"SuzetteAS"};
        int Unit5AS = ListRate5AS.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = 0;
        double RateSubMain410 = 0;
        String[] ListSubMain4 = new String[]{""};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 0;
        String[] ListSubMain3 = new String[]{""};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .60;
        double RateSubSecondary410 = 2.33;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.20;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .43;
        double RateSub410 = 2.25;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo","Nonold"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = 1.03;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo","Nonold"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        int pick2;
        if(pullNum == 9){
            if((pick = pick-Rate5AS10*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            }else if((pick = pick-RateMain510*UnitMain5)<=0) {
                pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if((pick = pick-Rate5AS*Unit5AS)<=0) {
                pick2 = (int) (Math.random() * Unit5AS);
                gachalist[pullNum] = ListRate5AS[pick2] + "5";
            } else if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void ClaudeBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Claude"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Claude"};
        int UnitMain4 = ListMain4.length;


        //Rate Up Main Unit 5*
        double RateMain50 = .4;
        double RateMain5100 = .4;
        String[] ListMain50 = new String[]{"Isuka","Mighty"};
        int UnitMain50 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain40 = .2;
        double RateMain4100 = .72;
        String[] ListMain40 = new String[]{"Isuka","Mighty"};
        int UnitMain40 = ListMain4.length;


        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .10;
        double RateSecondary510 = .12;
        String[] ListSecondary5 = new String[]{"Shion","Suzette","Anabel","Nagi","Shanie","Myrus"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .15;
        double RateSecondary410 = .45;
        String[] ListSecondary4 = new String[]{"Shion","Suzette","Anabel","Nagi","Shanie","Myrus"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .15;
        double Rate510 = .18;
        String[] ListRate5 = new String[]{"Mariel","Toova","Lokido","Laclair","Ewan","Yuna","Cetie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .19;
        double Rate410 = .70;
        String[] ListRate4 = new String[]{"Mariel","Toova","Lokido","Laclair","Ewan","Yuna","Cetie"};
        int Unit4 = ListRate4.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Nikeh","Otoha","Akane","Komachi","Lingli"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Nikeh","Otoha","Akane","Komachi","Lingli"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .63;
        double RateSubSecondary410 = 2.53;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.15;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .42;
        double RateSub410 =2.27;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =1.08;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);

        if(pullNum == 9){
            if((pick = pick-RateMain510*UnitMain5)<=0) {
                int pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain5100*UnitMain50)<=0) {
                int pick2 = (int)(Math.random()*UnitMain50);
                gachalist[pullNum] = ListMain50[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                int pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateMain4100*UnitMain40)<=0) {
                int pick2 = (int)(Math.random()*UnitMain40);
                gachalist[pullNum] = ListMain40[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                int pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                int pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                int pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain50 * UnitMain50) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain50);
                gachalist[pullNum] = ListMain50[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateMain40 * UnitMain40) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain40);
                gachalist[pullNum] = ListMain40[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                int pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                int pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                int pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void MightyASBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"MightyAS","Mighty"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Mighty"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .11;
        double RateSecondary510 = .12;
        String[] ListSecondary5 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi","Shanie","Myrus"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .17;
        double RateSecondary410 = .72;
        String[] ListSecondary4 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi","Shanie","Myrus"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .13;
        double Rate510 = .17;
        String[] ListRate5 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .21;
        double Rate410 = .72;
        String[] ListRate4 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie"};
        int Unit4 = ListRate4.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = 0;
        double RateSubMain410 = 0;
        String[] ListSubMain4 = new String[]{""};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 0;
        String[] ListSubMain3 = new String[]{""};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .64;
        double RateSubSecondary410 = 2.48;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.22;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .45;
        double RateSub410 = 2.36;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = 1.1;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.50;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);

        if(pullNum == 9){
            if((pick = pick-RateMain510*UnitMain5)<=0) {
                int pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                int pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                int pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                int pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                int pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                int pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                int pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                int pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void TsukihaBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Tsukiha","Shion"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Tsukiha","Shion"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .12;
        double RateSecondary510 = .14;
        String[] ListSecondary5 = new String[]{"Suzette","Anabel","Isuka","Nagi"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .17;
        double RateSecondary410 = .57;
        String[] ListSecondary4 = new String[]{"Suzette","Anabel","Isuka","Nagi"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .22;
        double Rate510 = .25;
        String[] ListRate5 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .23;
        double Rate410 = .82;
        String[] ListRate4 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie"};
        int Unit4 = ListRate4.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Nikeh","Otoha","Akane","Komachi","Lingli"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Nikeh","Otoha","Akane","Komachi","Lingli"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .65;
        double RateSubSecondary410 = 3.2;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.15;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .40;
        double RateSub410 =2.22;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =.98;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Yazuki","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.58;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);

        if(pullNum == 9){
            if((pick = pick-RateMain510*UnitMain5)<=0) {
                int pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                int pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                int pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                int pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                int pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                int pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                int pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                int pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void FelminaBanner(int pullNum, String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Felmina"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = .131;
        String[] ListMain4 = new String[]{"Felmina"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .19;
        double RateSecondary510 = .21;
        String[] ListSecondary5 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .20;
        double RateSecondary410 = .69;
        String[] ListSecondary4 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .22;
        double Rate510 = .25;
        String[] ListRate5 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .23;
        double Rate410 = .82;
        String[] ListRate4 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna"};
        int Unit4 = ListRate4.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Miyu"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Miyu"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .68;
        double RateSubSecondary410 = 2.63;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.16;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .45;
        double RateSub410 =2.39;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =.97;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.70;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);

        if(pullNum == 9){
            if((pick = pick-RateMain510*UnitMain5)<=0) {
                int pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                int pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                int pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                int pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                int pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                int pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                int pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                int pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void BertrandBanner(int pullNum,String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"Bertrand"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = .131;
        String[] ListMain4 = new String[]{"Bertrand"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .19;
        double RateSecondary510 = .21;
        String[] ListSecondary5 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .20;
        double RateSecondary410 = .69;
        String[] ListSecondary4 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .22;
        double Rate510 = .25;
        String[] ListRate5 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .23;
        double Rate410 = .82;
        String[] ListRate4 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna"};
        int Unit4 = ListRate4.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = .7;
        double RateSubMain410 = 3.2;
        String[] ListSubMain4 = new String[]{"Miyu"};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 1.4;
        String[] ListSubMain3 = new String[]{"Miyu"};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .68;
        double RateSubSecondary410 = 2.63;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.16;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .45;
        double RateSub410 =2.39;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =.97;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.70;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);

        if(pullNum == 9){
            if((pick = pick-RateMain510*UnitMain5)<=0) {
                int pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                int pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                int pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                int pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                int pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                int pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                int pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                int pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void SuzetteASBanner(int pullNum,String[] gachalist){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"SuzetteAS","Suzette"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = .131;
        String[] ListMain4 = new String[]{"Suzette"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .12;
        double RateSecondary510 = .15;
        String[] ListSecondary5 = new String[]{"Shion","Anabel","Isuka","Nagi"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .22;
        double RateSecondary410 = .74;
        String[] ListSecondary4 = new String[]{"Shion","Anabel","Isuka","Nagi"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .17;
        double Rate510 = .20;
        String[] ListRate5 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .25;
        double Rate410 = .89;
        String[] ListRate4 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna"};
        int Unit4 = ListRate4.length;

        //Rate Up Sub Main 4*
        double RateSubMain4 = 0;
        double RateSubMain410 = 0;
        String[] ListSubMain4 = new String[]{""};
        int UnitSubMain4 = ListSubMain4.length;
        //Rate Up Sub Main 3*
        double RateSubMain3 = 0;
        String[] ListSubMain3 = new String[]{""};
        int UnitSubMain3 = ListSubMain3.length;
        //Rate Up Sub Secondary 4*
        double RateSubSecondary4 = .62;
        double RateSubSecondary410 = 2.62;
        String[] ListSubSecondary4 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary4 = ListSubSecondary4.length;
        //Rate Up Sub Secondary 3*
        double RateSubSecondary3 = 1.12;
        String[] ListSubSecondary3 = new String[]{"Kreevo","Pom","Prai","Soira","Nero","Erina"};
        int UnitSubSecondary3 = ListSubSecondary3.length;
        //Rate Up Sub 4*
        double RateSub4 = .47;
        double RateSub410 =2.42;
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Miyu","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =.87;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Miyu","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.85;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);

        if(pullNum == 9){
            if((pick = pick-RateMain510*UnitMain5)<=0) {
                int pick2 = (int)(Math.random()*UnitMain5);
                gachalist[pullNum] = ListMain5[pick2]+"5";
            }else if((pick = pick-RateMain410*UnitMain4)<=0) {
                int pick2 = (int)(Math.random()*UnitMain4);
                gachalist[pullNum] = ListMain4[pick2]+"4";
            }else if((pick = pick-RateSecondary510*UnitSecondary5)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2]+"5";
            }else if((pick = pick-RateSecondary410*UnitSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2]+"4";
            }else if((pick = pick-Rate510*Unit5)<=0) {
                int pick2 = (int)(Math.random()*Unit5);
                gachalist[pullNum] = ListRate5[pick2]+"5";
            }else if((pick = pick-Rate410*Unit4)<=0) {
                int pick2 = (int)(Math.random()*Unit4);
                gachalist[pullNum] = ListRate4[pick2]+"4";
            }else if((pick = pick-RateSubMain410*UnitSubMain4)<=0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            }else if((pick = pick-RateSubSecondary410*UnitSubSecondary4)<=0) {
                int pick2 = (int)(Math.random()*UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2]+"4";
            }else{
                int pick2 = (int)(Math.random()*UnitSub4);
                gachalist[pullNum] = ListSub4[pick2]+"4";
            }
        }
        else {
            if ((pick = pick - RateMain5 * UnitMain5) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain5);
                gachalist[pullNum] = ListMain5[pick2] + "5";
            } else if ((pick = pick - RateMain4 * UnitMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitMain4);
                gachalist[pullNum] = ListMain4[pick2] + "4";
            } else if ((pick = pick - RateSecondary5 * UnitSecondary5) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary5);
                gachalist[pullNum] = ListSecondary5[pick2] + "5";
            } else if ((pick = pick - RateSecondary4 * UnitSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSecondary4);
                gachalist[pullNum] = ListSecondary4[pick2] + "4";
            } else if ((pick = pick - Rate5 * Unit5) <= 0) {
                int pick2 = (int) (Math.random() * Unit5);
                gachalist[pullNum] = ListRate5[pick2] + "5";
            } else if ((pick = pick - Rate4 * Unit4) <= 0) {
                int pick2 = (int) (Math.random() * Unit4);
                gachalist[pullNum] = ListRate4[pick2] + "4";
            } else if ((pick = pick - RateSubMain4 * UnitSubMain4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain4);
                gachalist[pullNum] = ListSubMain4[pick2] + "4";
            } else if ((pick = pick - RateSubMain3 * UnitSubMain3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubMain3);
                gachalist[pullNum] = ListSubMain3[pick2] + "3";
            } else if ((pick = pick - RateSubSecondary4 * UnitSubSecondary4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary4);
                gachalist[pullNum] = ListSubSecondary4[pick2] + "4";
            } else if ((pick = pick - RateSubSecondary3 * UnitSubSecondary3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSubSecondary3);
                gachalist[pullNum] = ListSubSecondary3[pick2] + "3";
            } else if ((pick = pick - RateSub4 * UnitSub4) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub4);
                gachalist[pullNum] = ListSub4[pick2] + "4";
            } else if ((pick = pick - RateSub3 * UnitSub3) <= 0) {
                int pick2 = (int) (Math.random() * UnitSub3);
                gachalist[pullNum] = ListSub3[pick2] + "3";
            } else {
                int pick2 = (int) (Math.random() * UnitStandard3);
                gachalist[pullNum] = ListStandard3[pick2] + "3";
            }
        }
    }
    public static void fiveBanner(MessageReceivedEvent event,int pullNum, String[] gachalist) {
        //Rate Main 5*
        double RateMain5 = .5;
        double RateMain510 = .5;
        String[] ListMain5 = new String[]{"Elga","ToovaAS","IsukaAS","MightyAS","Tsukiha","Claude"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = 1;
        double RateSecondary510 = 1;
        String[] ListSecondary5 = new String[]{"Myrus","Melina","SuzetteAS","Bertrand","Felmina"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up 5*
        double Rate5 = 7.95;
        double Rate510 = 7.95;
        String[] ListRate5 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi","Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Shanie","Cetie","Myrus","Melina"};
        int Unit5 = ListRate5.length;
        double pick = (Math.random()*100);

        String gachatemp = "Failure";

        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            gachatemp = ListMain5[pick2];
            gachalist[pullNum] = ListMain5[pick2]+"5";
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            gachatemp = ListSecondary5[pick2];
            gachalist[pullNum] = ListSecondary5[pick2]+"5";
        }else{
            int pick2 = (int) (Math.random() * Unit5);
            gachatemp = ListRate5[pick2];
            gachalist[pullNum] = ListRate5[pick2] + "5";
        }
        //System.out.println("Temp is: " + gachaTest(event,gachatemp,"16"));
        replaceSelected(event.getAuthor().getId(),gachaTest(event,gachatemp,"16"),gachatemp,16);
    }
    public static void jpBanner(int pullNum, String[] gachalist) {
        //Rate Main 5*
        double RateMain5 = 0;
        double RateMain510 = 0;
        String[] ListMain5 = new String[]{""};
        int UnitMain5 = ListMain5.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = 0;
        double RateSecondary510 = 0;
        String[] ListSecondary5 = new String[]{""};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up 5*
        double Rate5 = 1;
        double Rate510 = 1;
        String[] ListRate5 = new String[]{"Dewey","Elga","Filo","Hozuki","Illulu","Radica","Ravi","Rosetta","Shigure","Tsubame","Veina","Viacca"};
        int Unit5 = ListRate5.length;
        double pick = (Math.random()*100);
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            gachalist[pullNum] = ListMain5[pick2]+"5";
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            gachalist[pullNum] = ListSecondary5[pick2]+"5";
        }else{
            int pick2 = (int) (Math.random() * Unit5);
            gachalist[pullNum] = ListRate5[pick2] + "5";
        }
    }


    public static String gachaTest(MessageReceivedEvent event,String UnitName,String Light){
        String fileName = "Storage3.txt";
        String line;
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null){
                if(line.startsWith(event.getAuthor().getId())){
                    if(line.startsWith(event.getAuthor().getId()+" "+ UnitName)){
                        int leng = event.getAuthor().getId().length() + UnitName.length()+1;
                        bufferedReader.close();
                        return line.substring(leng);
                    }
                }
            }
            add2(event.getAuthor().getId(),UnitName,Light);
            bufferedReader.close();
        }catch(FileNotFoundException ex){
            System.out.println("     Unable to open file '" + fileName + "'");
        }catch(IOException ex){
            System.out.println("     Error reading file '" + fileName + "'");
        }
        return "";
    }
    public static void add2(String UserID, String UnitName, String Light){
        try{
            Writer output;
            output = new BufferedWriter(new FileWriter("Storage3.txt", true));
            output.append("\n" + UserID + " " + UnitName + Light);
            output.close();
        }catch(Exception e){
            System.out.println("     Problem initializing data.");
        }
    }
    public static void replaceSelected(String replaceWith, String type, String UnitName, int Light){
        try{
            BufferedReader file = new BufferedReader(new FileReader("Storage3.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null){
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(replaceWith + " " + UnitName + type, replaceWith + " " + UnitName + String.valueOf(Integer.parseInt(type) + Light));
            //System.out.println(inputStr.substring(1,inputStr.length()));
            //inputStr = inputStr.replace(replaceWith + (Integer.parseInt(inputStr.substring(inputStr.indexOf(0),inputStr.length()))),replaceWith + (Integer.parseInt(inputStr.substring(inputStr.indexOf(0),inputStr.length()))+1));

            FileOutputStream fileOut = new FileOutputStream("Storage3.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("     New Roll Added.");
        }
    }
    public static void gachaMeList(MessageReceivedEvent event){
        String fileName = "Storage3.txt";
        String output = "```";
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(event.getAuthor().getId())) {
                    if (line.startsWith(event.getAuthor().getId())) {
                        output = output + line.substring(event.getAuthor().getId().length()) + "\n";
                    }
                }
            }
            output = output + "```";
            bufferedReader.close();
            event.getTextChannel().sendMessage(output).queue();
        }catch(FileNotFoundException ex){
            System.out.println("     Unable to open file '" + fileName + "'");
        }catch(IOException ex){
            System.out.println("     Error reading file '" + fileName + "'");
        }catch(NullPointerException ex){
            System.out.println("     Null Pointer Exception?");
        }
    }
}
