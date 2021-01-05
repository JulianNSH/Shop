package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class Products {
    int idProduct;
    int groupId;
    String groupName;
    String[] productGroupArr;

    String productName;
    String[] productNameArr;

    double price;
    double[] priceArr;

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
        System.out.println("\n+======PRODUCTS CREATE======+");
        System.out.println(" Number of Products>>> ");
        int i = scn.nextInt();

        productNameArr = new String[i];
        productGroupArr = new String[i];
        priceArr = new double[i];

        for (int j = 0; j<i; j++){
            System.out.printf("Product Nr-%s\n", j+1);
            System.out.println("*Name: ");
            productNameArr[j] = scn.next();
            System.out.println("*GroupName: ");
            productGroupArr[j] = scn.next();
            System.out.println("*ProductPrice: ");
            priceArr[j] = scn.nextDouble();
        }
        productsMenu();
    }
    void showProductsData(){
        System.out.println("\n+======LIST OF PRODUCTS======+");
        System.out.print(  "|Product          |GroupOfProduct   |Price    |\n");
        System.out.println("|---------------------------------------------|");
        if(productNameArr != null) {
            for(int j=0; j<productNameArr.length; j++) {

                System.out.printf("|%-17s",productNameArr[j]); System.out.printf("|%-17s",productGroupArr[j]);  System.out.printf("|%-9s|\n",priceArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|---------------------------------------------|");
        productsMenu();
    }
}
