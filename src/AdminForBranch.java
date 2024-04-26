import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides administrative functionalities for managing branches.
 * It includes methods to open and close branches.
 */
public class AdminForBranch implements IAdminForBranch{
	private IDataManager<Branch, String> branchDB;

    /**
     * Constructor that initialises the DataManager for managing branch data.
     */
	public AdminForBranch(){
		this.branchDB=DataManagerForBranch.getInstance();
	}
	
    /**
     * Opens a new branch by prompting the admin to input necessary details with error checking.
     * Ensures the branch name is unique and the staff quota is within specified limits.
     */
	public void openBranch(){
		//obtain the attributes for the new branch
		Scanner sc = GlobalResource.SCANNER;
        int staffQuota = -1;
        while (staffQuota < 1 || staffQuota > 15) {
            System.out.println("Enter the staff quota (Between 1-15):");
            if (sc.hasNextInt()) {  // Check if the next token is an integer
                staffQuota = sc.nextInt();  // Read the integer
                if (staffQuota < 1 || staffQuota > 15) {
                    System.out.println("Invalid input: Quota must be between 1 and 15.");
                }
            } else {
                System.out.println("Invalid input: Please enter a number.");
                sc.nextLine();  
            }
        }
        sc.nextLine();
        System.out.println("Enter the branch name:");
        String branchName = sc.nextLine();
        while(branchDB.find(branchName)!=null){
            System.out.println("A branch with this name already exists");
            System.out.println("Enter a new branch name:");
            branchName = sc.nextLine();
        }
        System.out.println("Enter the location:");
        String location = sc.nextLine();
        //create a new branch object
        Branch branch = new Branch(branchName,location,staffQuota);
        //add the new Branch object to branchList in DataManagerForBranch
        branchDB.add(branch);
	}
	
     /**
     * Closes an existing branch with the branch name inputted by admin.
     * All associated accounts and menu items will also be deleted upon confirmation.
     */
	public void closeBranch(){
		Scanner sc = GlobalResource.SCANNER;
        IDisplay displayFormatter = new Display(); 
        ArrayList<Branch> branchList = branchDB.getAll();
        if (branchList.size() >= 0){
            displayFormatter.displayAll(branchDB.getAll());;
            System.out.println("Enter the name of the branch to be closed (case-sensitive):");
            String branchName = sc.nextLine();
            Branch branch = branchDB.find(branchName);//find the branch

            if (branch != null){
                System.out.println("Warning: Closing the branch will cause all account and menu items associated to it to be deleted.");
                System.out.println("To cancel this operation, enter 0.");
                System.out.println("To confirm this operation, enter 1.");
                int option = GetOption.getBinaryNumber();
                if (option == 1){
                    branchDB.delete(branch);//delete the branch
                    IDataManagerWithCount accountDB = DataManagerForAccount.getInstance();
                    IDataManager<FoodItem,Integer> foodItemDB = DataManagerForFoodItem.getInstance();

                    ArrayList<Account> accList = accountDB.getAll();
                    ArrayList<Account> deletedAccList = new ArrayList<Account>();

                    ArrayList<FoodItem> foodItemList = foodItemDB.getAll();
                    ArrayList<FoodItem> deletedFoodItems = new ArrayList<FoodItem>();

                    for (Account acc: accList){
                        if (acc instanceof Staff){
                            Staff staffAcc = (Staff) acc;
                            if (staffAcc.getBranchName().equals(branchName)){
                                deletedAccList.add(staffAcc);
                            }
                        }
                    }

                    for (Account acc: deletedAccList){
                        accountDB.delete(acc);
                    }

                    for (FoodItem item: foodItemList){
                        if (item.getBranchName().equals(branchName)){
                            deletedFoodItems.add(item);
                        }
                    }

                    for (FoodItem item: deletedFoodItems){
                        foodItemDB.delete(item);
                    }

                }
            }
            else{
                System.out.println("Branch not found! Returning to user page...");
            }
	    }
        else{
            System.out.println("No branch to be closed! Returning to user page...");
        }
    }
}
