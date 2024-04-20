public class Staff extends Account implements IGetBranchName {

	protected String branchName;
	protected IDataManager<Order,String> orderDB;
	protected IDisplay displayFormatter;

	/**
	 * 
	 * @param name
	 * @param staffID
	 * @param role
	 * @param gender
	 * @param age
	 * @param branchName
	 * @param orderDB
	 * @param diaplayFormatter
	 */
	public Staff(String name, String staffID, String role, String gender, int age, String branchName, IDataManager<Order,String> orderDB, IDisplay diaplayFormatter) {
		// TODO - implement Staff.Staff
		this.name = name;
		this.staffID = staffID;
		this.role = role;
		this.gender = gender;
		this.branchName = branchName;
		this.orderDB = orderDB;
		this.displayFormatter =diaplayFormatter;
		throw new UnsupportedOperationException();
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void displayNewOrders() {
		// TODO - implement Staff.displayNewOrders
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void viewOrder(int orderID) {
		// TODO - implement Staff.viewOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void processOrder(int orderID) {
		// TODO - implement Staff.processOrder
		throw new UnsupportedOperationException();
	}

	public void showOptions() {
		// TODO - implement Staff.showOptions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param choice
	 */
	public void selectOptions(int choice) {
		// TODO - implement Staff.selectOptions
		throw new UnsupportedOperationException();
	}

}