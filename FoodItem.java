package OOP_Project_Classes;
public class FoodItem {

	private String name;
	private String itemCategory;
	private double price;
	private String branchName;

	public FoodItem(String name, String itemCategory, double price, String branchName)
	{
		this.name = name;
		this.itemCategory = itemCategory;
		this.price = price;
		this.branchName = branchName;
	}

	public String getName()
	{
		return this.name;
	}

	public String getItemCategory()
	{
		return this.itemCategory
	}

	public double getPrice()
	{
		return this.price;
	}

	public String getBranchName()
	{
		return this.branchName;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setItemCategory(String itemCategory)
	{
		this.itemCategory = itemCategory;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setBranchName(String branchName) 
	{
		this.branchName = branchName;
	}

	public String toString()
	{
		return this.name + ", price : $" + this.price + ", category : " + this.itemCategory;
	}

}
// DONE