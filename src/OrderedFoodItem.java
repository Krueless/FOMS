/**
 * Represents an ordered item in the system which extends the basic FoodItem class.
 * It includes additional functionality to manage the quantity of the food item ordered.
 */
public class OrderedFoodItem extends FoodItem {

    private int quantity;
    /**
     * Constructs a new OrderedFoodItem from an existing FoodItem object and a specified quantity.
     *
     * @param foodItem The FoodItem to be used for creating the ordered item.
     * @param quantity The quantity of the food item ordered.
     */
    public OrderedFoodItem(FoodItem foodItem, int quantity) {
        super(foodItem.getID(), foodItem.getName(), foodItem.getPrice(), foodItem.getItemCategory(), foodItem.getBranchName());
        this.quantity = quantity;
    }
    /**
     * Calculates the total price of the ordered item based on its quantity and unit price.
     *
     * @return The total price of the ordered item.
     */
    public double calculatePrice() {
        return this.quantity * this.getPrice();
    }

    /**
     * Returns the quantity of the food item ordered.
     *
     * @return The quantity of the ordered item.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Sets the quantity of the ordered food item.
     *
     * @param quantity The new quantity to be set for the ordered item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the ordered food item, detailing its name, price, and quantity.
     *
     * @return A formatted string describing the ordered food item.
     */
    @Override
    public String toString() {
        String out = this.getName() + " : $" + String.format("%.2f",this.getPrice()) + " x " + this.getQuantity();
        return out;
    }
}