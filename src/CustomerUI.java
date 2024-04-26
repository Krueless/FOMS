import java.util.Scanner;

public class CustomerUI {

	private String branchName;
	private IDataManager<Order,Integer> orderDB;

	public void createOrder() {
		DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
		DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
		DisplayFilteredByBranch displayformatter = new DisplayFilteredByBranch();
        Scanner sc = GlobalResource.SCANNER;
        boolean valid = false;
        OrderControl control;
        while (!valid) {
            try {
                System.out.println("Please select an option. ");
                System.out.println("(1) Dine-in");
                System.out.println("(2) Takeaway");
                String option = sc.nextLine();
                switch (option) {
                    case "1": //Dine-in
                        valid = true;
                        int orderID = orderDB.getAll().size() + 1;
                        Order newOrderDineIn = new Order(Integer.valueOf(orderID), false, branchName);
                        orderDB.add(newOrderDineIn);
                        System.out.println("Your order ID is: " + newOrderDineIn.getOrderID());
                        control = new OrderControl(orderDB, foodItemDB, displayformatter, newOrderDineIn, branchName);
                        control.chooseOptions();
                        break;
                    case "2": //Takeaway
                        valid = true;
                        Order newOrderTakeaway = new Order(orderDB.getAll().size() + 1, true, branchName);
                        orderDB.add(newOrderTakeaway);
                        System.out.println("Your order ID is: " + newOrderTakeaway.getOrderID());
                        control = new OrderControl(orderDB, foodItemDB, displayformatter, newOrderTakeaway,branchName);
                        control.chooseOptions();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
	}

    private void completeOrder(Order order)
    {
        System.out.println("Would you like to pick up? Press 1 for yes and 0 for no");
        int choice = GetOption.getBinaryNumber();
        if (choice == 1){
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderDB.update(order);
            System.out.println("Thank you for shopping at McOOP!");
        }
        else if (choice == 0){
            System.out.println("Remember to pick up your food before it cancels");
        }
        
    }

	/**
	 * 
	 * @param orderID
	 */
	public void checkOrder(int orderID) {
        Order order = orderDB.find(orderID);
    
        if (order != null){
            if (order.getBranchName().equals(branchName)){
                System.out.println(order.viewOrderStatus());
                if (order.getOrderStatus() == OrderStatus.READY_TO_PICKUP)
                {
                    completeOrder(order);
                }
            }
            else{
                System.out.println("OrderID keyed in not for this branch!");
            }
		    System.out.println("Returning to customer page...");
        }
        else
            System.out.println("Invalid orderID! Returning to customer page...");
	}

	public void showCustomerOptions() {
		Scanner sc = GlobalResource.SCANNER;
        boolean quit = false;

        while (!quit) {
            try {
                System.out.println("Welcome to " + branchName);
                System.out.println("Please select one of the following options.");
                System.out.println("(1) Create Order");
                System.out.println("(2) Check Order Status");
                System.out.println("(3) Quit");
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        createOrder();
                        break;
                    case "2":
                        System.out.println("What is your order ID? ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        checkOrder(id);
                        break;
                    case "3":
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
        System.out.println("Quit successfully.");
        
	}

	/**
	 * 
	 * @param branchName
	 */
	public CustomerUI(String branchName, IDataManager<Order,Integer> orderDB) {
		this.branchName = branchName;
		this.orderDB = orderDB;

        showCustomerOptions();
	}

}