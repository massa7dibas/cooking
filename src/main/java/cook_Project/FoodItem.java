package cook_Project;

import java.util.*;

public class FoodItem {
    private final String itemId;
    private final String name;
    private final List<String> ingredients;
    private final double price;

    public FoodItem(String itemId, String name, List<String> ingredients, double price) {
        this.itemId = itemId;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }
}
