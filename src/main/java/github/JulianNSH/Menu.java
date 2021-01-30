package github.JulianNSH;
import github.JulianNSH.DataBase.ExportDataToDataBase;
import github.JulianNSH.DataBase.ImportDataFromDataBase;
import github.JulianNSH.XML.ExportDataToXML;
import github.JulianNSH.XML.ImportDataFromXML;
import github.JulianNSH.XML.UpdateDataFromXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import static java.lang.System.exit;

public class Menu {
    //objects for further using in menu
    Employees employees = new Employees();
    Products products = new Products();
    ProductGroups productGroups = new ProductGroups();
    BuyersCC buyersCC = new BuyersCC();
    Sales sales = new Sales();

    //objects for Import/Export/Update existent data in XML
    ImportDataFromXML impXML = new ImportDataFromXML();
    ExportDataToXML expXML = new ExportDataToXML();
    UpdateDataFromXML updXML = new UpdateDataFromXML();

    //objects for Import/Export/Update existent data in DataBase
    ImportDataFromDataBase impDB = new ImportDataFromDataBase();
    ExportDataToDataBase expDB = new ExportDataToDataBase();

    Scanner scn = new Scanner(System.in);

    Menu() throws ParserConfigurationException {}

    // main menu that binds all entities
    public void showMenu() throws TransformerException, IOException, SAXException, ParserConfigurationException, SQLException {

        System.out.println("\n+=========MAIN MENU=========+");
        System.out.println("|<1>Employees               |");
        System.out.println("|<2>Products                |");
        System.out.println("|<3>ProductGroups           |");
        System.out.println("|<4>BuyersWithClubCard      |");
        System.out.println("|<5>Sales                   |");
        System.out.println("+===========================+");
        System.out.println("|<6>Import From XML         |");
        System.out.println("|<7>Export To XML           |");
        System.out.println("|<8>Update XML              |");
        System.out.println("+===========================+");
        System.out.println("|<9> Import From DataBase   |");
        System.out.println("|<10>Export To DataBase     |");
        System.out.println("|<11>Update DataBase        |");
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
            case 6: impXML.importMenu();                   showMenu(); break;
            case 7: expXML.exportMenu();                   showMenu(); break;
            case 8: updXML.updateMenu();                   showMenu(); break;
           // case 10: expDB.connect();                      showMenu(); break;
            case 0: exit(0);                                  break;
            default:
                System.out.println("!!!Invalid Input!!!"); showMenu();
        }

    }
}
