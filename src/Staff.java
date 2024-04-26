import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a staff member in the system.
 * Inherits from the Account class and implements the IGetBranchName interface.
 */
public class Staff extends Account implements IGetBranchName {
    /**
     * The name of the branch associated with the staff member.
     */
	protected String branchName;

    /**
     * The data manager for orders.
     */
	protected IDataManager<Order,Integer> orderDB;

    /**
     * The display formatter for filtered orders by branch.
     */
	protected IDisplayFilteredByBranch displayFormatter;

    /**
     * Constructs a Staff object with the specified details.
     * @param name The name of the staff member.
     * @param staffID The staff ID.
     * @param role The role of the staff member.
     * @param gender The gender of the staff member.
     * @param age The age of the staff member.
     * @param branchName The branch name associated with the staff member.
     * @param orderDB The data manager for managing orders.
     * @param displayFormatter The display formatter for filtered orders.
     */
	public Staff(String name,String staffID,String role,String gender,int age,String branchName, IDisplayFilteredByBranch displayFormatter){
		super.name=name;
		super.staffID=staffID;
		super.role=role;
		super.gender=gender;
		super.age=age;
		this.branchName=branchName;
		this.displayFormatter=displayFormatter;
	}

    /**
     * Retrieves the branch name associated with the staff member.
     * @return The branch name.
     */
	public String getBranchName() {
		return branchName;
	}

    /**
     * Change the branch name associated with the staff member.
     */
	public void setBranchName(String branchName){
		this.branchName=new String(branchName);
	}
	
	/**
     * Displays new orders (status: PREPARING) filtered by branch.
     */
	public void displayNewOrders(){
		ArrayList<Order> orderList=orderDB.getAll();
		ArrayList<IGetBranchName> newOrders=new ArrayList<IGetBranchName>();
		//loop through orderList and add only the new orders to newOrders
		for(int i = 0; i<orderList.size();i++){
			if(orderList.get(i).getOrderStatus()==OrderStatus.PREPARING)
				newOrders.add(orderList.get(i));
		}
		displayFormatter.displayFilteredByBranch(newOrders,branchName);
	}
	
    /**
     * Allows the staff member to view an order from his/her branch.
     */
	public void viewOrder(){
        Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter the Order ID:");
        try{
            int orderID = sc.nextInt();
            Order order = orderDB.find(Integer.valueOf(orderID));
            if (order != null && order.getBranchName().equals(branchName)) {
                sc.nextLine();
                System.out.println(order);
            } else {
                sc.nextLine();
                System.out.println("Order not found or does not belong to your branch. Returning to user page...");
            }
        }catch (Exception e){
            sc.nextLine();
            System.out.println("Order ID must be number! Returning to user page...");
        }
    }
	
    /**
     * Allows the staff member to process a new order from his/her branch, changing its status to ready to pickup
     */
	public void processOrder(){
		Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter the Order ID to be processed:");
        try{
            int orderID = sc.nextInt();
            Order order = orderDB.find(Integer.valueOf(orderID));
            if (order != null && order.getBranchName().equals(branchName)) {
                order.setOrderStatus(OrderStatus.READY_TO_PICKUP);
                orderDB.update(order);
                System.out.println("Order status updated to ready for collection. Returning to user page...");
                sc.nextLine();
            } else {
                sc.nextLine();
                System.out.println("Order not found or does not belong to your branch.");
            }        
        }catch (Exception e){
            sc.nextLine();
            System.out.println("Order ID must be number! Returning to user page...");
        }
	}

	/**
     * Allows the staff member to select options from menu.
     */
	public void selectOptions(){
        Scanner sc = GlobalResource.SCANNER;
        orderDB = DataManagerForOrder.getInstance();
		boolean quit =false;
		while(!quit){
            showOptions();
			String option=sc.nextLine();
			switch(option){
			    case "1":
                    displayNewOrders();
                    break;
			    case "2":
                    viewOrder();
                    break;
			    case "3":
                    processOrder();
                    break;
                case "4":
                    quit = true;
                    break;
			    default:
			        System.out.println("Invalid option. Please try again");
			        break;
			}
        }
        System.out.println("Log out successfully.");
    }
    /**
     * Displays the menu options for staff.
     */
	public void showOptions(){
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Staff Page");
        System.out.println("Please select one of the following options");
        System.out.println("(1) Display new orders");
        System.out.println("(2) View order");
        System.out.println("(3) Process order");
        System.out.println("(4) Log out");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
	}

}
