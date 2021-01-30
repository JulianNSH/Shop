/*  EXPORT DATA CLASS
* Use DOM Parser to transfer data from
* memory to the external XML file
*/

package github.JulianNSH.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import github.JulianNSH.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.exit;

public class ExportDataToXML  {
    Scanner scn = new Scanner(System.in);

    public ExportDataToXML() throws ParserConfigurationException {}

    public int exportMenu() throws TransformerException {

        System.out.println("\n+==========EXPORT==========+");
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
            case 1: exportAll(); break;
            case 2: exportEmployees(); break;
            case 3: exportProducts(); break;
            case 4: exportProductGroups(); break;
            case 5: exportBuyers(); break;
            case 6: exportSales(); break;
            case 7: return 0;
            case 0: exit(0);
            default: System.out.println("!!!Invalid Input!!!"); exportMenu();
        }
        return 0;
    }

    //obtain parser that produces DOM object trees and document instances
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


    private void exportAll() throws TransformerException {
        exportEmployees();
        exportProducts();
        exportProductGroups();
        exportBuyers();
        exportSales();

        exportMenu();
    }

    private void exportEmployees() throws TransformerException {
        // check if memory isn't empty
        if(Employees.nameArr != null) {

            //create XML document
            Document employeesXML = docBuilder.newDocument();

            //create root element of XML document
            Element employeesXMLRoot = employeesXML.createElement("employees");

            //add the node to the end of the list of children
            employeesXML.appendChild(employeesXMLRoot);


            for (int i = 0; i < Employees.nameArr.length; i++) {
                // create a child element in root element
                Element employee = employeesXML.createElement("employee");
                //add the node to the end of the list of children
                employeesXMLRoot.appendChild(employee);
                //add the id attribute to the element
                employee.setAttribute("id", String.valueOf(Employees.employeeId[i]));

                //create a child element for element from above
                Element employeeName = employeesXML.createElement("name");
                //create the node with given string from memory
                employeeName.appendChild(employeesXML.createTextNode(Employees.nameArr[i]));
                //add the node
                employee.appendChild(employeeName);

                Element employeeSurname = employeesXML.createElement("surname");
                employeeSurname.appendChild(employeesXML.createTextNode(Employees.surnameArr[i]));
                employee.appendChild(employeeSurname);

                Element employeePosition = employeesXML.createElement("position");
                employeePosition.appendChild(employeesXML.createTextNode(Employees.positionArr[i]));
                employee.appendChild(employeePosition);

                Element employeeAge = employeesXML.createElement("age");
                employeeAge.appendChild(employeesXML.createTextNode(String.valueOf(Employees.ageArr[i])));
                employee.appendChild(employeeAge);

                Element employeeSalary = employeesXML.createElement("salary");
                employeeSalary.appendChild(employeesXML.createTextNode(String.valueOf(Employees.salaryArr[i])));
                employee.appendChild(employeeSalary);
            }

            //transform source tree into a result tree
            Transformer toXML = TransformerFactory.newInstance().newTransformer();
            //use ident for correct hierarchy of nodes
            toXML.setOutputProperty(OutputKeys.INDENT, "yes");

            //holder for transformation source tree in the form of DOM tree
            DOMSource source = new DOMSource(employeesXML);
            //result of transformation with given directory
            StreamResult result = new StreamResult(new File("src/main/resources/employees.xml"));

            //transform XML source to a result (external file)
            toXML.transform(source, result);

            System.out.println("Exported successfully to 'employees.xml'");
        } else {
            System.out.println("!!!Nothing to export in 'Employees'!!!");
        }
    }

    /*
    * All the methods below work according to the same principle
     */
    private void exportProducts() throws TransformerException {
        if(Products.productNameArr != null) {
            Document productsXML = docBuilder.newDocument();
            Element productsXMLRoot = productsXML.createElement("products");
            productsXML.appendChild(productsXMLRoot);

            for (int i = 0; i < Products.productNameArr.length; i++) {
                Element product = productsXML.createElement("product");
                productsXMLRoot.appendChild(product);
                product.setAttribute("id", String.valueOf(Products.productId[i]));

                Element productName = productsXML.createElement("productName");
                productName.appendChild(productsXML.createTextNode(Products.productNameArr[i]));
                product.appendChild(productName);

                Element productGroup = productsXML.createElement("productGroup");
                productGroup.appendChild(productsXML.createTextNode(String.valueOf(Products.productGroupIdArr[i])));
                product.appendChild(productGroup);

                Element productPrice = productsXML.createElement("price");
                productPrice.appendChild(productsXML.createTextNode(String.valueOf(Products.priceArr[i])));
                product.appendChild(productPrice);

            }
            Transformer toXML = TransformerFactory.newInstance().newTransformer();
            toXML.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(productsXML);
            StreamResult result = new StreamResult(new File("src/main/resources/products.xml"));

            toXML.transform(source, result);
            System.out.println("Exported successfully to 'products.xml'");
        } else {
            System.out.println("!!!Nothing to export in 'Products'!!!");
        }
    }

    private void exportProductGroups() throws TransformerException {
        if(ProductGroups.groupNameArr != null) {
            Document productGroupsXML = docBuilder.newDocument();
            Element productGroupsXMLRoot = productGroupsXML.createElement("groups");
            productGroupsXML.appendChild(productGroupsXMLRoot);

            for (int i = 0; i < ProductGroups.groupNameArr.length; i++) {
                Element group = productGroupsXML.createElement("group");
                productGroupsXMLRoot.appendChild(group);
                group.setAttribute("id", String.valueOf(ProductGroups.groupId[i]));

                Element groupName = productGroupsXML.createElement("groupName");
                groupName.appendChild(productGroupsXML.createTextNode(ProductGroups.groupNameArr[i]));
                group.appendChild(groupName);

                Element numberOfUnits = productGroupsXML.createElement("numberOfUnits");
                numberOfUnits.appendChild(productGroupsXML.createTextNode(String.valueOf(ProductGroups.numberOfUnitsArr[i])));
                group.appendChild(numberOfUnits);

            }
            Transformer toXML = TransformerFactory.newInstance().newTransformer();
            toXML.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(productGroupsXML);
            StreamResult result = new StreamResult(new File("src/main/resources/groups.xml"));

            toXML.transform(source, result);
            System.out.println("Exported successfully to 'groups.xml'");
        } else {
            System.out.println("!!!Nothing to export in 'Product Groups'!!!");
        }
    }

    private void exportBuyers() throws TransformerException {
        if(BuyersCC.nameBuyerArr != null) {
            Document buyersXML = docBuilder.newDocument();
            Element buyersXMLRoot = buyersXML.createElement("buyers");
            buyersXML.appendChild(buyersXMLRoot);

            for (int i = 0; i < BuyersCC.nameBuyerArr.length; i++) {
                Element buyer = buyersXML.createElement("buyer");
                buyersXMLRoot.appendChild(buyer);
                buyer.setAttribute("id", String.valueOf(BuyersCC.buyerId[i]));

                Element buyerName = buyersXML.createElement("buyerName");
                buyerName.appendChild(buyersXML.createTextNode(BuyersCC.nameBuyerArr[i]));
                buyer.appendChild(buyerName);

                Element buyerSurname = buyersXML.createElement("buyerSurname");
                buyerSurname.appendChild(buyersXML.createTextNode(BuyersCC.surnameBuyerArr[i]));
                buyer.appendChild(buyerSurname);

                Element acquisitions = buyersXML.createElement("acquisitions");
                acquisitions.appendChild(buyersXML.createTextNode(String.valueOf(BuyersCC.acquisitionsArr[i])));
                buyer.appendChild(acquisitions);

                Element discount = buyersXML.createElement("discount");
                discount.appendChild(buyersXML.createTextNode(String.valueOf(BuyersCC.discountArr[i])));
                buyer.appendChild(discount);
            }
            Transformer toXML = TransformerFactory.newInstance().newTransformer();
            toXML.setOutputProperty(OutputKeys.INDENT, "yes");


            DOMSource source = new DOMSource(buyersXML);
            StreamResult result = new StreamResult(new File("src/main/resources/buyers.xml"));

            toXML.transform(source, result);
            System.out.println("Exported successfully to 'buyers.xml'");
        } else {
            System.out.println("!!!Nothing to export in 'Buyers With Club Cards'!!!");
        }
    }

    private void exportSales() throws TransformerException {
        if(Sales.saleId != null) {
            Document salesXML = docBuilder.newDocument();
            Element salesXMLRoot = salesXML.createElement("sales");
            salesXML.appendChild(salesXMLRoot);

            for (int i = 0; i < Sales.saleId.length; i++) {
                Element sale = salesXML.createElement("sale");
                salesXMLRoot.appendChild(sale);
                sale.setAttribute("id", String.valueOf(Sales.saleId[i]));

                Element productForSale = salesXML.createElement("productId");
                productForSale.appendChild(salesXML.createTextNode(String.valueOf(Sales.productId[i])));
                sale.appendChild(productForSale);


                Element discount = salesXML.createElement("discount");
                discount.appendChild(salesXML.createTextNode(String.valueOf(Sales.discountArr[i])));
                sale.appendChild(discount);

            }
            Transformer toXML = TransformerFactory.newInstance().newTransformer();
            toXML.setOutputProperty(OutputKeys.INDENT, "yes");


            DOMSource source = new DOMSource(salesXML);
            StreamResult result = new StreamResult(new File("src/main/resources/sales.xml"));

            toXML.transform(source, result);
            System.out.println("Exported successfully to 'sales.xml'");
        } else {
            System.out.println("!!!Nothing to export in 'Sales'!!!");
        }
    }
}
