import java.util.ArrayList;

/**
 * Handles the user interface for payment processing. It allows users to select a payment method,
 * processes the payment, and prints a receipt if the payment is successful.
 */
public class PaymentUI {
    private IDataManager<IPayment,String> paymentDB;          
    private IDisplay displayFormatter;                   
    /**
     * Constructs a PaymentUI with necessary data management and display components.
     *
     * @param paymentDB The data manager for accessing payment methods.
     * @param displayFormatter The display component for formatting the display of payment options.
     */
    public PaymentUI(IDataManager<IPayment,String> paymentDB, IDisplay displayFormatter) {
        this.paymentDB = paymentDB;
        this.displayFormatter = displayFormatter;
    }

    /**
     * Displays all available payment options and prompts the user to choose one.
     * 
     */
    public void showPaymentOptions() {
        displayFormatter.displayAll(paymentDB.getAll());
        System.out.println("Please choose which payment option you want.");
    }

    /**
     * Processes the payment for an order based on the user-selected payment method.
     * If the user enters invalid input, it catches the exception and prompts again.
     * If the payment is successful, it prints a receipt; otherwise, it notifies the user of the failure.
     *
     * @param order The order for which payment is to be processed.
     */
    public Boolean choosePaymentOption(Order order) 
	{
        showPaymentOptions();
        int choice = GetOption.getValidNumber(paymentDB.getAll().size());
        ArrayList<IPayment> paymentList = paymentDB.getAll();
		IPayment selectedPayment = paymentList.get(choice - 1);
		if (selectedPayment.processPayment(order)) 
		{
            System.out.println("Payment Successful.");
			selectedPayment.printReceipt(order);
            return true;
		} 
		else 
		{
			System.out.println("Payment processing failed.");
            return false;
		}
		
    }

}