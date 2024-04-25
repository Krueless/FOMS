import java.util.ArrayList;
import java.io.*; 
import java.util.Scanner;


public class DataManagerForAccount implements IDataManagerWithCount{
	private ArrayList<Account> accountList;
	private static DataManagerForAccount instance;
	private final Serializer<Account> serializer;

	private DataManagerForAccount() {
		serializer = new Serializer<Account>("../src/accountData.ser");
		loadData();
	}

	public static DataManagerForAccount getInstance() {
		if (instance == null) {
			instance = new DataManagerForAccount();
		}
		return instance;
	}

	private void loadData(){
		try{
			accountList = serializer.deserialize();
		}catch (IOException | ClassNotFoundException e){
			System.out.println("Serialized file not found or invalid, initialising from CSV.");
			accountList = new ArrayList<Account>();
			initializeFromCSV();
		}
	}

	private void initializeFromCSV() {
		DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
		DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
		DisplayFilteredByBranch staffDisplay = new DisplayFilteredByBranch();
	
		File f = new File("../src/staff_list.csv");
		try{
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				String role = data[2];
				switch (role){
					case "A":
						accountList.add(new Admin(data[0], data[1], data[2], data[3], Integer.parseInt(data[4])));
						System.out.println("added");
                        break;
					case "M":
						accountList.add(new Manager(data[0], data[1], data[2], data[3],Integer.parseInt(data[4]), data[5], orderDB,staffDisplay, foodItemDB, instance));
						System.out.println("added");
                        break;
					case "S":
						accountList.add(new Staff(data[0], data[1], data[2], data[3],Integer.parseInt(data[4]), data[5], orderDB,staffDisplay));
						System.out.println("added");
                        break;
				}
			}
			serializer.serialize(accountList);
			System.out.println("CSV data initialised.");
			sc.close();
		}catch (FileNotFoundException e){
			System.out.println("Error: CSV File not found");
		}
       
    }


	/**
	 * 
	 * @param oldAccount
	 * @param newAccount
	 */
	public void update(Account newAccount) {
		String staffID = newAccount.getStaffID();
		for (int i = 0; i < accountList.size(); i++) {
			if (accountList.get(i).getStaffID().equals(staffID)){
				accountList.set(i, newAccount);
				System.out.println("Successfully updated staff.");
				break;
			}
		}
		serializer.serialize(accountList);
	}

	/**
	 * 
	 * @param account
	 */
	public void add(Account newAccount) {
		accountList.add(newAccount);
        System.out.println("Successfully added account.");
		serializer.serialize(accountList);
	}

	/**
	 * 
	 * @param account
	 */
	public void delete(Account account) {
		if (accountList.remove(account)){
            System.out.println("Successfully removed account.");
		    serializer.serialize(accountList);
        }else{
            System.out.println("Failed to remove account.");
        }
	}

	/**
	 * 
	 * @param staffID
	 */
	public Account find(String staffID) {
		for (Account currAcc: accountList) {
			if (currAcc.getStaffID().equals(staffID))
				return currAcc;
		}
		return null;
	}

	public ArrayList<Account> getAll() {
		return accountList;
	}

	/**
	 * 
	 * @param branchName
	 */
	public int countStaffInBranch(String branchName) {
		int staffCount = 0;
		for (Account currAcc: accountList) {
			if (currAcc.getRole() == "S"){
				if (currAcc instanceof Staff){
					Staff staffAcc = (Staff) currAcc;
					if (staffAcc.getBranchName() == branchName) 
						staffCount++;
				}
			}
		}
		return staffCount;
	}

	/**
	 * 
	 * @param branchName
	 */
	public int countManagerInBranch(String branchName) {
		int managerCount = 0;
		for (Account currAcc: accountList) {
			if (currAcc.getRole() == "M"){
				if (currAcc instanceof Manager){
					Manager managerAcc = (Manager) currAcc;
					if (managerAcc.getBranchName() == branchName) 
						managerCount++;
				}
			}
		}
		return managerCount;
	}

}