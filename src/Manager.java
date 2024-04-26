import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a Manager in the system.
 * Inherits from the Staff class which extends the Account class and implements the IGetBranchName interface.
 */
public class Manager extends Staff{
    private IDataManager<FoodItem, Integer> foodItemDB;
    private IDataManager<Account, String> accountDB;
    private ArrayList<FoodItem> foodItemList;
    private ArrayList<IGetBranchName> menuList;

    public Manager(String name,String staffID,String role,String gender,int age,String branchName, IDataManager<Order, Integer> orderDB, IDisplayFilteredByBranch displayFormatter, IDataManager<FoodItem,Integer> foodItemDB, IDataManager<Account, String> accountDB){
        super(name,staffID,role,gender,age,branchName,orderDB,displayFormatter);
        this.foodItemDB=foodItemDB;
        this.accountDB=accountDB;

        foodItemList = foodItemDB.getAll();
        menuList = new ArrayList<IGetBranchName>();

		for (FoodItem item: foodItemList){
		    menuList.add((IGetBranchName)item);
        }
    }

    private void displayMenu(){
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println(branchName + " Menu");
        displayFormatter.displayFilteredByBranch(menuList, branchName);
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
    }
   /**
     * Allows Manager to display the staff list in the branch supervised by the manager. 
     */
    public void displayStaff(){
        String branchName=super.getBranchName();
        ArrayList<Account> accountList = accountDB.getAll();
        ArrayList<IGetBranchName> branchNameList = new ArrayList<>(accountList.size());
        for (Account item : accountList) {
            if (item instanceof Staff){
                branchNameList.add((IGetBranchName)item);
            }
        }
        displayFormatter.displayFilteredByBranch(branchNameList,branchName);
    }

   /**
     * Allows Manager to add a food item to menu of its branch
     */
    public void addItem(){
        //get details of food item
        displayMenu();
        Scanner sc=GlobalResource.SCANNER;
        int FoodID = menuList.size() + 1;
        boolean duplicate = true;
        do{
            System.out.println("Enter the name of the new food item: ");
            String name=sc.nextLine();
    
            for (FoodItem item: foodItemList){
                if (item.getBranchName().equals(branchName) && item.getName().equals(name)){
                    duplicate = false;
                    System.out.println("New food item must be different from existing. Try again!");
                    break;
                }
            }
        } while (duplicate);
        
        String itemCategory = "NA", choice;
        do{
            System.out.println("Select the food category of new item");
            System.out.println("(1) Set meal");
            System.out.println("(2) Burger");
            System.out.println("(3) Sides");
            System.out.println("(4) Drink");
            choice = sc.nextLine();

            switch (choice){
                case "1":
                    itemCategory  = "Set meal";
                    break;
                case "2":
                    itemCategory  = "Burger";
                    break;
                case "3":
                    itemCategory  = "Sides";
                    break;
                case "4":
                    itemCategory  = "Drink";
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }while (itemCategory.equals("NA"));
        
        double price = 0;
        do{
            try {
                System.out.println("Enter the price of the food item");
                price = sc.nextDouble();
                sc.nextLine();
                if (price <= 0) {
                    System.out.println("Please input a positive number!");
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); 
                System.out.println("Please input a valid numeric!");
            }
        }while (price <= 0);
        
        //construct new food item
        FoodItem fooditem=new FoodItem(FoodID,name,price,super.getBranchName(),itemCategory);
        //add the new food item to menu
        foodItemDB.add(fooditem);
    }

   /**
     * Allows Manager to edit a food item in the menu of its branch
     */
    public void editItem(){
        //get the foodID of the food item to edit
        Scanner sc = GlobalResource.SCANNER;
        displayMenu();
        System.out.println("Enter the FoodID");
        int foodID=sc.nextInt();
        //search for the fooditem in foodItemDB
        FoodItem foodItem=foodItemDB.find(foodID);
        if(foodItem!=null){
            if(foodItem.getBranchName().equals(branchName)==false){
                System.out.println("FoodItem not in menu of this branch! Returning to user page...");
                sc.close();
                return;
            }
            //foodItem found
            //select the attribute to edit
            int choice;
            do{
                System.out.println("Select the attribute to edit:");
                System.out.println("1. Availability of FoodItem");
                System.out.println("2. Price of FoodItem");
                System.out.println("3. Exit");
                choice=sc.nextInt();
                switch(choice){
                    case 1:
                    System.out.println("Select the availability of the food item");
                    System.out.println("1. Available");
                    System.out.println("2. Not Available");
                    int availability_choice = sc.nextInt();
                    switch (availability_choice){
                        case 1:
                        foodItem.setAvailability(true);
                        break;
                        case 2:
                        foodItem.setAvailability(false);
                        break;
                        default:
                        System.out.println("Invalid option");
                    }
                    break;
                    case 2:
                    System.out.println("Enter the new price of the food item");
                    double price=sc.nextDouble();
                    foodItem.setPrice(price);
                    break;
                }
            }while(choice != 3);
            foodItemDB.update(foodItem);
        }else{
            System.out.println("Food item not found! Returning to user page...");
        }
    }
	
    /**
     * Allows Manager to remove an item from menu of the branch it is in charge of
     */
    public void removeItem(){
        Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter the FoodID");
        int foodID=sc.nextInt();
        //search for the fooditem with the corresponding FoodID
        FoodItem foodItem=foodItemDB.find(foodID);
        if(foodItem!=null){
            if(foodItem.getBranchName().equals(super.getBranchName())==false){
                System.out.println("foodItem not in menu of this branch! Returning to user page...");
            }
            //foodItem found
            //delete foodItem
            else{
                foodItemDB.delete(foodItem);
            }
        }else{
            System.out.println("Food item does not exist! Returning to user page...");
        }
    }

     /**
     * Allows the Manager to select options from menu, and handles user input
     */
	public void selectOptions(){
        Scanner sc = GlobalResource.SCANNER;
        boolean quit = false;

        while(!quit){
            showOptions();
            String option=sc.nextLine();
            
            switch(option){
                case "1":
                    displayStaff();
                    break;
                case "2":
                    addItem();
                    break;
                case "3":
                    editItem();
                    break;
                case "4":
                    removeItem();
                    break;
                case "5":
                    displayNewOrders();
                    break;
                case "6":
                    viewOrder();
                    break;
                case "7":
                    processOrder();
                    break;
                case "8":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
                    break;
            }
        }
        System.out.println("Log out successfully.");
    }
    /**
     * Prints the available options that the Manager can do
     */
    public void showOptions(){
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Manager Page");
        System.out.println("Please select one of the following options");
        System.out.println("(1) Display staff in branch");
        System.out.println("(2) Add item to menu");
        System.out.println("(3) Edit item in menu");
        System.out.println("(4) Remove item from menu");
        System.out.println("(5) Display new orders");
        System.out.println("(6) View order");
        System.out.println("(7) Process order");
        System.out.println("(8) Log out");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
    }
}
