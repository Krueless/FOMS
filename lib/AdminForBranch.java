import java.util.Scanner;
public class AdminForBranch extends Admin implements IAdminForBranch {
	private IDataManager branchDB;
	private IDisplay displayFormatter;
	public AdminForBranch(IDataManager branchDB,IDisplay displayFormatter){
		this.branchDB=branchDB;
		this.displayFormatter=displayFormatter;
	}
	public void openBranch(){
		//obtain the attributes for the new branch
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the staff quota");
		int staffQuota=sc.nextInt();
		System.out.println("Enter the branch name");
		String branchName=sc.nextLine();
		Sytem.out.println("Enter the location");
		String location=sc.nextLine();
		//create a new branch object
		Branch branch=new Branch(staffQuota,branchName,location);
		//add the new Branch object to branchList in DataManagerForBranch
	        branchDB.add(branch);
	    }
	public void closeBranch(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the branch to be closed");
		String branchName=sc.nextLine();
		Branch branch=branchDB.find(branchName);//find the branch
		branchDB.delete(branch);//delete the branch
	}
}
