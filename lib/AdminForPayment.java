import java.util.Scanner;
public class AdminForPayment implements IAdminForPayment {
	private IDataManager<IPayment, String> paymentDB;
	public AdminForPayment(){
	    this.paymentDB=DataManagerForPayment.getInstance();
	}
    /**
     * Allows admin to add a new payment method
     */
	public void addPaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Select option");
	    System.out.println("1.Online");
	    System.out.println("2.Credit");
	    try{
		    int choice=sc.nextInt();
		    System.out.println("Enter name of new payment method");
		    String name=sc.nextLine();
		    IPayment paymentMode;
		    if(choice==1){
			    paymentMode=new Online(name);
		    }else if(choice==2){
			    paymentMode=new Credit(name);
		    }else{
			    System.out.println("Invalid payment option");
			    System.out.println("Returning to user page...");
			    sc.close();
			    return;
		    }
		    paymentDB.add(paymentMode);
	    }catch (Exception e){
	            System.out.println("Choice of payment type must be number! Returning to user page...");
	    }
	    sc.close();
	}
	
    /**
     * Allows admin to remove an existing payment method
     */
	public void removePaymentMethod(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the payment method to remove");
		String paymentName=sc.nextLine();
		IPayment paymentMethod=paymentDB.find(paymentName);
		if(paymentMethod!=null){
			paymentDB.delete(paymentMethod);
		}else{
			System.out.println("Payment method not found! Returning to user page...");
		}
		sc.close();
	}
}
