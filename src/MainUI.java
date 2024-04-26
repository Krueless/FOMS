import java.util.Scanner;

/**
 * MainUI class handles the user interface for Fastfood Ordering and Management System (FOMS).
 * It provides options for the user to enter the system as a customer or staff.
 */
public class MainUI {

    /**
     * Displays options to the user and handles user input to navigate system as customer or staff.
     */
    public void showOptions() {
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Fastfood Ordering and Management System (FOMS)");
        System.out.println("Please select one of the following options.");
        System.out.println("(1) Customer");
        System.out.println("(2) Sign in as Staff");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
    }

    /**
     * Processes the user's choice from the main menu.
     */
    public void chooseOption() {
        Scanner sc = GlobalResource.SCANNER;
        boolean valid = false;
        while (!valid) {
            showOptions();
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    DataManagerForBranch branchDB = DataManagerForBranch.getInstance();
                    Display displayFormatter = new Display();
                    new BranchUI(branchDB, displayFormatter);
                    valid = true;
                    break;
                case "2":
                    PasswordControl pwControl = new PasswordControl(DataManagerForAccount.getInstance());
                    LoginSystem loginSystem = new LoginSystem(pwControl);
                    loginSystem.showLoginPage();
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    break;
            }
        }
    }

    /**
     * Main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Application shutting down. Saving data...");
            DataManagerForAccount.getInstance().saveData();
            DataManagerForBranch.getInstance().saveData();
            DataManagerForFoodItem.getInstance().saveData();
            DataManagerForOrder.getInstance().saveData();
            DataManagerForPayment.getInstance().saveData();
        }));
        try{
            MainUI mainUI = new MainUI();
            mainUI.chooseOption();
        }
        finally {
            GlobalResource.SCANNER.close();
        }
    }
}
