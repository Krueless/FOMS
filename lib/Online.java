import java.util.Scanner;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Online} class implements the {@code IPayment} interface to handle online payments via a QR code system.
 * It includes methods to process the payment and print a receipt for the transaction.
 */
public class Online implements IPayment {
    private static final long serialVersionUID = 1L;
    private String name;  // Name of the online payment system

    /**
     * Constructs a new {@code Online} object with the specified name of the online payment type.
     *
     * @param name The name of the online payment type
     */

	public Online()
	{
		this.name = "PayNow";
	}
    public Online(String name) 
	{
        this.name = name;
    }

    /**
     * Returns the name of the online payment type.
     *
     * @return The name of the payment type.
     */
    public String getName() 
	{
        return this.name;
    }

    /**
     * Sets the name of the online payment system to the specified string.
     *
     * @param name The new name of the payment system.
     */
    public void setName(String name) 
	{
        this.name = name;
    }

    /**
     * Processes the payment for an order by simulating a PayNow QR code scan.
     * It displays the total cost and simulates a payment confirmation after a delay.
     * This implementation assumes the payment process always succeeds for simulation purposes.
     *
     * @param order The order for which payment is to be processed.
     * @return {@code true} to indicate that the payment was processed successfully.
     */
    @Override
    public boolean processPayment(Order order) {
        Scanner sc = new Scanner(System.in);
        var cartItems = order.getCartItems();
        int totalCost = 0;
		        
		// Calculate total cost by iterating over each item in the cart
		
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println(cartItems.get(i).getName() + " x " + 
                cartItems.get(i).getQuantity() + " : " + 
                cartItems.get(i).getPrice() * cartItems.get(i).getQuantity());
            totalCost += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }
        System.out.println("Total: $" + String.format("%.2f", totalCost));

        System.out.println("Please scan the PayNow QR Code below for payment");
        System.out.println("Simulating payment...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
			System.out.println("There was an error while validating your payment");
            sc.close();
            return processPayment(order);  
        }
        System.out.println("Order Successful");
        printReceipt(order);  
        sc.close();
        return true;  
    }

    /**
     * Prints a receipt for the order, including the order details and the payment confirmation time.
     * The receipt includes each item's name, quantity, price, and the total payment made.
     *
     * @param order The order for which the receipt is printed.
     */
    @Override
    public void printReceipt(Order order) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        var cartItems = order.getCartItems();
        int totalCost = 0;

        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println(cartItems.get(i).getName() + " x " + cartItems.get(i).getQuantity() + " : " +
                cartItems.get(i).getPrice() * cartItems.get(i).getQuantity());
            totalCost += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }
        System.out.println("Total: $" + String.format("%.2f", totalCost));
        System.out.println("Order " + order.getOrderID() + " paid on " + formattedDateTime);
    }
}