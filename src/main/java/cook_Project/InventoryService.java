package cook_Project;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    public static List<RestockSuggestion> suggestRestock(List<String> restrictions) {
        List<RestockSuggestion> suggestions = new ArrayList<>();
        for (InventoryItem item : InventoryRepository.getAllItems()) {
            if (item.needsRestock()) {
                PriceQuote quote = SupplierIntegrationService.getBestPrice(item.getIngredientId());
                suggestions.add(new RestockSuggestion(item.getIngredientId(), item.getReorderQuantity(), quote));
            }
        }
        return suggestions;
    }
}
