package github.JulianNSH;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;
import static java.lang.System.exit;

public class Sales {

    public static int[]  saleId;

    public static int[] productId;

    public static double[] discountArr;


    public Sales(){}

    //method that alloc array dimensions, used for importing data from XML file
    public void allocateMemory(int index){
        saleId = new int[index];
        productId = new int[index];
        discountArr = new double[index];
    }
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

        int i=scn.nextInt();
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

        saleId = new int[i];
        productId = new int[i];
        discountArr = new double[i];

        for (int j = 0; j<i; j++){
            assignInputToArray(j);
        }
        salesMenu();
    }

    private void modifySalesData(){
        if(saleId != null){
            System.out.println("\n+======SALES MODIFY======+");
            System.out.println(" Input ID of Sale>>> ");
            int i = scn.nextInt()-1;
            //find data with specified id
            for (int j = 0; j< saleId.length; j++){
                if (saleId[j]==i) {
                    assignInputToArray(j);
                    System.out.println("~Sales data was modified successfully~");
                }
                if (j==saleId.length){
                    System.out.println("Sales with given ID doesn't exist");
                }
            }

        } else {
            System.out.println("!!!Empty Data!!!");
        }
        salesMenu();
    }

    private void deleteSalesData(){
        if(saleId != null){
            System.out.println("\n+======SALES DELETE======+");
            System.out.println(" Input ID of Sale>>> ");
            int i = scn.nextInt()-1;
            //find data with specified id
            for (int j = 0; j< saleId.length; j++){
                if (saleId[j]==i) {
                    saleId = ArrayUtils.remove(saleId, j);
                    productId = ArrayUtils.remove(productId, j);
                    discountArr = ArrayUtils.remove(discountArr, j);
                    System.out.println("~Sales data was modified successfully~");
                }
                if (j==saleId.length){
                    System.out.println("Sales with given ID doesn't exist");
                }
            }

        } else {
            System.out.println("!!!Empty Data!!!");
        }

        salesMenu();
    }

    private void showSalesData(){
        System.out.println("\n+======LIST PRODUCTS FOR SALE======+");
        System.out.print(  "|ID  |ProductID     |Discount %      |\n");
        System.out.println("|------------------------------------|");
        if(saleId != null) {
            for(int j=0; j<saleId.length; j++) {
                System.out.printf("|%-4s", saleId[j]);System.out.printf("|%-14s", productId[j]);
                System.out.printf("|%-16s|\n",discountArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|------------------------------------|");
        salesMenu();
    }

    private void assignInputToArray(int index){
        System.out.printf("SALE Nr-%s\n", index+1);
        System.out.println("*ID: ");
        saleId[index] = scn.nextInt();
        System.out.println("*ProductID: ");
        productId[index] = scn.nextInt();
        System.out.println("*Discount %: ");
        discountArr[index] = scn.nextDouble();
    }
}
