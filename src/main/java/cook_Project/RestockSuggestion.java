package cook_Project;

public class RestockSuggestion {
    private String ingredientId;
    private int suggestedQuantity;
    private PriceQuote priceQuote;

    public RestockSuggestion(String ingredientId, int suggestedQuantity, PriceQuote priceQuote) {
        this.ingredientId = ingredientId;
        this.suggestedQuantity = suggestedQuantity;
        this.priceQuote = priceQuote;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public PriceQuote getPriceQuote() {
        return priceQuote;
    }
}
