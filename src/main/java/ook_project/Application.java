package ook_project;

import java.util.*;

public class Application {
    public static User mainUser = null;

    private static final List<Customer> customerList = new ArrayList<>();

    public static List<Admin> getAdminList() {
        return adminList;
    }

    private static final List<Supplier> supplierList = new ArrayList<>();

    public static List<Chef> getChefList() {
        return chefList;
    }
    public static void chefListpush(Chef f){
        chefList.add(f);
    }

   public static void clearCheiflist(){
        chefList.clear();
   }



    private static final ArrayList<Recipe> recipes = new ArrayList<>();
    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static List getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    public static void clearRecipes() {
        recipes.clear();
    }



    private static final List<Ingredient> ingredients = new ArrayList<>();

    public static void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public static List<Ingredient> getAllIngredients() {
        return new ArrayList<>(ingredients);
    }


    private static final List<InventoryItem> items = new ArrayList<>();

    public static void addItem(InventoryItem item) {
        items.add(item);
    }

    public static List<InventoryItem> getAllItems() {
        return new ArrayList<>(items);
    }


    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void addTask(Task task) { tasks.add(task); }
    public static List<Task> getAllTasks() { return new ArrayList<>(tasks); }

    public static void clearTasks() {
        tasks=new ArrayList<>();
    }



        private static final List<Admin> adminList = new ArrayList<>();
    private static final List<Chef> chefList = new ArrayList<>();
    private static final List<KitchenManager> kitchenManagerList = new ArrayList<>();

    private static String confirmationMessage;
    private static String loginErrorMessage;
    private static String profileUpdateMessage;



    public static boolean isEmailRegistered(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email)) return true;
        }
        for (Supplier supplier : supplierList) {
            if (supplier.getEmail().equals(email)) return true;
        }
        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email)) return true;
        }
        for (Chef chef : chefList) {
            if (chef.getEmail().equals(email)) return true;
        }
        for (KitchenManager kitchenManager : kitchenManagerList) {
            if (kitchenManager.getEmail().equals(email)) return true;
        }
        return false;
    }

    public static void registerUser(String id, String name, String email, String password, String phone, String role) {
        if (isEmailRegistered(email)) {
            confirmationMessage = "Email is already registered.";
            return;
        }

        switch (role) {
            case "customer" -> customerList.add(new Customer(id, name, email, password, phone, ""));
            case "supplier" -> supplierList.add(new Supplier(id, name, email, password, phone));
            case "admin" -> adminList.add(new Admin(id, name, email, password, phone));
            case "chef" -> chefList.add(new Chef(id, name, email, password, phone, 0, 0));
            case "kitchenManager" -> kitchenManagerList.add(new KitchenManager(id, name, email, password, phone));
            default -> System.out.println("Invalid role");
        }

        confirmationMessage = "Registration Successful";
    }

    public static String getConfirmationMessage() {
        return confirmationMessage;
    }

    public static void loginUser(String email, String password) {
        for (Customer customer : customerList) {
            if (customer.signIn(email, password)) {
                mainUser = customer;
                loginErrorMessage = null;
                return;
            }
        }
        for (Supplier supplier : supplierList) {
            if (supplier.signIn(email, password)) {
                mainUser = supplier;
                loginErrorMessage = null;
                return;
            }
        }
        for (Admin admin : adminList) {
            if (admin.signIn(email, password)) {
                mainUser = admin;
                loginErrorMessage = null;
                return;
            }
        }
        for (Chef chef : chefList) {
            if (chef.signIn(email, password)) {
                mainUser = chef;
                loginErrorMessage = null;
                return;
            }
        }
        for (KitchenManager kitchenManager : kitchenManagerList) {
            if (kitchenManager.signIn(email, password)) {
                mainUser = kitchenManager;
                loginErrorMessage = null;
                return;
            }
        }

        loginErrorMessage = "Incorrect email or password";
    }

    public static boolean isUserLoggedIn() {
        return mainUser != null;
    }

    public static String getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public static void updateUserProfile(String newName, String newEmail, String newPhone) {
        if (mainUser != null) {
            mainUser.editProfile(newName, newEmail, newPhone);
            profileUpdateMessage = "Profile Updated Successfully";
        } else {
            profileUpdateMessage = "No user is logged in";
        }
    }

    public static String getProfileUpdateMessage() {
        return profileUpdateMessage;
    }

    public static List<KitchenManager> getKitchenManagerList() {
        return kitchenManagerList;
    }

    public static List<Supplier> getSupplierList() {
        return supplierList;
    }

    public static List<Customer> getCustomerList() {
return customerList;
    }
}
