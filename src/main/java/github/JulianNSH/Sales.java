package github.JulianNSH;

import java.util.Scanner;
import static java.lang.System.exit;

public class Sales {
    int idSale;
    int idProduct;
    String nameProductSale;
    String[] nameProductSaleArr;

    double productPriceBeforeSale;
    double[] productPriceBeforeSaleArr;

    double discount;
    double[] discountArr;

    double priceAfterDiscount;
    double[] priceAfterDiscountArr;

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
        System.out.println("\n+======SALES CREATE======+");
        System.out.println(" Number of SALES>>> ");
        int i = scn.nextInt();

        nameProductSaleArr = new String[i];
        discountArr = new double[i];
        priceAfterDiscountArr = new double[i];

        for (int j = 0; j<i; j++){
            System.out.printf("SALE Nr-%s\n", j+1);
            System.out.println("*ProductForSale: ");
            nameProductSaleArr[j] = scn.next();
            System.out.println("*ProductPrice %: ");
            productPriceBeforeSaleArr[j] = scn.nextDouble();
            System.out.println("*Discount %: ");
            discountArr[j] = scn.nextDouble();
            System.out.println("*PriceAfterDiscount: ");
            priceAfterDiscountArr[j] = scn.nextDouble();
        }
        salesMenu();
    }
    void showSalesData(){
        System.out.println("\n+======LIST PRODUCTS FOR SALE======+");
        System.out.print(  "|Product          |Price      |Discount %   |PriceWithDiscount    |\n");
        System.out.println("|-----------------------------------------------------------------|");
        if(nameProductSaleArr != null) {
            for(int j=0; j<nameProductSaleArr.length; j++) {

                System.out.printf("|%-17s",nameProductSaleArr[j]);System.out.printf("|%-11s",productPriceBeforeSaleArr[j]);
                System.out.printf("|%-13s",discountArr[j]);  System.out.printf("|%-21s|\n",priceAfterDiscountArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|-----------------------------------------------------------------|");
        salesMenu();
    }
}
