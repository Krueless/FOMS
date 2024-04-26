import java.io.Serializable;

/**
 * The {@code IAdminForPayment} interface defines the administrative operations needed for managing payment methods within a system.
 */
public interface IAdminForPayment extends Serializable {

    /**
     * Adds a new payment method to the system.
	 * 
     */
    void addPaymentMethod();

    /**
     * Removes an existing payment method from the system.
     *
     */
    void removePaymentMethod();

}
