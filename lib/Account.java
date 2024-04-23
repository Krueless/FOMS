import java.io.Serializable;
public abstract class Account implements Serializable {

	protected String staffID;
	protected String password = "password";
	protected String role;
	protected String gender;
	protected int age;
	protected String name;

	public abstract void showOptions();

	/**
	 * 
	 * @param choice
	 */
	public abstract void selectOptions(int choice);

	/**
	 * 
	 * @param password
	 */
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	public String getStaffID() {
		return this.staffID;
	}

	/**
	 * 
	 * @param staffID
	 */
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getRole() {
		return this.role;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param newPassword
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

}
