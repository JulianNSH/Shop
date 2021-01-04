package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class BuyersCC {
    int idBuyer;
    String Name;
    String Surname;
    double acquisitions;
    double discount;

    BuyersCC(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    int buyersMenu(){
        System.out.println("\n+===BUYERS WITH CLUB CARD===+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createBuyersData(); break;
            case 2: showBuyersData();  break;
            case 3: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); buyersMenu();
        }
        return 0;
    }
    void createBuyersData(){

        buyersMenu();
    }
    void showBuyersData(){
        buyersMenu();
    }

}
