package cook_Project;

public class OrderLine {
    private String ingredientId;
    private int quantity;
    private double price;

    public OrderLine(String ingredientId, int quantity, double price) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
