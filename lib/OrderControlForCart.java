import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages the functionalities related to modifying the shopping cart, including adding to, removing from,
 * and editing items within the cart.
 */
public class OrderControlForCart {

    /**
     * Adds a specified food item to the cart after prompting the user for the item and quantity.
     *
     * @param order The current order being modified.
     * @param branchName The name of the branch to fetch menu items from.
     * @return The updated order after adding the new item.
     */
    public Order addToCart(Order order, String branchName, IDataManager menuDB) 
	{
		FoodItem foodItem = getFoodItem(menuDB);
        int quantity = getValidNumber();
        OrderedFoodItem orderedFoodItem = new OrderedFoodItem(foodItem, quantity);
        order.getCartItems().add(orderedFoodItem);
		System.out.println("Item successfully added to cart!");
        sc.close();
        return order;
    }

    /**
     * Removes a specified item from the cart after displaying all items and prompting the user to choose one to remove.
     *
     * @param order The current order being modified.
     * @param displayFormatter The display formatter used to show items in the cart.
     * @return The updated order after removing the selected item.
     */
    public Order removeFromCart(Order order, IDisplay displayFormatter) {
        Scanner sc = new Scanner(System.in);

        displayFormatter.displayAll(order.getCartItems());
        System.out.println((order.getCartItems().size() + 1) + " Quit");
        System.out.println("Please choose which item number you wish to remove");

        int index = getValidNumber(order.getCartItems().size() + 1);

        if (index == order.getCartItems().size() + 1) {
            System.out.println("Going back to Customer Interface");
			return null;
        } else {
            order.getCartItems().remove(index);
        }
		System.out.println("Item successfully removed from cart!");
        sc.close();
        return order;
    }

    /**
     * Allows the user to edit the quantity of a selected item in the cart after displaying all items.
     *
     * @param order The current order being modified.
     * @param displayFormatter The display formatter used to show items in the cart.
     * @return The updated order after changing the quantity of the selected item.
     */
    public Order editCart(Order order, IDisplay displayFormatter) {
        Scanner sc = new Scanner(System.in);
        displayFormatter.displayAll(order.getCartItems());

        System.out.println((order.getCartItems().size() + 1) + " Quit");
        System.out.println("Please choose which item quantity you want to edit");
        int index = getValidNumber(order.getCartItems().size() + 1);
        if (index == order.getCartItems().size() + 1) {
            System.out.println("Going back to Customer Interface");
			return null;
        } else {
            System.out.println("Please input what quantity you want to change it to");
            int newQuantity = getValidNumber();
            OrderedFoodItem item = order.getCartItems().get(index);
            item.setQuantity(newQuantity);
			order.getCartItems().set(index,item);
        }
        sc.close();
        return order;
    }

	private FoodItem getFoodItemName(IDataManager menuDB)
	{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the name of the food item you want");
        String foodItemName = sc.nextLine();
        FoodItem foodItem = menuDB.find(branchName, foodItemName);
		if (foodItem == null)
		{
			System.out.println("Please choose a valid food item/input the name correctly");
			return getFoodItemName(IDataManager menudb);
		}
	}
	private int getValidNumber()
	{
		System.out.println("Please input how many you want");
		try {
            int quantity = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please input a valid integer");
			return getValidQuantity();
        }
		if (quantity<=0)
		{
			System.out.println("Please input a positive number!");
			return getValidQuantity();
		}
		return quantity;
	}

	private int getValidNumber(int max)
	{
		System.out.println("Please input how many you want");
		try {
            int quantity = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please input a valid integer");
			return getValidQuantity();
        }
		if (quantity<=0 || quantity > max)
		{
			System.out.println("Please input a valid number!");
			return getValidQuantity(max);
		}
		return quantity;
	}
}