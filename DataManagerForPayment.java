package OOP_Project_Classes;
public class DataManagerForPayment implements IDataManager {

	private ArrayList<IPayment> paymentList;
	private static DataManagerForPayment instance;

	private DataManagerForPayment() {
	}

	public static DataManagerForPayment getInstance() 
	{
		if (instance == null) 
		{
			instance = new DataManagerForPayment;
		}
		return instance;
	}

	public void update(IPayment newPayment) 
	{
		for(int i = 0; i < paymentList.size(); i++)
		{
			if (paymentList.get(i).getName().equals(newPayment.getName()))
			{
				paymentList.set(i, newPayment);
				return;
			}
		}
	}

	public void add(IPayment payment) 
	{
		for(int i = 0; i < paymentList.size(); i++)
		{
			if (paymentList.get(i).getName().equals(payment.getName()))
			{
				System.out.println("Payment is already inside.");
				return;
			}
		}
		paymentList.add(payment);
		return;
	}

	public void delete(IPayment payment) 
	{
		for(int i = 0; i < paymentList.size(); i++)
		{
			if (paymentList.get(i).getName().equals(payment.getName()))
			{
				paymentList.remove(i);
				return;
			}
		}
		System.out.println("Payment method is not inside the list");
		return;
	}

	public IPayment find(String paymentName) 
	{
		for(int i = 0; i < paymentList.size(); i++)
		{
			if (paymentList.get(i).getName().equals(paymentName))
			{
				return paymentList.get(i);
			}
		}
	}

}