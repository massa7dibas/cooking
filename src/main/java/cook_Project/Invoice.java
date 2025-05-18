package cook_Project;

import java.util.*;

public class Invoice {
    private final String invoiceId;
    private final String orderId;
    private final List<InvoiceItem> items;
    private final double total;
    private final String status;

    public Invoice(String orderId, List<InvoiceItem> items) {
        this.invoiceId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        this.total = items.stream().mapToDouble(InvoiceItem::getTotal).sum();
        this.status = "Issued";
    }

    public String getInvoiceId() { return invoiceId; }
    public String getOrderId() { return orderId; }
    public List<InvoiceItem> getItems() { return items; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
}
