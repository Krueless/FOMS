import java.io.Serializable;

/**
 * The {@code IGetBranchName} interface defines the method for retrieving the name of a branch.
 * <p>This interface extends the {@code Serializable} interface, ensuring that implementing classes can be serialized.
 */
public interface IGetBranchName extends Serializable{

    /**
     * Retrieves the name of the branch.
     * 
     * @return A {@code String} representing the name of the branch.
     */
    public String getBranchName();

}