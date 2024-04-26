import java.util.Scanner;

/**
 * Provides a globally accessible {@link Scanner} instance that can be used throughout the application
 * This class simplifies the management of input resources by centralising the creation of a single {@code Scanner} object.
 * 
 * <p>Usage of a single shared {@code Scanner} across different parts of an application can help in
 * reducing the repetition of code and improve readability.
 * 
 */
public class GlobalResource {
    public static final Scanner SCANNER = new Scanner(System.in);
}