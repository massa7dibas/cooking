package cook_Project;

import java.util.*;

public class MealPlan {
    private String planId;
    private List<FoodItem> meals;
    private Date startDate;
    private Date endDate;

    public MealPlan() {
        this.planId = UUID.randomUUID().toString();
        this.meals = new ArrayList<>();
        this.startDate = new Date();
        this.endDate = new Date();
    }

    public MealPlan(String planId, List<FoodItem> meals, Date startDate, Date endDate) {
        this.planId = planId;
        this.meals = meals;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPlanId() {
        return planId;
    }

    public List<FoodItem> getMeals() {
        return meals;
    }

    public void addMeal(FoodItem meal) {
        this.meals.add(meal);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
