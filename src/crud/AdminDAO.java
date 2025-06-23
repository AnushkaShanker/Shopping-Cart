package crud;

import java.sql.*;
import database.DBConnection;
import entity.Admin;
public class AdminDAO {
    public Admin getAdminById(int adminId) {
        Admin admin = null;
        String query = "SELECT * FROM Admin WHERE adminId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
