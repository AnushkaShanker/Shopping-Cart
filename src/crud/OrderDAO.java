package crud;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import database.DBConnection;
import entity.Order;
public class OrderDAO {
    public Order getOrderById(int orderId) {
        Order order = null;
        String query = "SELECT * FROM Orders WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setPaymentStatus(rs.getString("paymentStatus"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setShippingAddress(rs.getString("shippingAddress"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                orders.add(extractOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void addOrder(Order order) {
        String query = "INSERT INTO Orders VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getUserId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getPaymentStatus());
            stmt.setString(6, order.getOrderStatus());
            stmt.setString(7, order.getShippingAddress());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        String query = "UPDATE Orders SET userId = ?, orderDate = ?, totalAmount = ?, paymentStatus = ?, orderStatus = ?, shippingAddress = ? WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, order.getUserId());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getPaymentStatus());
            stmt.setString(5, order.getOrderStatus());
            stmt.setString(6, order.getShippingAddress());
            stmt.setInt(7, order.getOrderId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String query = "DELETE FROM Orders WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("orderId"));
        order.setUserId(rs.getInt("userId"));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setTotalAmount(rs.getDouble("totalAmount"));
        order.setPaymentStatus(rs.getString("paymentStatus"));
        order.setOrderStatus(rs.getString("orderStatus"));
        order.setShippingAddress(rs.getString("shippingAddress"));
        return order;
    }

public static void main(String[] args) {
    OrderDAO dao = new OrderDAO();
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    while (true) {
        System.out.println("\n====== Order Management ======");
        System.out.println("1. Add Order");
        System.out.println("2. View All Orders");
        System.out.println("3. View Order by ID");
        System.out.println("4. Update Order");
        System.out.println("5. Delete Order");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            switch (choice) {
                case 1:
                    Order newOrder = new Order();
                    System.out.print("Enter Order ID: ");
                    newOrder.setOrderId(scanner.nextInt());

                    System.out.print("Enter User ID: ");
                    newOrder.setUserId(scanner.nextInt());

                    scanner.nextLine();
                    System.out.print("Enter Order Date (yyyy-MM-dd): ");
                    newOrder.setOrderDate(sdf.parse(scanner.nextLine()));

                    System.out.print("Enter Total Amount: ");
                    newOrder.setTotalAmount(scanner.nextDouble());

                    scanner.nextLine();
                    System.out.print("Enter Payment Status: ");
                    newOrder.setPaymentStatus(scanner.nextLine());

                    System.out.print("Enter Order Status: ");
                    newOrder.setOrderStatus(scanner.nextLine());

                    System.out.print("Enter Shipping Address: ");
                    newOrder.setShippingAddress(scanner.nextLine());

                    dao.addOrder(newOrder);
                    System.out.println("Order added.");
                    break;

                case 2:
                    List<Order> orders = dao.getAllOrders();
                    for (Order o : orders) {
                        System.out.println(o.getOrderId() + " | User: " + o.getUserId() +
                                " | Date: " + sdf.format(o.getOrderDate()) +
                                " | Amount: " + o.getTotalAmount() +
                                " | Payment: " + o.getPaymentStatus() +
                                " | Status: " + o.getOrderStatus() +
                                " | Address: " + o.getShippingAddress());
                    }
                    break;

                case 3:
                    System.out.print("Enter Order ID: ");
                    Order order = dao.getOrderById(scanner.nextInt());
                    if (order != null) {
                        System.out.println("Order ID: " + order.getOrderId());
                        System.out.println("User ID: " + order.getUserId());
                        System.out.println("Order Date: " + sdf.format(order.getOrderDate()));
                        System.out.println("Total Amount: " + order.getTotalAmount());
                        System.out.println("Payment Status: " + order.getPaymentStatus());
                        System.out.println("Order Status: " + order.getOrderStatus());
                        System.out.println("Shipping Address: " + order.getShippingAddress());
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;

                case 4:
                    Order updateOrder = new Order();
                    System.out.print("Enter Order ID to Update: ");
                    updateOrder.setOrderId(scanner.nextInt());

                    System.out.print("Enter New User ID: ");
                    updateOrder.setUserId(scanner.nextInt());

                    scanner.nextLine();
                    System.out.print("Enter New Order Date (yyyy-MM-dd): ");
                    updateOrder.setOrderDate(sdf.parse(scanner.nextLine()));

                    System.out.print("Enter New Total Amount: ");
                    updateOrder.setTotalAmount(scanner.nextDouble());

                    scanner.nextLine();
                    System.out.print("Enter New Payment Status: ");
                    updateOrder.setPaymentStatus(scanner.nextLine());

                    System.out.print("Enter New Order Status: ");
                    updateOrder.setOrderStatus(scanner.nextLine());

                    System.out.print("Enter New Shipping Address: ");
                    updateOrder.setShippingAddress(scanner.nextLine());

                    dao.updateOrder(updateOrder);
                    System.out.println("Order updated.");
                    break;

                case 5:
                    System.out.print("Enter Order ID to Delete: ");
                    dao.deleteOrder(scanner.nextInt());
                    System.out.println("Order deleted.");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine(); // clear input buffer
        }
    }
}
}
