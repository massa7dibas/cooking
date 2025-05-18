package cook_Project;

import java.util.List;

public class RecipeRequest {
    private List availableIngredients;
    private List dietaryPreferences;
    private Integer maxPrepTime;
    public RecipeRequest(List<String> availableIngredients, List<String> dietaryPreferences, Integer maxPrepTime) {
        this.availableIngredients = availableIngredients;
        this.dietaryPreferences = dietaryPreferences;
        this.maxPrepTime = maxPrepTime;
    }

    public List<String> getAvailableIngredients() {
        return availableIngredients;
    }

    public List<String> getDietaryPreferences() {
        return dietaryPreferences;
    }

    public Integer getMaxPrepTime() {
        return maxPrepTime;
    }}