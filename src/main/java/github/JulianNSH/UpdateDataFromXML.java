package github.JulianNSH;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.System.exit;

public class UpdateDataFromXML {
    Scanner scn = new Scanner(System.in);

    UpdateDataFromXML(){}

    public int updateMenu() throws ParserConfigurationException, SAXException, IOException, TransformerException {

        System.out.println("\n+==========UPDATE==========+");
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
            case 1: updateAll(); break;
            case 2: updateEmployees(); break;
            case 3: updateProducts(); break;
            case 4: updateProductGroups(); break;
            case 5: updateBuyers(); break;
            case 6: updateSales(); break;
            case 7: return 0;
            case 0: exit(0);
            default: System.out.println("!!!Invalid Input!!!"); updateMenu();
        }
        return 0;
    }


    private void updateAll() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        updateEmployees();
        updateProducts();
        updateProductGroups();
        updateBuyers();
        updateSales();

    }
    private void updateEmployees() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String path = "src/main/resources/employees.xml";
        // check if memory isn't empty
        if(Employees.nameArr !=null){
            //check if file exists in given path
            if (Files.isReadable(Paths.get(path))) {
                //obtain a Document from XML file
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document fromXML = dBuilder.parse(path);
                //put all text nodes in normal form
                fromXML.getDocumentElement().normalize();

                //get root node
                NodeList employeeList = fromXML.getElementsByTagName("employee");

                //loop the elements update
                if (Employees.nameArr.length < employeeList.getLength()) {
                    for (int j = 0; j < Employees.nameArr.length; j++) {
                        for (int i = 0; i < employeeList.getLength(); i++) {
                            //get node from document tree
                            Node employeeNode = employeeList.item(i);
                            //check if node is a element of tree and has the same index as array input
                            if (employeeNode.getNodeType() == Node.ELEMENT_NODE && Integer.parseInt(employeeNode.getAttributes().getNamedItem("id").getTextContent()) == j) {

                                Element employeeElement = (Element) employeeNode;
                                //get elements from node by tag name and assign new value from array

                                employeeElement.getElementsByTagName("name").item(0).setTextContent(Employees.nameArr[j]);
                                employeeElement.getElementsByTagName("surname").item(0).setTextContent(Employees.surnameArr[j]);
                                employeeElement.getElementsByTagName("position").item(0).setTextContent(Employees.positionArr[j]);
                                employeeElement.getElementsByTagName("age").item(0).setTextContent(String.valueOf(Employees.ageArr[j]));
                                employeeElement.getElementsByTagName("salary").item(0).setTextContent(String.valueOf(Employees.salaryArr[j]));
                            }
                        }
                    }
                } else {
                    System.out.println("!!!Fields for Update doesn't exist in XML file.Export data first.!!!");
                }

                //write content into XML file
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(fromXML);
                StreamResult result = new StreamResult(new File(path));

                transformer.transform(source, result);

                System.out.println("File 'employees.xml' was modified successfully");
            } else {
                System.out.println("!!!File 'employees.xml' doesn't exist!!!");
            }
        } else {
            System.out.println("!!!'Employees' is empty!!!");
        }
    }

    /*
     * All the methods below work according to the same principle
     */

    private void updateProducts() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        String path = "src/main/resources/products.xml";
        // check if memory isn't empty
        if(Products.productNameArr !=null){
            //check if file exists in given path
            if (Files.isReadable(Paths.get(path))) {
                //obtain a Document from XML file
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document fromXML = dBuilder.parse(path);
                //put all text nodes in normal form
                fromXML.getDocumentElement().normalize();

                //get root node
                NodeList productList = fromXML.getElementsByTagName("product");

                //loop the elements update
                if (Products.productNameArr.length < productList.getLength()) {
                    for (int j = 0; j < Products.productNameArr.length; j++) {
                        for (int i = 0; i < productList.getLength(); i++) {
                            //get node from document tree
                            Node productNode = productList.item(i);
                            //check if node is a element of tree and has the same index as array input
                            if (productNode.getNodeType() == Node.ELEMENT_NODE && Integer.parseInt(productNode.getAttributes().getNamedItem("id").getTextContent()) == j) {

                                Element productElement = (Element) productNode;
                                //get elements from node by tag name and assign new value from array

                                productElement.getElementsByTagName("productName").item(0).setTextContent(Products.productNameArr[j]);
                                productElement.getElementsByTagName("productGroup").item(0).setTextContent(Products.productGroupArr[j]);
                                productElement.getElementsByTagName("price").item(0).setTextContent(String.valueOf(Products.priceArr[j]));

                            }
                        }
                    }
                } else {
                    System.out.println("!!!Fields for Update doesn't exist in XML file.Export data first.!!!");
                }

                //write content into XML file
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(fromXML);
                StreamResult result = new StreamResult(new File(path));

                transformer.transform(source, result);

                System.out.println("File 'products.xml' was modified successfully");
            } else {
                System.out.println("!!!File 'products.xml' doesn't exist!!!");
            }
        } else {
            System.out.println("!!!'Products' is empty!!!");
        }
    }

    private void updateProductGroups() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        String path = "src/main/resources/groups.xml";
        // check if memory isn't empty
        if(ProductGroups.groupNameArr !=null){
            //check if file exists in given path
            if (Files.isReadable(Paths.get(path))) {
                //obtain a Document from XML file
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document fromXML = dBuilder.parse(path);
                //put all text nodes in normal form
                fromXML.getDocumentElement().normalize();

                //get root node
                NodeList groupList = fromXML.getElementsByTagName("group");

                //loop the elements update
                if (ProductGroups.groupNameArr.length < groupList.getLength()) {
                    for (int j = 0; j < ProductGroups.groupNameArr.length; j++) {
                        for (int i = 0; i < groupList.getLength(); i++) {
                            //get node from document tree
                            Node groupNode = groupList.item(i);
                            //check if node is a element of tree and has the same index as array input
                            if (groupNode.getNodeType() == Node.ELEMENT_NODE && Integer.parseInt(groupNode.getAttributes().getNamedItem("id").getTextContent()) == j) {

                                Element groupElement = (Element) groupNode;
                                //get elements from node by tag name and assign new value from array

                                groupElement.getElementsByTagName("groupName").item(0).setTextContent(ProductGroups.groupNameArr[j]);
                                groupElement.getElementsByTagName("numberOfUnits").item(0).setTextContent(String.valueOf(ProductGroups.numberOfUnitsArr[j]));

                            }
                        }
                    }
                } else {
                    System.out.println("!!!Fields for Update doesn't exist in XML file.Export data first.!!!");
                }

                //write content into XML file
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(fromXML);
                StreamResult result = new StreamResult(new File(path));

                transformer.transform(source, result);

                System.out.println("File 'groups.xml' was modified successfully");
            } else {
                System.out.println("!!!File 'groups.xml' doesn't exist!!!");
            }
        } else {
            System.out.println("!!!'Product Groups' is empty!!!");
        }
    }

    private void updateBuyers() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        String path = "src/main/resources/buyers.xml";
        // check if memory isn't empty
        if(BuyersCC.nameBuyerArr !=null){
            //check if file exists in given path
            if (Files.isReadable(Paths.get(path))) {
                //obtain a Document from XML file
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document fromXML = dBuilder.parse(path);
                //put all text nodes in normal form
                fromXML.getDocumentElement().normalize();

                //get root node
                NodeList buyerList = fromXML.getElementsByTagName("employee");

                //loop the elements update
                if (Employees.nameArr.length < buyerList.getLength()) {
                    for (int j = 0; j < Employees.nameArr.length; j++) {
                        for (int i = 0; i < buyerList.getLength(); i++) {
                            //get node from document tree
                            Node buyerNode = buyerList.item(i);
                            //check if node is a element of tree and has the same index as array input
                            if (buyerNode.getNodeType() == Node.ELEMENT_NODE && Integer.parseInt(buyerNode.getAttributes().getNamedItem("id").getTextContent()) == j) {

                                Element buyerElement = (Element) buyerNode;
                                //get elements from node by tag name and assign new value from array

                                buyerElement.getElementsByTagName("buyerName").item(0).setTextContent(BuyersCC.nameBuyerArr[i]);
                                buyerElement.getElementsByTagName("buyerSurname").item(0).setTextContent(BuyersCC.surnameBuyerArr[i]);
                                buyerElement.getElementsByTagName("acquisitions").item(0).setTextContent(String.valueOf(BuyersCC.acquisitionsArr[i]));
                                buyerElement.getElementsByTagName("discount").item(0).setTextContent(String.valueOf(BuyersCC.discountArr[i]));
                            }
                        }
                    }
                } else {
                    System.out.println("!!!Fields for Update doesn't exist in XML file.Export data first.!!!");
                }

                //write content into XML file
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(fromXML);
                StreamResult result = new StreamResult(new File(path));

                transformer.transform(source, result);

                System.out.println("File 'buyers.xml' was modified successfully");
            } else {
                System.out.println("!!!File 'buyers.xml' doesn't exist!!!");
            }
        } else {
            System.out.println("!!!'Buyers With Club Cards' is empty!!!");
        }
    }

    private void updateSales() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        String path = "src/main/resources/sales.xml";
        // check if memory isn't empty
        if(Sales.nameProductSaleArr !=null){
            //check if file exists in given path
            if (Files.isReadable(Paths.get(path))) {
                //obtain a Document from XML file
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document fromXML = dBuilder.parse(path);
                //put all text nodes in normal form
                fromXML.getDocumentElement().normalize();

                //get root node
                NodeList saleList = fromXML.getElementsByTagName("sale");

                //loop the elements update
                if (Sales.nameProductSaleArr.length < saleList.getLength()) {
                    for (int j = 0; j < Sales.nameProductSaleArr.length; j++) {
                        for (int i = 0; i < saleList.getLength(); i++) {
                            //get node from document tree
                            Node saleNode = saleList.item(i);
                            //check if node is a element of tree and has the same index as array input
                            if (saleNode.getNodeType() == Node.ELEMENT_NODE && Integer.parseInt(saleNode.getAttributes().getNamedItem("id").getTextContent()) == j) {

                                Element saleElement = (Element) saleNode;
                                //get elements from node by tag name and assign new value from array

                                saleElement.getElementsByTagName("productForSale").item(0).setTextContent(Sales.nameProductSaleArr[i]);
                                saleElement.getElementsByTagName("productPrice").item(0).setTextContent(String.valueOf(Sales.productPriceBeforeSaleArr[i]));
                                saleElement.getElementsByTagName("discount").item(0).setTextContent(String.valueOf(Sales.discountArr[i]));
                                saleElement.getElementsByTagName("afterDiscount").item(0).setTextContent(String.valueOf(Sales.priceAfterDiscountArr[i]));
                            }
                        }
                    }
                } else {
                    System.out.println("!!!Fields for Update doesn't exist in XML file.Export data first.!!!");
                }

                //write content into XML file
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(fromXML);
                StreamResult result = new StreamResult(new File(path));

                transformer.transform(source, result);

                System.out.println("File 'sales.xml' was modified successfully");
            } else {
                System.out.println("!!!File 'sales.xml' doesn't exist!!!");
            }
        } else {
            System.out.println("!!!'Sales' is empty!!!");
        }
    }

}
