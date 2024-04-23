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
        displayFormatter.displayFilteredByBranch(accountDB.getAll(),branchName);
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
        Integer foodIDInteger=new Integer(foodID);
        FoodItem foodItem=foodItemDB.find(foodIDInteger);
        if(foodItem.getBranchName().equals(super.getBranchName())==false){
            System.out.println("FoodItem not in menu of this branch");
            return;
        }
        //foodItem found
        //select the attribute to edit
        int choice;
        do{
            System.out.println("Select the attribute to edit:")
            System.out.println("1. name of FoodItem");
            System.out.println("2. description");
            System.out.println("3. category of FoodItem");
            System.out.println("4. price of FoodItem");
            System.out.println("5. branch where FoodItem is in");
            System.out.println("6. exit");
            choice=sc.nextInt();
            switch(choice){
                case 1:
                System.out.println("Enter the new name of the food item");
                String name=sc.nextLine();
                foodItem.setName(name);
                break;
                case 2:
                System.out.println("Enter the new description of the food item");
                String description=sc.nextLine();
                foodItem.setDescription(description);
                break;
                case 3:
                System.out.println("Enter the new category of the food item");
                String itemCategory=sc.nextLine();
                foodItem.setItemCategory(itemCategory);
                break;
                case 4:
                System.out.println("Enter the new price of the food item");
                double price=sc.nextDouble();
                foodItem.setPrice(price);
                break;
                case 5:
                System.out.println("Enter the new branch containing food item");
                String branchName=sc.nextLine();
                foodItem.setBranchName(branchName);
                break;
            }
        }while(choice>=1 && choice<=5);
        foodItemDB.update(foodItem);
    }
    public void removeItem(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the FoodID");
        int foodID=sc.nextInt();
        //search for the fooditem with the corresponding FoodID
        Integer foodIDInteger=new Integer(foodID);
        FoodItem foodItem=foodItemDB.find(foodIDInteger);
        if(foodItem.getBranchName().equals(super.getBranchName())==false){
            System.out.println("foodItem not in menu of this branch");
            return;
        }
        //foodItem found
        //delete foodItem
        fooItemDB.delete(foodItem);
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
                System.out.println("1. display staff in branch");
                System.out.println("2. add item to menu");
                System.out.println("3. edit item in menu");
                System.out.println("4. remove item from menu");
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
    }
}
