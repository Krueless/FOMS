package OOP_Project_Classes;
public class Online implements IPayment {
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
	
	public void processPayment(Order order) {
		Scanner sc = new Scanner(System.in);
		cartItems = order.getCartItems()
		int totalCost = 0;

		for (int i = 0; i < cartItems.size();i++)
		{
			System.out.println(cartItems[i].name + " x " + cartItems[i].quantity + " : " + cartItems[i].price*cartItems[i].quantity);
			totalCost += cartItems[i].price*cartItems[i].quantity;
		}
		System.out.println("Total: $" + String.format("%.2f", totalCost));

		System.out.println("Please scan the PayNow QR Code below for payment");
		System.out.println("Simulating payment...");
		Thread.sleep(3000);
		System.out.println("Order Successful");
		//Checking for credit card details, assume is true; Check if sc.close is needed
		printReceipt();
	}

	public boolean printReceipt(Order o) {
		import java.time.ZonedDateTime;
		import java.time.ZoneId;
		import java.time.format.DateTimeFormatter;

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
		System.out.println("Order "+order.orderID+" paid on " + formattedDateTime);
	}

}
// DONE