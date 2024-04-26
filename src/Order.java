import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.ArrayList;
import java.time.Duration;

/**
 * Represents an order within a restaurant management system. This class' only responsibility is to hold the details of an order,
 * including identification, status, pricing, takeaway or dine-in option, list of ordered items, and timestamp.
 */
public class Order implements IGetBranchName{
    private static final long serialVersionUID = 1L;
    private Integer orderID;
    private OrderStatus orderStatus;
    private double price;
    private boolean takeaway;
    private ArrayList<OrderedFoodItem> cartItems;
    private ZonedDateTime timestamp;
    private String branchName;

    /**
     * Constructs a new Order with specified details, initializing the order status to ORDERING and setting the timestamp.
     *
     * @param orderID The unique identifier for the order.
     * @param price The total price of the order.
     * @param takeaway Indicates whether the order is for takeaway (true) or dine-in (false).
     */
    public Order(Integer orderID, boolean takeaway, String branchName) {
        this.orderID = orderID;
        this.orderStatus = OrderStatus.ORDERING;
        this.price = 0.0;
        this.takeaway = takeaway;
        this.cartItems = new ArrayList<>();
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        this.branchName = branchName;
    }

    public String getBranchName(){
        return branchName;
    }

    /**
     * Returns the unique identifier of the order.
     *
     * @return The order ID.
     */
    public Integer getOrderID() {
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


    public void updateCancelled() {
        if (orderStatus == OrderStatus.READY_TO_PICKUP) {
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
            Duration duration = Duration.between(timestamp, now);
    
            if (duration.toMinutes() >= 2) {
                this.setOrderStatus(OrderStatus.CANCELLED);
            }
        }
    }

    /**
     * Generates a detailed string representation of the order, including all attributes and their values,
     * formatted for easy reading. This is particularly useful for logging and debugging purposes.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        updateCancelled();
        String returnString = String.format("OrderID: %d\t OrderStatus: %s\t Price: %.2f",
                                            this.orderID, this.orderStatus, this.price);
        if (this.takeaway) {
            returnString += " (Takeaway)\n";
        } else {
            returnString += " (Dine-in)\n";
        }
        returnString += "CartItems:\n";
        for (OrderedFoodItem item : this.cartItems) {
            returnString += String.format("\tName: %s\tQuantity: %d\tPrice: %.2f\n",
                                          item.getName(), item.getQuantity(), item.calculatePrice());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = timestamp.format(formatter);
        returnString += "Timestamp: " + formattedDateTime;
        return returnString;
    }


    /**
     * @return
     */
    public String viewOrderStatus() {
        updateCancelled();
        String returnString = null;
        switch(orderStatus){
            case ORDERING:
            returnString = "Order has not yet been sent";
            break;
            case PREPARING:
            returnString = "Order is being Prepared";
            break;
            case READY_TO_PICKUP:
            returnString = "Order is ready to be picked up!";
            break;
            case COMPLETED:
            returnString = "Order was completed and has been picked up.";
            break;
            case CANCELLED:
            returnString = "Order was cancelled as it was not picked up within the specified timeframe.";
            break;
        }
        return returnString;

    }
}