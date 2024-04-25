import java.util.ArrayList;
import java.util.Scanner;

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
     * If the user enters invalid input, it catches the exception and prompts again.
     *
     * @param order The order for which payment is being processed.
     */
    public void showPaymentOptions(Order order) {
        displayFormatter.displayAll(paymentDB.getAll()); //TIDO error checking
        int choice = getValidNumber(order.getCartItems().size());
		choosePaymentOption(order, choice);
    }

    /**
     * Processes the payment for an order based on the user-selected payment method.
     * If the payment is successful, it prints a receipt; otherwise, it notifies the user of the failure.
     *
     * @param order The order for which payment is to be processed.
     * @param choice The index of the payment method chosen by the user.
     */
    public void choosePaymentOption(Order order, int choice) 
	{
        ArrayList<IPayment> paymentList = paymentDB.getAll();
		IPayment selectedPayment = paymentList.get(choice - 1);
		if (selectedPayment.processPayment(order)) 
		{
			selectedPayment.printReceipt(order);
		} 
		else 
		{
			System.out.println("Payment processing failed.");
		}
		
    }

	private int getValidNumber(int max)
	{
		System.out.println("Please input how many you want");
        Scanner sc = new Scanner(System.in);
        int quantity = -1;
		try {
            quantity = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Please input a valid integer");
            sc.close();
			return getValidNumber(max);
        }
		if (quantity<=0 || quantity > max)
		{
			System.out.println("Please input a valid number!");
            sc.close();
			return getValidNumber(max);
		}
        sc.close();
		return quantity;
	}

}