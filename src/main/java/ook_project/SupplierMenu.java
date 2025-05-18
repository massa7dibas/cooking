package ook_project;

import java.util.*;

import static ook_project.Application.logger;

public class SupplierMenu {
    public static void show(Scanner scanner) {
        while (true) {
            logger.info("\n--- Supplier Menu ---");
            logger.info("1. View Restock Suggestions");
            logger.info("2. Send Purchase Order");
            logger.info("0. Logout");
            logger.info("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
                    for (RestockSuggestion r : rs)
                        logger.info("Ingredient: " + r.getIngredientId() + ", Qty: " + r.getSuggestedQuantity());
                    break;
                case 2:
                    PurchaseOrder po = PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock());
                    if (po != null) {
                        PurchaseOrderService.sendOrder(po);
                    }
                    logger.info("PO Sent: " + po.getOrderId());
                    break;
                case 0:
                    return;
            }
        }
    }
}
//package cook_Project;
//
//import java.util.*;
//
//public class SupplierMenu {
//    public static void show(Scanner scanner) {
//        while (true) {
//            logger.info("\n--- Supplier Menu ---");
//            logger.info("1. View Restock Suggestions");
//            logger.info("2. Send Purchase Order");
//            logger.info("3. View Purchase Order Summary");
//            logger.info("4. Edit Profile");
//            logger.info("0. Logout");
//            logger.info("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//            switch (choice) {
//                case 1:
//                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
//                    for (RestockSuggestion r : rs) {
//                        logger.info("Ingredient: " + r.getIngredientId() + ", Qty: " + r.getSuggestedQuantity() + ", Price: " + r.getPriceQuote().getPrice());
//                    }
//                    break;
//                case 2:
//                    List<RestockSuggestion> suggestions = InventoryService.suggestRestock();
//                    PurchaseOrder po = PurchaseOrderService.createPurchaseOrder(suggestions);
//                    PurchaseOrderService.sendOrder(po);
//                    logger.info("PO Sent: " + po.getOrderId());
//                    break;
//                case 3:
//                    PurchaseOrder summaryPo = PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock());
//                    logger.info("Purchase Order ID: " + summaryPo.getOrderId());
//                    for (PurchaseItem item : summaryPo.getItems()) {
//                        logger.info(item.getIngredientId() + " - Qty: " + item.getQuantity() + " - Price: " + item.getPrice());
//                    }
//                    logger.info("Total Amount: " + summaryPo.getTotalAmount());
//                    break;
//                case 4:
//                    logger.info("New Name: "); String nn = scanner.nextLine();
//                    logger.info("New Email: "); String ne = scanner.nextLine();
//                    logger.info("New Phone: "); String np = scanner.nextLine();
//                    Application.updateUserProfile(nn, ne, np);
//                    logger.info(Application.getProfileUpdateMessage());
//                    break;
//                case 0:
//                    return;
//            }
//        }
//    }
//}