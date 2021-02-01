package github.JulianNSH.DataBase;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;
/*
    This class provides showing tables form database
    and offers possibility to ADD, UPDATE and DELETE rows directly in the DataBase
 */
public class ShowDataFromDataBase {
    Scanner scn = new Scanner(System.in);
    Connection databaseConnector = ConnectToDB.connect();

    //method that shows menu
    public int showDBaseMenu() throws SQLException {
        System.out.println("\n+========SHOW DATABASE========+");
        System.out.println("|<1>Employees             |");
        System.out.println("|<2>Buyers With Club Cards|");
        System.out.println("|<3>Product Groups        |");
        System.out.println("|<4>Products              |");
        System.out.println("|<5>Sales                 |");
        System.out.println("|<7>                <<Back|");
        System.out.println("|<0>Exit                  |");
        System.out.println("+=========================+");
        System.out.println("Enter Option>>>  ");

        int i = scn.nextInt();

        switch (i) {
            case 1: showDBaseEmployees(); break;
            case 2: showDBaseBuyers(); break;
            case 3: showDBaseProductGroups(); break;
            case 4: showDBaseProducts(); break;
            case 5: showDBaseSales(); break;
            case 6: return 0;
            case 0: exit(0);
            default: System.out.println("!!!Invalid Input!!!"); showDBaseMenu();
        }
        return 0;
    }

