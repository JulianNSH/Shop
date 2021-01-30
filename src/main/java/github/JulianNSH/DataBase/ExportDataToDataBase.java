package github.JulianNSH.DataBase;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExportDataToDataBase {


    public ExportDataToDataBase(){}

    Connection conn = ConnectToDB.connect();
    public void exportDBMenu() throws SQLException {
        if (conn !=null){
            //System.out.println("Connected");
            String sql = "SELECT * from employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("id_employee")+"\t"+
                                    rs.getString("name_empl")+"\t"+
                                    rs.getString("surname_empl")+"\t"+
                                    rs.getString("position")+"\t"+
                                    rs.getString("age")+"\t"+
                                    rs.getString("salary")+"\t");
            }
        } else {
            System.out.println("Can't connect to the DataBase");
        }
    }

}
