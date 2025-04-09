package cook_Project;

public class PriceQuote {
    private String ingredientId;
    private String supplierId;
    private double price;

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
