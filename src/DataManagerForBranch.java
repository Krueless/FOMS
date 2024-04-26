import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a list of {@code Branch} objects ensuring that only one instance of this manager exists in the application using the Singleton design pattern.
 * If serialization data is not available, it initializes the branch list from a CSV file.
 */
public class DataManagerForBranch implements IDataManager<Branch,String>{
	private ArrayList<Branch> branchList;
	private static DataManagerForBranch instance;
	private final Serializer<Branch> serializer;

	/**
     * Private constructor to prevent instantiation outside of this class.
     * Initializes the branch list either by deserializing from a file or by loading from a CSV file
     * when the serialized data is not available.
     */
	private DataManagerForBranch() {
        serializer = new Serializer<Branch>("data/branchData.ser");
        loadData();
	}

	/**
     * Provides access to the singleton instance of the {@code DataManagerForBranch}.
     * If the instance does not exist, it is created using the private constructor. This method ensures
     * that only one instance of DataManagerForBranch is active at any time, conforming to the Singleton design pattern.
     *
     * @return The singleton instance of {@code DataManagerForBranch}.
     */
	public static DataManagerForBranch getInstance() {
		if (instance == null) {
			instance = new DataManagerForBranch();
		}
		return instance;
	}

	/**
     * Serializes the current list of branches to a file for persistence.
     */
    public void saveData(){
        serializer.serialize(branchList);
    }

	/**
     * Loads branch data from a serialized file. If the file is not found or cannot be read,
     * it initializes the branch list from a CSV file and logs this action.
     */
	public void loadData(){
		try{
			branchList = serializer.deserialize();
		}catch (IOException | ClassNotFoundException e){
			System.out.println("Branch: Serialized file not found or invalid, initialising from CSV.");
			branchList = new ArrayList<Branch>();
			initializeFromCSV();
		}
	}

    /**
     * Initializes branch data from a CSV file. This method reads branch details from a CSV,
     * creates branch objects, and adds them to the list.
     */
    private void initializeFromCSV() {
		File f = new File("data/branch_list.csv");
        try (Scanner scanner = new Scanner(f)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                // Assuming the CSV is properly formatted to match the Branch constructor
                String line = scanner.nextLine();
                String[] data = line.split(",");
                // Create new Branch object and add to the list
                Branch branch = new Branch(data[0], data[1], Integer.parseInt(data[2])); // Adapt constructor call as necessary
                branchList.add(branch);
            }
            serializer.serialize(branchList);
            System.out.println("Branch CSV data initialised.");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Branch CSV File not found");
        }
    }

	/**
     * Adds a new branch to the list.
     * @param newBranch The branch to be added.
     */
	public void add(Branch newBranch) {
		branchList.add(newBranch);
        System.out.println("Successfully added branch.");
		serializer.serialize(branchList);
	}

	/**
     * Deletes a specified branch from the list.
     * @param branch The branch to be deleted.
     */
	public void delete(Branch branch) {
		if (branchList.remove(branch)){
                System.out.println("Successfully removed branch.");
                serializer.serialize(branchList);
        	}
        	else{
                System.out.println("Failed to remove branch.");
        	}
	    }

	/**
     * Updates a specific branch in the list.
     * @param newBranch The branch with updated details.
     */
	public void update( Branch newBranch) {
		String name = newBranch.getBranchName();
        for (int i = 0; i < branchList.size(); i++){
            if (branchList.get(i).getBranchName().compareTo(name) == 0){
                branchList.set(i, newBranch);
                serializer.serialize(branchList);
                System.out.println("Successfully updated branch. ");
                break;
            }
        }
    }

	/**
     * Finds and returns a branch by its name.
     * @param branchName The name of the branch to find.
     * @return The found {@code Branch}, or {@code null} if not found.
     */
	public Branch find(String branchName) {
		for (Branch branch : branchList){
            if (branch.getBranchName().compareTo(branchName) == 0){
                return branch;
            }
        }
		return null;
	}

	/**
     * Retrieves the complete list of branches.
     * @return An ArrayList of {@code Branch} objects.
     */
	public ArrayList<Branch> getAll() {
		return branchList;
	}
}





