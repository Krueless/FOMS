public class QuotaChecker {
    public static Boolean checkQuota(int numStaff, int numManager){
        if(numStaff >= 1 && numStaff <= 4){
            if(numManager == 1){
                return true;
            }
        }else if(numStaff >= 5 && numStaff <= 8){
            if(numManager == 2){
                return true;
            }
        }else if(numStaff >= 9 && numStaff <= 15){
            if(numManager == 3){
                return true;
            }
        }
        return false;
    }
}
