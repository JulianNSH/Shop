package github.JulianNSH.DataBase;

import github.JulianNSH.*;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

/*
    This class provides exporting data form memory to existing tables in DataBase
 */

public class ExportDataToDataBase {
    Scanner scn = new Scanner(System.in);
    Connection databaseConnector = ConnectToDB.connect();

    public ExportDataToDataBase(){}


    public int exportDBaseMenu() throws SQLException {
        //export should be realised in this order (as in menu and 'exportDBaseAll')
        //to avoid errors caused by lack of PrimaryKeys-ForeignKeys
        System.out.println("\n+========DB EXPORT========+");
        System.out.println("|<1>ALL                   |");
        System.out.println("|<2>Employees             |");
        System.out.println("|<3>Buyers With Club Cards|");
        System.out.println("|<4>Product Groups        |");
        System.out.println("|<5>Products              |");
        System.out.println("|<6>Sales                 |");
        System.out.println("|<7>                <<Back|");
        System.out.println("|<0>Exit                  |");
        System.out.println("+=========================+");
        System.out.println("Enter Option>>>  ");

        int i = scn.nextInt();

        switch (i) {
            case 1: exportDBaseAll(); break;
            case 2: exportDBaseEmployees(); break;
            case 3: exportDBaseBuyers(); break;
            case 4: exportDBaseProductGroups(); break;
            case 5: exportDBaseProducts(); break;
            case 6: exportDBaseSales(); break;
            case 7: return 0;
            case 0: exit(0);
            default: System.out.println("!!!Invalid Input!!!"); exportDBaseMenu();
        }
        return 0;
    }

    private void exportDBaseAll() throws SQLException {
        exportDBaseEmployees();
        exportDBaseBuyers();
        exportDBaseProductGroups();
        exportDBaseProducts();
        exportDBaseSales();
    }

    private void exportDBaseEmployees() throws SQLException {
        // check if memory isn't empty
        if(Employees.nameArr != null) {
            //check database connection
            if (databaseConnector !=null){

                //prepare query statement for inserting into the table
               String sqlQuery = "INSERT INTO employees(id_employee, name_empl, surname_empl, position, age, salary) VALUES(?,?,?,?,?,?)";
               PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

               //inserting each row into the table
               for (int i=0; i<Employees.employeeId.length; i++){
                   sqlStatement.setInt(1, Employees.employeeId[i]);
                   sqlStatement.setString(2, Employees.nameArr[i]);
                   sqlStatement.setString(3, Employees.surnameArr[i]);
                   sqlStatement.setString(4, Employees.positionArr[i]);
                   sqlStatement.setInt(5, Employees.ageArr[i]);
                   sqlStatement.setDouble(6, Employees.salaryArr[i]);

                   //updating table for insert
                   sqlStatement.executeUpdate();
               }

                sqlStatement.close();
                System.out.println("Exported successfully to 'employees' table");

            } else {
                System.out.println("Can't connect to the DataBase");
            }
        } else {
            System.out.println("!!!Nothing to export in 'Employees'!!!");
        }

    }

    private void exportDBaseProducts() throws SQLException {

        // check if memory isn't empty
        if(Products.productId != null) {
            //check database connection
            if (databaseConnector !=null){
                //prepare query statement for inserting into the table
                String sqlQuery = "INSERT INTO products(id_product, name_product, id_group, price) VALUES(?,?,?,?)";
                PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);
                //inserting each row into the table
                for (int i=0; i<Products.productId.length; i++){
                    sqlStatement.setInt(1, Products.productId[i]);
                    sqlStatement.setString(2, Products.productNameArr[i]);
                    sqlStatement.setInt(3, Products.productGroupIdArr[i]);
                    sqlStatement.setDouble(4, Products.priceArr[i]);
                    //updating table for insert
                    sqlStatement.executeUpdate();

                }
                sqlStatement.close();
                System.out.println("Exported successfully to 'products' table");

            } else {
                System.out.println("Can't connect to the DataBase");
            }
        } else {
            System.out.println("!!!Nothing to export in 'Products'!!!");
        }
    }

    private void exportDBaseProductGroups() throws SQLException{

        // check if memory isn't empty
        if(ProductGroups.groupId != null) {
            //check database connection
            if (databaseConnector !=null){
                //prepare query statement for inserting into the table
                String sqlQuery = "INSERT INTO product_groups(id_group, group_name, units_number) VALUES(?,?,?)";
                PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);
                //inserting each row into the table
                for (int i=0; i<ProductGroups.groupId.length; i++){
                    sqlStatement.setInt(1, ProductGroups.groupId[i]);
                    sqlStatement.setString(2, ProductGroups.groupNameArr[i]);
                    sqlStatement.setInt(3, ProductGroups.numberOfUnitsArr[i]);
                    //updating table for insert
                    sqlStatement.executeUpdate();

                }
                sqlStatement.close();
                System.out.println("Exported successfully to 'product_groups' table");

            } else {
                System.out.println("Can't connect to the DataBase");
            }
        } else {
            System.out.println("!!!Nothing to export in 'ProductGroups'!!!");
        }
    }

    private void exportDBaseBuyers() throws SQLException {
        // check if memory isn't empty
        if(BuyersCC.buyerId != null) {
            //check database connection
            if (databaseConnector !=null){
                //prepare query statement for inserting into the table
                String sqlQuery = "INSERT INTO buyerscc(id_buyer, name_buyer, surname_buyer, acquisitions, discount) VALUES(?,?,?,?,?)";
                PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);
                //inserting each row into the table
                for (int i=0; i<BuyersCC.buyerId.length; i++){
                    sqlStatement.setInt(1, BuyersCC.buyerId[i]);
                    sqlStatement.setString(2, BuyersCC.nameBuyerArr[i]);
                    sqlStatement.setString(3, BuyersCC.surnameBuyerArr[i]);
                    sqlStatement.setDouble(4, BuyersCC.acquisitionsArr[i]);
                    sqlStatement.setInt(5, (int) BuyersCC.discountArr[i]);
                    //updating table for insert
                    sqlStatement.executeUpdate();

                }
                sqlStatement.close();
                System.out.println("Exported successfully to 'buyerscc' table");

            } else {
                System.out.println("Can't connect to the DataBase");
            }
        } else {
            System.out.println("!!!Nothing to export in 'BuyersCC'!!!");
        }
    }

    private void exportDBaseSales() throws SQLException {

        // check if memory isn't empty
        if(Sales.saleId != null) {
            //check database connection
            if (databaseConnector !=null){
                //prepare query statement for inserting into the table
                String sqlQuery = "INSERT INTO sales(id_sale, id_product, discount) VALUES(?,?,?)";
                PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);
                //inserting each row into the table
                for (int i=0; i<Sales.saleId.length; i++){
                    sqlStatement.setInt(1, Sales.saleId[i]);
                    sqlStatement.setInt(2, Sales.productId[i]);
                    sqlStatement.setInt(3, (int) Sales.discountArr[i]);
                    //updating table for insert
                    sqlStatement.executeUpdate();

                }
                sqlStatement.close();
                System.out.println("Exported successfully to 'sales' table");

            } else {
                System.out.println("Can't connect to the DataBase");
            }
        } else {
            System.out.println("!!!Nothing to export in 'Sales'!!!");
        }
    }


}
