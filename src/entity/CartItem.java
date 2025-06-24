package entity;

public class CartItem {
	 private int cartItemId;
	    private int cartId;
	    private Product product;
	    private int quantity;

	    // Getters and Setters
	    public int getCartItemId() {
	        return cartItemId;
	    }
	    public void setCartItemId(int cartItemId) {
	        this.cartItemId = cartItemId;
	    }
	    public int getCartId() {
	        return cartId;
	    }
	    public void setCartId(int cartId) {
	        this.cartId = cartId;
	    }
	
	    public int getQuantity() {
	        return quantity;
	    }
	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }
	    public Product getProduct() {
	        return product;
	    }
	    public void setProduct(Product product) {
	        this.product = product;
	    }
	    public void printCartItem() {
	        System.out.println("CartItem ID: " + cartItemId + ", Product ID: " + product.getProductId() + ", Product Name: " + product.getName() +
	                ", Price: " + product.getPrice() + ", Quantity: " + quantity);
	    }
}


