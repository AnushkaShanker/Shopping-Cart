package database;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("✅ Connection successful using db.properties!");
                conn.close();
            } else {
                System.out.println("❌ Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
