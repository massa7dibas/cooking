package cook_Project;

public class PriceQuote {
    private final String ingredientId;
    private final String supplierId;
    private final double price;

    public PriceQuote(String ingredientId, String supplierId, double price) {
        this.ingredientId = ingredientId;
        this.supplierId = supplierId;
        this.price = price;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public double getPrice() {
        return price;
    }
}
