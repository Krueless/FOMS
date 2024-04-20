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
                System.out.println(i+1 +": " + array.get(i).toString());
        }
        }
	}

	/**
	 * 
	 * @param data
	 * @param branchName
	 */
	public void displayFilteredByBranch(IGetBranchName data, String branchName) {
		if (data instanceof ArrayList){
            @SuppressWarnings("unchecked")
            ArrayList<IGetBranchName> array = (ArrayList<IGetBranchName>) data;
            int j = 1;
            for (int i = 0; i < array.size(); i++){
                if (array.get(i).getBranchName().compareTo(branchName) == 0){
                    System.out.println(j +": " + array.get(i).toString());
                    j++ ;
                }
                
            }
        }
	}

}