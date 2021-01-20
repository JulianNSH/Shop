package github.JulianNSH;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;

import static java.lang.System.exit;


public class Products {

    private String[] productGroupArr;

    private String[] productNameArr;

    private double[] priceArr;

    Products(){}

     int i = 0;
    Scanner scn = new Scanner(System.in);

    public int productsMenu(){
        System.out.println("\n+=========PRODUCTS==========+");
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
            case 1: createProductsData(); break;
            case 2: showProductsData();  break;
            case 3: modifyProductsData(); break;
            case 4: deleteProductsData(); break;
            case 5: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); productsMenu();
        }
        return 0;
    }
    private void createProductsData(){
        System.out.println("\n+======PRODUCTS CREATE======+");
        System.out.println(" Number of Products>>> ");
        int i = scn.nextInt();

        productNameArr = new String[i];
        productGroupArr = new String[i];
        priceArr = new double[i];

        for (int j = 0; j<i; j++){
            assignInputToArray(j);
        }
        productsMenu();
    }

    private void modifyProductsData(){
        if(productNameArr != null){
        System.out.println("\n+======PRODUCTS CREATE======+");
        System.out.println(" Input ID of Product>>> ");
        int i = scn.nextInt()-1;
            //check if array with input ID exists
            if(i>=0 && i<productNameArr.length){
                assignInputToArray(i);
                System.out.println("~Product data was modified successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        productsMenu();
    }
    private void deleteProductsData(){
        if(productNameArr != null){
            System.out.println("\n+======PRODUCTS DELETE======+");
            System.out.println(" Input ID of Product>>> ");
            int i = scn.nextInt()-1;
            //check if array with input ID exists
            if(i>=0 && i<productNameArr.length){
                productNameArr = ArrayUtils.remove(productNameArr, i);
                productGroupArr = ArrayUtils.remove(productGroupArr, i);
                priceArr = ArrayUtils.remove(priceArr, i);
                System.out.println("~Product data was deleted successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        productsMenu();
    }
    private void showProductsData(){
        System.out.println("\n+======LIST OF PRODUCTS======+");
        System.out.print(  "|ID  |Product          |GroupOfProduct   |Price    |\n");
        System.out.println("|--------------------------------------------------|");
        if(productNameArr != null) {
            for(int j=0; j<productNameArr.length; j++) {

                System.out.printf("|%-4s", j+1); System.out.printf("|%-17s",productNameArr[j]);
                System.out.printf("|%-17s",productGroupArr[j]);  System.out.printf("|%-9s|\n",priceArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|--------------------------------------------------|");
        productsMenu();
    }

    //method for input data, used to avoid code repeat
    private void assignInputToArray(int index){
        System.out.printf("Product Nr-%s\n", index+1);
        System.out.println("*Name: ");
        productNameArr[index] = scn.next();
        System.out.println("*GroupName: ");
        productGroupArr[index] = scn.next();
        System.out.println("*ProductPrice: ");
        priceArr[index] = scn.nextDouble();
    }
}
