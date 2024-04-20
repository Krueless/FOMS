import java.util.ArrayList;

public class Display implements IDisplay {

	/**
	 * 
	 * @param data
	 */
	public void displayAll(Object data) {
		if (data instanceof ArrayList){
            @SuppressWarnings("unchecked")
            ArrayList<Object> array = (ArrayList<Object>) data;
            for (int i = 0; i < array.size(); i++){
            System.out.println(i+1 +": " + array.get(i).toString());
        }
        }
	}

}