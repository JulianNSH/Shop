package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class Products {
    int idProduct;
    int groupId;
    String productName;
    double price;

    Products(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    int productsMenu(){
        System.out.println("\n+=========PRODUCTS==========+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createProductsData(); break;
            case 2: showProductsData();  break;
            case 3: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); productsMenu();
        }
        return 0;
    }
    void createProductsData(){

        productsMenu();
    }
    void showProductsData(){
        productsMenu();
    }
}
