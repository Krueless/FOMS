import java.io.Serializable;

public interface IAdminForStaff extends Serializable{

	public abstract void editStaff();

	public abstract void removeStaff();

	public abstract void addStaff();

	public abstract void displayStaff();

	public abstract void assignManager();

	public abstract void promoteStaff();

	public abstract void transferStaff();

}
