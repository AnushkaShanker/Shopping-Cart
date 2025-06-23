package crud;
import java.sql.*;
import database.DBConnection;
import entity.User;
public class UserDAO {

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM User WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getInt("phone"));
                user.setShippingAddress(rs.getString("shippingAddress"));
                user.setBillingAddress(rs.getString("billingAddress"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        String query = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getPhone());
            stmt.setString(6, user.getShippingAddress());
            stmt.setString(7, user.getBillingAddress());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add update/delete methods as needed
}

