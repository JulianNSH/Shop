package github.JulianNSH;

import github.JulianNSH.DataBase.ExportDataToDataBase;
import github.JulianNSH.DataBase.ImportDataFromDataBase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

/*
*               SHOP APPLICATION
*                  (console)
*                  PHASE III
*
*   Add the database to keep information instead of files.
*   The app will be able to add/edit/remove data directly in the database.
*/
public class Shop {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException, SQLException {
        Menu start = new Menu();
        start.showMenu();           //call the menu method

    }
}
