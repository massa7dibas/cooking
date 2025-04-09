package cook_Project;

import java.util.UUID;

public class InventoryItem {
    private String ingredientId;
    private int quantityOnHand;
    private int reorderThreshold;
    private int reorderQuantity;

    public InventoryItem(String ingredientId, int quantityOnHand, int reorderThreshold, int reorderQuantity) {
        this.ingredientId = ingredientId;
        this.quantityOnHand = quantityOnHand;
        this.reorderThreshold = reorderThreshold;
        this.reorderQuantity = reorderQuantity;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public int getReorderQuantity() {
        return reorderQuantity;
    }

    public boolean needsRestock() {
        return quantityOnHand <= reorderThreshold;
    }

    public String getId() {
        return ingredientId;
    }
}
