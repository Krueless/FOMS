import java.io.Serializable;
import java.util.Scanner;
public class AdminForPayment implements IAdminForPayment, Serializable {
	private IDataManager<IPayment, String> paymentDB;
	public AdminForPayment(){
	    this.paymentDB=DataManagerForPayment.getInstance();
	}
    /**
     * Allows admin to add a new payment method
     */
	public void addPaymentMethod(){
	    Scanner sc = GlobalResource.SCANNER;
		String choice;
		String name;
		IPayment paymentMode;
	    try{
			System.out.println("Select option");
	    	System.out.println("1.Online");
	    	System.out.println("2.Credit");
		    choice = sc.nextLine();
		    System.out.println("Enter name of new payment method");
		    name = sc.nextLine();
			switch (choice){
				case "1":
				paymentMode = new Online(name);
				break;
				case "2":
				paymentMode = new Credit(name);
				break;
				default:
				System.out.println("Invalid payment option");
			    System.out.println("Returning to user page...");
				return;
			}
		    paymentDB.add(paymentMode);
	    }catch (Exception e){
	            System.out.println("Choice of payment type must be number! Returning to user page...");
	    }
	}
	
    /**
     * Allows admin to remove an existing payment method
     */
	public void removePaymentMethod(){
		Scanner sc = GlobalResource.SCANNER;
		System.out.println("Enter the payment method to remove");
		String paymentName = sc.nextLine();
		IPayment paymentMethod=paymentDB.find(paymentName);
		if(paymentMethod != null){
			paymentDB.delete(paymentMethod);
		}else{
			System.out.println("Payment method not found! Returning to user page...");
		}
	}
}
