import java.io.Serializable;

/**
 * The {@code IDisplay} interface defines a standard method for displaying data within the system.
 * 
 */
public interface IDisplay extends Serializable {

    /**
     * Displays the provided data in a format suitable for the specific implementation.
     *
     * @param data The data to be displayed. This could be any object, from simple data types to complex custom objects.
     */
    void displayAll(Object data);

}