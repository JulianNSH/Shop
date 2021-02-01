/*  IMPORT DATA CLASS
 * As ExportClass, use DOM Parser to retrieve
 * data from external XML file to memory
 */

package github.JulianNSH.XML;

import static java.lang.System.exit;

import github.JulianNSH.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImportDataFromXML {
    Scanner scn = new Scanner(System.in);
    public ImportDataFromXML(){}

    public int importMenu() throws ParserConfigurationException, IOException, SAXException {

        System.out.println("\n+========XML IMPORT========+");
        System.out.println("|<1>ALL                    |");
        System.out.println("|<2>Employees              |");
        System.out.println("|<3>Products               |");
        System.out.println("|<4>Product Groups         |");
        System.out.println("|<5>Buyers With Club Cards |");
        System.out.println("|<6>Sales                  |");
        System.out.println("|<7>                 <<Back|");
        System.out.println("|<0>Exit                   |");
        System.out.println("+==========================+");
        System.out.println("Enter Option>>> ");

        int i = scn.nextInt();

        switch (i) {
            case 1: importAll(); break;
            case 2: importEmployees(); break;
            case 3: importProducts(); break;
            case 4: importProductGroups(); break;
            case 5: importBuyers(); break;
            case 6: importSales(); break;
            case 7: return 0;
            case 0: exit(0);
            default: System.out.println("!!!Invalid Input!!!"); importMenu();
        }
        return 0;
    }

    private void importAll() throws ParserConfigurationException, IOException, SAXException {
        importEmployees();
        importProducts();
        importProductGroups();
        importBuyers();
        importSales();

        importMenu();

    }

    private void importEmployees() throws ParserConfigurationException, IOException, SAXException {
        //create new file instance
        File employeesXMLFile = new File("src/main/resources/employees.xml");

        if(employeesXMLFile.exists()) {
            //obtain a Document from XML file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document fromXML = dBuilder.parse(employeesXMLFile);

            //put all text nodes in normal form
            fromXML.getDocumentElement().normalize();

            //get root node
            NodeList employeeList = fromXML.getElementsByTagName("employee");

            Employees tempObj = new Employees();
            tempObj.allocateMemory(employeeList.getLength());//using class method to allocate memory to the arrays

            for (int i = 0; i < employeeList.getLength(); i++) {
                //get node from document tree
                Node employeeNode = employeeList.item(i);
                //check if node is a element of tree
                if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element employeeElement = (Element) employeeNode;
                    //get elements from node by tag name and assign it to arrays
                    Employees.employeeId[i] = Integer.parseInt(employeeElement.getAttribute("id"));
                    Employees.nameArr[i] = employeeElement.getElementsByTagName("name").item(0).getTextContent();
                    Employees.surnameArr[i] = employeeElement.getElementsByTagName("surname").item(0).getTextContent();
                    Employees.positionArr[i] = employeeElement.getElementsByTagName("position").item(0).getTextContent();
                    Employees.ageArr[i] = Integer.parseInt(employeeElement.getElementsByTagName("age").item(0).getTextContent());
                    Employees.salaryArr[i] = Double.parseDouble(employeeElement.getElementsByTagName("salary").item(0).getTextContent());
                }
            }
            System.out.println("Imported successfully from 'employees.xml'");
        } else {
            System.out.println("!!!File 'employees.xml' doesn't exist!!!");
        }
    }

    /*
     * All the methods below work according to the same principle
     */

    private void importProducts() throws IOException, SAXException, ParserConfigurationException {

        File productsXMLFile = new File("src/main/resources/products.xml");

        if(productsXMLFile.exists()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document fromXML = dBuilder.parse(productsXMLFile);

            fromXML.getDocumentElement().normalize();

            NodeList productList = fromXML.getElementsByTagName("product");

            Products tempObj = new Products();
            tempObj.allocateMemory(productList.getLength());

            for (int i = 0; i < productList.getLength(); i++) {
                Node productNode = productList.item(i);

                if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element productElement = (Element) productNode;

                    Products.productId[i] = Integer.parseInt(productElement.getAttribute("id"));
                    Products.productNameArr[i] = productElement.getElementsByTagName("productName").item(0).getTextContent();
                    Products.productGroupIdArr[i] = Integer.parseInt(productElement.getElementsByTagName("productGroup").item(0).getTextContent());
                    Products.priceArr[i] = Double.parseDouble(productElement.getElementsByTagName("price").item(0).getTextContent());
                }
            }
            System.out.println("Imported successfully from 'products.xml'");
        } else {
            System.out.println("!!!File 'products.xml' doesn't exist!!!");
        }

    }

    private void importProductGroups() throws IOException, SAXException, ParserConfigurationException {

        File productGroupsXMLFile = new File("src/main/resources/groups.xml");

        if(productGroupsXMLFile.exists()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document fromXML = dBuilder.parse(productGroupsXMLFile);

            fromXML.getDocumentElement().normalize();

            NodeList groupList = fromXML.getElementsByTagName("group");

            ProductGroups tempObj = new ProductGroups();
            tempObj.allocateMemory(groupList.getLength());

            for (int i = 0; i < groupList.getLength(); i++) {
                Node groupNode = groupList.item(i);

                if (groupNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element groupElement = (Element) groupNode;

                    ProductGroups.groupId[i] = Integer.parseInt(groupElement.getAttribute("id"));
                    ProductGroups.groupNameArr[i] = groupElement.getElementsByTagName("groupName").item(0).getTextContent();
                    ProductGroups.numberOfUnitsArr[i] = Integer.parseInt(groupElement.getElementsByTagName("numberOfUnits").item(0).getTextContent());
                    }
            }
            System.out.println("Imported successfully from 'groups.xml'");
        } else {
            System.out.println("!!!File 'groups.xml' doesn't exist!!!");
        }
    }

    private void importBuyers() throws IOException, SAXException, ParserConfigurationException {

        File buyersXMLFile = new File("src/main/resources/buyers.xml");

        if(buyersXMLFile.exists()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document fromXML = dBuilder.parse(buyersXMLFile);

            fromXML.getDocumentElement().normalize();

            NodeList buyerList = fromXML.getElementsByTagName("buyer");

            BuyersCC tempObj = new BuyersCC();
            tempObj.allocateMemory(buyerList.getLength());//using method to allocate memory to the arrays

            for (int i = 0; i < buyerList.getLength(); i++) {
                Node buyerNode = buyerList.item(i);

                if (buyerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element buyerElement = (Element) buyerNode;

                    BuyersCC.buyerId[i] = Integer.parseInt(buyerElement.getAttribute("id"));
                    BuyersCC.nameBuyerArr[i] = buyerElement.getElementsByTagName("buyerName").item(0).getTextContent();
                    BuyersCC.surnameBuyerArr[i] = buyerElement.getElementsByTagName("buyerSurname").item(0).getTextContent();
                    BuyersCC.acquisitionsArr[i] = Double.parseDouble(buyerElement.getElementsByTagName("acquisitions").item(0).getTextContent());
                    BuyersCC.discountArr[i] = Double.parseDouble(buyerElement.getElementsByTagName("discount").item(0).getTextContent());
                }
            }
            System.out.println("Imported successfully from 'buyers.xml'");
        } else {
            System.out.println("!!!File 'buyers.xml' doesn't exist!!!");
        }
    }

    private void importSales() throws IOException, SAXException, ParserConfigurationException {
        File salesXMLFile = new File("src/main/resources/sales.xml");

        if(salesXMLFile.exists()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document fromXML = dBuilder.parse(salesXMLFile);

            fromXML.getDocumentElement().normalize();

            NodeList saleList = fromXML.getElementsByTagName("sale");

            Sales tempObj = new Sales();
            tempObj.allocateMemory(saleList.getLength());//using method to allocate memory to the arrays

            for (int i = 0; i < saleList.getLength(); i++) {
                Node saleNode = saleList.item(i);

                if (saleNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element saleElement = (Element) saleNode;

                    Sales.saleId[i] = Integer.parseInt(saleElement.getAttribute("id"));
                    Sales.productId[i] = Integer.parseInt(saleElement.getElementsByTagName("productId").item(0).getTextContent());
                    Sales.discountArr[i] = Double.parseDouble(saleElement.getElementsByTagName("discount").item(0).getTextContent());
                }
            }
            System.out.println("Imported successfully from 'sales.xml'");
        } else {
            System.out.println("!!!File 'sales.xml' doesn't exist!!!");
        }
    }

}
