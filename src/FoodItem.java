
public class FoodItem implements IGetBranchName {
    private static final long serialVersionUID = 1L;
	private Integer foodItemID;
	private String name;
	private String itemCategory;
	private double price;
	private String branchName;
	private boolean available = true;

	/**
     * Constructs a new FoodItem with specified details.
     *
     * @param foodItemID    The unique identifier of the food item.
     * @param name          The name of the food item.
     * @param price         The price of the food item.
     * @param branchName    The branch where the food item is available.
     * @param itemCategory  The category of the food item.
     */
	public FoodItem(int foodItemID, String name, double price, String branchName, String itemCategory){
        this.foodItemID = foodItemID;
        this.name = name;
        this.price = price;
        this.branchName = branchName;
        this.itemCategory = itemCategory;
    }
	/**
     * Returns the branch name where the food item is available.
     * @return The name of the branch.
     */
	public String getBranchName() {
		return this.branchName;
	}

	/**
     * Sets the branch name where the food item will be available.
     * @param branchName The name of the branch.
     */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
     * Sets the price of the food item.
     * @param price The new price of the food item.
     */
	public void setPrice(double price){
		this.price = price;
	}

	/**
     * Sets the availability of the food item.
     * @param available The new availability state of the food item.
     */
	public void setAvailability(boolean available){
		this.available = available;
	}

	/**
     * Returns the availability of the food item.
     * @return {@code true} if the item is available, otherwise {@code false}.
     */
    public Boolean getAvailability(){
		return available;
	}

	/**
     * Returns the name of the food item.
     * @return The name of the food item.
     */
	public String getName(){
		return name;
	}

	/**
     * Returns the category of the food item.
     * @return The category that the food item belongs to.
     */
	public String getItemCategory(){
		return itemCategory;
	}

	/**
     * Returns the price of the food item.
     * @return The current price of the food item.
     */
	public double getPrice(){
		return price;
	}

	/**
     * Returns the unique identifier of the food item.
     * @return The ID of the food item.
     */
	public int getID(){
        return foodItemID;
    }

	/**
     * Provides a string representation of the food item, detailing its ID, name, price, category, and availability.
     * @return A string summarizing the food item details.
     */
	public String toString(){
        String out;
        out = "Food ID: " + foodItemID + ", Name: " + name + ", Price: " + String.format("%.2f", price)  + ", Category: " + itemCategory;
        if (available)
            return out + " (available)";
        else
            return out + " (not available)";
    }
}