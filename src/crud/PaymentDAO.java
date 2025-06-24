package crud;
import java.sql.*;
import database.DBConnection;
import entity.Payment;
import java.util.*;
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
    public static void main(String[] args){
    PaymentDAO dao = new PaymentDAO();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n====== Payment Management ======");
        System.out.println("1. Add Payment");
        System.out.println("2. View All Payments");
        System.out.println("3. View Payment by Order ID");
        System.out.println("4. Update Payment");
        System.out.println("5. Delete Payment");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Payment newPayment = new Payment();
                System.out.print("Enter Payment ID: ");
                newPayment.setPaymentId(scanner.nextInt());

                System.out.print("Enter Order ID: ");
                newPayment.setOrderId(scanner.nextInt());

                System.out.print("Enter Amount: ");
                newPayment.setAmount(scanner.nextDouble());

                scanner.nextLine(); // consume newline
                System.out.print("Enter Payment Status: ");
                newPayment.setPaymentStatus(scanner.nextLine());

                dao.addPayment(newPayment);
                System.out.println("Payment added.");
                break;

            case 2:
                List<Payment> allPayments = dao.getAllPayments();
                for (Payment payment : allPayments) {
                    System.out.println(payment.getPaymentId() + " - Order: " + payment.getOrderId() +
                            ", Amount: " + payment.getAmount() + ", Status: " + payment.getPaymentStatus());
                }
                break;

            case 3:
                System.out.print("Enter Order ID: ");
                int orderId = scanner.nextInt();
                Payment payment = dao.getPaymentByOrderId(orderId);
                if (payment != null) {
                    System.out.println("Payment ID: " + payment.getPaymentId() +
                            ", Amount: " + payment.getAmount() +
                            ", Status: " + payment.getPaymentStatus());
                } else {
                    System.out.println("No payment found for this order.");
                }
                break;

            case 4:
                Payment updatePayment = new Payment();
                System.out.print("Enter Payment ID to Update: ");
                updatePayment.setPaymentId(scanner.nextInt());

                System.out.print("Enter New Order ID: ");
                updatePayment.setOrderId(scanner.nextInt());

                System.out.print("Enter New Amount: ");
                updatePayment.setAmount(scanner.nextDouble());

                scanner.nextLine(); // consume newline
                System.out.print("Enter New Payment Status: ");
                updatePayment.setPaymentStatus(scanner.nextLine());

                dao.updatePayment(updatePayment);
                System.out.println("Payment updated.");
                break;

            case 5:
                System.out.print("Enter Payment ID to Delete: ");
                int deleteId = scanner.nextInt();
                dao.deletePayment(deleteId);
                System.out.println("Payment deleted.");
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










