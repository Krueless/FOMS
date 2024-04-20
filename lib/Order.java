import java.io.*;
public class Order implements Serializable{

	private int orderID;
	private String orderStatus;
	private double price;
	private boolean takeaway;
	private ArrayList<OrderedFoodItem> cartItems;
	private zoneDateTime timestamp;
	private String branchName;

	public int getOrderID() {
		return this.orderID;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * 
	 * @param orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getTakeaway() {
		return this.takeaway;
	}

	/**
	 * 
	 * @param takeaway
	 */
	public void setTakeaway(boolean takeaway) {
		this.takeaway = takeaway;
	}

	public ArrayList<OrderedFoodItem> getCartItems() {
		// TODO - implement Order.getCartItems
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cartItems
	 */
	public void setCartItems(ArrayList<OrderedFoodItem> cartItems) {
		// TODO - implement Order.setCartItems
		throw new UnsupportedOperationException();
	}

	public double getTimestamp() {
		return this.timestamp;
	}

	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 * @param orderID
	 * @param orderStatus
	 * @param price
	 * @param takeaway
	 * @param cartItems
	 */
	public Order(int orderID, String orderStatus, double price, boolean takeaway, ArrayList<OrderedFoodItem> cartItems) {
		// TODO - implement Order.Order
		throw new UnsupportedOperationException();
	}

	public String getBranchName() {
		return this.branchName;
	}

}