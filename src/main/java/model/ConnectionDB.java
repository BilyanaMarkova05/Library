package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String url = "jdbc:postgresql://localhost:5432/LibraryDB";
    private static final String user = "postgres";
    private static final String password = "Bibs7980";

    public static Connection connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
