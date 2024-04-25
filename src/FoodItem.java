
public class FoodItem implements IGetBranchName {
    private static final long serialVersionUID = 1L;
	private Integer foodItemID;
	private String name;
	private String itemCategory;
	private double price;
	private String branchName;
	private boolean available = true;

	public String getBranchName() {
		return this.branchName;
	}

	/**
	 * 
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public void setAvailability(boolean available){
		this.available = available;
	}

    public Boolean getAvailability(){
		return available;
	}

	public String getName(){
		return name;
	}

	public String getItemCategory(){
		return itemCategory;
	}

	public double getPrice(){
		return price;
	}

	public int getID(){
        return foodItemID;
    }

	public String toString(){
        String out;
        out = "Food ID: " + foodItemID + ", Name: " + name + ", Price: " + price + ", Category: " + itemCategory;
        if (available)
            return out + " (available)";
        else
            return out + " (not available)";
    }

	/**
	 * 
	 * @param name
	 * @param itemCategory
	 * @param price
	 * @param branchName
	 */
	public FoodItem(int foodItemID, String name, double price, String branchName, String itemCategory){
        this.foodItemID = foodItemID;
        this.name = name;
        this.price = price;
        this.branchName = branchName;
        this.itemCategory = itemCategory;
    }

}