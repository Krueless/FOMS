public class QuotaChecker {
    public static Boolean checkQuotaForManager(int numStaff, int numManager){
        if (numStaff == 0){
            return false;
        }
        else if (numStaff >= 1 && numStaff <= 4){
            return (numManager == 1);
        }
        else if (numStaff >= 5 && numStaff <= 8){
            return (numManager <= 2);
        }
        else {
            return (numManager <= 3);
        }
    }

    public static Boolean checkQuotaForStaff(int numStaff, int numManager){
        if (numManager == 0){
            return false;
        }
        else if (numManager == 1){
            return (numStaff <= 4);
        }
        else if (numManager == 2){
            return (numStaff <= 8);
        }
        else{
            return (numStaff <= 15);
        }
    }
}
