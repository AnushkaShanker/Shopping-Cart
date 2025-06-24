package entity;
import java.util.*;
public class User {
    private int userId;
    private String password;
    private String phone;
    private String shippingAddress;
    private String billingAddress;
    private List<Order> orders = new ArrayList<>();
    private String name;
    private String email;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public String getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

    public void printOrders() {
        System.out.println("Orders for user: " + getName());
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() + ", Total: " + o.getTotalAmount());
        }
    }
}
