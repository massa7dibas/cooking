
package cook_Project;

public class InvoiceItem {
    private final String name;
    private final int quantity;
    private final double unitPrice;
    private final double total;

    public InvoiceItem(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = quantity * unitPrice;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotal() { return total; }
}
