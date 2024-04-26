
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
    public Order addToCart(Order order, IDataManager<FoodItem, Integer> foodItemDB, String branchName) 
	{
        FoodItem foodItem = getFoodItem(foodItemDB, branchName);
        System.out.println("Please input the number of food item(s) you want");
        int quantity = GetOption.getValidNumber();
        OrderedFoodItem orderedFoodItem = new OrderedFoodItem(foodItem, quantity);

        for (int i = 0;i<order.getCartItems().size(); i++){
            OrderedFoodItem currentFoodItem = order.getCartItems().get(i);
            if (orderedFoodItem.getID() == currentFoodItem.getID()){
                currentFoodItem.setQuantity(currentFoodItem.getQuantity() + orderedFoodItem.getQuantity());
                order.getCartItems().set(i, currentFoodItem);
                order.setPrice(order.getPrice()+currentFoodItem.getPrice());
                return order;
            }
        }
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
        if (order.getCartItems().size() == 0)
        {
            System.out.println("Your cart is empty!");
            return null;
        }
        displayFormatter.displayAll(order.getCartItems());
        System.out.println((order.getCartItems().size() + 1) + ") Quit");
        System.out.println("Please choose which item number you wish to remove");
        int index = GetOption.getValidNumber(order.getCartItems().size() + 1)-1;
        if (index == order.getCartItems().size()) {
            System.out.println("Going back to Customer Interface");
			return null;
        } else {
            order.setPrice(order.getPrice()-order.getCartItems().get(index).getPrice());
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
        if (order.getCartItems().size() == 0)
        {
            System.out.println("Your cart is empty!");
            return null;
        }
        displayFormatter.displayAll(order.getCartItems());
        System.out.println((order.getCartItems().size() + 1) + ") Quit");
        System.out.println("Please choose which item quantity you want to edit");
        int index = GetOption.getValidNumber(order.getCartItems().size() + 1)-1;
        if (index == order.getCartItems().size()) {
            System.out.println("Going back to Customer Interface");
			return null;
        } else {
            System.out.println("Please input what quantity you want to change it to");
            int newQuantity = GetOption.getValidNumber();
            OrderedFoodItem item = order.getCartItems().get(index);
            order.setPrice(order.getPrice() - item.getPrice());
            item.setQuantity(newQuantity);
            order.setPrice(order.getPrice() + item.getPrice());
			order.getCartItems().set(index,item);
        }
        return order;
    }

	private FoodItem getFoodItem(IDataManager<FoodItem, Integer> foodItemDB, String branchName)
	{
        System.out.println("Please input the ID of the food item you want");
        int foodItemID = GetOption.getValidNumber();
        FoodItem foodItem = foodItemDB.find(foodItemID);
		if (foodItem == null || !foodItem.getBranchName().equals(branchName) || !foodItem.getAvailability())
		{
			System.out.println("Please input a valid ID!");
			return getFoodItem(foodItemDB, branchName);
		}
        return foodItem;
	}
}