package github.JulianNSH.DataBase;

import github.JulianNSH.*;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

/*
    This class provides importing data from existing tables in given database
 */

public class ImportDataFromDataBase {
    Scanner scn = new Scanner(System.in);
    Connection databaseConnector = ConnectToDB.connect();

    public int importDBaseMenu() throws SQLException{
        System.out.println("\n+========DB IMPORT========+");
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
            case 1: importDBaseAll(); break;
            case 2: importDBaseEmployees(); break;
            case 3: importDBaseBuyers(); break;
            case 4: importDBaseProductGroups(); break;
            case 5: importDBaseProducts(); break;
            case 6: importDBaseSales(); break;
            case 7: return 0;
            case 0: exit(0);
            default: System.out.println("!!!Invalid Input!!!"); importDBaseMenu();
        }
        return 0;
    }

    private void importDBaseAll() throws SQLException{
        importDBaseEmployees();
        importDBaseBuyers();
        importDBaseProductGroups();
        importDBaseProducts();
        importDBaseSales();
    }

    //method to get number of rows for memory alloc
    private int getNumberOfRows(ResultSet res) throws SQLException {
        int temp = 0;
        while (res.next()){
            temp++;
        }
        return temp;
    }

    private void importDBaseEmployees() throws SQLException{
        //check connection with DataBase
        if(databaseConnector != null){
            String sqlQuery = "SELECT * from employees";    //String with SQL query
            Statement sqlStatement = databaseConnector.createStatement(); //create statement
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery); //create result table with data form query

            //creating new object for empty entity
            Employees tempObj = new Employees();
            tempObj.allocateMemory(getNumberOfRows(sqlResult));//using class method to allocate memory to the arrays

            //creating new ResultSet obj because using last(), beforeFirst() cause errors
            sqlResult = sqlStatement.executeQuery(sqlQuery);

            //cross the ResultSet and insert data into entity arrays
            int i = 0;
            while (sqlResult.next()){
                Employees.employeeId[i] = sqlResult.getInt("id_employee");
                Employees.nameArr[i] = sqlResult.getString("name_empl");
                Employees.surnameArr[i] = sqlResult.getString("surname_empl");
                Employees.positionArr[i] = sqlResult.getString("position");
                Employees.ageArr[i] = sqlResult.getInt("age");
                Employees.salaryArr[i] = sqlResult.getDouble("salary");

                i++;
            }
            System.out.println("Successfully imported from 'employees' table");
        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }
/*
    All methods below working by same principle
 */
    private void importDBaseBuyers() throws SQLException {
        if(databaseConnector != null){
            String sqlQuery = "SELECT * from buyerscc";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            BuyersCC tempObj = new BuyersCC();
            tempObj.allocateMemory(getNumberOfRows(sqlResult));//using class method to allocate memory to the arrays

             sqlResult = sqlStatement.executeQuery(sqlQuery);

            int i = 0;
            while (sqlResult.next()){
                BuyersCC.buyerId[i] = sqlResult.getInt("id_buyer");
                BuyersCC.nameBuyerArr[i] = sqlResult.getString("name_buyer");
                BuyersCC.surnameBuyerArr[i] = sqlResult.getString("surname_buyer");
                BuyersCC.acquisitionsArr[i] = sqlResult.getDouble("acquisitions");
                BuyersCC.discountArr[i] = sqlResult.getInt("discount");

                i++;
            }
            System.out.println("Successfully imported from 'buyerscc' table");
        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

    private void importDBaseProductGroups() throws SQLException{
        if(databaseConnector != null){
            String sqlQuery = "SELECT * from product_groups";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            ProductGroups tempObj = new ProductGroups();
            tempObj.allocateMemory(getNumberOfRows(sqlResult));//using class method to allocate memory to the arrays

            sqlResult = sqlStatement.executeQuery(sqlQuery);

            int i = 0;
            while (sqlResult.next()){
                ProductGroups.groupId[i] = sqlResult.getInt("id_group");
                ProductGroups.groupNameArr[i] = sqlResult.getString("group_name");
                ProductGroups.numberOfUnitsArr[i] = sqlResult.getInt("units_number");

                i++;
            }
            System.out.println("Successfully imported from 'product_groups' table");
        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

    private void importDBaseProducts() throws SQLException{
        if(databaseConnector != null){
            String sqlQuery = "SELECT * from products";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            Products tempObj = new Products();
            tempObj.allocateMemory(getNumberOfRows(sqlResult));//using class method to allocate memory to the arrays

            sqlResult = sqlStatement.executeQuery(sqlQuery);

            int i = 0;
            while (sqlResult.next()){
                Products.productId[i] = sqlResult.getInt("id_product");
                Products.productNameArr[i] = sqlResult.getString("name_product");
                Products.productGroupIdArr[i] = sqlResult.getInt("id_group");
                Products.priceArr[i] = sqlResult.getDouble("price");

                i++;
            }
            System.out.println("Successfully imported from 'products' table");
        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

    private void importDBaseSales() throws SQLException{
        if(databaseConnector != null){
            String sqlQuery = "SELECT * from sales";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            Sales tempObj = new Sales();
            tempObj.allocateMemory(getNumberOfRows(sqlResult));//using class method to allocate memory to the arrays

             sqlResult = sqlStatement.executeQuery(sqlQuery);

            int i = 0;
            while (sqlResult.next()){
                Sales.saleId[i] = sqlResult.getInt("id_sale");
                Sales.productId[i] = sqlResult.getInt("id_product");
                Sales.discountArr[i] = sqlResult.getInt("discount");

                i++;
            }
            System.out.println("Successfully imported from 'sales' table");
        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }
}
