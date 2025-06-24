package crud;

import java.sql.*;
import database.DBConnection;
import entity.Shipment;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShipmentDAO {

    public Shipment getShipmentByOrderId(int orderId) {
        Shipment shipment = null;
        String query = "SELECT * FROM Shipment WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                shipment = new Shipment();
                shipment.setShipmentId(rs.getInt("shipmentId"));
                shipment.setOrderId(rs.getInt("orderId"));
                shipment.setShipmentDate(rs.getDate("shipmentDate"));
                shipment.setTrackingNumber(rs.getInt("trackingNumber"));
                shipment.setCarrier(rs.getString("carrier"));
                shipment.setStatus(rs.getString("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipment;
    }

    public List<Shipment> getAllShipments() {
        List<Shipment> shipments = new ArrayList<>();
        String query = "SELECT * FROM Shipment";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Shipment shipment = new Shipment();
                shipment.setShipmentId(rs.getInt("shipmentId"));
                shipment.setOrderId(rs.getInt("orderId"));
                shipment.setShipmentDate(rs.getDate("shipmentDate"));
                shipment.setTrackingNumber(rs.getInt("trackingNumber"));
                shipment.setCarrier(rs.getString("carrier"));
                shipment.setStatus(rs.getString("status"));
                shipments.add(shipment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    public void addShipment(Shipment shipment) {
        String query = "INSERT INTO Shipment (orderId, shipmentDate, trackingNumber, carrier, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, shipment.getOrderId());
            stmt.setDate(2, new java.sql.Date(shipment.getShipmentDate().getTime()));
            stmt.setInt(3, shipment.getTrackingNumber());
            stmt.setString(4, shipment.getCarrier());
            stmt.setString(5, shipment.getStatus());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    shipment.setShipmentId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShipment(Shipment shipment) {
        String query = "UPDATE Shipment SET orderId = ?, shipmentDate = ?, trackingNumber = ?, carrier = ?, status = ? WHERE shipmentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, shipment.getOrderId());
            stmt.setDate(2, new java.sql.Date(shipment.getShipmentDate().getTime()));
            stmt.setInt(3, shipment.getTrackingNumber());
            stmt.setString(4, shipment.getCarrier());
            stmt.setString(5, shipment.getStatus());
            stmt.setInt(6, shipment.getShipmentId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShipment(int shipmentId) {
        String query = "DELETE FROM Shipment WHERE shipmentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, shipmentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ShipmentDAO dao = new ShipmentDAO();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\n====== Shipment Management ======");
            System.out.println("1. Add Shipment");
            System.out.println("2. View All Shipments");
            System.out.println("3. View Shipment by Order ID");
            System.out.println("4. Update Shipment");
            System.out.println("5. Delete Shipment");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        Shipment newShipment = new Shipment();

                        System.out.print("Enter Order ID: ");
                        newShipment.setOrderId(scanner.nextInt());

                        scanner.nextLine();
                        System.out.print("Enter Shipment Date (yyyy-MM-dd): ");
                        String dateStr = scanner.nextLine();
                        Date shipmentDate = sdf.parse(dateStr);
                        newShipment.setShipmentDate(shipmentDate);

                        System.out.print("Enter Tracking Number: ");
                        newShipment.setTrackingNumber(scanner.nextInt());

                        scanner.nextLine();
                        System.out.print("Enter Carrier: ");
                        newShipment.setCarrier(scanner.nextLine());

                        System.out.print("Enter Status: ");
                        newShipment.setStatus(scanner.nextLine());

                        dao.addShipment(newShipment);
                        System.out.println("Shipment added with ID: " + newShipment.getShipmentId());
                        break;

                    case 2:
                        List<Shipment> shipments = dao.getAllShipments();
                        for (Shipment s : shipments) {
                            System.out.println(s.getShipmentId() + " - Order: " + s.getOrderId() +
                                    ", Date: " + sdf.format(s.getShipmentDate()) +
                                    ", Tracking #: " + s.getTrackingNumber() +
                                    ", Carrier: " + s.getCarrier() +
                                    ", Status: " + s.getStatus());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Order ID: ");
                        int orderId = scanner.nextInt();
                        Shipment found = dao.getShipmentByOrderId(orderId);
                        if (found != null) {
                            System.out.println("Shipment ID: " + found.getShipmentId() +
                                    ", Date: " + sdf.format(found.getShipmentDate()) +
                                    ", Tracking #: " + found.getTrackingNumber() +
                                    ", Carrier: " + found.getCarrier() +
                                    ", Status: " + found.getStatus());
                        } else {
                            System.out.println("No shipment found for this order.");
                        }
                        break;

                    case 4:
                        Shipment update = new Shipment();
                        System.out.print("Enter Shipment ID to Update: ");
                        update.setShipmentId(scanner.nextInt());

                        System.out.print("Enter New Order ID: ");
                        update.setOrderId(scanner.nextInt());

                        scanner.nextLine();
                        System.out.print("Enter New Shipment Date (yyyy-MM-dd): ");
                        update.setShipmentDate(sdf.parse(scanner.nextLine()));

                        System.out.print("Enter New Tracking Number: ");
                        update.setTrackingNumber(scanner.nextInt());

                        scanner.nextLine();
                        System.out.print("Enter New Carrier: ");
                        update.setCarrier(scanner.nextLine());

                        System.out.print("Enter New Status: ");
                        update.setStatus(scanner.nextLine());

                        dao.updateShipment(update);
                        System.out.println("Shipment updated.");
                        break;

                    case 5:
                        System.out.print("Enter Shipment ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        dao.deleteShipment(deleteId);
                        System.out.println("Shipment deleted.");
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
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}

               
