package ook_project;

import java.util.List;

public class RecipeRequest {
    private final List availableIngredients;
    private final List dietaryPreferences;
    private final Integer maxPrepTime;
    public RecipeRequest(List<String> availableIngredients, List<String> dietaryPreferences, Integer maxPrepTime) {
        this.availableIngredients = availableIngredients;
        this.dietaryPreferences = dietaryPreferences;
        this.maxPrepTime = maxPrepTime;
    }

    public List getAvailableIngredients() {
        return availableIngredients;
    }

    public List getDietaryPreferences() {
        return dietaryPreferences;
    }

    public Integer getMaxPrepTime() {
        return maxPrepTime;
    }}