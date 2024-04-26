/**
 * The {@code IOrderControlForCart} interface defines what the necessary operations for managing a shopping cart within the ordering system.
 * It outlines methods for adding items to the cart, removing them, and editing their quantities, ensuring that the cart reflects
 * the customer's current selections before finalising an order.
 */
public interface IOrderControlForCart {

    /**
     * Adds a food item to the specified order's cart. This method should fetch the food item from a data manager,
     * based on some criteria or user input, and add it to the order.
     *
     * @param order The order to which the food item will be added.
     * @param foodItemDB The data manager that provides access to food items.
     * @return The updated order with the new item added, or the original order if the item could not be added.
     */
    Order addToCart(Order order, IDataManager<FoodItem, Integer> foodItemDB);

    /**
     * Removes an item from the specified order's cart. This method interacts with a display formatter to show the cart contents
     * and makes sure the user selects the right item to be removed.
     *
     * @param order The order from which an item will be removed.
     * @param displayFormatter The display interface used to format and show cart items, helping the user decide which item to remove.
     * @return The updated order after the item has been removed, or the original order if no item was removed.
     */
    Order removeFromCart(Order order, IDisplay displayFormatter);

    /**
     * Edits the quantity of an item in the specified order's cart. This method can use a display formatter
     * to show cart items and allow the user to select and modify the quantity of a particular item.
     *
     * @param order The order whose cart item quantity will be edited.
     * @param displayFormatter The display interface used to present the cart items and facilitate user interaction for editing quantities.
     * @return The updated order after the item's quantity has been edited, or the original order if no changes were made.
     */
    Order editCart(Order order, IDisplay displayFormatter);
}