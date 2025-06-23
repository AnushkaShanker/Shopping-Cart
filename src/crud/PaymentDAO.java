package crud;
import java.sql.*;
import database.DBConnection;
import entity.Payment;

public class PaymentDAO {
    public Payment getPaymentByOrderId(int orderId) {
        Payment payment = null;
        String query = "SELECT * FROM Payment WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment();
                payment.setPaymentId(rs.getInt("paymentId"));
                payment.setOrderId(rs.getInt("orderId"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentStatus(rs.getString("paymentStatus"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
