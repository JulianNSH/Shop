package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class BuyersCC {
    int idBuyer;
    String nameBuyer;
    String[] nameBuyerArr;

    String surnameBuyer;
    String[] surnameBuyerArr;

    double acquisitions;
    double[] acquisitionsArr;

    double discount;
    double[] discountArr;

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
        System.out.println("\n+======BUYERS CREATE======+");
        System.out.println(" Number Of ClubBuyers>>> ");
        int i = scn.nextInt();

        nameBuyerArr = new String[i];
        surnameBuyerArr = new String[i];
        acquisitionsArr = new double[i];
        discountArr = new double[i];

        for (int j = 0; j<i; j++){
            System.out.printf("Buyer Nr-%s\n", j+1);
            System.out.println("*BuyerName: ");
            nameBuyerArr[j] = scn.next();
            System.out.println("*BuyerSurname: ");
            surnameBuyerArr[j] = scn.next();
            System.out.println("*Acquisitions: ");
            acquisitionsArr[j] = scn.nextDouble();
            System.out.println("*Discount: ");
            discountArr[j] = scn.nextDouble();
        }
        buyersMenu();
    }
    void showBuyersData(){
        System.out.println("\n+======LIST OF BUYERS WITH CLUB CARD======+");
        System.out.print(  "|NameBuyer      |SurnameBuyer   |Acquisitions  |Discount  |\n");
        System.out.println("|---------------------------------------------------------|");
        if(nameBuyerArr != null) {
            for(int j=0; j<nameBuyerArr.length; j++) {

                System.out.printf("|%-15s",nameBuyerArr[j]); System.out.printf("|%-15s",surnameBuyerArr[j]);
                System.out.printf("|%-14s",acquisitionsArr[j]);System.out.printf("|%-10s|\n",discountArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|---------------------------------------------------------|");
        buyersMenu();
    }

}
