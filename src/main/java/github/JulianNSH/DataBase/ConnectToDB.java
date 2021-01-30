package github.JulianNSH.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {
    private static final String url = "jdbc:postgresql://localhost:5432/shop";
    private static final String user = "postgres";
    private static final String password = "root";

    public ConnectToDB(){}

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to PG server");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
