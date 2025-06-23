package crud;
import java.sql.*;
import database.DBConnection;
import entity.Shipment;
import java.util.ArrayList;
import java.util.List;

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
        String query = "INSERT INTO Shipment VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, shipment.getShipmentId());
            stmt.setInt(2, shipment.getOrderId());
            stmt.setDate(3, new java.sql.Date(shipment.getShipmentDate().getTime()));
            stmt.setInt(4, shipment.getTrackingNumber());
            stmt.setString(5, shipment.getCarrier());
            stmt.setString(6, shipment.getStatus());

            stmt.executeUpdate();

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
}
