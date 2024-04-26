/**
 * The QuotaChecker class provides static methods to check staffing and management quotas
 * based on provided staff and manager counts. It helps ensure that staffing levels are
 * maintained within specified limits.
 */
public class QuotaChecker {

    /**
     * Checks if the number of managers is appropriate for the given number of staff when adding a manager.
     *
     * @param numStaff The current number of staff.
     * @param numManager The current number of managers.
     * @return {@code true} if the number of managers is within the allowed limit for the given staff count,
     *         {@code false} otherwise.
     */
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

    /**
     * Checks if the number of managers is sufficient for the given number of staff when removing a manager.
     *
     * @param numStaff The current number of staff.
     * @param numManager The current number of managers.
     * @return {@code true} if removing a manager would still meet the minimum required number of managers,
     *         {@code false} otherwise.
     */
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

    /**
     * Checks if adding more staff is allowed under the current number of managers.
     *
     * @param numStaff The current number of staff.
     * @param numManager The current number of managers.
     * @return {@code true} if additional staff can be added without exceeding the quota for the given number of managers,
     *         {@code false} otherwise.
     */
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

    /**
     * Checks if staff can be removed without falling below the minimum staff count required for the current number of managers.
     *
     * @param numStaff The current number of staff.
     * @param numManager The current number of managers.
     * @return {@code true} if staff can be removed while still meeting the minimum staff requirements,
     *         {@code false} otherwise.
     */
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