
package cook_Project;

public class InvoiceItem {
    private String name;
    private int quantity;
    private double unitPrice;
    private double total;

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
