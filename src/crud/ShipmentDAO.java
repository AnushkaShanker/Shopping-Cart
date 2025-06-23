package crud;
import java.sql.*;
import database.DBConnection;
import entity.Shipment;
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
}