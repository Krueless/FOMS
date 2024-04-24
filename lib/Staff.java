import java.util.ArrayList;
import java.util.Scanner;
public class Staff extends Account implements IGetBranchName {

	protected String branchName;
	protected IDataManager<Order,Integer> orderDB;
	protected IDisplayFilteredByBranch displayFormatter;
	public Staff(String name,String staffID,String role,String gender,int age,String branchName, IDataManager<Order, Integer> orderDB, IDisplayFilteredByBranch displayFormatter){
		super.name=name;
		super.staffID=staffID;
		super.role=role;
		super.gender=gender;
		super.age=age;
		this.branchName=branchName;
		this.orderDB=DataManagerForOrder.getInstance();
		this.displayFormatter=displayFormatter;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName){
		this.branchName=new String(branchName);
	}
	
	public void displayNewOrders(){
		ArrayList<Order> orderList=orderDB.getAll();
		ArrayList<Order> newOrders=new ArrayList<Order>();
		//loop through orderList and add only the new orders to newOrders
		for(int i=0;i<orderList.size();i++){
			Order order=orderList.get(i);
			if(order.getOrderStatus()==OrderStatus.PREPARING){
				newOrders.add(order);
			}
		}
		ArrayList<IGetBranchName> branchNameList = new ArrayList<>(newOrders.size());
        for (Order item : newOrders) {
            branchNameList.add(item);
        }
		displayFormatter.displayFilteredByBranch(branchNameList,branchName);
	}
	
	public void viewOrder(int orderID){
		Order order=orderDB.find(orderID);
		//displayorder
		order.toString(); //use toString() method in Order class to get all attributes (encapsulation)
	}
	
	public void processOrder(int orderID){
		Order order=orderDB.find(orderID);
		order.setOrderStatus(OrderStatus.READY_TO_PICKUP);
	}
	
	public void selectOptions(int choice){
		Scanner sc=new Scanner(System.in);
		int orderID;
		switch(choice){
		    case 1:
		    displayNewOrders();
		    break;
		    case 2:
		    System.out.println("Enter the orderID:");
		    orderID=sc.nextInt();
		    viewOrder(orderID);
		    break;
		    case 3:
		    System.out.println("Enter the orderID:");
		    orderID=sc.nextInt();
		    processOrder(orderID);
		    break;
		    default:break;
		}
		sc.close();
	}
	
	public void showOptions(){
		Scanner sc=new Scanner(System.in);
		boolean valid=false;
		while(!valid){
		    try{
			System.out.println("Please select one of the following options");
			System.out.println("1. Display new orders");
			System.out.println("2. View order");
			System.out.println("3. Process order");
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
			    selectOptions(3);
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
