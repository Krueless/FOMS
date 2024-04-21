package OOP_Project_Classes;

import display_classes.IDisplay;

public class OrderControlForCart {

	public Order addToCart(Order order, String branchName) 
	{
		DataManagerForFoodItem menuDB = DataManagerForFoodItem.getInstance();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the name of the food item you want");
		String foodItemName = sc.next();
		FoodItem foodItem = menuDB.find(branchName, foodItemName);
		System.out.println("Please input how many you want");
		int quantity = sc.nextInt();
		OrderedFoodItem orderedFoodItem =  new OrderedFoodItem(foodItem, quantity);
		ArrayList <FoodItem> newCart = order.getCartItems().add(orderedFoodItem)
		order.setCartItems(newCart);
		return order;
	}

	public Order removeFromCart(Order order, IDisplay displayFormatter) 
	{
		Scanner sc = new Scanner(System.in)
		displayFormatter.displayAll(order.getCartItems());
		System.out.println((order.getCartItems().size() + 2) + " Quit");
		System.out.println("Please choose which item number you wish to remove");
		int index = sc.nextInt();
		if (index == order.getCartItems().size() + 2)
		{
			System.out.println("Going back to Customer Interface");
			return order;
		}

		ArrayList<FoodItem> newCart = order.getCartItems().remove(index-1);
		order.setCartItems(newCart);
		return order;
	}

	public Order editCart(Order order, IDisplay displayFormatter) 
	{
		Scanner sc = new Scanner(System.in)
		displayFormatter.displayAll(order.getCartItems());
		System.out.println((order.getCartItems().size() + 2) + " Quit");
		System.out.println("Please choose which item quantity you want to edit");
		int index = sc.nextInt();
		if (index == order.getCartItems().size() + 2)
		{
			System.out.println("Going back to Customer Interface");
			return order;
		}
		System.out.println("Please input what quantity you want to change it to");
		int newQuantity = sc.nextInt();
		FoodItem targetFoodItem = order.getCartItems().get(index + 1)
		ArrayList <FoodItem> newCart = order.getCartItems().set(index + 1, targetFoodItem.setQuantity(newQuantity));
		order.setCartItems(newCart);
		return order;
	}

}
// DONE