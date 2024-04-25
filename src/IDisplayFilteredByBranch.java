import java.util.ArrayList;

public interface IDisplayFilteredByBranch extends IDisplay {

	/**
	 * 
	 * @param data
	 * @param branchName
	 */
	void displayFilteredByBranch(ArrayList<IGetBranchName> data, String branchName);

}