import java.io.Serializable;

public class FoodItem implements IGetBranchName, Serializable {
	private int foodItemID;
	private String name;
	private String itemCategory;
	private double price;
	private String branchName;
	private boolean available;

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
        out = "Name: " + name + ", Price: " + price + ", Branch: " + branchName + ", Category: " + itemCategory;
        return out;
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