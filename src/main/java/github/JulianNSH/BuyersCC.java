package github.JulianNSH;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;

import static java.lang.System.exit;

public class BuyersCC {

    public static int[] buyerId;

    public static String[] nameBuyerArr;

    public static String[] surnameBuyerArr;

    public static double[] acquisitionsArr;

    public static double[] discountArr;

    public BuyersCC(){}

    //method that alloc array dimensions, used for importing data from XML file
    public void allocateMemory(int index){
        buyerId = new int[index];
        nameBuyerArr = new String[index];
        surnameBuyerArr = new String[index];
        acquisitionsArr = new double[index];
        discountArr = new double[index];
    }

    Scanner scn = new Scanner(System.in);

    public int buyersMenu(){
        System.out.println("\n+===BUYERS WITH CLUB CARD===+");
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
            case 1: createBuyersData(); break;
            case 2: showBuyersData();  break;
            case 3: modifyBuyersData(); break;
            case 4: deleteBuyersData(); break;
            case 5: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); buyersMenu();
        }
        return 0;
    }
    public void createBuyersData(){
        System.out.println("\n+======BUYERS CREATE======+");
        System.out.println(" Number Of ClubBuyers>>> ");
        int i = scn.nextInt();

        buyerId = new int[i];
        nameBuyerArr = new String[i];
        surnameBuyerArr = new String[i];
        acquisitionsArr = new double[i];
        discountArr = new double[i];

        for (int j = 0; j<i; j++){
            assignInputToArray(j);
        }
        buyersMenu();
    }

    private void modifyBuyersData(){
        if(nameBuyerArr != null){
            System.out.println("\n+======BUYER MODIFY======+");
            System.out.println(" Input ID of Buyer>>> ");
            int i = scn.nextInt();
            //find data with specified id
            for (int j = 0; j< buyerId.length; j++){
                if (buyerId[j]==i) {
                    assignInputToArray(j);
                    System.out.println("~Buyer data was modified successfully~");
                }
                if (j==buyerId.length){
                    System.out.println("Buyer with given ID doesn't exist");
                }
            }

        } else {
            System.out.println("!!!Empty Data!!!");
        }

        buyersMenu();
    }

    private void deleteBuyersData(){
        if(nameBuyerArr != null){
            System.out.println("\n+======BUYER DELETE======+");
            System.out.println(" Input ID of Buyer>>> ");
            int i = scn.nextInt();
            //find data with specified id
            for (int j = 0; j< buyerId.length; j++){
                if (buyerId[j]==i) {
                    buyerId = ArrayUtils.remove(buyerId, j);
                    nameBuyerArr = ArrayUtils.remove(nameBuyerArr, j);
                    surnameBuyerArr = ArrayUtils.remove(surnameBuyerArr, j);
                    acquisitionsArr = ArrayUtils.remove(acquisitionsArr, j);
                    discountArr = ArrayUtils.remove(discountArr, j);

                    System.out.println("~Buyer data was modified successfully~");
                }
                if (j==buyerId.length){
                    System.out.println("Buyer with given ID doesn't exist");
                }
            }

        } else {
            System.out.println("!!!Empty Data!!!");
        }

        buyersMenu();
    }

    public void showBuyersData(){
        System.out.println("\n+======LIST OF BUYERS WITH CLUB CARD======+");
        System.out.print(  "|ID  |NameBuyer      |SurnameBuyer   |Acquisitions  |Discount  |\n");
        System.out.println("|--------------------------------------------------------------|");
        if(nameBuyerArr != null) {
            for(int j=0; j<nameBuyerArr.length; j++) {
                System.out.printf("|%-4s", buyerId[j]);
                System.out.printf("|%-15s",nameBuyerArr[j]); System.out.printf("|%-15s",surnameBuyerArr[j]);
                System.out.printf("|%-14s",acquisitionsArr[j]);System.out.printf("|%-10s|\n",discountArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|--------------------------------------------------------------|");
        buyersMenu();
    }

    private void assignInputToArray(int index){
        System.out.printf("Buyer Nr-%s\n", index+1);
        System.out.println("*ID: ");
        buyerId[index] = scn.nextInt();
        System.out.println("*BuyerName: ");
        nameBuyerArr[index] = scn.next();
        System.out.println("*BuyerSurname: ");
        surnameBuyerArr[index] = scn.next();
        System.out.println("*Acquisitions: ");
        acquisitionsArr[index] = scn.nextDouble();
        System.out.println("*Discount: ");
        discountArr[index] = scn.nextDouble();
    }
}
