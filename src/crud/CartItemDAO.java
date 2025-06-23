package crud;
import java.sql.*;
import database.DBConnection;
import entity.CartItem;
import java.util.*;
public class CartItemDAO {
	
	    public List<CartItem> getCartItemsByCartId(int cartId) {
	        List<CartItem> items = new ArrayList<>();
	        String query = "SELECT * FROM CartItem WHERE cartId = ?";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, cartId);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                CartItem item = new CartItem();
	                item.setCartItemId(rs.getInt("cartItemId"));
	                item.setCartId(rs.getInt("cartId"));
	                item.setProductId(rs.getInt("productId"));
	                item.setQuantity(rs.getInt("quantity"));
	                items.add(item);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return items;
	    }
	}


