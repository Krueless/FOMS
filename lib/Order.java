import java.time.ZonedDateTime;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Represents an order within a restaurant management system. This class' only responsibility is to hold the details of an order,
 * including identification, status, pricing, takeaway or dine-in option, list of ordered items, and timestamp.
 */
public class Order implements Serializable{
    private int orderID;
    private OrderStatus orderStatus;
    private double price;
    private boolean takeaway;
    private ArrayList<OrderedFoodItem> cartItems;
    private ZonedDateTime timestamp;

    /**
     * Constructs a new Order with specified details, initializing the order status to ORDERING and setting the timestamp.
     *
     * @param orderID The unique identifier for the order.
     * @param price The total price of the order.
     * @param takeaway Indicates whether the order is for takeaway (true) or dine-in (false).
     */
    public Order(int orderID, double price, boolean takeaway) {
        this.orderID = orderID;
        this.orderStatus = OrderStatus.ORDERING;
        this.price = price;
        this.takeaway = takeaway;
        this.cartItems = new ArrayList<>();
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
    }

    /**
     * Returns the unique identifier of the order.
     *
     * @return The order ID.
     */
    public int getOrderID() {
        return this.orderID;
    }

    /**
     * Returns the current status of the order.
     *
     * @return The order status.
     */
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * Returns the total price of the order.
     *
     * @return The price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the list of food items in the order.
     *
     * @return An ArrayList of OrderedFoodItem objects.
     */
    public ArrayList<OrderedFoodItem> getCartItems() {
        return this.cartItems;
    }

    /**
     * Returns whether the order is for takeaway.
     *
     * @return True if the order is for takeaway, false otherwise.
     */
    public boolean getTakeaway() {
        return this.takeaway;
    }

    /**
     * Returns the timestamp of the last status update of the order.
     *
     * @return The timestamp of the order.
     */
    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Updates the status of the order and the timestamp to reflect the change.
     *
     * @param newStatus The new status to be set for the order.
     */
    public void setOrderStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        System.out.println("Order status updated to: " + newStatus);
    }

    /**
     * Sets the price of the order.
     *
     * @param price The new price of the order.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets whether the order is for takeaway.
     *
     * @param takeaway True if the order is for takeaway, false otherwise.
     */
    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }

    /**
     * Replaces the current list of ordered food items with a new list.
     *
     * @param cartItems The new list of OrderedFoodItem objects.
     */
    public void setCartItems(ArrayList<OrderedFoodItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Resets the timestamp to the current time, typically called when the order starts processing.
     */
    public void startTime() {
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
    }

    /**
     * Generates a detailed string representation of the order, including all attributes and their values,
     * formatted for easy reading. This is particularly useful for logging and debugging purposes.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        String returnString = null;
        switch(orderStatus){
            case OrderStatus.ORDERING:
            returnString = "Order has not yet been sent";
            break;
            case OrderStatus.PREPARING:
            returnString = "Order is being Prepared";
            break;
            case OrderStatus.READY_TO_PICKUP:
            returnString = "Order is ready to be picked up!";
            break;
            case OrderStatus.COMPLETED:
            returnString = "Order was completed and has been picked up.";
            break;
            case OrderStatus.CANCELLED:
            returnString = "Order was cancelled";
            break;
        }
        return returnString;

    }
}