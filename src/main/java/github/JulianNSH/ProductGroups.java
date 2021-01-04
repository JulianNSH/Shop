package github.JulianNSH;

import java.util.Scanner;

import static java.lang.System.exit;

public class ProductGroups {
    int idProductGroup;
    String groupName;
    int numberOfUnits;

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

        productGroupsMenu();
    }
    void showProductGroupsData(){
        productGroupsMenu();
    }
}
