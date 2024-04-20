import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.Scanner;

public class DataManagerForFoodItem implements IDataManager<FoodItem, Integer>, Serializable {

	private ArrayList<FoodItem> foodItemList;
	private static DataManagerForFoodItem instance;
	private static final long serialVersionUID = 1L;
	private final Serializer<FoodItem> serializer;

	private DataManagerForFoodItem() {
		foodItemList = new ArrayList<>();
        serializer = new Serializer<FoodItem>("../src/foodItemData.ser");
        // Deserialize data when initializing the instance
        deserializeData();
	}

	public static DataManagerForFoodItem getInstance() {
		if (instance == null) {
			instance = new DataManagerForFoodItem();
		}
		return instance;
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
                System.out.println("Successfully updated food item. ");
                break;
            }
        }
        serializer.serialize(foodItemList);
        return;
	}

	/**
	 * 
	 * @param foodItem
	 */
	public void add(FoodItem foodItem) {
		foodItemList.add(foodItem);
        serializer.serialize(foodItemList);
	}

	/**
	 * 
	 * @param foodItem
	 */
	public void delete(FoodItem foodItem) {
		if (foodItemList.remove(foodItem)){
            System.out.println("Succesfully removed food item.");
        }
        else{
            System.out.println("Failed to remove food item.");
        }
        serializer.serialize(foodItemList);
	}

	public ArrayList<FoodItem> getAll() {
		return foodItemList;
	}

	public void serializeData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("foodItemData.ser"))) {
            out.writeObject(foodItemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        // Deserialization method
        @SuppressWarnings("unchecked")
        private void deserializeData() {
            Path filePath = Paths.get("foodItemData.ser");
            if (Files.exists(filePath)) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("foodItemData.ser"))) {
                    foodItemList = (ArrayList<FoodItem>) in.readObject();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                // If the serialized file does not exist, we may need to initialize from CSV
                initializeFromCSV("food_item_list.csv");
            }
        }

            // Method to read CSV and initialize data
    private void initializeFromCSV(String csvFileName) {
        try (Scanner scanner = new Scanner(new File(csvFileName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                // Assuming the CSV is properly formatted to match the Branch constructor
                String line = scanner.nextLine();
                String[] data = line.split(",");
                // Create new Branch object and add to the list
                FoodItem foodItem = new FoodItem(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]), data[3], data[4]); // Adapt constructor call as necessary
                foodItemList.add(foodItem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
}