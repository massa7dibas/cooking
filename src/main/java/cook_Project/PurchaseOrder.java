package cook_Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PurchaseOrder {
    private final String orderId;
    private final String supplierId;
    private final List<OrderLine> lines;
    private String status;

    public PurchaseOrder(String supplierId) {
        this.orderId = UUID.randomUUID().toString();
        this.supplierId = supplierId;
        this.lines = new ArrayList<>();
        this.status = "CREATED";
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

    public void addLine(OrderLine line) {
        lines.add(line);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
