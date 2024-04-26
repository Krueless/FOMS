import java.util.Scanner;

/**
 * This class provides a user interface for navigate the branch to order from.
 * It use a data manager to retrieve branch data and a display formatter for displaying branch information.
 */
public class BranchUI {

	private IDataManager<Branch,String> branchDB;
	private IDisplay displayFormatter;

	/**
     * Constructs a BranchUI object with specified data manager and display formatter.
     * Initializes the user interface to allows users to choose a branch from a list.
     * Continues to prompt the user until a valid branch name is chosen.
     * @param branchDB The data manager for branch data.
     * @param displayFormatter The display formatter for presenting branch data.
     */
	public BranchUI(IDataManager<Branch, String> branchDB, IDisplay displayFormatter) {
		this.branchDB = branchDB;
		this.displayFormatter = displayFormatter;
		boolean keep_asking = true;
		Scanner sc = GlobalResource.SCANNER;
		while (keep_asking){
			showBranches();
			String branchName = sc.nextLine();
			if (chooseBranch(branchName)){
				keep_asking = false;
				DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
				new CustomerUI(branchName, orderDB);
			}
		}
	}

    /**
     * Checks if the given branch name exists in the database and selects it if available.
     * @param branchName The name of the branch to choose.
     * @return true if the branch exists and is chosen, false otherwise.
     */
	public boolean chooseBranch(String branchName) {
		if (branchDB.find(branchName) != null){
        		System.out.println("Successfully chose branch");
        		return true;
        	}
        	System.out.println("Please correctly key in a name of a branch. ");
        	return false;
	}

    /**
     * Displays all available branches using the display formatter and prompts the user
     * to key in the branch name to visit.
     */
	public void showBranches() {
		displayFormatter.displayAll(branchDB.getAll());
		System.out.println("Please key in the branch name which you would like to visit.");
	}
}
