package OOP_Project_Classes;

import display_classes.IDisplay;

public class OrderControl {

	private IDataManager orderDB;
	private IDataManager foodItemDB;
	private IDisplay displayFormatter;
	private OrderControlForCheckout checkout;
	private OrderControlForCart cart;
	private Order order;
	private String branchName;


	public OrderControl(IDataManager orderDB, IDataManager foodItemDB, IDisplay displayFormatter, OrderControlForCheckout checkout, OrderControlForCart cart, Order order, String branchName)
	{
		this.orderDB = orderDB;
		this.foodItemDB = foodItemDB;
		this.displayFormatter = displayFormatter;
		this.checkout = checkout;
		this.cart = cart;
		this.order = order;
		this.branchName = branchName;
	}

	public void viewMenu() 
	{
		displayFormatter.displayFilteredByBranch(foodItemDB.getAll(), branchName);
	}

	public void showOptions() 
	{
		System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
		System.out.println("Welcome!, please choose one of the following options: ");
		System.out.println("(1) Add to cart");
		System.out.println("(2) Remove from cart");
		System.out.println("(3) Edit cart");
		System.out.println("(4) Checkout");
		System.out.println("(5) Change dine-in option");
		// System.out.println("(6) Quit");
		System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
		chooseOptions();
	}

	public void chooseOptions()
	{
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice)
		{
			case 1:
			viewMenu();
			Order newOrder = cart.addToCart(order, branchName);
			orderDB.update(newOrder);
			break;

			case 2:
			Order newOrder = cart.removeFromCart(order);
			orderDB.update(newOrder)
			break;

			case 3:
			Order newOrder = cart.editCart(order);
			orderDB.update(newOrder)
			break;

			case 4:
			Order newOrder = checkout.checkOut(order, displayFormatter);
			newOrder = checkout.changeOrderStatus(order, OrderStatus.PREPARING);
			orderDB.update(newOrder);
			// End Program OR Go back to Customer UI
			break;

			case 5:
			Scanner sc = new Scanner(System.in);
			Order newOrder = checkout.changeDineInOption(order)
			orderDB.update(newOrder);
			break;

			// case 6:
			// End Program
			// break;
		}
	}

}