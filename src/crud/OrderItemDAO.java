package crud;
import java.sql.*;
import database.DBConnection;
import entity.OrderItem;
import java.util.*;

public class OrderItemDAO {
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String query = "SELECT * FROM OrderItem WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("orderItemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setProductId(rs.getInt("productId"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        String query = "SELECT * FROM OrderItem";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("orderItemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setProductId(rs.getInt("productId"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void addOrderItem(OrderItem item) {
        String query = "INSERT INTO OrderItem VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, item.getOrderItemId());
            stmt.setInt(2, item.getOrderId());
            stmt.setInt(3, item.getProductId());
            stmt.setInt(4, item.getQuantity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderItem(OrderItem item) {
        String query = "UPDATE OrderItem SET orderId = ?, productId = ?, quantity = ? WHERE orderItemId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setInt(4, item.getOrderItemId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderItem(int orderItemId) {
        String query = "DELETE FROM OrderItem WHERE orderItemId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderItemId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        OrderItemDAO dao = new OrderItemDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Order Item Management ======");
            System.out.println("1. Add Order Item");
            System.out.println("2. View All Order Items");
            System.out.println("3. View Order Items by Order ID");
            System.out.println("4. Update Order Item");
            System.out.println("5. Delete Order Item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    OrderItem newItem = new OrderItem();
                    System.out.print("Enter OrderItem ID: ");
                    newItem.setOrderItemId(scanner.nextInt());

                    System.out.print("Enter Order ID: ");
                    newItem.setOrderId(scanner.nextInt());

                    System.out.print("Enter Product ID: ");
                    newItem.setProductId(scanner.nextInt());

                    System.out.print("Enter Quantity: ");
                    newItem.setQuantity(scanner.nextInt());

                    dao.addOrderItem(newItem);
                    System.out.println("Order item added.");
                    break;

                case 2:
                    List<OrderItem> allItems = dao.getAllOrderItems();
                    for (OrderItem item : allItems) {
                        System.out.println(item.getOrderItemId() + " - Order: " + item.getOrderId() +
                                ", Product: " + item.getProductId() + ", Qty: " + item.getQuantity());
                    }
                    break;

                case 3:
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    List<OrderItem> orderItems = dao.getOrderItemsByOrderId(orderId);
                    for (OrderItem item : orderItems) {
                        System.out.println(item.getOrderItemId() + " - Product: " + item.getProductId() +
                                ", Qty: " + item.getQuantity());
                    }
                    break;

                case 4:
                    OrderItem updateItem = new OrderItem();
                    System.out.print("Enter OrderItem ID to Update: ");
                    updateItem.setOrderItemId(scanner.nextInt());

                    System.out.print("Enter New Order ID: ");
                    updateItem.setOrderId(scanner.nextInt());

                    System.out.print("Enter New Product ID: ");
                    updateItem.setProductId(scanner.nextInt());

                    System.out.print("Enter New Quantity: ");
                    updateItem.setQuantity(scanner.nextInt());

                    dao.updateOrderItem(updateItem);
                    System.out.println("Order item updated.");
                    break;

                case 5:
                    System.out.print("Enter OrderItem ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    dao.deleteOrderItem(deleteId);
                    System.out.println("Order item deleted.");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
