package OOP_Project_Classes;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Order 
{
    private int orderID;
    private OrderStatus orderStatus;
    private double price;
    private boolean takeaway;
    private ArrayList<OrderedFoodItem> cartItems;
    private ZonedDateTime timestamp;

    public Order(int orderID, double price, boolean takeaway) 
	{
        this.orderID = orderID;
        this.orderStatus = OrderStatus.ORDERING;
        this.price = price;
        this.takeaway = takeaway;
        this.cartItems = new ArrayList<>();
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore")); // Initialize timestamp at order creation
    }

    public int getOrderID() 
	{
        return this.orderID;
    }

    public OrderStatus getOrderStatus() 
	{
        return this.orderStatus;
    }

    public double getPrice() 
	{
        return this.price;
    }

    public ArrayList<OrderedFoodItem> getCartItems() 
	{
        return this.cartItems;
    }

    public boolean getTakeaway() 
	{
        return this.takeaway;
    }

    public ZonedDateTime getTimestamp() 
	{
        return this.timestamp;
    }

    public void setOrderStatus(OrderStatus newStatus) 
	{
        this.orderStatus = newStatus;
        System.out.println("Order status updated to: " + newStatus);
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore")); // Update timestamp when status changes
    }

    public void setPrice(double price) 
	{ // Fixed type to double
        this.price = price;
    }

    public void setTakeaway(boolean takeaway) 
	{
        this.takeaway = takeaway;
    }

    public void setCartItems(ArrayList<OrderedFoodItem> cartItems) 
	{
        this.cartItems = cartItems;
    }

    public void startTime() 
	{
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
    }

	String toString(){
		String returnString=String.format("orderID: %d\norderStatus: %s\nprice: %lf\n");
		if(takeaway==true){
			returnString=returnString.concat("takeaway\n");
		}else{
			returnString=returnString.concat("Not takeaway\n");
		}
		returnString=returnString.concat("cartItems:\n");
		for(int i=0;i<cartItems.size();i++){
			OrderedFoodItem orderedFoodItem=cartItems.get(i);
			returnString.concat("\tName: %s\tQuantity: %d\tPrice: %lf\n",orderedFoodItem.FoodItem.getName(),orderedFoodItem.getQuantity(),orderedFoodItem.calculatePrice());
	
		}
		returnString.concat("timestamp: %lf\n",getTimestamp());
		return returnString;
	}
}