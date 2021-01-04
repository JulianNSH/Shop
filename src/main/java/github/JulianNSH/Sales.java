package github.JulianNSH;

import java.util.Scanner;
import static java.lang.System.exit;

public class Sales {
    int idSale;
    int idProduct;
    String nameProduct;
    double discount;
    double priceDiscount;

    Sales(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    int salesMenu(){
        System.out.println("\n+==========SALES============+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createSalesData(); break;
            case 2: showSalesData();  break;
            case 3: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); salesMenu();
        }
        return 0;
    }
    void createSalesData(){

        salesMenu();
    }
    void showSalesData(){
        salesMenu();
    }
}
