public class BranchForDisplay extends Branch {
    private int staffQuota;
	private String branchName;
	private String location;
    
    public BranchForDisplay(String branchName, String location, int staffQuota){
        super(branchName, location, staffQuota);
    }

    public String toString(){
        String out;
        out = "Branch Name: " + branchName + ", Location: " + location;
        return out;
    }
}
