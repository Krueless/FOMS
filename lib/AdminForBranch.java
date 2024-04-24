import java.util.Scanner;
public class AdminForBranch implements IAdminForBranch {
	private IDataManager<Branch, String> branchDB;
	public AdminForBranch(){
		this.branchDB=DataManagerForBranch.getInstance();
	}
	
    /**
     * Allows admin to open a new branch
     */
	public void openBranch(){
		//obtain the attributes for the new branch
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the staff quota");
		try{
			int staffQuota=sc.nextInt();
			System.out.println("Enter the branch name");
			String branchName=sc.nextLine();
			while(branchDB.find(branchName)!=null){
				System.out.println("A branch with this name already exists");
				System.out.println("Enter a new branch name");
				branchName=sc.nextLine();
			}
			System.out.println("Enter the location");
			String location=sc.nextLine();
			//create a new branch object
			Branch branch=new Branch(branchName,location,staffQuota);
			//add the new Branch object to branchList in DataManagerForBranch
			branchDB.add(branch);
		}catch(Exception e){
			System.out.println("Staff quota must be number! Returning to user page...");
		}
		sc.close();
	}
	
    /**
     * Allows admin to close an existing branch
     */
	public void closeBranch(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the branch to be closed");
		String branchName=sc.nextLine();
		Branch branch=branchDB.find(branchName);//find the branch
		if(branch!=null){
			branchDB.delete(branch);//delete the branch
		}else{
			System.out.println("Branch not found! Returning to user page...");
		}
		sc.close();
	}
}
