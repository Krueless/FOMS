import java.util.ArrayList;

public class DisplayFilteredByBranch implements IDisplayFilteredByBranch {

	/**
	 * 
	 * @param data
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
	 * 
	 * @param data
	 * @param branchName
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

