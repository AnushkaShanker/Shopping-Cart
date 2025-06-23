package entity;

public class Payment {
	 private int paymentId;
	    private int orderId;
	    private double amount;
	    private String paymentStatus;

	    // Getters and Setters
	    public int getPaymentId() {
	        return paymentId;
	    }
	    public void setPaymentId(int paymentId) {
	        this.paymentId = paymentId;
	    }
	    public int getOrderId() {
	        return orderId;
	    }
	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }
	    public double getAmount() {
	        return amount;
	    }
	    public void setAmount(double amount) {
	        this.amount = amount;
	    }
	    public String getPaymentStatus() {
	        return paymentStatus;
	    }
	    public void setPaymentStatus(String paymentStatus) {
	        this.paymentStatus = paymentStatus;
	    }
}
