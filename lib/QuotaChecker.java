public class QuotaChecker {
  protected Admin admin;
  protected IDataManager counter;
  public bool checkQuota(String branchName){
    int numStaff=counter.countStaffInBranch(branchName);
    int numManager=counter.countManagerInBranch(branchName);
}
