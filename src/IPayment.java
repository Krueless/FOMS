import java.io.Serializable;
/**
 * The {@code IPayment} interface defines the contract for payment classes within the system.
 * It specifies the methods that must be implemented by any class that handles payment processing.
 */
public interface IPayment extends Serializable {

    /**
     * Processes the payment for a transaction.
     * This method should include all logic necessary to perform the transaction, such as
     * verifying payment details and confirming the transaction success.
     *
     * @return {@code true} if the payment is processed successfully, {@code false} otherwise.
     */
    boolean processPayment(Order order);

    /**
     * Prints a receipt for the transaction.
     * This method should output a receipt that includes details of the transaction,
     * such as items purchased and total cost.
     */
    void printReceipt(Order order);

    String getName();

    public void setName(String name);
}