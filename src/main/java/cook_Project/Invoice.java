package cook_Project;

import java.util.*;

public class Invoice {
    private String invoiceId;
    private String orderId;
    private List<InvoiceItem> items;
    private double total;
    private String status;
    private Date issueDate;

    public Invoice(String orderId, List<InvoiceItem> items) {
        this.invoiceId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        this.total = items.stream().mapToDouble(InvoiceItem::getTotal).sum();
        this.status = "Issued";
        this.issueDate = new Date();
    }

    public String getInvoiceId() { return invoiceId; }
    public String getOrderId() { return orderId; }
    public List<InvoiceItem> getItems() { return items; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public Date getIssueDate() { return issueDate; }
}
