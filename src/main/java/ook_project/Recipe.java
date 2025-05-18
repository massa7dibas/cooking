package ook_project;

import java.util.List;

public class Recipe {
    private final String recipeId;
    private final String name;
    private final int prepTime;
    private final List ingredients;
    private final List dietaryTags;
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

    public List getIngredients() {
        return ingredients;
    }

    public List getDietaryTags() {
        return dietaryTags;
    }}