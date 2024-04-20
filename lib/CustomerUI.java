import java.util.Scanner;

public class CustomerUI {

	private String branchName;

	public void createOrder() {
		// TODO - implement CustomerUI.createOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void checkOrder(int orderID) {
		// TODO - implement CustomerUI.checkOrder
		throw new UnsupportedOperationException();
	}

	public void showCustomerOptions() {
		Scanner sc = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Welcome to " + branchName);
                System.out.println("Please select one of the following options.");
                System.out.println("1. Create Order");
                System.out.println("2. Check Order Status");
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        valid = true;
                        createOrder();
                        break;
                    case "2":
                        System.out.println("What is your order ID? ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        checkOrder(id);;
                        valid = true;
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

        sc.close();
        
	}

	/**
	 * 
	 * @param branchName
	 */
	public CustomerUI(String branchName) {
		this.branchName = branchName;
        showCustomerOptions();
	}

}