package database;
import java.sql.*;
public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/shopdb";
        String username = "root";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }
}







