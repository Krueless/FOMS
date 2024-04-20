public class FoodItem implements IGetBranchName {
	private String name;
	private String itemCategory;
	private double price;
	private String branchName;

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

	/**
	 * 
	 * @param name
	 * @param itemCategory
	 * @param price
	 * @param branchName
	 */
	public FoodItem(String name, String itemCategory, double price, String branchName) {
		// TODO - implement FoodItem.FoodItem
		throw new UnsupportedOperationException();
	}

}