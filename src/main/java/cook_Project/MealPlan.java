package cook_Project;

import java.util.*;

public class MealPlan {
    private final List<FoodItem> meals;

    public MealPlan() {
        this.meals = new ArrayList<>();
    }

    public List<FoodItem> getMeals() {
        return meals;
    }

    public void addMeal(FoodItem meal) {
        this.meals.add(meal);
    }


}
