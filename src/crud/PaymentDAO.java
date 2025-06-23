package crud;
import java.sql.*;
import database.DBConnection;
import entity.Payment;
import java.util.ArrayList;
import java.util.List;

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

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("paymentId"));
                payment.setOrderId(rs.getInt("orderId"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentStatus(rs.getString("paymentStatus"));
                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public void addPayment(Payment payment) {
        String query = "INSERT INTO Payment VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payment.getPaymentId());
            stmt.setInt(2, payment.getOrderId());
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getPaymentStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePayment(Payment payment) {
        String query = "UPDATE Payment SET orderId = ?, amount = ?, paymentStatus = ? WHERE paymentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payment.getOrderId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentStatus());
            stmt.setInt(4, payment.getPaymentId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(int paymentId) {
        String query = "DELETE FROM Payment WHERE paymentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, paymentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
