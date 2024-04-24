import java.util.ArrayList;
import java.util.Scanner;
public class Manager extends Staff{
    private IDataManager<FoodItem, Integer> foodItemDB;
    private IDataManager<Account, String> accountDB;
    private IDisplayFilteredByBranch displayFormatter;
    public Manager(String name,String staffID,String role,String gender,int age,String branchName, IDataManager<Order, Integer> orderDB, IDisplayFilteredByBranch displayFormatter, IDataManager<FoodItem,Integer> foodItemDB, IDataManager<Account, String> accountDB){
        super(name,staffID,role,gender,age,branchName,orderDB,displayFormatter);
        this.foodItemDB=foodItemDB;
        this.accountDB=accountDB;
    }
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
    public void addItem(){
        //get details of food item
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the FoodID");
        int FoodID=sc.nextInt();
        System.out.println("Enter the name of the food item");
        String name=sc.nextLine();
        System.out.println("Enter the category of the food item");
        String itemCategory=sc.nextLine();
        System.out.println("Enter the price of the food item");
        double price=sc.nextDouble();
        //construct new food item
        FoodItem fooditem=new FoodItem(FoodID,name,price,super.getBranchName(),itemCategory);
        //add the new food item to menu
        foodItemDB.add(fooditem);
        sc.close();
    }
    public void editItem(){
        //get the foodID of the food item to edit
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the FoodID");
        int foodID=sc.nextInt();
        //search for the fooditem in foodItemDB
        FoodItem foodItem=foodItemDB.find(foodID);
        if(foodItem.getBranchName().equals(super.getBranchName())==false){
            System.out.println("FoodItem not in menu of this branch");
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
    }
    public void removeItem(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the FoodID");
        int foodID=sc.nextInt();
        //search for the fooditem with the corresponding FoodID
        FoodItem foodItem=foodItemDB.find(foodID);
        if(foodItem.getBranchName().equals(super.getBranchName())==false){
            System.out.println("foodItem not in menu of this branch");
        }
        //foodItem found
        //delete foodItem
        else{
            foodItemDB.delete(foodItem);
        }
        sc.close();
    }
    public void selectOptions(int choice){
        switch(choice){
            case 1:
            displayStaff();
            break;
            case 2:
            addItem();
            break;
            case 3:
            editItem();
            break;
            case 4:
            removeItem();
            break;
            default:break;
        }
    }
    public void displayOptions(){
        Scanner sc=new Scanner(System.in);
        boolean valid=false;
        while(!valid){
            try{
                System.out.println("Please select one of the following options");
                System.out.println("1. Display staff in branch");
                System.out.println("2. Add item to menu");
                System.out.println("3. Edit item in menu");
                System.out.println("4. Remove item from menu");
                int choice=sc.nextInt();
                if(choice>=1 && choice<=4){
                    selectOptions(choice);
                    valid=true;
                }else{
                    System.out.println("Invalid Option. Please try again");
                }
            }catch(Exception e){
                System.out.println("An error occurred: "+e.getMessage());
                System.out.println("Please try again");
            }
        }
        sc.close();
    }
}
