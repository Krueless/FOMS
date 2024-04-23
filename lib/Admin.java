public class Admin extends Account {
	private IAdminForStaff adminForStaff;
	private IAdminForBranch adminForBranch;
	private IAdminForPayment adminForPayment;
	public Admin(String name,String staffID,String role,String gender,int age,String password,IDisplay displayFormatter){
		super.name=name;
		super.staffID=staffID;
		super.role=role;
		super.gender=gender;
		super.age=age;
		super.password=password;
	        if(displayFormatter==null){
	            System.out.println("Error: displayFormatter is null");
	        }
	        adminForStaff=new AdminForStaff(accountDB,branchDB,displayFormatter);
	        adminForBranch=new AdminForBranch(branchDB,displayFormatter);
	        adminForPayment=new AdminForPayment(paymentDB,displayFormatter);
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
