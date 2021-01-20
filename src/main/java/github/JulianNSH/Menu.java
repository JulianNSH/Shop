package github.JulianNSH;
import java.util.Scanner;
import static java.lang.System.exit;

public class Menu {
    //objects for further using in menu
    Employees employees = new Employees();
    Products products = new Products();
    ProductGroups productGroups = new ProductGroups();
    BuyersCC buyersCC = new BuyersCC();
    Sales sales = new Sales();

    int i = 0;
    Scanner scn = new Scanner(System.in);

    Menu(){}

    // main menu that binds all entities
    public void showMenu(){

        System.out.println("\n+=========MAIN MENU=========+");
        System.out.println("|<1>Employees               |");
        System.out.println("|<2>Products                |");
        System.out.println("|<3>ProductGroups           |");
        System.out.println("|<4>BuyersWithClubCard      |");
        System.out.println("|<5>Sales                   |");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        i = scn.nextInt();

        // call menu methods from another classes
        switch (i){
            case 1: employees.employeeMenu();           showMenu(); break;
            case 2: products.productsMenu();            showMenu(); break;
            case 3: productGroups.productGroupsMenu();  showMenu(); break;
            case 4: buyersCC.buyersMenu();              showMenu(); break;
            case 5: sales.salesMenu();                  showMenu(); break;
            case 0: exit(0);                                  break;
            default:
                System.out.println("!!!Invalid Input!!!"); showMenu();
        }

    }
}
