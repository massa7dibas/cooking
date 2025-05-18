package ook_project;

import java.util.List;

public class PurchaseOrderService {
    public static PurchaseOrder createPurchaseOrder(List<RestockSuggestion> suggestions) {
        if (suggestions.isEmpty()) {
            return null;
        }
        String supplierId = suggestions.get(0).getPriceQuote().getSupplierId();
        PurchaseOrder po = new PurchaseOrder(supplierId);
        for (RestockSuggestion rs : suggestions) {
            po.addLine(new OrderLine(rs.getIngredientId(), rs.getSuggestedQuantity(), rs.getPriceQuote().getPrice()));
        }
        return po;
    }

    public static void sendOrder(PurchaseOrder po) {
        po.setStatus("SENT");
    }
}
