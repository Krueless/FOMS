import java.util.Scanner;

public class BranchUI {

	private IDataManager<Branch,String> branchDB;
	private IDisplay displayFormatter;

	/**
	 * 
	 * @param branchName
	 */
	public boolean chooseBranch(String branchName) {
		if (branchDB.find(branchName) != null){
        		System.out.println("Successfully chose branch");
        		return true;
        	}
        	System.out.println("Please correctly key in a name of a branch. ");
        	return false;
	}

	public void showBranches() {
		displayFormatter.displayAll(branchDB.getAll());
	}

	/**
	 * 
	 * @param branchDB
	 * @param displayFormatter
	 */
	public BranchUI(IDataManager<Branch, String> branchDB, IDisplay displayFormatter) {
		this.branchDB = branchDB;
        	this.displayFormatter = displayFormatter;
        	boolean keep_asking = true;
        	Scanner sc = new Scanner(System.in);
        	while (keep_asking){
	            	try{
		                showBranches();
		                System.out.println("Please key in the branch name which you would like to visit.");
		                String branchName = sc.nextLine();
		                if (chooseBranch(branchName)){
		                    keep_asking = false;
							DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
		                    new CustomerUI(branchName, orderDB);
		                }
		    	}
		    	catch(Exception e){
				System.out.println("An error occurred: " + e.getMessage());
				System.out.println("Please try again.\n");
		    	}
		}
		sc.close();
	}

}
