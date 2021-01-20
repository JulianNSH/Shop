package github.JulianNSH;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;
import static java.lang.System.exit;

public class Sales {

    private String[] nameProductSaleArr;

    private double[] productPriceBeforeSaleArr;

    private double[] discountArr;

    private double[] priceAfterDiscountArr;

    Sales(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    public int salesMenu(){
        System.out.println("\n+==========SALES============+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>Modify                  |");
        System.out.println("|<4>Delete                  |");
        System.out.println("|<5>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createSalesData(); break;
            case 2: showSalesData();  break;
            case 3: modifySalesData(); break;
            case 4: deleteSalesData(); break;
            case 5: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); salesMenu();
        }
        return 0;
    }
    private void createSalesData(){
        System.out.println("\n+======SALES CREATE======+");
        System.out.println(" Number of SALES>>> ");
        int i = scn.nextInt();

        nameProductSaleArr = new String[i];
        discountArr = new double[i];
        productPriceBeforeSaleArr = new double[i];
        priceAfterDiscountArr = new double[i];

        for (int j = 0; j<i; j++){
            assignInputToArray(j);
        }
        salesMenu();
    }

    private void modifySalesData(){
        if(nameProductSaleArr != null){
            System.out.println("\n+======SALES MODIFY======+");
            System.out.println(" Input ID of Sale>>> ");
            int i = scn.nextInt()-1;
            //check if array with input ID exists
            if(i>=0 && i<nameProductSaleArr.length){
                assignInputToArray(i);
                System.out.println("~Sale data was modified successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        salesMenu();
    }

    private void deleteSalesData(){
        if(nameProductSaleArr != null){
            System.out.println("\n+======SALES DELETE======+");
            System.out.println(" Input ID of Sale>>> ");
            int i = scn.nextInt()-1;

            //check if array with input ID exists
            if(i>=0 && i<nameProductSaleArr.length){
                nameProductSaleArr = ArrayUtils.remove(nameProductSaleArr, i);
                discountArr = ArrayUtils.remove(discountArr, i);
                productPriceBeforeSaleArr = ArrayUtils.remove(priceAfterDiscountArr, i);
                priceAfterDiscountArr = ArrayUtils.remove(priceAfterDiscountArr, i);

                System.out.println("~Sale data was deleted successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        salesMenu();
    }

    private void showSalesData(){
        System.out.println("\n+======LIST PRODUCTS FOR SALE======+");
        System.out.print(  "|ID  |Product          |Price      |Discount %   |PriceWithDiscount    |\n");
        System.out.println("|----------------------------------------------------------------------|");
        if(nameProductSaleArr != null) {
            for(int j=0; j<nameProductSaleArr.length; j++) {
                System.out.printf("|%-4s", j+1);
                System.out.printf("|%-17s",nameProductSaleArr[j]);System.out.printf("|%-11s",productPriceBeforeSaleArr[j]);
                System.out.printf("|%-13s",discountArr[j]);  System.out.printf("|%-21s|\n",priceAfterDiscountArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|----------------------------------------------------------------------|");
        salesMenu();
    }

    private void assignInputToArray(int index){
        System.out.printf("SALE Nr-%s\n", index+1);
        System.out.println("*ProductForSale: ");
        nameProductSaleArr[index] = scn.next();
        System.out.println("*ProductPrice %: ");
        productPriceBeforeSaleArr[index] = scn.nextDouble();
        System.out.println("*Discount %: ");
        discountArr[index] = scn.nextDouble();
        System.out.println("*PriceAfterDiscount: ");
        priceAfterDiscountArr[index] = scn.nextDouble();
    }
}
