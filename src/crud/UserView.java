package view;

import entity.User;
import service.UserService;
import java.util.Scanner;

public class UserView {
    private final UserService userService;
    private final Scanner scanner;

    public UserView(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
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
                        addUserMenu();
                        break;
                    case 2:
                        viewUserMenu();
                        break;
                    case 3:
                        updateUserMenu();
                        break;
                    case 4:
                        deleteUserMenu();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    private void addUserMenu() {
        User newUser = new User();
        System.out.print("Enter User ID: ");
        newUser.setUserId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter Name: ");
        newUser.setName(scanner.nextLine());
        System.out.print("Enter Email: ");
        newUser.setEmail(scanner.nextLine());
        System.out.print("Enter Password: ");
        newUser.setPassword(scanner.nextLine());
        System.out.print("Enter Phone: ");
        newUser.setPhone(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter Shipping Address: ");
        newUser.setShippingAddress(scanner.nextLine());
        System.out.print("Enter Billing Address: ");
        newUser.setBillingAddress(scanner.nextLine());

        userService.addUser(newUser);
        System.out.println("User added successfully!");
    }

    private void viewUserMenu() {
        System.out.print("Enter User ID: ");
        User user = userService.getUserById(scanner.nextInt());
        if (user != null) {
            System.out.println("\nUser Details:");
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Shipping Address: " + user.getShippingAddress());
            System.out.println("Billing Address: " + user.getBillingAddress());
        } else {
            System.out.println("User not found!");
        }
    }

    private void updateUserMenu() {
        User updateUser = new User();
        System.out.print("Enter User ID to update: ");
        updateUser.setUserId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        updateUser.setName(scanner.nextLine());
        System.out.print("Enter New Email: ");
        updateUser.setEmail(scanner.nextLine());
        System.out.print("Enter New Password: ");
        updateUser.setPassword(scanner.nextLine());
        System.out.print("Enter New Phone: ");
        updateUser.setPhone(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter New Shipping Address: ");
        updateUser.setShippingAddress(scanner.nextLine());
        System.out.print("Enter New Billing Address: ");
        updateUser.setBillingAddress(scanner.nextLine());

        userService.updateUser(updateUser);
        System.out.println("User updated successfully!");
    }

    private void deleteUserMenu() {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        userService.deleteUser(userId);
        System.out.println("User deleted successfully!");
    }
}