    private void showDBaseEmployees() throws SQLException{
        if (databaseConnector !=null){
            //System.out.println("Connected");
            //select all fields from table and creates statement and resultSet
            String sqlQuery = "SELECT * from employees";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            //showing data from table
            System.out.println("\n+================EMPLOYEES DATABASE================+");
            System.out.print(  "|ID  |Name      |Surname   |Position  |Age|Salary  |\n");
            System.out.println("|--------------------------------------------------|");
            while (sqlResult.next()){
                System.out.printf("|%-4s",   sqlResult.getString("id_employee"));
                System.out.printf("|%-10s",  sqlResult.getString("name_empl"));
                System.out.printf("|%-10s",  sqlResult.getString("surname_empl"));
                System.out.printf("|%-10s",  sqlResult.getString("position"));
                System.out.printf("|%-3s",   sqlResult.getString("age"));
                System.out.printf("|%-8s|\n",sqlResult.getString("salary"));
            }
            System.out.println("|--------------------------------------------------|");

            //offer possibility to make changes in table
            System.out.println("+====OPTIONS====+");
            System.out.println("|1.Add          |");
            System.out.println("|2.Update       |");
            System.out.println("|3.Delete       |");
            System.out.println("|4.       <<Back|");
            System.out.println("|0.Exit         |");
            System.out.println(" >>> ");
            int i = scn.nextInt();

            //call methods by given input
            switch (i) {
                case 1: addRowEmployees(); break;
                case 2: updateRowEmployees(); break;
                case 3: deleteRowEmployees(); break;
                case 4: showDBaseMenu();
                case 0: exit(0);
                default: System.out.println("!!!Invalid Input!!!"); showDBaseEmployees();
            }

        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

    //method to add in table new rows
    private void addRowEmployees() throws SQLException{
        //get number of new rows to input
        System.out.println("Input NR of new rows>>> ");
        int add = scn.nextInt();

        //prepare SQL query and statement
        String sqlQuery = "INSERT INTO employees(id_employee, name_empl, surname_empl, position, age, salary) VALUES(?,?,?,?,?,?)";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        //adding new row by given input
        for (int i =0; i<add; i++){
            System.out.println("*ID:");
            sqlStatement.setInt(1, scn.nextInt());
            System.out.println("*Name:");
            sqlStatement.setString(2, scn.next());
            System.out.println("*Surname:");
            sqlStatement.setString(3, scn.next());
            System.out.println("*Position:");
            sqlStatement.setString(4, scn.next());
            System.out.println("*Age:");
            sqlStatement.setInt(5, scn.nextInt());
            System.out.println("*Salary:");
            sqlStatement.setDouble(6, scn.nextDouble());

            //update table
            sqlStatement.executeUpdate();
        }
        //close
        sqlStatement.close();
        System.out.println("Rows added successfully");
        //return
        showDBaseEmployees();
    }

    //method to update fields in selected row
    private void updateRowEmployees() throws SQLException{
        //select row by ID
        System.out.println("Input ID for UPDATE>>>");
        int updID = scn.nextInt();

        //prepare SQL query and statement
        String sqlQuery = "UPDATE employees SET name_empl= ?, surname_empl= ?, position= ?, age = ?, salary = ?  WHERE id_employee = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        //modify fields in selected row
        System.out.println("*NEW Name:");
        sqlStatement.setString(1, scn.next());
        System.out.println("*NEW Surname:");
        sqlStatement.setString(2, scn.next());
        System.out.println("*NEW Position:");
        sqlStatement.setString(3, scn.next());
        System.out.println("*NEW Age:");
        sqlStatement.setInt(4, scn.nextInt());
        System.out.println("*NEW Salary:");
        sqlStatement.setDouble(5, scn.nextDouble());
        sqlStatement.setInt(6, updID);
        //update and close table
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row updated successfully");

        //return to menu
        showDBaseEmployees();
    }

    //method to delete row by given ID
    private void deleteRowEmployees() throws SQLException{
        //select row by ID
        System.out.println("Input ID for DELETE>>>");
        int delID = scn.nextInt();

        //prepare SQL query and statement
        String sqlQuery = "DELETE FROM employees WHERE id_employee = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        //apply query to statement
        sqlStatement.setInt(1, delID);

        //update and close table
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row deleted successfully");

        //return
        showDBaseEmployees();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
    All methods below working by same principle
 */
    private void showDBaseBuyers() throws SQLException {
        if (databaseConnector !=null){
            String sqlQuery = "SELECT * from buyerscc";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            System.out.println("\n+===============BUYERS WITH CLUB CARDS DATABASE================+");
            System.out.print(  "|ID  |NameBuyer      |SurnameBuyer   |Acquisitions  |Discount  |\n");
            System.out.println("|--------------------------------------------------------------|");
            while (sqlResult.next()){
                System.out.printf("|%-4s", sqlResult.getInt("id_buyer"));
                System.out.printf("|%-15s",sqlResult.getString("name_buyer"));
                System.out.printf("|%-15s",sqlResult.getString("surname_buyer"));
                System.out.printf("|%-14s",sqlResult.getDouble("acquisitions"));
                System.out.printf("|%-10s|\n",sqlResult.getInt("discount"));

            }
            System.out.println("|--------------------------------------------------------------|");
            System.out.println("+====OPTIONS====+");
            System.out.println("|1.Add          |");
            System.out.println("|2.Update       |");
            System.out.println("|3.Delete       |");
            System.out.println("|4.       <<Back|");
            System.out.println("|0.Exit         |");
            System.out.println(" >>> ");
            int i = scn.nextInt();

            //call methods by given input
            switch (i) {
                case 1: addRowBuyers(); break;
                case 2: updateRowBuyers(); break;
                case 3: deleteRowBuyers(); break;
                case 4: showDBaseMenu();
                case 0: exit(0);
                default: System.out.println("!!!Invalid Input!!!"); showDBaseBuyers();
            }

        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

    private void addRowBuyers() throws SQLException{
        System.out.println("Input NR of new rows>>> ");
        int add = scn.nextInt();

        String sqlQuery = "INSERT INTO buyerscc(id_buyer, name_buyer, surname_buyer, acquisitions, discount) VALUES(?,?,?,?,?)";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        for (int i =0; i<add; i++){
            System.out.println("*ID:");
            sqlStatement.setInt(1, scn.nextInt());
            System.out.println("*BuyerName:");
            sqlStatement.setString(2, scn.next());
            System.out.println("*BuyerSurname:");
            sqlStatement.setString(3, scn.next());
            System.out.println("*Acquisitions:");
            sqlStatement.setDouble(4, scn.nextDouble());
            System.out.println("*Discount:");
            sqlStatement.setInt(5, scn.nextInt());

            sqlStatement.executeUpdate();
        }
        sqlStatement.close();
        System.out.println("Rows added successfully");
        showDBaseBuyers();
    }

    private void updateRowBuyers() throws SQLException{
        System.out.println("Input ID for UPDATE>>>");
        int updID = scn.nextInt();

        String sqlQuery = "UPDATE buyerscc SET name_buyer= ?, surname_buyer= ?, acquisitions= ?, discount= ? WHERE id_buyer = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);


        System.out.println("*NEW BuyerName:");
        sqlStatement.setString(1, scn.next());
        System.out.println("*NEW BuyerSurname:");
        sqlStatement.setString(2, scn.next());
        System.out.println("*NEW Acquisitions:");
        sqlStatement.setDouble(3, scn.nextDouble());
        System.out.println("*NEW Discount:");
        sqlStatement.setInt(4, scn.nextInt());
        sqlStatement.setInt(5, updID);

        sqlStatement.executeUpdate();

        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row updated successfully");

        showDBaseBuyers();
    }

    private void deleteRowBuyers() throws SQLException{
        System.out.println("Input ID for DELETE>>>");
        int delID = scn.nextInt();

        String sqlQuery = "DELETE FROM buyerscc WHERE id_buyer = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        sqlStatement.setInt(1, delID);
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row deleted successfully");

        showDBaseBuyers();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showDBaseProductGroups() throws SQLException {
        if (databaseConnector !=null){
            String sqlQuery = "SELECT * from product_groups";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            System.out.println("\n+========PRODUCT GROUPS DATABASE=======+");
            System.out.print(  "|ID  |GroupName          |ProductUnits |\n");
            System.out.println("|--------------------------------------|");
            while (sqlResult.next()){
                System.out.printf("|%-4s", sqlResult.getInt("id_group"));
                System.out.printf("|%-19s",sqlResult.getString("group_name"));
                System.out.printf("|%-13s|\n",sqlResult.getInt("units_number"));
            }
            System.out.println("|--------------------------------------|");
            System.out.println("+====OPTIONS====+");
            System.out.println("|1.Add          |");
            System.out.println("|2.Update       |");
            System.out.println("|3.Delete       |");
            System.out.println("|4.       <<Back|");
            System.out.println("|0.Exit         |");
            System.out.println(" >>> ");
            int i = scn.nextInt();

            switch (i) {
                case 1: addRowGroups(); break;
                case 2: updateRowGroups(); break;
                case 3: deleteRowGroups(); break;
                case 4: showDBaseMenu();
                case 0: exit(0);
                default: System.out.println("!!!Invalid Input!!!"); showDBaseProductGroups();
            }

        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

    private void addRowGroups() throws SQLException{
        System.out.println("Input NR of new rows>>> ");
        int add = scn.nextInt();

        String sqlQuery = "INSERT INTO product_groups(id_group, group_name, units_number) VALUES(?,?,?)";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        for (int i =0; i<add; i++){
            System.out.println("*ID:");
            sqlStatement.setInt(1, scn.nextInt());
            System.out.println("*GroupName:");
            sqlStatement.setString(2, scn.next());
            System.out.println("*UnitsInGroup:");
            sqlStatement.setInt(3, scn.nextInt());

            sqlStatement.executeUpdate();
        }
        sqlStatement.close();
        System.out.println("Rows added successfully");
        showDBaseProductGroups();
    }

    private void updateRowGroups() throws SQLException{
        System.out.println("Input ID for UPDATE>>>");
        int updID = scn.nextInt();

        String sqlQuery = "UPDATE product_groups SET group_name = ?, units_number =? WHERE id_group = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        System.out.println("*NEW GroupName:");
        sqlStatement.setString(1, scn.next());
        System.out.println("*NEW UnitsInGroup:");
        sqlStatement.setInt(2, scn.nextInt());
        sqlStatement.setInt(3, updID);

        sqlStatement.executeUpdate();
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row updated successfully");

        showDBaseProductGroups();
    }

    private void deleteRowGroups() throws SQLException{
        System.out.println("Input ID for DELETE>>>");
        int delID = scn.nextInt();

        String sqlQuery = "DELETE FROM product_groups WHERE id_group = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);
        sqlStatement.setInt(1, delID);
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row deleted successfully");
        showDBaseProductGroups();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showDBaseProducts() throws SQLException{
        if (databaseConnector !=null){
            String sqlQuery = "SELECT * from products";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            System.out.println("\n+=============PRODUCTS DATABASE============+");
            System.out.print(  "|ID  |Product          |GroupId   |Price    |\n");
            System.out.println("|-------------------------------------------|");
            while (sqlResult.next()){
                System.out.printf("|%-4s", sqlResult.getInt("id_product"));
                System.out.printf("|%-17s",sqlResult.getString("name_product"));
                System.out.printf("|%-10s", sqlResult.getInt("id_group"));
                System.out.printf("|%-9s|\n",sqlResult.getDouble("price"));
            }
            System.out.println("|-------------------------------------------|");
            System.out.println("+====OPTIONS====+");
            System.out.println("|1.Add          |");
            System.out.println("|2.Update       |");
            System.out.println("|3.Delete       |");
            System.out.println("|4.       <<Back|");
            System.out.println("|0.Exit         |");
            System.out.println(" >>> ");
            int i = scn.nextInt();

            //call methods by given input
            switch (i) {
                case 1: addRowProducts(); break;
                case 2: updateRowProducts(); break;
                case 3: deleteRowProducts(); break;
                case 4: showDBaseMenu();
                case 0: exit(0);
                default: System.out.println("!!!Invalid Input!!!"); showDBaseProducts();
            }

        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }


    private void addRowProducts() throws SQLException{
        System.out.println("Input NR of new rows>>> ");
        int add = scn.nextInt();
        String sqlQuery = "INSERT INTO products(id_product, name_product, id_group, price) VALUES(?,?,?,?)";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        for (int i =0; i<add; i++){
            System.out.println("*ID:");
            sqlStatement.setInt(1, scn.nextInt());
            System.out.println("*NameProduct:");
            sqlStatement.setString(2, scn.next());
            System.out.println("*GroupID:");
            sqlStatement.setInt(3, scn.nextInt());
            System.out.println("*ProductPrice:");
            sqlStatement.setDouble(4, scn.nextDouble());

            sqlStatement.executeUpdate();
        }
        sqlStatement.close();
        System.out.println("Rows added successfully");
        showDBaseProducts();
    }

    private void updateRowProducts() throws SQLException{
        System.out.println("Input ID for UPDATE>>>");
        int updID = scn.nextInt();

        String sqlQuery = "UPDATE products SET name_product = ?, id_group = ?, price = ? WHERE id_product = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        System.out.println("*NEW NameProduct:");
        sqlStatement.setString(1, scn.next());
        System.out.println("*NEW GroupID:");
        sqlStatement.setInt(2, scn.nextInt());
        System.out.println("*NEW ProductPrice:");
        sqlStatement.setDouble(3, scn.nextDouble());
        sqlStatement.setInt(4, updID);

        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row updated successfully");

        showDBaseProducts();
    }

    private void deleteRowProducts() throws SQLException{
        System.out.println("Input ID for DELETE>>>");
        int delID = scn.nextInt();

        String sqlQuery = "DELETE FROM products WHERE id_product = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        sqlStatement.setInt(1, delID);
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row deleted successfully");

        showDBaseProducts();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showDBaseSales() throws SQLException{
        if (databaseConnector !=null){
            String sqlQuery = "SELECT * from sales";
            Statement sqlStatement = databaseConnector.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery);

            System.out.println("\n+===========SALES DATABASE===========+");
            System.out.print(  "|ID  |ProductID     |Discount %      |\n");
            System.out.println("|------------------------------------|");
            while (sqlResult.next()){
                System.out.printf("|%-4s", sqlResult.getInt("id_sale"));
                System.out.printf("|%-14s", sqlResult.getInt("id_product"));
                System.out.printf("|%-16s|\n",sqlResult.getInt("discount"));

            }
            System.out.println("|------------------------------------|");
            System.out.println("+====OPTIONS====+");
            System.out.println("|1.Add          |");
            System.out.println("|2.Update       |");
            System.out.println("|3.Delete       |");
            System.out.println("|4.       <<Back|");
            System.out.println("|0.Exit         |");
            System.out.println(" >>> ");
            int i = scn.nextInt();

            switch (i) {
                case 1: addRowSales(); break;
                case 2: updateRowSales(); break;
                case 3: deleteRowSales(); break;
                case 4: showDBaseMenu();
                case 0: exit(0);
                default: System.out.println("!!!Invalid Input!!!"); showDBaseSales();
            }

        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }
    private void addRowSales() throws SQLException{
        System.out.println("Input NR of new rows>>> ");
        int add = scn.nextInt();

        String sqlQuery = "INSERT INTO sales(id_sale, id_product, discount) VALUES(?,?,?)";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        for (int i =0; i<add; i++){
            System.out.println("*ID:");
            sqlStatement.setInt(1, scn.nextInt());
            System.out.println("*ProductID:");
            sqlStatement.setInt(2, scn.nextInt());
            System.out.println("*Discount:");
            sqlStatement.setInt(3, scn.nextInt());

            sqlStatement.executeUpdate();
        }
        sqlStatement.close();
        System.out.println("Rows added successfully");
        showDBaseSales();
    }

    private void updateRowSales() throws SQLException{
        System.out.println("Input ID for UPDATE>>>");
        int updID = scn.nextInt();

        String sqlQuery = "UPDATE sales SET id_product = ?, discount =? WHERE id_sale = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);

        System.out.println("*NEW ProductID:");
        sqlStatement.setInt(1, scn.nextInt());
        System.out.println("*NEW Discount:");
        sqlStatement.setInt(2, scn.nextInt());
        sqlStatement.setInt(3, updID);

        sqlStatement.executeUpdate();
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row updated successfully");

        showDBaseSales();
    }

    private void deleteRowSales() throws SQLException{
        System.out.println("Input ID for DELETE>>>");
        int delID = scn.nextInt();

        String sqlQuery = "DELETE FROM sales WHERE id_sale = ?";
        PreparedStatement sqlStatement = databaseConnector.prepareStatement(sqlQuery);
        sqlStatement.setInt(1, delID);
        sqlStatement.executeUpdate();
        sqlStatement.close();

        System.out.println("Row deleted successfully");
        showDBaseSales();
    }

}
