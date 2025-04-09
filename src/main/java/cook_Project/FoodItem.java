package cook_Project;

import java.util.*;

public class FoodItem {
    private String itemId;
    private String name;
    private List<String> ingredients;
    private double price;

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
