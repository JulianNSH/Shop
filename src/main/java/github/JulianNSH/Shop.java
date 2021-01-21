package github.JulianNSH;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/*
*               SHOP APPLICATION
*                  (console)
*                  PHASE II
*
*   Add the functionality to read/write/update information to a physical file
*/
public class Shop {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        Menu start = new Menu();
        start.showMenu();           //call the menu method
    }
}
