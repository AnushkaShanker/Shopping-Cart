package crud;

import java.sql.*;
import database.DBConnection;
import entity.User;
import java.util.*;

public class UserDAO {

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM User WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setShippingAddress(rs.getString("shippingAddress"));
                user.setBillingAddress(rs.getString("billingAddress"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        String query = "INSERT INTO User (name, email, password, phone, shippingAddress, billingAddress) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getShippingAddress());
            stmt.setString(6, user.getBillingAddress());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    user.setUserId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String query = "UPDATE User SET name = ?, email = ?, password = ?, phone = ?, shippingAddress = ?, billingAddress = ? WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getShippingAddress());
            stmt.setString(6, user.getBillingAddress());
            stmt.setInt(7, user.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String query = "DELETE FROM User WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== User Management ======");
            System.out.println("1. Add User");
            System.out.println("2. View User by ID");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        User newUser = new User();

                        System.out.print("Enter Name: ");
                        newUser.setName(scanner.nextLine());

                        System.out.print("Enter Email: ");
                        newUser.setEmail(scanner.nextLine());

                        System.out.print("Enter Password: ");
                        newUser.setPassword(scanner.nextLine());

                        System.out.print("Enter Phone: ");
                        newUser.setPhone(scanner.nextLine());

                        System.out.print("Enter Shipping Address: ");
                        newUser.setShippingAddress(scanner.nextLine());

                        System.out.print("Enter Billing Address: ");
                        newUser.setBillingAddress(scanner.nextLine());

                        dao.addUser(newUser);
                        System.out.println("User added with ID: " + newUser.getUserId());
                        break;

                    case 2:
                        System.out.print("Enter User ID: ");
                        int viewId = scanner.nextInt();
                        User user = dao.getUserById(viewId);
                        if (user != null) {
                            System.out.println("User ID: " + user.getUserId());
                            System.out.println("Name: " + user.getName());
                            System.out.println("Email: " + user.getEmail());
                            System.out.println("Phone: " + user.getPhone());
                            System.out.println("Shipping Address: " + user.getShippingAddress());
                            System.out.println("Billing Address: " + user.getBillingAddress());
                        } else {
                            System.out.println("No user found with this ID.");
                        }
                        break;

                    case 3:
                        User updateUser = new User();
                        System.out.print("Enter User ID to Update: ");
                        updateUser.setUserId(scanner.nextInt());

                        scanner.nextLine();
                        System.out.print("Enter New Name: ");
                        updateUser.setName(scanner.nextLine());

                        System.out.print("Enter New Email: ");
                        updateUser.setEmail(scanner.nextLine());

                        System.out.print("Enter New Password: ");
                        updateUser.setPassword(scanner.nextLine());

                        System.out.print("Enter New Phone: ");
                        updateUser.setPhone(scanner.nextLine());

                        System.out.print("Enter New Shipping Address: ");
                        updateUser.setShippingAddress(scanner.nextLine());

                        System.out.print("Enter New Billing Address: ");
                        updateUser.setBillingAddress(scanner.nextLine());

                        dao.updateUser(updateUser);
                        System.out.println("User updated.");
                        break;

                    case 4:
                        System.out.print("Enter User ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        dao.deleteUser(deleteId);
                        System.out.println("User deleted.");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // clear input buffer
            }
        }
    }
}
