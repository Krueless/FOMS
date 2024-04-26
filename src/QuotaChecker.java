public class QuotaChecker {
    public static Boolean checkQuotaForAddManager(int numStaff, int numManager){
        if (numStaff >= 1 && numStaff <= 4){
            return (numManager == 1);
        }
        else if (numStaff >= 5 && numStaff <= 8){
            return (numManager <= 2);
        }
        else if (numStaff >= 9 && numStaff <= 15) {
            return (numManager <= 3);
        }
        else {
            return false;
        }
    }

    public static Boolean checkQuotaForRemoveManager(int numStaff, int numManager){
        if (numStaff >= 1 && numStaff <= 4){
            return (numManager >= 1);
        }
        else if (numStaff >= 5 && numStaff <= 8){
            return (numManager >= 2);
        }
        else if (numStaff >= 9 && numStaff <= 15) {
            return (numManager >= 3);
        }
        else {
            return false;
        }
    }

    public static Boolean checkQuotaForAddStaff(int numStaff, int numManager){
        if (numManager == 1){
            return (numStaff <= 4);
        }
        else if (numManager == 2){
            return (numStaff <= 8);
        }
        else if (numManager == 3){
            return (numStaff <= 15);
        }
        else{
            return false;
        }
    }

    public static Boolean checkQuotaForRemoveStaff(int numStaff, int numManager){
        if (numManager == 1){
            return (numStaff >= 1);
        }
        else if (numManager == 2){
            return (numStaff >= 5);
        }
        else if (numManager == 3){
            return (numStaff >= 9);
        }
        else{
            return false;
        }
    }
}
