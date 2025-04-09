package test_Package;

import cook_Project.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import java.util.*;

public class InventoryandSupplierManagementStep {
    private List<RestockSuggestion> suggestions;
    private PurchaseOrder purchaseOrder;

    @Given("the following inventory items exist:")
    public void the_following_inventory_items_exist(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String ingredientId = row.get("ingredientId");
            int quantityOnHand = Integer.parseInt(row.get("quantityOnHand"));
            int reorderThreshold = Integer.parseInt(row.get("reorderThreshold"));
            int reorderQuantity = Integer.parseInt(row.get("reorderQuantity"));
            InventoryRepository.addItem(new InventoryItem(ingredientId, quantityOnHand, reorderThreshold, reorderQuantity));
        }
    }

    @Given("the following suppliers are registered:")
    public void the_following_suppliers_are_registered(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String supplierId = row.get("supplierId");
            String ingredientId = row.get("ingredientId");
            double price = Double.parseDouble(row.get("price"));
            SupplierIntegrationService.registerClient(new SupplierAPIClient() {
                public PriceQuote getLivePrice(String id) {
                    if (id.equals(ingredientId)) {
                        return new PriceQuote(ingredientId, supplierId, price);
                    }
                    return null;
                }
            });
        }
    }

    @When("the system checks inventory levels")
    public void the_system_checks_inventory_levels() {
        suggestions = InventoryService.suggestRestock(new ArrayList<>());
    }

    @Then("it should suggest restock for ingredient {string} with quantity {int} and best price from supplier {string}")
    public void it_should_suggest_restock_for_ingredient_with_quantity_and_best_price_from_supplier(String ingredientId, Integer qty, String supplierId) {
        RestockSuggestion found = null;
        for (RestockSuggestion rs : suggestions) {
            if (rs.getIngredientId().equals(ingredientId)) {
                found = rs;
                break;
            }
        }
        assertNotNull(found);
        assertEquals(qty.intValue(), found.getSuggestedQuantity());
        assertEquals(supplierId, found.getPriceQuote().getSupplierId());
    }

    @Given("restock suggestions exist:")
    public void restock_suggestions_exist(DataTable dataTable) {
        suggestions = new ArrayList<>();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String ingredientId = row.get("ingredientId");
            int suggestedQuantity = Integer.parseInt(row.get("suggestedQuantity"));
            String supplierId = row.get("supplierId");
            double price = Double.parseDouble(row.get("price"));
            PriceQuote quote = new PriceQuote(ingredientId, supplierId, price);
            suggestions.add(new RestockSuggestion(ingredientId, suggestedQuantity, quote));
        }
    }

    @When("the system generates a purchase order")
    public void the_system_generates_a_purchase_order() {
        purchaseOrder = PurchaseOrderService.createPurchaseOrder(suggestions);
    }

    @Then("a purchase order should be created for supplier {string} with lines:")
    public void a_purchase_order_should_be_created_for_supplier_with_lines(String supplierId, DataTable dataTable) {
        assertNotNull(purchaseOrder);
        assertEquals(supplierId, purchaseOrder.getSupplierId());
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String ingredientId = row.get("ingredientId");
            int quantity = Integer.parseInt(row.get("quantity"));
            double price = Double.parseDouble(row.get("price"));
            OrderLine found = null;
            for (OrderLine line : purchaseOrder.getLines()) {
                if (line.getIngredientId().equals(ingredientId)) {
                    found = line;
                    break;
                }
            }
            assertNotNull(found);
            assertEquals(quantity, found.getQuantity());
            assertEquals(price, found.getPrice(), 0.001);
        }
    }

    @Then("the order status should be {string}")
    public void the_order_status_should_be(String status) {
        assertEquals(status, purchaseOrder.getStatus());
    }

    @Given("a purchase order for supplier {string} exists with status {string}")
    public void a_purchase_order_for_supplier_exists_with_status(String supplierId, String status) {
        purchaseOrder = new PurchaseOrder(supplierId);
        purchaseOrder.setStatus(status);
    }

    @When("the system sends the purchase order")
    public void the_system_sends_the_purchase_order() {
        PurchaseOrderService.sendOrder(purchaseOrder);
    }
}
