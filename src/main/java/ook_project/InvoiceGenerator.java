
package ook_project;

import java.util.*;

public class InvoiceGenerator {
    public static Invoice generateInvoice(Order order) {
        Map<String, InvoiceItem> map = new LinkedHashMap<>();
        for (FoodItem fi : order.getItems()) {
            String name = fi.getName();
            double price = fi.getPrice();
            if (map.containsKey(name)) {
                InvoiceItem existing = map.get(name);
                int qty = existing.getQuantity() + 1;
                map.put(name, new InvoiceItem(name, qty, price));
            } else {
                map.put(name, new InvoiceItem(name, 1, price));
            }
        }
        List<InvoiceItem> items = new ArrayList<>(map.values());
        return new Invoice(order.getOrderId(), items);
    }
}
