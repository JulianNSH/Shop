package github.JulianNSH;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;

import static java.lang.System.exit;

public class ProductGroups {

    public static int[] groupId;
    public static String[] groupNameArr;

    public static int[] numberOfUnitsArr;

    public ProductGroups(){}
    //method that alloc array dimensions, used for importing data from XML file
    public void allocateMemory(int index){
        groupId = new int[index];
        groupNameArr = new String[index];
        numberOfUnitsArr = new int[index];
    }

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

        groupId = new int[i];
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
            int i = scn.nextInt();
            //find data with specified id
            for (int j = 0; j< groupId.length; j++){
                if (groupId[j]==i) {
                    assignInputToArray(j);
                    System.out.println("~ProductGroups data was modified successfully~");
                }
                if (j==groupId.length){
                    System.out.println("ProductGroups with given ID doesn't exist");
                }
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
            int i = scn.nextInt();
            //find data with specified id
            for (int j = 0; j< groupId.length; j++){
                if (groupId[j]==i) {
                    groupId = ArrayUtils.remove(groupId, j);
                    groupNameArr = ArrayUtils.remove(groupNameArr, j);
                    numberOfUnitsArr = ArrayUtils.remove(numberOfUnitsArr, j);

                    System.out.println("~ProductGroups data was modified successfully~");
                }
                if (j==groupId.length){
                    System.out.println("ProductGroups with given ID doesn't exist");
                }
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

                System.out.printf("|%-4s", groupId[j]); System.out.printf("|%-19s",groupNameArr[j]);  System.out.printf("|%-13s|\n",numberOfUnitsArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|--------------------------------------|");
        productGroupsMenu();
    }

    private void assignInputToArray(int index){
        System.out.printf("Group Nr-%s\n", index+1);
        System.out.println("*ID: ");
        groupId[index] = scn.nextInt();
        System.out.println("*GroupName: ");
        groupNameArr[index] = scn.next();
        System.out.println("*UnitsInGroup: ");
        numberOfUnitsArr[index] = scn.nextInt();
    }
}
