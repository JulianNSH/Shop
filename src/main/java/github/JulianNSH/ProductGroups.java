package github.JulianNSH;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;

import static java.lang.System.exit;

public class ProductGroups {

    private String[] groupNameArr;

    private int[] numberOfUnitsArr;

    ProductGroups(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    public int productGroupsMenu(){
        System.out.println("\n+======PRODUCT GROUPS=======+");
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
            case 1: createProductGroupsData(); break;
            case 2: showProductGroupsData();  break;
            case 3: modifyProductGroupsData(); break;
            case 4: deleteProductGroupsData(); break;
            case 5: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); productGroupsMenu();
        }
        return 0;
    }
    private void createProductGroupsData(){
        System.out.println("\n+======GROUPS CREATE======+");
        System.out.println(" Number of Groups>>> ");
        int i = scn.nextInt();

        groupNameArr = new String[i];
        numberOfUnitsArr = new int[i];

        for (int j = 0; j<i; j++){
            assignInputToArray(j);
        }
        productGroupsMenu();
    }

    private  void modifyProductGroupsData(){
        if(groupNameArr != null){
            System.out.println("\n+======GROUP MODIFY======+");
            System.out.println(" Input ID of Group>>> ");
            int i = scn.nextInt()-1;
            //check if array with input ID exists
            if(i>=0 && i<groupNameArr.length){
                assignInputToArray(i);
                System.out.println("~Group data was modified successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        productGroupsMenu();
    }

    private void deleteProductGroupsData(){
        if(groupNameArr != null){
            System.out.println("\n+======GROUP DELETE======+");
            System.out.println(" Input ID of Group>>> ");
            int i = scn.nextInt()-1;
            //check if array with input ID exists
            if(i>=0 && i<groupNameArr.length){
                groupNameArr = ArrayUtils.remove(groupNameArr, i);
                numberOfUnitsArr = ArrayUtils.remove(numberOfUnitsArr, i);

                System.out.println("~Product data was deleted successfully~");
            } else {
                System.out.println("!!!Input ID doesn't exist!!!");
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        productGroupsMenu();
    }

    private void showProductGroupsData(){
        System.out.println("\n+======LIST OF GROUPS======+");
        System.out.print(  "|ID  |GroupName          |ProductUnits |\n");
        System.out.println("|--------------------------------------|");
        if(groupNameArr != null) {
            for(int j=0; j<groupNameArr.length; j++) {

                System.out.printf("|%-4s", j+1); System.out.printf("|%-19s",groupNameArr[j]);  System.out.printf("|%-13s|\n",numberOfUnitsArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|--------------------------------------|");
        productGroupsMenu();
    }

    private void assignInputToArray(int index){
        System.out.printf("Group Nr-%s\n", index+1);
        System.out.println("*GroupName: ");
        groupNameArr[index] = scn.next();
        System.out.println("*UnitsInGroup: ");
        numberOfUnitsArr[index] = scn.nextInt();
    }
}
