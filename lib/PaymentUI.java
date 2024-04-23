package OOP_Project_Classes;

import display_classes.IDisplay;

public class PaymentUI 
{

	private IDataManager paymentDB;
	private IDisplay displayFormatter;
	
	public PaymentUI(IDataManager paymentDB,IDisplay displayFormatter)
	{
		this.paymentDB = paymentDB;
		this.displayFormatter = displayFormatter;
	}

	public void showPaymentOptions(Order order)
	{
		displayFormatter.displayAll(paymentDB.getAll());
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		choosePaymentOption(order,choice);	
	}

	public void choosePaymentOption(Order order, int choice) 
	{
		ArrayList<IPayment> paymentList = paymentDB.getAll()

		if (paymentList.get(choice-1).processPayment(order))
		{
			paymentList.get(choice-1).printReceipt(order);
		}
	}
}