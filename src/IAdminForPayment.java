import java.io.Serializable;

public interface IAdminForPayment extends Serializable {

	public abstract void addPaymentMethod();

	public abstract void removePaymentMethod();

}
