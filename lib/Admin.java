public class Admin extends Account {
	private IAdminForStaff adminForStaff;
	private IAdminForBranch adminForBranch;
	private IAdminForPayment adminForPayment;
	
	public Admin(String name,String staffID,String role,String gender,int age){
		super.name=name;
		super.staffID=staffID;
		super.role=role;
		super.gender=gender;
		super.age=age;
		adminForStaff=new AdminForStaff();
		adminForBranch=new AdminForBranch();
		adminForPayment=new AdminForPayment();
	}
	
	public void selectOptions(int choice) {
		// TODO - implement Admin.selectOptions
		throw new UnsupportedOperationException();
	}

	public void showOptions() {
		// TODO - implement Admin.showOptions
		throw new UnsupportedOperationException();
	}

}
