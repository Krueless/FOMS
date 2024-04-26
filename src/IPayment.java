import java.io.Serializable;

/**
 * The {@code IPayment} interface shows what the operations for processing payments are within the system.
 * It is designed to be implemented by any class that handles different types of payment methods,
 * ensuring that all payment processors offer consistent functionality.
 * Extends the {@code Serializable} interface to allow objects to be serialized for storage or transmission.
 */
public interface IPayment extends Serializable {

    /**
     * Processes the payment for the specified order.
     * Implementers are to complete all tasks necessary to finalize the payment, such as validating payment credentials,
     * charging the specified amount, and returning a success or failure status. 
     *
     * @param order The order for which payment is being processed, containing details such as items and total cost.
     * @return {@code true} if the payment is processed successfully, {@code false} otherwise.
     */
    boolean processPayment(Order order);

    /**
     * Generates and outputs a receipt for the specified order.
     * The receipt details all relevant transaction information, including itemized costs and the final amount paid.
     *
     * @param order The order for which the receipt is being printed, containing transaction details.
     */
    void printReceipt(Order order);

    /**
     * Retrieves the name of the payment method.
     * This method returns a string that uniquely identifies the type of payment.
     *
     * @return The name of the payment method.
     */
    String getName();

    /**
     * Sets the name of the payment method.
     * This name should be descriptive enough to clearly indicate the type of payment being used.
     *
     * @param name The new name for the payment method.
     */
    void setName(String name);
}
