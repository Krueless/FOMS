public class AdminForPayment extends Admin implements IAdminForPayment {
	private IDataManager paymentDB;
	private IDisplay displayFormatter;
	public AdminForPayment(IDataManager paymentDB,IDisplay displayFormatter){
	    this.paymentDB=paymentDB;
	    this.displayFormatter=displayFormatter;
	}
	public void addPaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter the new payment method");
	    // to be continued: how to create a new payment class
	    paymentDB.add()
	}
	public void removePaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter the payment method to remove");
	    String paymentMethod=sc.nextLine();
	    IPayment paymentMethodClass=paymentDB.find(paymentMethod);
	    paymentDB.delete(paymentMethodClass);
	}
}
