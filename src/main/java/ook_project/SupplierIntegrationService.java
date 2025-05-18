package ook_project;

import java.util.ArrayList;
import java.util.List;

public class SupplierIntegrationService {
    private static final List<SupplierAPIClient> clients = new ArrayList<>();

    public static void registerClient(SupplierAPIClient client) {
        clients.add(client);
    }

    public static PriceQuote getBestPrice(String ingredientId) {
        PriceQuote best = null;
        for (SupplierAPIClient client : clients) {
            PriceQuote quote = client.getLivePrice(ingredientId);
            if (quote != null && (best == null || quote.getPrice() < best.getPrice())) {
                best = quote;
            }
        }
        return best;
    }
}
