import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class providing static methods to safely read different types of numeric input from the standard input.
 * This class handles common input errors such as non-integer inputs and numbers out of expected range,
 * ensuring that only valid data is returned. 
 * 
 */
public class GetOption {
    /**
     * Reads a positive integer from the standard input.
     * Repeatedly prompts the user until a valid positive integer is entered.
     *
     * @return A valid positive integer input by the user.
     */
    public static int getValidNumber() {
        Scanner sc = GlobalResource.SCANNER;
        while (true) {
            try {
                int number = sc.nextInt();
                if (number > 0) {
                    sc.nextLine();
                    return number;
                } else {
                    sc.nextLine();
                    System.out.println("Please input a positive number!");
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); 
                System.out.println("Please input a valid integer");
            } catch (Exception e){
                sc.nextLine();
                System.out.println("An error occured, please try again.");
            }
        }
    }
    /**
     * Reads an integer from the standard input that must be within the specified range (inclusive).
     * Repeatedly prompts the user until a valid integer within the specified range is included.
     *
     * @param max The maximum valid value that can be entered.
     * @return A valid integer input by the user between 1 and max, inclusive.
     */
	public static int getValidNumber(int max) {
        Scanner sc = GlobalResource.SCANNER;
        while (true) {
            try {
                int quantity = sc.nextInt();
                if (quantity > 0 && quantity <= max) {
                    sc.nextLine();
                    return quantity;
                } else {
                    sc.nextLine();
                    System.out.println("Please input a number between 1 and " + max + "!");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please input a valid integer");
            } catch (Exception e){
                sc.nextLine();
                System.out.println("An error occured, please try again.");
            }
        }
    }

    /**
     * Reads an integer from the standard input that must be either 0 or 1.
     * Repeatedly prompts the user until a 0 or 1 is entered.
     *
     * @return A binary number (0 or 1) input by the user.
     */
    public static int getBinaryNumber() {
        Scanner sc = GlobalResource.SCANNER;
        while (true) {
            try {
                int number = sc.nextInt();
                if (number == 0 || number == 1) {
                    sc.nextLine();
                    return number;
                } else {
                    sc.nextLine();
                    System.out.println("Please input a 1 or 0");
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); 
                System.out.println("Please input a valid integer");
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("An error occured, please try again.");
            }
        }
    }
}

