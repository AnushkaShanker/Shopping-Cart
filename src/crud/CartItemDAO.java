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
	    public List<CartItem> getAllCartItems() {
	        List<CartItem> items = new ArrayList<>();
	        String query = "SELECT * FROM CartItem";

	        try (Connection conn = DBConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {

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

	    public void addCartItem(CartItem item) {
	        String query = "INSERT INTO CartItem VALUES (?, ?, ?, ?)";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, item.getCartItemId());
	            stmt.setInt(2, item.getCartId());
	            stmt.setInt(3, item.getProductId());
	            stmt.setInt(4, item.getQuantity());

	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void updateCartItem(CartItem item) {
	        String query = "UPDATE CartItem SET cartId = ?, productId = ?, quantity = ? WHERE cartItemId = ?";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, item.getCartId());
	            stmt.setInt(2, item.getProductId());
	            stmt.setInt(3, item.getQuantity());
	            stmt.setInt(4, item.getCartItemId());

	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteCartItem(int cartItemId) {
	        String query = "DELETE FROM CartItem WHERE cartItemId = ?";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, cartItemId);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   
	    public static void main(String[] args) {
	    CartItemDAO dao = new CartItemDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Cart Item Management ======");
            System.out.println("1. Add Cart Item");
            System.out.println("2. View All Cart Items");
            System.out.println("3. View Cart Items by Cart ID");
            System.out.println("4. Update Cart Item");
            System.out.println("5. Delete Cart Item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    CartItem newItem = new CartItem();
                    System.out.print("Enter CartItem ID: ");
                    newItem.setCartItemId(scanner.nextInt());

                    System.out.print("Enter Cart ID: ");
                    newItem.setCartId(scanner.nextInt());

                    System.out.print("Enter Product ID: ");
                    newItem.setProductId(scanner.nextInt());

                    System.out.print("Enter Quantity: ");
                    newItem.setQuantity(scanner.nextInt());

                    dao.addCartItem(newItem);
                    System.out.println("Cart item added.");
                    break;

                case 2:
                    List<CartItem> allItems = dao.getAllCartItems();
                    for (CartItem item : allItems) {
                        System.out.println(item.getCartItemId() + " - Cart: " + item.getCartId() + ", Product: " + item.getProductId() + ", Qty: " + item.getQuantity());
                    }
                    break;

                case 3:
                    System.out.print("Enter Cart ID: ");
                    int cartId = scanner.nextInt();
                    List<CartItem> cartItems = dao.getCartItemsByCartId(cartId);
                    for (CartItem item : cartItems) {
                        System.out.println(item.getCartItemId() + " - Product: " + item.getProductId() + ", Qty: " + item.getQuantity());
                    }
                    break;

                case 4:
                    CartItem updateItem = new CartItem();
                    System.out.print("Enter CartItem ID to Update: ");
                    updateItem.setCartItemId(scanner.nextInt());

                    System.out.print("Enter New Cart ID: ");
                    updateItem.setCartId(scanner.nextInt());

                    System.out.print("Enter New Product ID: ");
                    updateItem.setProductId(scanner.nextInt());

                    System.out.print("Enter New Quantity: ");
                    updateItem.setQuantity(scanner.nextInt());

                    dao.updateCartItem(updateItem);
                    System.out.println("Cart item updated.");
                    break;

                case 5:
                    System.out.print("Enter CartItem ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    dao.deleteCartItem(deleteId);
                    System.out.println("Cart item deleted.");
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


