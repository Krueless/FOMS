import java.util.ArrayList;

    /**
     * Implements {@link IDisplayFilteredByBranch} to provide functionality for displaying lists of items that can be filtered by branch name.
     * This class is designed to display information about items, such as accounts or other branch-specific data.
     * 
     */
public class DisplayFilteredByBranch implements IDisplayFilteredByBranch {
    /**
     * Displays all elements within a list by calling each element's {@code toString()} method.
     * If the list contains elements, it prints each element prefixed with its position in the list.
     * @param data The list of elements to be displayed, expected to be an instance of {@code ArrayList}.
     */
	public void displayAll(Object data) {
		if (data instanceof ArrayList){
            @SuppressWarnings("unchecked")
            ArrayList<Object> array = (ArrayList<Object>) data;
            for (int i = 0; i < array.size(); i++){
                System.out.println(i+1 +") " + array.get(i).toString());
            }
            if (array.size() == 0){
                System.out.println("No records found!"); 
            }
        }
	}

	/**
     * Displays items filtered by a specific branch name. Only items belonging to the specified branch are displayed.
     * 
     * @param data The list of items, each implementing {@link IGetBranchName}, to be filtered and displayed.
     * @param branchName The branch name used as the filter criterion. Only items with this branch name are displayed.
     */
	public void displayFilteredByBranch(ArrayList<IGetBranchName> data, String branchName) {
        int j = 1;
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getBranchName().compareTo(branchName) == 0){
                System.out.println(j +") " + data.get(i).toString());
                j++ ;
            }
        }
        if (j == 1){
            System.out.println("No records found for this branch!");
        }
    }
}

