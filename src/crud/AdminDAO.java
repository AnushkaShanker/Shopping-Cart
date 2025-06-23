package crud;

import java.sql.*;
import database.DBConnection;
import entity.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    // Get admin by ID
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

    // Get admin by email (useful for login)
    public Admin getAdminByEmail(String email) {
        Admin admin = null;
        String query = "SELECT * FROM Admin WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
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

    // Get all admins
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Add new admin
    public boolean addAdmin(Admin admin) {
        String query = "INSERT INTO Admin (adminId, name, email, password) VALUES (?, ?, ?, ?)";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, admin.getAdminId());
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Update admin
    public boolean updateAdmin(Admin admin) {
        String query = "UPDATE Admin SET name = ?, email = ?, password = ? WHERE adminId = ?";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.setInt(4, admin.getAdminId());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Delete admin
    public boolean deleteAdmin(int adminId) {
        String query = "DELETE FROM Admin WHERE adminId = ?";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Check if email already exists (for validation)
    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM Admin WHERE email = ?";
        boolean exists = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Authenticate admin (for login)
    public Admin authenticate(String email, String password) {
        String query = "SELECT * FROM Admin WHERE email = ? AND password = ?";
        Admin admin = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
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
