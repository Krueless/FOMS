import java.util.InputMismatchException;
import java.util.Scanner;

public class GetOption {
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

