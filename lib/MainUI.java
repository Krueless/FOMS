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
        Scanner sc = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Fastfood Ordering and Management System (FOMS)");
                System.out.println("Please select one of the following options.");
                System.out.println("1. Customer");
                System.out.println("2. Sign in as Staff");
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        chooseOption(1);
                        valid = true;
                        break;
                    case "2":
                        chooseOption(2);
                        valid = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        sc.close();
    }

    /**
     * Processes the user's choice from the main menu.
     * @param choice integer representing the user's role selection.
     */
    public void chooseOption(int choice) {
        // TODO - implement MainUI.chooseOption
        // This method should be implemented to handle different user choices.
		if (choice == 1) {
			DataManagerForBranch branchDB = DataManagerForBranch.getInstance();
			Display displayFormatter = new Display();
			BranchUI branchUI = new BranchUI(branchDB, displayFormatter);
            branchUI.showBranches();
		}
		else{
			PasswordControl pwControl = new PasswordControl(DataManagerForAccount.getInstance());
			LoginSystem loginSystem = new LoginSystem(pwControl);
            loginSystem.showLoginPage();
		}
       
    }

    /**
     * Main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
        mainUI.showOptions();
    }
}
