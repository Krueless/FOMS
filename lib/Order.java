public class Order {

	private int orderID;
	private string orderStatus;
	private double price;
	private boolean takeaway;
	private OrderedFoodItem[] cartItems;
	private double timestamp;

	public int getOrderID() {
		return this.orderID;
	}

}