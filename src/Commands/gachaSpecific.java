package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class gachaSpecific {
    public static void pickMe(MessageReceivedEvent event){
        int pullNum = 1;
        String temp2 = event.getMessage().getContent().substring(7);
        String temp = "";
        int banner = 0;
        int rollcap = 10000;


        if (!temp2.contains(" ")){
            temp = temp2;
            banner = 0;
        }else{
            try{
                temp = temp2.substring(0, temp2.indexOf(" "));
                banner = Integer.valueOf(temp2.substring(temp2.indexOf(" ") + 1));
            }catch(NumberFormatException e){event.getTextChannel().sendMessage("Please use the correct format.").queue();return;}
        }


        switch(banner){
            case 1:
                while(!MightyASBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 2:
                while(!ClaudeBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 3:
                while(!IsukaASBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 4:
                while(!ShannonBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 5:
                while(!RenriBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 6:
                while(!ElgaBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 7:
                while(!ToovaASBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            case 9:
                while(!fiveBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }break;
            default:
                while(!fiveBanner(temp) && pullNum<rollcap){
                    pullNum++;
                }
        }
        if(pullNum == rollcap){
            event.getTextChannel().sendMessage(event.getAuthor().getName() + " will roll a " + temp + " only in their dreams").queue();
        }else{
            event.getTextChannel().sendMessage(event.getAuthor().getName() + " took " + pullNum + " rolls to roll " + temp).queue();
        }
    }
    public static boolean ToovaASBanner(String unit) {
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-Rate5AS*Unit5AS)<=0) {
            int pick2 = (int) (Math.random() * Unit5AS);
            return ListRate5AS[pick2].equals(unit);
        }else if((pick = pick-RateMain50*UnitMain50)<=0){
            int pick2 = (int)(Math.random()*UnitMain50);
            return ListMain50[pick2].equals(unit);
        }else if((pick = pick-RateMain40*UnitMain40)<=0){
            int pick2 = (int)(Math.random()*UnitMain40);
            return ListMain40[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4Special*Unit4Special)<=0){
            int pick2 = (int)(Math.random()*Unit4Special);
            return List4Special[pick2].equals(unit);
        }else if((pick = pick-Rate3Special*Unit3Special)<=0){
            int pick2 = (int)(Math.random()*Unit3Special);
            return List3Special[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean ElgaBanner(String unit) {
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-Rate5AS*Unit5AS)<=0) {
            int pick2 = (int) (Math.random() * Unit5AS);
            return ListRate5AS[pick2].equals(unit);
        }else if((pick = pick-RateMain50*UnitMain50)<=0){
            int pick2 = (int)(Math.random()*UnitMain50);
            return ListMain50[pick2].equals(unit);
        }else if((pick = pick-RateMain40*UnitMain40)<=0){
            int pick2 = (int)(Math.random()*UnitMain40);
            return ListMain40[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4Special*Unit4Special)<=0){
            int pick2 = (int)(Math.random()*Unit4Special);
            return List4Special[pick2].equals(unit);
        }else if((pick = pick-Rate3Special*Unit3Special)<=0){
            int pick2 = (int)(Math.random()*Unit3Special);
            return List3Special[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean RenriBanner(String unit){
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-Rate5AS*Unit5AS)<=0){
            int pick2 = (int)(Math.random()*Unit5AS);
            return ListRate5AS[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4Special*Unit4Special)<=0){
            int pick2 = (int)(Math.random()*Unit4Special);
            return List4Special[pick2].equals(unit);
        }else if((pick = pick-Rate3Special*Unit3Special)<=0){
            int pick2 = (int)(Math.random()*Unit3Special);
            return List3Special[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean ShannonBanner(String unit){
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-Rate5AS*Unit5AS)<=0){
            int pick2 = (int)(Math.random()*Unit5AS);
            return ListRate5AS[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4Special*Unit4Special)<=0){
            int pick2 = (int)(Math.random()*Unit4Special);
            return List4Special[pick2].equals(unit);
        }else if((pick = pick-Rate3Special*Unit3Special)<=0){
            int pick2 = (int)(Math.random()*Unit3Special);
            return List3Special[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean IsukaASBanner(String unit){
        //Rate Up Main Unit 5*
        double RateMain5 = .8;
        double RateMain510 = .8;
        String[] ListMain5 = new String[]{"IsukaAS","Isuka"};
        int UnitMain5 = ListMain5.length;
        //Rate Up Main Unit 4*
        double RateMain4 = .4;
        double RateMain410 = 1.31;
        String[] ListMain4 = new String[]{"Isuka"};
        int UnitMain4 = ListMain4.length;
        //Rate Up Secondary Unit 5*
        double RateSecondary5 = .09;
        double RateSecondary510 = .10;
        String[] ListSecondary5 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi","Myrus","Melina"};
        int UnitSecondary5 = ListSecondary5.length;
        //Rate Up Secondary Unit 4*
        double RateSecondary4 = .15;
        double RateSecondary410 = .55;
        String[] ListSecondary4 = new String[]{"Shion","Suzette","Anabel","Isuka","Nagi","Myrus","Melina"};
        int UnitSecondary4 = ListSecondary4.length;
        //Rate Up 5*
        double Rate5 = .12;
        double Rate510 = .15;
        String[] ListRate5 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie","Shanie"};
        int Unit5 = ListRate5.length;
        //Rate Up 4*
        double Rate4 = .18;
        double Rate410 = .65;
        String[] ListRate4 = new String[]{"Mariel","Mighty","Toova","Lokido","Laclair","Ewan","Yuna","Cetie","Shanie"};
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
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = 1.03;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean ClaudeBanner(String unit){
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
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =1.08;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.51;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean MightyASBanner(String unit){
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
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 = 1.1;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Nikeh","Otoha","Akane","Yio","Benedict","Denny","Miranda","Cyuca","Komachi","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Lingli","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.50;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean TsukihaBanner(String unit){
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
        String[] ListSub4 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub4 = ListSub4.length;
        //Rate Up Sub 3*
        double RateSub3 =.98;
        String[] ListSub3 = new String[]{"Raven","Bivette","Lele","Sevyn","Breeno","Myron","Yio","Benedict","Denny","Miranda","Cyuca","Nomar","Foran","Ciel","Parisa","Darunis","Rovella","Rufus","May","Samora","Ruina","Lovinia","Chiyo"};
        int UnitSub3 = ListSub3.length;

        //Rate Up Standard 3*
        double RateStandard3 = 1.58;
        String[] ListStandard3 = new String[]{"Kilqia","Naylia","Menesia","Rodeia","Sauria","Asia","Urania","Pasia","Yssis","Natore","Tohme","Esta","Bria","Nixa","Emms","Ctos","Noome","Uuo","Koot","Oor","Een","Saithe","Tayme","Toole"};
        int UnitStandard3 = ListStandard3.length;



        double pick = (Math.random()*100);
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean FelminaBanner(String unit){
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean BertrandBanner(String unit){
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean SuzetteASBanner(String unit){
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateMain4*UnitMain4)<=0){
            int pick2 = (int)(Math.random()*UnitMain4);
            return ListMain4[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary4*UnitSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary4);
            return ListSecondary4[pick2].equals(unit);
        }else if((pick = pick-Rate5*Unit5)<=0){
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }else if((pick = pick-Rate4*Unit4)<=0){
            int pick2 = (int)(Math.random()*Unit4);
            return ListRate4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain4*UnitSubMain4)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain4);
            return ListSubMain4[pick2].equals(unit);
        }else if((pick = pick-RateSubMain3*UnitSubMain3)<=0){
            int pick2 = (int)(Math.random()*UnitSubMain3);
            return ListSubMain3[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary4*UnitSubSecondary4)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary4);
            return ListSubSecondary4[pick2].equals(unit);
        }else if((pick = pick-RateSubSecondary3*UnitSubSecondary3)<=0){
            int pick2 = (int)(Math.random()*UnitSubSecondary3);
            return ListSubSecondary3[pick2].equals(unit);
        }else if((pick = pick-RateSub4*UnitSub4)<=0){
            int pick2 = (int)(Math.random()*UnitSub4);
            return ListSub4[pick2].equals(unit);
        }else if((pick = pick-RateSub3*UnitSub3)<=0){
            int pick2 = (int)(Math.random()*UnitSub3);
            return ListSub3[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*UnitStandard3);
            return ListStandard3[pick2].equals(unit);
        }
    }
    public static boolean fiveBanner(String unit) {
        //Rate Main 5*
        double RateMain5 = .5;
        double RateMain510 = .5;
        String[] ListMain5 = new String[]{"IsukaAS","MightyAS","Tsukiha","Claude"};
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
        if((pick = pick-RateMain5*UnitMain5)<=0){
            int pick2 = (int)(Math.random()*UnitMain5);
            return ListMain5[pick2].equals(unit);
        }else if((pick = pick-RateSecondary5*UnitSecondary5)<=0){
            int pick2 = (int)(Math.random()*UnitSecondary5);
            return ListSecondary5[pick2].equals(unit);
        }else{
            int pick2 = (int)(Math.random()*Unit5);
            return ListRate5[pick2].equals(unit);
        }
    }
}

