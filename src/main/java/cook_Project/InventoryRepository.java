package cook_Project;

import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {
    private static List<InventoryItem> items = new ArrayList<>();

    public static void addItem(InventoryItem item) {
        items.add(item);
    }

    public static List<InventoryItem> getAllItems() {
        return new ArrayList<>(items);
    }

    public static InventoryItem findByIngredientId(String ingredientId) {
        for (InventoryItem item : items) {
            if (item.getIngredientId().equals(ingredientId)) {
                return item;
            }
        }
        return null;
    }
}
