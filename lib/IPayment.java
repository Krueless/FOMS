import java.io.Serializable;
/**
 * The {@code IPayment} interface defines the contract for payment classes within the system.
 * It specifies the methods that must be implemented by any class that handles payment processing.
 */
public interface IPayment implements Serializable {

    /**
     * Processes the payment for a transaction.
     * This method should include all logic necessary to perform the transaction, such as
     * verifying payment details and confirming the transaction success.
     *
     * @return {@code true} if the payment is processed successfully, {@code false} otherwise.
     */
    boolean processPayment();

    /**
     * Prints a receipt for the transaction.
     * This method should output a receipt that includes details of the transaction,
     * such as items purchased and total cost.
     */
    void printReceipt();
}