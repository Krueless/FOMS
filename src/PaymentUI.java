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
     * 
     */
    public void showPaymentOptions() {
        displayFormatter.displayAll(paymentDB.getAll()); //TIDO error checking
        System.out.println("Please choose which payment option you want.");
    }

    /**
     * Processes the payment for an order based on the user-selected payment method.
     * If the user enters invalid input, it catches the exception and prompts again.
     * If the payment is successful, it prints a receipt; otherwise, it notifies the user of the failure.
     *
     * @param order The order for which payment is to be processed.
     * @param choice The index of the payment method chosen by the user.
     */
    public Boolean choosePaymentOption(Order order) 
	{
        showPaymentOptions();
        int choice = getValidNumber(paymentDB.getAll().size());
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

	private int getValidNumber(int max) {
        Scanner sc = GlobalResource.SCANNER;
        while (true) {
            try {
                int quantity = sc.nextInt();
                if (quantity > 0 && quantity <= max) {
                    return quantity;
                } else {
                    sc.nextLine();
                    System.out.println("Please input a number between 1 and " + max + "!");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please input a valid integer");
            }
        }
    }

}