
import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.Scanner;
import java.io.Serializable;

public class DataManagerForBranch implements IDataManager<Branch,String>, Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Branch> branchList;
	private static DataManagerForBranch instance;
	private final Serializer<Branch> serializer;

	private DataManagerForBranch() {
		branchList = new ArrayList<>();
	        serializer = new Serializer<Branch>("../src/branchData.ser");
	        // Deserialize data when initializing the instance
	        deserializeData();
	}

	public static DataManagerForBranch getInstance() {
		if (instance == null) {
			instance = new DataManagerForBranch();
		}
		return instance;
	}

	/**
	 * 
	 * @param branch
	 */
	public void add(Branch branch) {
		branchList.add(branch);
		serializer.serialize(branchList);
	}

	/**
	 * 
	 * @param branch
	 */
	public void delete(Branch branch) {
		if (branchList.remove(branch)){
            		System.out.println("Successfully removed branch.");
        	}
        	else{
            		System.out.println("Failed to remove branch.");
        	}
		serializer.serialize(branchList);
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

	public void serializeData() {
        	try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("branchData.ser"))) {
            		out.writeObject(branchList);
        	} catch (IOException e) {
            		e.printStackTrace();
        	}
    	}

        // Deserialization method
        @SuppressWarnings("unchecked")
        private void deserializeData() {
            Path filePath = Paths.get("branchData.ser");
            if (Files.exists(filePath)) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("branchData.ser"))) {
                    branchList = (ArrayList<Branch>) in.readObject();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                // If the serialized file does not exist, we may need to initialize from CSV
                initializeFromCSV("branch_list.csv");
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
                Branch branch = new Branch(data[0], data[1], Integer.parseInt(data[2])); // Adapt constructor call as necessary
                branchList.add(branch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}





