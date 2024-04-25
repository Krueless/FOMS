import java.util.Scanner;
import java.util.ArrayList;

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
		ArrayList<Branch> branchList = branchDB.getAll();
		ArrayList<BranchForDisplay> displayBranchList = new ArrayList<>(branchList.size());
		for (Branch item : branchList){
			displayBranchList.add((BranchForDisplay) item);
		}
		displayFormatter.displayAll(displayBranchList);
		System.out.println("Please key in the branch name which you would like to visit.");
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
		sc.close();
	}

}
