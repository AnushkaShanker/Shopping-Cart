package entity;
import java.util.Date;
public class Order {
	 private int orderId;
	    private int userId;
	    private Date orderDate;
	    private double totalAmount;
	    private String paymentStatus;
	    private String orderStatus;
	    private String shippingAddress;

	    // Getters and Setters
	    public int getOrderId() {
	        return orderId;
	    }
	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }
	    public int getUserId() {
	        return userId;
	    }
	    public void setUserId(int userId) {
	        this.userId = userId;
	    }
	    public Date getOrderDate() {
	        return orderDate;
	    }
	    public void setOrderDate(Date orderDate) {
	        this.orderDate = orderDate;
	    }
	    public double getTotalAmount() {
	        return totalAmount;
	    }
	    public void setTotalAmount(double totalAmount) {
	        this.totalAmount = totalAmount;
	    }
	    public String getPaymentStatus() {
	        return paymentStatus;
	    }
	    public void setPaymentStatus(String paymentStatus) {
	        this.paymentStatus = paymentStatus;
	    }
	    public String getOrderStatus() {
	        return orderStatus;
	    }
	    public void setOrderStatus(String orderStatus) {
	        this.orderStatus = orderStatus;
	    }
	    public String getShippingAddress() {
	        return shippingAddress;
	    }
	    public void setShippingAddress(String shippingAddress) {
	        this.shippingAddress = shippingAddress;
	    }
}
