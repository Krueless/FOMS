public class Staff extends Account implements IGetBranchName {

	protected String branchName;
	protected IDataManager orderDB;
	protected IDisplayFilteredBy displayFormatter;
	public Staff(String name,String staffID,String role,String gender,int age,String password,String branchName){
		super.name=name;
		super.staffID=staffID;
		super.password=password;
		super.role=role;
		super.gender=gender;
		super.age=age;
		this.branchName=branchName;
		this.orderDB=DataManagerForOrder.getInstance();
		this.displayFormatter=new DisplayWithFilter();
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName){
		this.branchName=branchName;
	}
	
	public void displayNewOrders(){
		displayFormatter.displayFilteredByBranch(orderDB.getAll(),branchName);
	}
	
	public void viewOrder(int orderID){
		Order order=orderDB.find(orderID);
		//displayorder
		order.toString(); //use toString() method in Order class to get all attributes (encapsulation)
	}
	
	public void processOrder(int orderID){
		Order order=orderDB.find(orderID);
		order.setOrderStatus(3);
	}
	
	public void selectOptions(int choice){
		Scanner sc=new Scanner(System.in);
		switch(choice){
		    case 1:
		    displayNewOrders();
		    break;
		    case 2:
		    System.out.println("Enter the orderID:");
		    int orderID=sc.nextInt();
		    viewOrder(orderID);
		    break;
		    case 3:
		    System.out.println("Enter the orderID:");
		    int orderID=sc.nextInt();
		    processOrder(orderID);
		    break;
		}
		sc.close();
	}
	
	public void displayOptions(){
		Scanner sc=new Scanner(System.in);
		boolean valid=false;
		while(!valid){
		    try{
			System.out.println("Please select one of the following options");
			System.out.println("1. display new orders");
			System.out.println("2. view order");
			System.out.println("3. process order");
			int option=sc.nextInt();
			switch(option){
			    case 1:
			    selectOptions(1);
			    valid=true;
			    break;
			    case 2:
			    selectOptions(2);
			    valid=true;
			    break;
			    case 3:
			    selectOption(3);
			    valid=true;
			    break;
			    default:
			    System.out.println("Invalid option. Please try again");
			    break;
			}
		    } catch (Exception e) {
			System.out.println("An error occurred: "+e.getMessage());
			System.out.println("Please try again");
		    }
		}
		sc.close();
	}

}
