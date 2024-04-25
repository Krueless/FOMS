
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManagerForBranch implements IDataManager<Branch,String>, Serializable {
	private ArrayList<Branch> branchList;
	private static DataManagerForBranch instance;
	private final Serializer<Branch> serializer;

	private DataManagerForBranch() {
	        serializer = new Serializer<Branch>("../src/branchData.ser");
	        loadData();
	}

	public static DataManagerForBranch getInstance() {
		if (instance == null) {
			instance = new DataManagerForBranch();
		}
		return instance;
	}

	public void loadData(){
		try{
			branchList = serializer.deserialize();
		}catch (IOException | ClassNotFoundException e){
			System.out.println("Serialized file not found or invalid, initialising from CSV.");
			e.printStackTrace();
			branchList = new ArrayList<Branch>();
			initializeFromCSV();
		}
	}

	/**
	 * 
	 * @param branch
	 */
	public void add(Branch newBranch) {
		branchList.add(newBranch);
        System.out.println("Successfully added branch.");
		serializer.serialize(branchList);
	}

	/**
	 * 
	 * @param branch
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
	 * 
	 * @param oldBranch
	 * @param newBranch
	 */
	public void update( Branch newBranch) {
		String name = newBranch.getBranchName();
	        for (int i = 0; i < branchList.size(); i++){
	            	if (branchList.get(i).getBranchName().compareTo(name) == 0){
		                branchList.set(i, newBranch);
		                System.out.println("Successfully updated branch. ");
		                break;
	        	}
        	}
        serializer.serialize(branchList);
        return;
	}

	/**
	 * 
	 * @param branchName
	 */
	public Branch find(String branchName) {
		for (Branch branch : branchList){
            		if (branch.getBranchName().compareTo(branchName) == 0){
                		return branch;
            		}
        	}
		return null;
	}

	public ArrayList<Branch> getAll() {
		return branchList;
	}

            // Method to read CSV and initialize data
    private void initializeFromCSV() {
		File f = new File("../src/branch_list.csv");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}





