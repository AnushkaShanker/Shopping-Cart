package crud;

import entity.Product;
import database.DBConnection;

import java.sql.*;
import java.util.*;

public class ProductDAO {

    // Get a product by ID
    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM Product WHERE productId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // Get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Add a product
    public boolean addProduct(Product product) {
        String query = "INSERT INTO Product (name, description, price, stock) VALUES (?, ?, ?, ?)";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    product.setProductId(keys.getInt(1));
                }
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // Update a product
    public boolean updateProduct(Product product) {
        String query = "UPDATE Product SET name = ?, description = ?, price = ?, stock = ? WHERE productId = ?";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getProductId());

            int rows = stmt.executeUpdate();
            success = rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // Delete a product
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM Product WHERE productId = ?";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            int rows = stmt.executeUpdate();
            success = rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== PRODUCT MENU ===");
            System.out.println("1. View All Products");
            System.out.println("2. View Product by ID");
            System.out.println("3. Add Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    List<Product> products = productDAO.getAllProducts();
                    for (Product p : products) {
                        System.out.println(p.getProductId() + " - " + p.getName() + " - " + p.getPrice());
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    Product product = productDAO.getProductById(pid);
                    if (product != null) {
                        System.out.println("ID: " + product.getProductId());
                        System.out.println("Name: " + product.getName());
                        System.out.println("Description: " + product.getDescription());
                        System.out.println("Price: " + product.getPrice());
                        System.out.println("Stock: " + product.getStock());
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    Product newProduct = new Product();
                    System.out.print("Name: ");
                    newProduct.setName(sc.nextLine());
                    System.out.print("Description: ");
                    newProduct.setDescription(sc.nextLine());
                    System.out.print("Price: ");
                    newProduct.setPrice(sc.nextDouble());
                    System.out.print("Stock Quantity: ");
                    newProduct.setStock(sc.nextInt());

                    if (productDAO.addProduct(newProduct)) {
                        System.out.println("Product added successfully! ID: " + newProduct.getProductId());
                    } else {
                        System.out.println("Failed to add product.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID of product to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    Product pUpdate = productDAO.getProductById(updateId);
                    if (pUpdate != null) {
                        System.out.print("New Name: ");
                        pUpdate.setName(sc.nextLine());
                        System.out.print("New Description: ");
                        pUpdate.setDescription(sc.nextLine());
                        System.out.print("New Price: ");
                        pUpdate.setPrice(sc.nextDouble());
                        System.out.print("New Stock Quantity: ");
                        pUpdate.setStock(sc.nextInt());

                        if (productDAO.updateProduct(pUpdate)) {
                            System.out.println("Product updated successfully.");
                        } else {
                            System.out.println("Failed to update product.");
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int delId = sc.nextInt();
                    if (productDAO.deleteProduct(delId)) {
                        System.out.println("Product deleted.");
                    } else {
                        System.out.println("Could not delete product.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}












