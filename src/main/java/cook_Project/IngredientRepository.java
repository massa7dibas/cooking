package cook_Project;

import java.util.ArrayList;
import java.util.List;

public class IngredientRepository {
    private static List<Ingredient> ingredients = new ArrayList<>();

    public static void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public static List<Ingredient> getAllIngredients() {
        return new ArrayList<>(ingredients);
    }
}