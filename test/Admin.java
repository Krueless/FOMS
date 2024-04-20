public class Admin extends Account {
	private IAdminForStaff adminForStaff;
	private IAdminForBranch adminForBranch;
	private IAdminForPayment adminForPayment;
	public Admin(String name,String staffID,String role,String gender,int age,String password,IDataManager branchDB,IDataManager paymentDB,IDataManager accountDB,IDisplay displayFormatter){
	super(name,staffID,role,gender,age,password);
	if(branchDB==null){
	    System.out.println("Error: branchDB is null");
	}else if(!(branchDB instanceof DataManagerForBranch)){
	    System.out.println("Error: Incorrect Data Manager");
	}
	if(accountDB==null){
	    System.out.println("Error: accountDB is null");
	}else if(!(accountDB instanceof DataManagerForAccount)){
	    System.out.println("Error: Incorrect Data Manager");
	}
        if(paymentDB==null){
            System.out.println("Error: paymentDB is null");
        }else if(!(paymentDB instanceof DataManagerForPayment)){
            System.out.println("Error: Incorrect Data Manager");
        }
        if(displayFormatter==null){
            System.out.println("Error: displayFormatter is null");
        }
        adminForStaff=new AdminForStaff(accountDB,branchDB,displayFormatter);
        adminForBranch=new AdminForBranch(branchDB,displayFormatter);
        adminForPayment=new AdminForPayment(paymentDB,displayFormatter);
	
	public void selectOptions(int choice) {
		// TODO - implement Admin.selectOptions
		throw new UnsupportedOperationException();
	}

	public void showOptions() {
		// TODO - implement Admin.showOptions
		throw new UnsupportedOperationException();
	}
	/*public Admin(String name, String staffID, String role, String gender, int age) {
		// TODO - implement Admin.Admin
		this.name = name;
		this.staffID = staffID;
		this.role = role;
		this.gender = gender;
	}*/

}
