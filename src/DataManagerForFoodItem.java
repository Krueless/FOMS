import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManagerForFoodItem implements IDataManager<FoodItem, Integer>{

	private ArrayList<FoodItem> foodItemList;
	private static DataManagerForFoodItem instance;
	private final Serializer<FoodItem> serializer;

	private DataManagerForFoodItem() {
        serializer = new Serializer<FoodItem>("data/foodItemData.ser");
        loadData();
	}

	public static DataManagerForFoodItem getInstance() {
		if (instance == null) {
			instance = new DataManagerForFoodItem();
		}
		return instance;
	}

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
	 * 
	 * @param branchName
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
	 * 
	 * @param oldFoodItem
	 * @param newFoodItem
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
	 * 
	 * @param foodItem
	 */
	public void add(FoodItem newFoodItem) {
		foodItemList.add(newFoodItem);
        System.out.println("Successfully added food item.");
        serializer.serialize(foodItemList);
	}

	/**
	 * 
	 * @param foodItem
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

	public ArrayList<FoodItem> getAll() {
		return foodItemList;
	}

    // Method to read CSV and initialize data
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