import java.util.Scanner;

public class LoginSystem {

	private IPasswordControl pwControl;

	/**
	 * 
	 * @param pwControl
	 */
	public LoginSystem(IPasswordControl pwControl) {
		this.pwControl = pwControl; 
	}

	/**
	 * 
	 * @param staffID
	 * @param password
	 */
	public void login(String staffID, String password) {
		Account acc = pwControl.validate(staffID, password);
		if (acc != null){
			System.out.println("Login Successful.");
			if (pwControl.checkFirstLogin(acc)){
				System.out.println("First login detected, please change your password.");
				promptPasswordChange(acc);
			} 
			acc.showOptions();

		}else{
			System.out.println("The StaffID/password keyed in is incorrect.");
			System.out.println("Directing to login page.");
			showLoginPage();
		}
		throw new UnsupportedOperationException();
	}

	public void showLoginPage() {
		// TODO - implement LoginSystem.showLoginPage
		Scanner sc = new Scanner(System.in);
		System.out.println("Login Page");
		System.out.println("Enter StaffID: ");
		String staffID = sc.nextLine();
		System.out.println("Enter password: ");
		String pwd = sc.nextLine();
		sc.close();
		login(staffID, pwd);
	}

	/**
	 * 
	 * @param account
	 */
	public void promptPasswordChange(Account account) {
		// TODO - implement LoginSystem.promptPasswordChange
		Scanner sc = new Scanner(System.in);
		System.out.println("New password should be at least 8 characters long.");
		System.out.println("Enter new password: ");
		String pwd = sc.nextLine();
		sc.close();
		if (pwControl.resetPassword(account, pwd))
			System.out.println("Password changed.");
		else
			System.out.println("Password requirement not met. Please try again.");
			promptPasswordChange(account);
	}

}
