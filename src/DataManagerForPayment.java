import java.util.ArrayList;
import java.io.*; 
import java.util.Scanner;

/**
 * The {@code DataManagerForPayment} class manages a list of {@code IPayment} objects.
 * This class uses the Singleton design pattern to ensure that only one instance of this manager exists throughout the application.
 * It provides methods to add, update, delete, and find payment methods in the list based on their names.
 */
public class DataManagerForPayment implements IDataManager<IPayment, String>
{

    private ArrayList<IPayment> paymentList; 
    private static DataManagerForPayment instance;
    private final Serializer<IPayment> serializer; 

    /**
     * Private constructor to prevent instantiation outside of this class.
     */
    private DataManagerForPayment() 
	{
		paymentList = new ArrayList<>();
	    serializer = new Serializer<IPayment>("data/paymentMethods.ser");
		loadData();
    }

    /**
     * Provides access to the singleton instance of the {@code DataManagerForPayment}.
     * If the instance does not exist, it is created.
     *
     * @return The singleton instance of {@code DataManagerForPayment}.
     */
    public static DataManagerForPayment getInstance() 
	{
        if (instance == null) 
		{
            instance = new DataManagerForPayment();
        }
        return instance;
    }

    public void saveData(){
        serializer.serialize(paymentList);
    }

	private void loadData(){
		try
		{
			paymentList = serializer.deserialize();
		}
		catch (IOException | ClassNotFoundException e)
		{
			System.out.println("Payment: Serialized file not found or invalid, initializing from CSV.");
			paymentList = new ArrayList<IPayment>();
			initializeFromCSV();
		}
	}

	private void initializeFromCSV() 
	{
	
		File f = new File("data/payment_list.csv");
		try{
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				String paymentType = data[0];
				String paymentName = data[1];
				switch(paymentType)
				{
					case "Credit":
					paymentList.add(new Credit(paymentName));
					break;
					case "Online":
					paymentList.add(new Online(paymentName));
					break;
				}
			}
			serializer.serialize(paymentList);
			System.out.println("Payment CSV data initialised.");
			sc.close();
		}catch (FileNotFoundException e){
			System.out.println("Error: Payment CSV File not found");
		}
       
    }

    /**
     * Updates an existing payment method in the payment list with new details.
     * If the payment method exists, it is replaced with the new payment object.
     *
     * @param newPayment The new payment object to replace the existing payment method.
     */
    public void update(IPayment newPayment) {
        for (int i = 0; i < paymentList.size(); i++) {
            if (paymentList.get(i).getName().equals(newPayment.getName())) {
                paymentList.set(i, newPayment);
				System.out.println("Payment method successfully updated!");
                break;
            }
        }
    }

    /**
     * Adds a new payment method to the payment list.
     * If a payment method with the same name already exists, it outputs a message and does not add the payment method.
     *
     * @param payment The new payment method to add to the list.
     */
    public void add(IPayment payment) {
        paymentList.add(payment);
        serializer.serialize(paymentList);
		System.out.println("Payment method successfully added!");
    }

    /**
     * Deletes a payment method from the payment list based on its name.
     * If the payment method exists, it is removed; otherwise, it prints a message indicating that the payment method was not found.
     *
     * @param payment The payment method to be deleted.
     */
    public void delete(IPayment payment) {
        for (int i = 0; i < paymentList.size(); i++) {
            if (paymentList.get(i).getName().equals(payment.getName())) {
                paymentList.remove(i);
				System.out.println("Payment method removed!");
                return;
            }
        }
        System.out.println("Payment method is not inside the list.");
    }

    /**
     * Finds and returns a payment method from the payment list based on its name.
     * If the payment method is found, it is returned; otherwise, it returns {@code null} indicating that the payment method was not found.
     *
     * @param paymentName The name of the payment method to find.
     * @return The found {@code IPayment} object, or {@code null} if not found.
     */
    public IPayment find(String paymentName) {
        for (IPayment payment: paymentList) {
            if (payment.getName().equals(paymentName)) {
                return payment;
            }
        }
        return null;
    }

    public ArrayList<IPayment> getAll(){
        return this.paymentList;
    }

}