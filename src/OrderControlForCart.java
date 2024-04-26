/**
 * Manages the functionalities related to modifying the shopping cart, including adding to, removing from,
 * and editing items within the cart. This class ensures the cart reflects the user's desired items before finalizing the order.
 */
public class OrderControlForCart implements IOrderControlForCart {

    /**
     * Adds a specified food item to the cart after prompting the user for the item ID and quantity. 
     * If the item already exists in the cart, it increases the quantity.
     *
     * @param order The current order being modified.
     * @param foodItemDB The data manager to fetch food items from.
     * @param branchName The name of the branch to ensure the food item belongs to the correct location.
     * @return The updated order after adding the new item or null if the item is not valid or available.
     */
    public Order addToCart(Order order, IDataManager<FoodItem, Integer> foodItemDB) {
        FoodItem foodItem = getFoodItem(foodItemDB, order.getBranchName());
        if (foodItem == null) {
            System.out.println("Food item is not available.");
            return null; 
        }
        System.out.println("Please input the number of food item(s) you want");
        int quantity = GetOption.getValidNumber();
        OrderedFoodItem orderedFoodItem = new OrderedFoodItem(foodItem, quantity);

        // Check for existing item in the cart and update quantity if found.
        for (OrderedFoodItem item : order.getCartItems()) {
            if (item.getName().equals(orderedFoodItem.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                order.setPrice(order.getPrice() + item.getPrice());
                System.out.println("Item quantity updated in cart!");
                return order;
            }
        }

        // Add new item to the cart.
        order.getCartItems().add(orderedFoodItem);
        System.out.println("Item successfully added to cart!");
        return order;
    }

    /**
     * Removes a specified item from the cart after displaying all items and prompting the user to choose one to remove.
     * If the cart is empty or the selected index is out of range, it returns without making changes.
     *
     * @param order The current order being modified.
     * @param displayFormatter The display formatter used to show items in the cart.
     * @return The updated order after removing the selected item, or null if no action was taken.
     */
    public Order removeFromCart(Order order, IDisplay displayFormatter) {
        if (order.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty!");
            return null;
        }

        displayFormatter.displayAll(order.getCartItems());
        System.out.println("Please choose which item number you wish to remove");
        int index = GetOption.getValidNumber(order.getCartItems().size() + 1) - 1;

        if (index == order.getCartItems().size()) {
            System.out.println("Going back to Customer Interface");
            return null;
        } else {
            order.setPrice(order.getPrice() - order.getCartItems().get(index).getPrice());
            order.getCartItems().remove(index);
            System.out.println("Item successfully removed from cart!");
            return order;
        }
    }

    /**
     * Allows the user to edit the quantity of a selected item in the cart after displaying all items.
     * If the cart is empty or the selected index is out of range, it returns without making changes.
     *
     * @param order The current order being modified.
     * @param displayFormatter The display formatter used to show items in the cart.
     * @return The updated order after changing the quantity of the selected item, or null if no action was taken.
     */
    public Order editCart(Order order, IDisplay displayFormatter) {
        if (order.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty!");
            return null;
        }

        displayFormatter.displayAll(order.getCartItems());
        System.out.println("Please choose which item quantity you want to edit");
        int index = GetOption.getValidNumber(order.getCartItems().size() + 1) - 1;

        if (index == order.getCartItems().size()) {
            System.out.println("Going back to Customer Interface");
            return null;
        } else {
            System.out.println("Please input what quantity you want to change it to");
            int newQuantity = GetOption.getValidNumber();
            OrderedFoodItem item = order.getCartItems().get(index);
            order.setPrice(order.getPrice() - order.getCartItems().get(index).getPrice());
            item.setQuantity(newQuantity);
            order.setPrice(order.getPrice() + item.getPrice());
            System.out.println("Item quantity updated in cart!");
            return order;
        }
    }

        /**
     * Private helper method to fetch a food item based on an ID input by the user, ensuring it matches the branch and availability.
     *
     * @param foodItemDB The data manager to fetch food items from.
     * @param branchName The name of the branch to ensure the food item belongs to the correct location.
     * @return The requested FoodItem if valid and available, otherwise prompts the user again for a valid ID.
     */
    private FoodItem getFoodItem(IDataManager<FoodItem, Integer> foodItemDB, String branchName) {
        while (true) {
            System.out.println("Please input the ID of the food item you want");
            int foodItemID = GetOption.getValidNumber();  // Assuming GetOption.getValidNumber() safely handles invalid input.
            FoodItem foodItem = foodItemDB.find(foodItemID);

            if (foodItem != null && foodItem.getBranchName().equals(branchName) && foodItem.getAvailability()) {
                return foodItem;
            } else {
                System.out.println("Invalid food item ID or item not available at this branch. Please try again.");
            }
        }
    }
}