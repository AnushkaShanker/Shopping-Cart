package main;

import crud.*;
import entity.*;
import java.util.*;
import java.util.Scanner;
import java.sql.Date;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAO();
    private static AdminDAO adminDAO = new AdminDAO();
    private static OrderDAO orderDAO = new OrderDAO();
    private static OrderItemDAO orderItemDAO = new OrderItemDAO();
    private static PaymentDAO paymentDAO = new PaymentDAO();
    private static ShipmentDAO shipmentDAO = new ShipmentDAO();
    private static CartItemDAO cartItemDAO = new CartItemDAO();

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("\n===== E-Commerce Management System =====");
            System.out.println("1. User Management");
            System.out.println("2. Admin Management");
            System.out.println("3. Order Management");
            System.out.println("4. Order Item Management");
            System.out.println("5. Payment Management");
            System.out.println("6. Shipment Management");
            System.out.println("7. Cart Item Management");
            System.out.println("8. System Utilities");
            System.out.println("0. Exit");
            System.out.print("\nSelect an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1:
                        userManagement();
                        break;
                    case 2:
                        adminManagement();
                        break;
                    case 3:
                        orderManagement();
                        break;
                    case 4:
                        orderItemManagement();
                        break;
                    case 5:
                        paymentManagement();
                        break;
                    case 6:
                        shipmentManagement();
                        break;
                    case 7:
                        cartItemManagement();
                        break;
                    case 8:
                        systemUtilities();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\nExiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                        pressAnyKeyToContinue();
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid number.");
                scanner.nextLine(); // clear invalid input
                pressAnyKeyToContinue();
            }
        }
        scanner.close();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pressAnyKeyToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // ===== User Management =====
    private static void userManagement() {
        boolean back = false;
        
        while (!back) {
            clearScreen();
            System.out.println("\n===== User Management =====");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. View User by ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Search Users");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    viewUserById();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    searchUsers();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    pressAnyKeyToContinue();
            }
        }
    }

    private static void addUser() {
        clearScreen();
        System.out.println("\n===== Add New User =====");
        User user = new User();
        
        try {
            System.out.print("Enter User ID: ");
            user.setUserId(scanner.nextInt());
            scanner.nextLine();
            
            System.out.print("Enter Name: ");
            user.setName(scanner.nextLine());
            
            System.out.print("Enter Email: ");
            user.setEmail(scanner.nextLine());
            
            System.out.print("Enter Password: ");
            user.setPassword(scanner.nextLine());
            
            System.out.print("Enter Phone: ");
            user.setPhone(scanner.nextInt());
            scanner.nextLine();
            
            System.out.print("Enter Shipping Address: ");
            user.setShippingAddress(scanner.nextLine());
            
            System.out.print("Enter Billing Address: ");
            user.setBillingAddress(scanner.nextLine());
            
            if (userDAO.addUser(user)) {
                System.out.println("\nUser added successfully!");
            } else {
                System.out.println("\nFailed to add user.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter correct data types.");
            scanner.nextLine();
        }
        pressAnyKeyToContinue();
    }

    private static void viewAllUsers() {
        clearScreen();
        System.out.println("\n===== All Users =====");
        List<User> users = userDAO.getAllUsers();
        
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.printf("%-10s %-20s %-25s %-15s %-30s %-30s%n", 
                "User ID", "Name", "Email", "Phone", "Shipping Address", "Billing Address");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            
            for (User user : users) {
                System.out.printf("%-10d %-20s %-25s %-15d %-30s %-30s%n", 
                    user.getUserId(), user.getName(), user.getEmail(), 
                    user.getPhone(), user.getShippingAddress(), user.getBillingAddress());
            }
        }
        pressAnyKeyToContinue();
    }

    // ... (similar implementations for other user management methods)

    // ===== Admin Management =====
    private static void adminManagement() {
        boolean back = false;
        
        while (!back) {
            clearScreen();
            System.out.println("\n===== Admin Management =====");
            System.out.println("1. Add Admin");
            System.out.println("2. View All Admins");
            System.out.println("3. View Admin by ID");
            System.out.println("4. Update Admin");
            System.out.println("5. Delete Admin");
            System.out.println("6. Admin Login");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                    viewAllAdmins();
                    break;
                case 3:
                    viewAdminById();
                    break;
                case 4:
                    updateAdmin();
                    break;
                case 5:
                    deleteAdmin();
                    break;
                case 6:
                    adminLogin();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    pressAnyKeyToContinue();
            }
        }
    }

    private static void addAdmin() {
        clearScreen();
        System.out.println("\n===== Add New Admin =====");
        Admin admin = new Admin();
        
        try {
            System.out.print("Enter Admin ID: ");
            admin.setAdminId(scanner.nextInt());
            scanner.nextLine();
            
            System.out.print("Enter Name: ");
            admin.setName(scanner.nextLine());
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            if (adminDAO.emailExists(email)) {
                System.out.println("\nEmail already exists. Please use a different email.");
                pressAnyKeyToContinue();
                return;
            }
            admin.setEmail(email);
            
            System.out.print("Enter Password: ");
            admin.setPassword(scanner.nextLine());
            
            if (adminDAO.addAdmin(admin)) {
                System.out.println("\nAdmin added successfully!");
            } else {
                System.out.println("\nFailed to add admin.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter correct data types.");
            scanner.nextLine();
        }
        pressAnyKeyToContinue();
    }

    private static void adminLogin() {
        clearScreen();
        System.out.println("\n===== Admin Login =====");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        Admin admin = adminDAO.authenticate(email, password);
        if (admin != null) {
            System.out.println("\nLogin successful! Welcome, " + admin.getName());
            adminDashboard(admin);
        } else {
            System.out.println("\nInvalid email or password.");
        }
        pressAnyKeyToContinue();
    }

    private static void adminDashboard(Admin admin) {
        boolean logout = false;
        
        while (!logout) {
            clearScreen();
            System.out.println("\n===== Admin Dashboard =====");
            System.out.println("Logged in as: " + admin.getName() + " (" + admin.getEmail() + ")");
            System.out.println("\n1. Manage Users");
            System.out.println("2. Manage Orders");
            System.out.println("3. View System Reports");
            System.out.println("4. Change Password");
            System.out.println("0. Logout");
            System.out.print("\nSelect an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    userManagement();
                    break;
                case 2:
                    orderManagement();
                    break;
                case 3:
                    viewSystemReports();
                    break;
                case 4:
                    changeAdminPassword(admin);
                    break;
                case 0:
                    logout = true;
                    System.out.println("\nLogged out successfully.");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    pressAnyKeyToContinue();
            }
        }
    }

    // ... (similar implementations for other admin management methods)

    // ===== Other Management Sections =====
    private static void orderManagement() {
        // Implementation with similar structure to userManagement
    }

    private static void orderItemManagement() {
        // Implementation with similar structure to userManagement
    }

    private static void paymentManagement() {
        // Implementation with similar structure to userManagement
    }

    private static void shipmentManagement() {
        // Implementation with similar structure to userManagement
    }

    private static void cartItemManagement() {
        // Implementation with similar structure to userManagement
    }

    private static void systemUtilities() {
        boolean back = false;
        
        while (!back) {
            clearScreen();
            System.out.println("\n===== System Utilities =====");
            System.out.println("1. Database Backup");
            System.out.println("2. System Information");
            System.out.println("3. Clear Cache");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nSelect an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    databaseBackup();
                    break;
                case 2:
                    systemInformation();
                    break;
                case 3:
                    clearCache();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    pressAnyKeyToContinue();
            }
        }
    }

    // ===== Utility Methods =====
    private static void viewSystemReports() {
        clearScreen();
        System.out.println("\n===== System Reports =====");
        // Implementation for generating reports
        System.out.println("Reports functionality will be implemented here.");
        pressAnyKeyToContinue();
    }

    private static void changeAdminPassword(Admin admin) {
        clearScreen();
        System.out.println("\n===== Change Password =====");
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        
        if (!currentPassword.equals(admin.getPassword())) {
            System.out.println("\nCurrent password is incorrect.");
            pressAnyKeyToContinue();
            return;
        }
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("\nPasswords do not match.");
            pressAnyKeyToContinue();
            return;
        }
        
        admin.setPassword(newPassword);
        if (adminDAO.updateAdmin(admin)) {
            System.out.println("\nPassword changed successfully!");
        } else {
            System.out.println("\nFailed to change password.");
        }
        pressAnyKeyToContinue();
    }

    private static void databaseBackup() {
        clearScreen();
        System.out.println("\n===== Database Backup =====");
        // Implementation for database backup
        System.out.println("Database backup functionality will be implemented here.");
        pressAnyKeyToContinue();
    }

    private static void systemInformation() {
        clearScreen();
        System.out.println("\n===== System Information =====");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("System Time: " + new Date(System.currentTimeMillis()));
        pressAnyKeyToContinue();
    }

    private static void clearCache() {
        clearScreen();
        System.out.println("\n===== Clear Cache =====");
        // Implementation for clearing cache
        System.out.println("Cache cleared successfully (simulated).");
        pressAnyKeyToContinue();
    }
}
