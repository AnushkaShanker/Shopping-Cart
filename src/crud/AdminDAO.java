package crud;

import java.sql.*;
import database.DBConnection;
import entity.Admin;
import java.util.*;
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
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Admin Management ======");
            System.out.println("1. Add Admin");
            System.out.println("2. View All Admins");
            System.out.println("3. Find Admin by ID");
            System.out.println("4. Update Admin");
            System.out.println("5. Delete Admin");
            System.out.println("6. Authenticate Admin");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    Admin newAdmin = new Admin();
                    System.out.print("Enter Admin ID: ");
                    newAdmin.setAdminId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Enter Name: ");
                    newAdmin.setName(scanner.nextLine());

                    System.out.print("Enter Email: ");
                    newAdmin.setEmail(scanner.nextLine());

                    System.out.print("Enter Password: ");
                    newAdmin.setPassword(scanner.nextLine());

                    boolean added = adminDAO.addAdmin(newAdmin);
                    System.out.println(added ? "Admin added successfully!" : "Failed to add admin.");
                    break;

                case 2:
                    List<Admin> admins = adminDAO.getAllAdmins();
                    System.out.println("\nAll Admins:");
                    for (Admin admin : admins) {
                        System.out.println(admin.getAdminId() + " - " + admin.getName() + " - " + admin.getEmail());
                    }
                    break;

                case 3:
                    System.out.print("Enter Admin ID: ");
                    int id = scanner.nextInt();
                    Admin found = adminDAO.getAdminById(id);
                    if (found != null) {
                        System.out.println("Admin Found: " + found.getName() + " - " + found.getEmail());
                    } else {
                        System.out.println("Admin not found.");
                    }
                    break;

                case 4:
                    Admin updateAdmin = new Admin();
                    System.out.print("Enter Admin ID to Update: ");
                    updateAdmin.setAdminId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Enter New Name: ");
                    updateAdmin.setName(scanner.nextLine());

                    System.out.print("Enter New Email: ");
                    updateAdmin.setEmail(scanner.nextLine());

                    System.out.print("Enter New Password: ");
                    updateAdmin.setPassword(scanner.nextLine());

                    boolean updated = adminDAO.updateAdmin(updateAdmin);
                    System.out.println(updated ? "Admin updated successfully!" : "Failed to update admin.");
                    break;

                case 5:
                    System.out.print("Enter Admin ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    boolean deleted = adminDAO.deleteAdmin(deleteId);
                    System.out.println(deleted ? "Admin deleted successfully!" : "Failed to delete admin.");
                    break;

                case 6:
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    Admin authenticated = adminDAO.authenticate(email, password);
                    if (authenticated != null) {
                        System.out.println("Welcome, " + authenticated.getName());
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
