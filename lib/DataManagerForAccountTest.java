public class DataManagerForAccountTest {

    public static void main(String[] args) {
        // Initialize DataManagerForAccount instance
        DataManagerForAccount dataManager = DataManagerForAccount.getInstance();
        // Run tests
        testAddAccount(dataManager);
        testFindAccount(dataManager);
        testDeleteAccount(dataManager);
    }

    private static void testAddAccount(DataManagerForAccount dataManager) {
        System.out.println("Running testAddAccount...");

        // Create a new account
        Staff newAccount = new Staff("Walter","Wlt2","M", "F",33,"NTU", DataManagerForOrder.getInstance(), new DisplayFilteredByBranch());
        dataManager.add(newAccount);

        // Verify the account was added
        Account result = dataManager.find("ffff");
        if (result != null && result.getStaffID().equals("ffff")) {
            System.out.println("testAddAccount PASSED.");
        } else {
            System.out.println("testAddAccount FAILED.");
        }
    }

    private static void testFindAccount(DataManagerForAccount dataManager) {
        System.out.println("Running testFindAccount...");

        // Try to find an account
        Account result = dataManager.find("TomC");

        // Verify the account is found
        if (result != null && result.getName().equals("Tom Chan")) {
            System.out.println("testFindAccount PASSED.");
        } else {
            System.out.println("testFindAccount FAILED.");
        }
    }

    private static void testDeleteAccount(DataManagerForAccount dataManager) {
        System.out.println("Running testDeleteAccount...");

        // Find and delete the account
        Account accountToDelete = dataManager.find("ffff");
        if (accountToDelete != null) {
            dataManager.delete(accountToDelete);
            // Verify the account was deleted
            Account result = dataManager.find("001");
            if (result == null) {
                System.out.println("testDeleteAccount PASSED.");
            } else {
                System.out.println("testDeleteAccount FAILED.");
            }
        } else {
            System.out.println("Account to delete not found, testDeleteAccount FAILED.");
        }
    }
}
