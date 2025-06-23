package entity;
import java.util.Date;
public class Shipment {
	 private int shipmentId;
	    private int orderId;
	    private Date shipmentDate;
	    private int trackingNumber;
	    private String carrier;
	    private String status;

	    // Getters and Setters
	    public int getShipmentId() {
	        return shipmentId;
	    }
	    public void setShipmentId(int shipmentId) {
	        this.shipmentId = shipmentId;
	    }
	    public int getOrderId() {
	        return orderId;
	    }
	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }
	    public Date getShipmentDate() {
	        return shipmentDate;
	    }
	    public void setShipmentDate(Date shipmentDate) {
	        this.shipmentDate = shipmentDate;
	    }
	    public int getTrackingNumber() {
	        return trackingNumber;
	    }
	    public void setTrackingNumber(int trackingNumber) {
	        this.trackingNumber = trackingNumber;
	    }
	    public String getCarrier() {
	        return carrier;
	    }
	    public void setCarrier(String carrier) {
	        this.carrier = carrier;
	    }
	    public String getStatus() {
	        return status;
	    }
	    public void setStatus(String status) {
	        this.status = status;
	    }
}
