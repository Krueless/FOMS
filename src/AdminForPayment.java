import java.util.ArrayList;
import java.util.Scanner;
public class AdminForPayment implements IAdminForPayment{
	private IDataManager<IPayment, String> paymentDB;
    /**
     * Allows admin to add a new payment method
     */
	public void addPaymentMethod(){
        paymentDB=DataManagerForPayment.getInstance();
	    Scanner sc = GlobalResource.SCANNER;
		String choice = "-1";

        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Select option");
	    	System.out.println("(1) Online");
	    	System.out.println("(2) Credit");
		    choice = sc.nextLine();
            if (!choice.equals("1") && !choice.equals("2"))
                System.out.println("Invalid option. Try again!");
        }
        System.out.println("Enter the name of new payment method:");
        String name = sc.nextLine();
        IPayment payment = paymentDB.find(name);
        if (payment == null){
            switch (choice){
                case "1":
                    paymentDB.add(new Online(name));
                    break;
                case "2":
                    paymentDB.add(new Credit(name));
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("Payment already exist!");
        }
        System.out.println("Returning to user page...");
	}
	
    /**
     * Allows admin to remove an existing payment method
     */
	public void removePaymentMethod(){
        paymentDB=DataManagerForPayment.getInstance();
		Scanner sc = GlobalResource.SCANNER;
        Display displayFormatter = new Display();
        ArrayList<IPayment> paymentList = paymentDB.getAll();
        if (paymentList.size() > 0){
            displayFormatter.displayAll(paymentList);
            System.out.println("Enter the name of payment method to be removed (case-senstive):");
            String paymentName = sc.nextLine();
            IPayment paymentMethod = paymentDB.find(paymentName);
            if(paymentMethod != null){
                paymentDB.delete(paymentMethod);
            }else{
                System.out.println("Payment method not found! Returning to user page...");
            }
        }else{
            System.out.println("No payment methods to remove! Returning to user page...");
        }
	}
}
