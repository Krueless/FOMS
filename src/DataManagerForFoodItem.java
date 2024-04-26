import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a list of {@code FoodItem} objects ensuring that only one instance of this manager exists in the application using the Singleton design pattern.
 * This class provides methods for operations on food items and handles their persistence through serialization.
 * If serialization data is not available, it initializes the food item list from a CSV file.
 */
public class DataManagerForFoodItem implements IDataManager<FoodItem, Integer>{

	private ArrayList<FoodItem> foodItemList;
	private static DataManagerForFoodItem instance;
	private final Serializer<FoodItem> serializer;

	/**
	 * Private constructor to prevent instantiation outside of this class.
	 * Initializes the food item list either by deserializing from a file or by loading from a CSV file
	 * when the serialized data is not available.
	 */
	private DataManagerForFoodItem() {
        serializer = new Serializer<FoodItem>("data/foodItemData.ser");
        loadData();
	}

	/**
	 * Provides access to the singleton instance of the {@code DataManagerForFoodItem}.
	 * If the instance does not exist, it is created using the private constructor. This method ensures
	 *
	 * @return The singleton instance of {@code DataManagerForFoodItem}.
	 */
	public static DataManagerForFoodItem getInstance() {
		if (instance == null) {
			instance = new DataManagerForFoodItem();
		}
		return instance;
	}

	/**
     * Serializes the current list of food items to a file for persistence.
     */
    public void saveData(){
        serializer.serialize(foodItemList);
    }

	/**
     * Loads food items from a serialized file. If the file is not found or cannot be read,
     * it initializes the food item list from a CSV file and logs this action.
     */
    public void loadData(){
        try{
			foodItemList = serializer.deserialize();
		}catch (IOException | ClassNotFoundException e){
			System.out.println("FoodItem: Serialized file not found or invalid, initialising from CSV.");
			foodItemList = new ArrayList<FoodItem>();
			initializeFromCSV();
		}
    }

	/**
     * Finds a food item by its ID.
     * @param foodItemID The ID of the food item to find.
     * @return The {@code FoodItem} if found, null otherwise.
     */
	public FoodItem find(Integer foodItemID) {
		int id = foodItemID.intValue();
        for (FoodItem foodItem : foodItemList){
            if (foodItem.getID() == id){
                return foodItem;
            }
        }
		return null;
	}

	/**
     * Updates a specific food item in the list.
     * @param newFoodItem The new food item with updated details.
     */
	public void update(FoodItem newFoodItem) {
		int id = newFoodItem.getID();
		for (int i = 0; i < foodItemList.size(); i++){
            if (foodItemList.get(i).getID() == id){
                foodItemList.set(i, newFoodItem);
                serializer.serialize(foodItemList);
                System.out.println("Successfully updated food item. ");
                break;
            }
        }
	}

	/**
     * Adds a new food item to the list.
     * @param newFoodItem The food item to be added.
     */
	public void add(FoodItem newFoodItem) {
		foodItemList.add(newFoodItem);
        System.out.println("Successfully added food item.");
        serializer.serialize(foodItemList);
	}

	/**
     * Deletes a food item from the list.
     * @param foodItem The food item to be deleted.
     */
	public void delete(FoodItem foodItem) {
		if (foodItemList.remove(foodItem)){
            System.out.println("Succesfully removed food item.");
            serializer.serialize(foodItemList);
        }
        else{
            System.out.println("Failed to remove food item.");
        }
	}

	/**
     * Retrieves the complete list of food items.
     * @return An ArrayList of {@code FoodItem} objects.
     */
	public ArrayList<FoodItem> getAll() {
		return foodItemList;
	}

    /**
     * Initializes food item data from a CSV file. This method reads food item details from a CSV,
     * creates food item objects, and adds them to the list.
     */
    private void initializeFromCSV() {
        File f = new File("data/menu_list.csv");
        try (Scanner scanner = new Scanner(f)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                // Assuming the CSV is properly formatted to match the Branch constructor
                String line = scanner.nextLine();
                String[] data = line.split(",");
                // Create new Branch object and add to the list
                FoodItem foodItem = new FoodItem(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]), data[3], data[4]); // Adapt constructor call as necessary
                foodItemList.add(foodItem);
            }
            serializer.serialize(foodItemList);
            System.out.println("FoodItem CSV data initialised.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: FoodItem CSV File not found");;
        }
	}
}