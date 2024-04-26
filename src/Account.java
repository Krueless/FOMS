import java.io.Serializable;

/**
 * This abstract class represents an account and implements the Serializable interface.
 * It defines the basic properties and functionalities that all account types have.
 */
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;
	protected String staffID;
	protected String password = "password";
	protected String role;
	protected String gender;
	protected int age;
	protected String name;

     /**
     * Displays the options available for the account.
     */
	public abstract void showOptions();

	 /**
     * Handles the selection of options by the user.
     */
	public abstract void selectOptions();

	/**
     * Validates the password entered by the user against the account's password.
     * @param password The password to validate.
     * @return true if the password matches the account's password, false otherwise.
     */
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

    /**
     * Retrieves the staff ID associated with the account.
     * @return The staff ID.
     */
	public String getStaffID() {
		return this.staffID;
	}

	/**
     * Sets the staff ID for the account.
     * @param staffID The new staff ID.
     */
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

    /**
     * Retrieves the role associated with the account.
     * @return The role.
     */
	public String getRole() {
		return this.role;
	}

	/**
     * Sets the role for the account.
     * @param role The new role.
     */
	public void setRole(String role) {
		this.role = role;
	}

    /**
     * Retrieves the gender of the account holder.
     * @return The gender.
     */
	public String getGender() {
		return this.gender;
	}

	/**
     * Sets the gender of the account holder.
     * @param gender The new gender.
     */
	public void setGender(String gender) {
		this.gender = gender;
	}

    /**
     * Retrieves the age of the account holder.
     * @return The age.
     */
	public int getAge() {
		return this.age;
	}

	/**
     * Sets the age of the account holder.
     * @param age The new age.
     */
	public void setAge(int age) {
		this.age = age;
	}

    /**
     * Retrieves the name of the account holder.
     * @return The name.
     */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the age of the account holder.
	 * @param name The new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Retrieves the password of the account holder.
     * @return The password.
     */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the new password of the account holder.
	 * @param newPassword The new password.
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

    /**
     * Provides a string representation of the account, including all attributes.
     * If the account is of type Staff, it includes the branch information.
     * @return A string representation of the account.
     */
	public String toString(){
		String out = "Staff ID: " + staffID + ", Role: " + role + ", Gender: " + gender + ", Age: " + age + ", Name: " + name;
		if (this instanceof Staff){
			Staff staffAccount = (Staff) this;
			out += ", Branch: " + staffAccount.getBranchName();
		}
		return out;
	}

}
