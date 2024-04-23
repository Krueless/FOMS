package OOP_Project_Classes;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Credit implements IPayment {
	private name;

	public Online(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean processPayment(Order order) 
	{
		Scanner sc = new Scanner(System.in);
		cartItems = order.getCartItems()
		int totalCost = 0;

		for (int i = 0; i < cartItems.size();i++)
		{
			System.out.println(cartItems.get(i).getName() + " x " + cartItems.get(i).getQuantity() + " : " + cartItems.get(i).getPrice*cartItems.get(i).getQuantity);
			totalCost += cartItems[i].getPrice()*cartItems[i].getQuantity();
		}
		System.out.println("Total: $" + String.format("%.2f", totalCost));



		System.out.println("Please input your credit card number");
		creditCardNumber = sc.next();
		System.out.println("Please input your CVV");
		cvv = sc.next();
		//Checking for credit card details, assume is true 
		return true;
	}

	public void printReceipt(Order order) 
	{
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

		cartItems = order.getCartItems()
		int totalCost = 0;

		for (int i = 0; i < cartItems.size();i++)
		{
			System.out.println(cartItems[i].name + " x " + cartItems[i].quantity + " : " + cartItems[i].price*cartItems[i].quantity);
			totalCost += cartItems[i].price*cartItems[i].quantity;
		}
		System.out.println("Total: $" + String.format("%.2f", totalCost));
		System.out.println("Order " + order.orderID + " paid on " + formattedDateTime);
	}

	public String toString()
	{
		return this.name + " " + getClass();
	}

}
// Done