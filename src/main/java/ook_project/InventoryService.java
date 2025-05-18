package ook_project;

import java.util.ArrayList;
import java.util.List;

import static ook_project.Application.getAllItems;

public class InventoryService {
    public static List<RestockSuggestion> suggestRestock() {
        List<RestockSuggestion> suggestions = new ArrayList<>();
        for (InventoryItem item : getAllItems()) {
            if (item.needsRestock()) {
                PriceQuote quote = SupplierIntegrationService.getBestPrice(item.getIngredientId());
                suggestions.add(new RestockSuggestion(item.getIngredientId(), item.getReorderQuantity(), quote));
            }
        }
        return suggestions;
    }
}
