import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Implements the {@code IPayment} interface to handle online payments for orders via a QR code system.
 * This class supports operations to process payments and print detailed receipts for transactions.
 * It assumes payments always succeed for demonstration purposes.
 */
public class Online implements IPayment {
    private static final long serialVersionUID = 1L;
    private String name;  // Name of the online payment system

    /**
     * Constructs a new {@code Online} object with the name of the online payment type.
     *
     * @param name The name of the online payment type used in transactions.
     */
    public Online(String name) 
	{
        this.name = name;
    }

    /**
     * Retrieves the name of the online payment system.
     *
     * @return The name of the online payment system.
     */
    public String getName() 
	{
        return this.name;
    }

    /**
     * Sets the name of the online payment system to the specified string.
     *
     * @param name The new name for the online payment system.
     */
    public void setName(String name) 
	{
        this.name = name;
    }

    /**
     * Processes the payment for the specified order by simulating a QR code payment.
     * Displays the total cost and simulates payment confirmation after a short delay.
     * Always returns {@code true} to indicate successful payment simulation.
     *
     * @param order The order for which payment is being processed.
     * @return {@code true} indicating that the payment was processed successfully.
     * @throws InterruptedException if the thread is interrupted during the simulated delay.
     */
    @Override
    public boolean processPayment(Order order) {
        var cartItems = order.getCartItems();
        float totalCost = 0;
		        
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println(cartItems.get(i).getName() + " x " + 
                cartItems.get(i).getQuantity() + " : " + 
                String.format("%.2f",cartItems.get(i).getPrice() * cartItems.get(i).getQuantity()));
            totalCost += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }
        System.out.println("Total: $" + String.format("%.2f", totalCost));

        System.out.println("Please scan the QR Code below for payment");
        System.out.println("Simulating payment...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
			System.out.println("There was an error while validating your payment");
            return false;  
        }
        return true;  
    }

    /**
     * Prints a receipt for the specified order, detailing each item purchased and the total cost.
     * The receipt also includes the payment confirmation time stamped with the current date and time.
     *
     * @param order The order for which the receipt is being printed.
     */
    @Override
    public void printReceipt(Order order) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        var cartItems = order.getCartItems();
        float totalCost = 0;

        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println(cartItems.get(i).getName() + " x " + cartItems.get(i).getQuantity() + " : " +
            String.format("%.2f",cartItems.get(i).getPrice() * cartItems.get(i).getQuantity()));
            totalCost += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }
        System.out.println("Total: $" + String.format("%.2f", totalCost));
        System.out.println("Order " + order.getOrderID() + " paid on " + formattedDateTime + " using " + name);
    }

     /**
     * Returns a string representation of the {@code Online} object, which includes the name of the payment system
     * and the class name, formatted for clarity and debugging purposes.
     *
     * @return A string representation of the {@code Online} payment system details.
     */
    @Override
    public String toString() 
	{
        return this.name + " (" + getClass().getSimpleName() + ")";
    }
}