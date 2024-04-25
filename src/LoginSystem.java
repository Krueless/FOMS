import java.util.Scanner;

/**
 * Manages the login process for staff in a system.
 * This class handles authentication and first-time login processes.
 */
public class LoginSystem {

	private IPasswordControl pwControl;
    /**
     * Constructs a LoginSystem with a specified password control mechanism.
     * 
     * @param pwControl An implementation of IPasswordControl for validating user credentials and managing passwords.
     */
	public LoginSystem(IPasswordControl pwControl) {
		this.pwControl = pwControl; 
	}

	/**
     * Attempts to login with provided staff ID and password.
     * If successful and it's the user's first login, prompts the user to change their password.
     * If unsuccessful, redirects to the login page.
     * 
     * @param staffID The staff ID as a String.
     * @param password The password associated with the staff ID as a String.
     */
	public void login(String staffID, String password) {
		Account acc = pwControl.validate(staffID, password);
		if (acc != null){
			System.out.println("Login Successful.");
			if (pwControl.checkFirstLogin(acc)){
				System.out.println("First login detected, please change your password.");
				promptPasswordChange(acc);
			} 
			acc.selectOptions();

		}else{
			System.out.println("The StaffID/password keyed in is incorrect.");
			System.out.println("Directing to login page.");
			showLoginPage();
		}
	}

    /**
     * Displays the login page and prompts the user to enter their login information.
     */
	public void showLoginPage() {
		Scanner sc = GlobalResource.SCANNER;
		System.out.println("Login Page");
		System.out.println("Enter StaffID: ");
		String staffID = sc.nextLine();
		System.out.println("Enter password: ");
		String pwd = sc.nextLine();
		login(staffID, pwd);
	}

	/**
     * Prompts the user to change their password. Continues prompting until the password meets requirements.
     * 
     * @param account The account for which the password needs to be changed.
     */
	public void promptPasswordChange(Account account) {
		Scanner sc = GlobalResource.SCANNER;
		System.out.println("New password must be at least 8 characters long and different from default password.");
		System.out.println("Enter new password: ");
		String pwd = sc.nextLine();
		if (pwControl.resetPassword(account, pwd)){
			System.out.println("Password changed.");
        }

		else{
            System.out.println("Password requirement not met. Please try again.");
			promptPasswordChange(account);
        }
	}
}
