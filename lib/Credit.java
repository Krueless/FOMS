import java.util.ArrayList;
import java.util.Scanner;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Credit} class implements the {@code IPayment} interface to handle payments made via credit cards.
 * It includes functionalities to process a payment by capturing credit card details and validating them,
 * and to print a detailed receipt for the transaction. Additionally, it provides basic getter and setter methods
 * for the payment method's name.
 */
public class Credit implements IPayment 
{
    private String name;  // Name of the payment method, default is "Mastercard"

    /**
     * Default constructor which sets the payment method name to "Mastercard".
     */
    public Credit() 
	{
        this.name = "Mastercard";
    }

    /**
     * Constructs a new {@code Credit} object with a specified payment method name.
     *
     * @param name The name of the payment method as a {@code String}.
     */
    public Credit(String name) 
	{
        this.name = name;
    }

    /**
     * Retrieves the name of the payment method.
     *
     * @return The name of the payment method.
     */
    public String getName() 
	{
        return this.name;
    }

    /**
     * Sets the payment method name to the specified string.
     *
     * @param name The new name of the payment method.
     */
    public void setName(String name) 
	{
        this.name = name;
    }

    /**
     * Processes the payment for an order by asking the user to enter their credit card details.
     * This method simulates the process of a credit card transaction by taking input from the user
     * and displaying the total cost of the order. The method assumes the payment is always successful
     * for demonstration purposes.
     *
     * @param order The order for which payment is to be processed.
     * @return {@code true} if the payment was processed successfully, {@code false} otherwise.
     */
    public boolean processPayment(Order order) {
        Scanner sc = new Scanner(System.in);
		// Retrieve items in the order
        ArrayList<OrderedFoodItem> cartItems = order.getCartItems(); 
		// Initialize total cost
        int totalCost = 0; 

        // Calculate total cost by iterating over each item in the cart

        for (int i = 0; i < cartItems.size(); i++) 
		{
            System.out.println(cartItems.get(i).getName() + " x " +
                cartItems.get(i).getQuantity() + " : " +
                cartItems.get(i).getPrice() * cartItems.get(i).getQuantity());
            totalCost += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }
        System.out.println("Total: $" + String.format("%.2f", totalCost));

        // Collect credit card information from user input
		try 
		{
			System.out.println("Please input your credit card number");
			long creditCardNumber = sc.nextLong(); 
			System.out.println("Please input your CVV");
			int cvv = sc.nextInt(); 
		} 
		catch (Exception e) 
		{
			System.out.println("Please input a valid card number/CVV");
			return processPayment(order);
		}

        sc.close();
		printReceipt(order);    
        return true; 
    }

    /**
     * Prints a receipt for the order, including a timestamp, list of items, their prices, quantities, and the total cost.
     * This method also displays the payment confirmation with the date and time of the transaction.
     *
     * @param order The order for which the receipt is printed.
     */
    public void printReceipt(Order order) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        ArrayList<OrderedFoodItem> cartItems = order.getCartItems();
        int totalCost = 0;
		// Maybe look into error checking here?
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println(cartItems.get(i).getName() + " x " + cartItems.get(i).getQuantity() + " : " +
                cartItems.get(i).getPrice() * cartItems.get(i).getQuantity());
            totalCost += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }
        System.out.println("Total: $" + String.format("%.2f", totalCost));
        System.out.println("Order " + order.getOrderID() + " paid on " + formattedDateTime);
    }

    /**
     * Returns a string representation of the {@code Credit} object, including the class name and payment method name.
     *
     * @return A string representing the {@code Credit} object, which includes the class name and the name of the payment method.
     */
    @Override
    public String toString() 
	{
        return this.name + " " + getClass().getSimpleName();
    }
}