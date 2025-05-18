package cook_Project;

import java.util.List;

public class Recipe {
    private String recipeId;
    private String name;
    private int prepTime;
    private List ingredients;
    private List dietaryTags;
    public Recipe(String recipeId, String name, int prepTime, List<String> ingredients, List<String> dietaryTags) {
        this.recipeId = recipeId;
        this.name = name;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
        this.dietaryTags = dietaryTags;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getDietaryTags() {
        return dietaryTags;
    }}