package cook_Project;


public class InventoryItem {
    private final String ingredientId;
    private final int quantityOnHand;
    private final int reorderThreshold;
    private final int reorderQuantity;

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
