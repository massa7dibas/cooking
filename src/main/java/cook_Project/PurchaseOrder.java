package cook_Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PurchaseOrder {
    private String orderId;
    private String supplierId;
    private List<OrderLine> lines;
    private String status;
    private Date dateCreated;

    public PurchaseOrder(String supplierId) {
        this.orderId = UUID.randomUUID().toString();
        this.supplierId = supplierId;
        this.lines = new ArrayList<>();
        this.status = "CREATED";
        this.dateCreated = new Date();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void addLine(OrderLine line) {
        lines.add(line);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
