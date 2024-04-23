public class QuotaChecker {
  protected IDataManagerWithCount counter;
  public QuotaChecker(){
    this.counter=DataManagerForAccount.getInstance();
  }
  public bool checkStaffQuota(String branchName){
    int numStaff=counter.countStaffInBranch(branchName);
    IDataManager branchDB=DataManagerForBranch.getInstance();
    Branch branch=branchDB.find(branchName);
    if(numStaff<branch.getStaffQuota()){
      return true;
    }
    return false;
  }
  public bool checkManagerQuota(String branchName){
    int numStaff=counter.countStaffInBranch(branchName);
    int numManager=counter.countManagerInBranch(branchName);
    if(numStaff>=1 && numStaff<=4){
      if(numManager<1){
        return true;
      }
    }else if(numStaff>=5 && numStaff<=8){
      if(numManager<2){
        return true;
      }
    }else if(numStaff>=9 && numStaff<=15){
      if(numManager<3){
        return true;
      }
    }
    return false;
}
