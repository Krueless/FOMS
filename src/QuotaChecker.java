public class QuotaChecker {
    public static Boolean checkQuotaForAddManager(int numStaff, int numManager){
        if (numStaff >= 0 && numStaff <= 4){
            return (numManager == 1);
        }
        else if (numStaff == 5){
            return (numManager == 1 || numManager == 2);
        }
        else if (numStaff >= 5 && numStaff <= 8){
            return (numManager == 2);
        }
        else if (numStaff == 9){
            return (numManager == 2 || numManager == 3);
        }
        else if (numStaff >= 9 && numStaff <= 15) {
            return (numManager == 3);
        }
        else {
            return false;
        }
    }

    public static Boolean checkQuotaForRemoveManager(int numStaff, int numManager){
        if (numStaff >= 0 && numStaff <= 4){
            return (numManager == 1);
        }
        else if (numStaff == 5){
            return (numManager == 1 || numManager == 2);
        }
        else if (numStaff >= 5 && numStaff <= 8){
            return (numManager == 2);
        }
        else if (numStaff == 9){
            return (numManager == 2 || numManager == 3);
        }
        else if (numStaff >= 9 && numStaff <= 15) {
            return (numManager == 3);
        }
        else {
            return false;
        }
    }

    public static Boolean checkQuotaForAddStaff(int numStaff, int numManager){
        if (numManager == 1){
            return (numStaff <= 5);
        }
        else if (numManager == 2){
            return (numStaff <= 9);
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
            return (numStaff >= 0);
        }
        else if (numManager == 2){
            return (numStaff >= 4);
        }
        else if (numManager == 3){
            return (numStaff >= 8);
        }
        else{
            return false;
        }
    }
}
