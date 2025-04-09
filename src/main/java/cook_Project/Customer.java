package cook_Project;

import java.util.*;

public class Customer extends User {
    private String dietaryPreferences;
    private List<Allergy> allergies;
    private List<Order> orderHistory;
    private MealPlan mealPlan;

    public Customer(String id, String name, String email, String password, String phoneNumber, String dietaryPreferences) {
        super(id, name, email, password, phoneNumber, "Customer");
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.mealPlan = new MealPlan();
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void addAllergy(Allergy allergy) {
        this.allergies.add(allergy);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrder(Order order) {
        this.orderHistory.add(order);
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
