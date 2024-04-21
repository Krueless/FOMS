package OOP_Project_Classes;

import display_classes.IDisplay;

public class OrderControlForCheckout {
	
	public void changeOrderStatus(Order order, OrderStatus status) 
	{
		order.setOrderStatus(status);
		return order;
	}

	public void checkOut(Order order,IDisplay displayFormatter) 
	{
		PaymentUI payment = new PaymentUI(DataManagerForPayment.getInstance(),displayFormatter);
		payment.showPaymentOptions(order);
	}

	public Order changeDineInOption(Order order) 
	{
		Scanner sc = new Scanner(System.in);
		String dineInOption = order.getTakeaway() ? "takeout" : "dine-in";
		System.out.println("Dine-in option is currently " + dineInOption);
		System.out.println("Would you like to change? If so, enter 1");
		int choice = sc.nextInt();
		if(choice == 1)
		{
			order.setTakeaway(!order.getTakeaway());
			return order;
		}
		return order;
	}

}