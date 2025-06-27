package entity;
import java.util.Date;
public class Order {
	 private List<OrderItem> items;
    private Payment payment;
    private Shipment shipment;

    public Order(List<OrderItem> items, Payment payment, Shipment shipment) {
        this.items = items;
        this.payment = payment;
        this.shipment = shipment;
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public void checkout(String address) {
        double total = calculateTotal();
        payment.processPayment(total);
        shipment.deliver(address);
    }
}
