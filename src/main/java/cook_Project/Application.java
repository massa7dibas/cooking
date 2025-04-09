package cook_Project;

import java.util.*;

public class Application {
    public static User main_User = null;

    private static List<Customer> customerList = new ArrayList<>();
    private static List<Supplier> supplierList = new ArrayList<>();
    private static List<Admin> adminList = new ArrayList<>();
    private static List<Chef> chefList = new ArrayList<>();
    private static List<KitchenManager> kitchenManagerList = new ArrayList<>();

    private static String confirmationMessage;
    private static String loginErrorMessage;
    private static String profileUpdateMessage;

    public static void initializeSystem() {
    }

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

        if (role.equals("customer")) {
            customerList.add(new Customer(id, name, email, password, phone, ""));
        } else if (role.equals("supplier")) {
            supplierList.add(new Supplier(id, name, email, password, phone, ""));
        } else if (role.equals("admin")) {
            adminList.add(new Admin(id, name, email, password, phone));
        } else if (role.equals("chef")) {
            chefList.add(new Chef(id, name, email, password, phone, ""));
        } else if (role.equals("kitchenManager")) {
            kitchenManagerList.add(new KitchenManager(id, name, email, password, phone, ""));
        }

        confirmationMessage = "Registration Successful";
    }

    public static String getConfirmationMessage() {
        return confirmationMessage;
    }

    public static void loginUser(String email, String password) {
        for (Customer customer : customerList) {
            if (customer.signIn(email, password)) {
                main_User = customer;
                loginErrorMessage = null;
                return;
            }
        }
        for (Supplier supplier : supplierList) {
            if (supplier.signIn(email, password)) {
                main_User = supplier;
                loginErrorMessage = null;
                return;
            }
        }
        for (Admin admin : adminList) {
            if (admin.signIn(email, password)) {
                main_User = admin;
                loginErrorMessage = null;
                return;
            }
        }
        for (Chef chef : chefList) {
            if (chef.signIn(email, password)) {
                main_User = chef;
                loginErrorMessage = null;
                return;
            }
        }
        for (KitchenManager kitchenManager : kitchenManagerList) {
            if (kitchenManager.signIn(email, password)) {
                main_User = kitchenManager;
                loginErrorMessage = null;
                return;
            }
        }

        loginErrorMessage = "Incorrect email or password";
    }

    public static boolean isUserLoggedIn() {
        return main_User != null;
    }

    public static String getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public static void updateUserProfile(String newName, String newEmail, String newPhone) {
        if (main_User != null) {
            main_User.editProfile(newName, newEmail, newPhone);
            profileUpdateMessage = "Profile Updated Successfully";
        } else {
            profileUpdateMessage = "No user is logged in";
        }
    }

    public static String getProfileUpdateMessage() {
        return profileUpdateMessage;
    }
}
