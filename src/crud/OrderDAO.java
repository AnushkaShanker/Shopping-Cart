package crud;

import java.sql.*;
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
}
