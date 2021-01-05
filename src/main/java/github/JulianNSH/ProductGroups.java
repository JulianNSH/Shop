package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class ProductGroups {
    int idProductGroup;
    String groupName;
    String[] groupNameArr;

    int numberOfUnits;
    int[] numberOfUnitsArr;

    ProductGroups(){}

    int i = 0;
    Scanner scn = new Scanner(System.in);

    int productGroupsMenu(){
        System.out.println("\n+======PRODUCT GROUPS=======+");
        System.out.println("|<1>Create                  |");
        System.out.println("|<2>Show                    |");
        System.out.println("|<3>                  <<Back|");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i=scn.nextInt();
        switch (i){
            case 1: createProductGroupsData(); break;
            case 2: showProductGroupsData();  break;
            case 3: return 0;
            case 0: exit(0); break;
            default:
                System.out.println("!!!Invalid Input!!!"); productGroupsMenu();
        }
        return 0;
    }
    void createProductGroupsData(){
        System.out.println("\n+======GROUPS CREATE======+");
        System.out.println(" Number of Groups>>> ");
        int i = scn.nextInt();

        groupNameArr = new String[i];
        numberOfUnitsArr = new int[i];

        for (int j = 0; j<i; j++){
            System.out.printf("Group Nr-%s\n", j+1);
            System.out.println("*GroupName: ");
            groupNameArr[j] = scn.next();
            System.out.println("*UnitsInGroup: ");
            numberOfUnitsArr[j] = scn.nextInt();
        }
        productGroupsMenu();
    }
    void showProductGroupsData(){
        System.out.println("\n+======LIST OF GROUPS======+");
        System.out.print(  "|GroupName          |ProductUnits |\n");
        System.out.println("|---------------------------------|");
        if(groupNameArr != null) {
            for(int j=0; j<groupNameArr.length; j++) {

                System.out.printf("|%-19s",groupNameArr[j]);  System.out.printf("|%-13s|\n",numberOfUnitsArr[j]);
            }
        } else {
            System.out.println("!!!Empty Data!!!");
        }
        System.out.println("|---------------------------------|");
        productGroupsMenu();
    }
}
