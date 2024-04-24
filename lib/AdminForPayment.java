import java.util.Scanner;
public class AdminForPayment extends Admin implements IAdminForPayment {
	private IDataManager<IPayment, String> paymentDB;
	public AdminForPayment(){
	    this.paymentDB=DataManagerForPayment.getInstance();
	}
	public void addPaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Select option");
	    System.out.println("1.Online");
	    System.out.println("2.Credit");
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
		    return;
	    }
	    paymentDB.add(paymentMode);
	}
	public void removePaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter the payment method to remove");
	    String paymentMethod=sc.nextLine();
	    IPayment paymentMethodClass=paymentDB.find(paymentMethod);
	    paymentDB.delete(paymentMethodClass);
	}
}
