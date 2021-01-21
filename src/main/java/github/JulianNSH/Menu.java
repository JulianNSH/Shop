package github.JulianNSH;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.exit;

public class Menu {
    //objects for further using in menu
    Employees employees = new Employees();
    Products products = new Products();
    ProductGroups productGroups = new ProductGroups();
    BuyersCC buyersCC = new BuyersCC();
    Sales sales = new Sales();

    //objects for Import/Export existent data
    ImportDataFromXML imp = new ImportDataFromXML();
    ExportDataToXML exp = new ExportDataToXML();

    Scanner scn = new Scanner(System.in);

    Menu() throws ParserConfigurationException {}

    // main menu that binds all entities
    public void showMenu() throws TransformerException, IOException, SAXException, ParserConfigurationException {

        System.out.println("\n+=========MAIN MENU=========+");
        System.out.println("|<1>Employees               |");
        System.out.println("|<2>Products                |");
        System.out.println("|<3>ProductGroups           |");
        System.out.println("|<4>BuyersWithClubCard      |");
        System.out.println("|<5>Sales                   |");
        System.out.println("+===========================+");
        System.out.println("|<6>Import From XML         |");
        System.out.println("|<7>Export To XML           |");
        System.out.println("+===========================+");
        System.out.println("|<0>Exit                    |");
        System.out.println("+===========================+");
        System.out.println("Enter Option>>> ");

        int i = scn.nextInt();

        // call menu methods from another classes
        switch (i){
            case 1: employees.employeeMenu();           showMenu(); break;
            case 2: products.productsMenu();            showMenu(); break;
            case 3: productGroups.productGroupsMenu();  showMenu(); break;
            case 4: buyersCC.buyersMenu();              showMenu(); break;
            case 5: sales.salesMenu();                  showMenu(); break;
            case 6: imp.importMenu();                   showMenu(); break;
            case 7: exp.exportMenu();                   showMenu(); break;
            case 0: exit(0);                                  break;
            default:
                System.out.println("!!!Invalid Input!!!"); showMenu();
        }

    }
}
