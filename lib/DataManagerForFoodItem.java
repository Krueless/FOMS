public class DataManagerForFoodItem implements IDataManager {

	private ArrayList<FoodItem> foodItemList;
	private static DataManagerForFoodItem instance;

	private DataManagerForFoodItem() {
		// TODO - implement DataManagerForMenu.DataManagerForMenu
		throw new UnsupportedOperationException();
	}

	public static DataManagerForFoodItem getInstance() {
		return this.instance;
	}

	/**
	 * 
	 * @param branchName
	 */
	public ArrayList<FoodItem> find(String branchName) {
		// TODO - implement DataManagerForMenu.find
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldFoodItem
	 * @param newFoodItem
	 */
	public void update(FoodItem oldFoodItem, FoodItem newFoodItem) {
		// TODO - implement DataManagerForMenu.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param foodItem
	 */
	public void add(FoodItem foodItem) {
		// TODO - implement DataManagerForMenu.add
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param foodItem
	 */
	public void delete(FoodItem foodItem) {
		// TODO - implement DataManagerForMenu.delete
		throw new UnsupportedOperationException();
	}

	public ArrayList<FoodItem> getAll() {
		// TODO - implement DataManagerForMenu.getAll
		throw new UnsupportedOperationException();
	}

}