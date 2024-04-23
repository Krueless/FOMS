package OOP_Project_Classes;
public class OrderedFoodItem extends FoodItem {

	private int quantity;

	public OrderedFoodItem(String name, String itemCategory, double price, String branchName, int quantity)
	{
		super(name, itemCategory, price, branchName);
		this.quantity = quantity;
	}

	public OrderedFoodItem(FoodItem foodItem, int quantity)
	{
		super(foodItem.getName(), foodItem.getItemCategory(), foodItem.getPrice(), foodItem.getBranchName());
		this.quantiy = quantity;
	}

	public double calculatePrice() 
	{
		return this.quantity * this.price;
	}

	public double getQuantity()
	{
		return this.quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public String toString()
	{
		out = this.getName() + " : " + "$" + this.getPrice() + " x" + this.getQuantity();
		return out;
	}
}

// DONE