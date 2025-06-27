package entity;
import java.util.*;
public class User {
    private int userId;
    private String username;
    private String email;
    private List<CartItem> cart;

    public User(int userId, String username, String email, List<CartItem> cart) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.cart = cart;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<CartItem> getCart() {
        return cart;
    }
}
