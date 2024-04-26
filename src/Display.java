import java.util.ArrayList;

/**
 * Implements {@link IDisplay} to provide a basic display functionality that can handle various types of data encapsulated in lists.
 * This class is designed to uniformly display any list of items.
 */
public class Display implements IDisplay {
    /**
     * Displays all elements in a list by calling each element's {@code toString()} method.
     * The method checks if the provided data is an instance of {@code ArrayList} and iterates over each element to display it.
     * Each item is prefixed with its position in the list to enhance readability and order.
     * If the list is empty, a message "No records found!" is displayed to indicate the absence of data.
     *
     * @param data The data to be displayed, expected to be an {@code ArrayList} of objects. The method will cast the data
     *             to an {@code ArrayList} of {@code Object} under the assumption that it is a valid instance of {@code ArrayList}.
     */
	public void displayAll(Object data) {
		if (data instanceof ArrayList){
            @SuppressWarnings("unchecked")
            ArrayList<Object> array = (ArrayList<Object>) data;
            for (int i = 0; i < array.size(); i++){
                System.out.println(i+1 +") " + array.get(i).toString());
            }
            if (array.size() == 0){
                System.out.println("No records found!");     
            }
        }
	}

}