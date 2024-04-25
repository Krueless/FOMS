import java.util.Scanner;

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
    public Order addToCart(Order order, IDataManager<FoodItem, Integer> foodItemDB) 
	{
		FoodItem foodItem = getFoodItem(foodItemDB);
        System.out.println("Please input how many you want to add.");
        int quantity = getValidNumber();
        OrderedFoodItem orderedFoodItem = new OrderedFoodItem(foodItem, quantity);
        order.getCartItems().add(orderedFoodItem);
		System.out.println("Item successfully added to cart!");
        return order;
    }

    /**
     * Removes a specified item from the cart after displaying all items and prompting the user to choose one to remove.
     *
     * @param order The current order being modified.
     * @param displayFormatter The display formatter used to show items in the cart.
     * @return The updated order after removing the selected item.
     */
    public Order removeFromCart(Order order, IDisplayFilteredByBranch displayFormatter) {
        Scanner sc =  GlobalResource.SCANNER;
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
        Scanner sc =  GlobalResource.SCANNER;
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
        return order;
    }

	private FoodItem getFoodItem(IDataManager<FoodItem, Integer> foodItemDB)
	{
        System.out.println("Please input the ID of the food item you want");
        int foodItemID = getValidNumber();
        FoodItem foodItem = foodItemDB.find(foodItemID);
		if (foodItem == null)
		{
			System.out.println("Please choose a valid food item");
			return getFoodItem(foodItemDB);
		}
        return foodItem;
	}
	private int getValidNumber() {
        Scanner sc = GlobalResource.SCANNER;
        while (true) {
            try {
                int number = sc.nextInt();
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Please input a positive number!");
                }
            } catch (Exception e) {
                sc.nextLine(); // consume the invalid input to avoid infinite loop
                System.out.println("Please input a valid integer");
            }
        }
    }

	private int getValidNumber(int max) {
        Scanner sc = GlobalResource.SCANNER;
        System.out.println("Please input how many you want");
        while (true) {
            try {
                int quantity = sc.nextInt();
                if (quantity > 0 && quantity <= max) {
                    return quantity;
                } else {
                    System.out.println("Please input a number between 1 and " + max + "!");
                }
            } catch (Exception e) {
                sc.nextLine(); // consume the invalid input
                System.out.println("Please input a valid integer");
            }
        }
    }
}