package ook_project;

import java.util.*;

public class SupplierMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Supplier Menu ---");
            System.out.println("1. View Restock Suggestions");
            System.out.println("2. Send Purchase Order");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
                    for (RestockSuggestion r : rs)
                        System.out.println("Ingredient: " + r.getIngredientId() + ", Qty: " + r.getSuggestedQuantity());
                    break;
                case 2:
                    PurchaseOrder po = PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock());
                    if (po != null) {
                        PurchaseOrderService.sendOrder(po);
                    }
                    System.out.println("PO Sent: " + po.getOrderId());
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
//            System.out.println("\n--- Supplier Menu ---");
//            System.out.println("1. View Restock Suggestions");
//            System.out.println("2. Send Purchase Order");
//            System.out.println("3. View Purchase Order Summary");
//            System.out.println("4. Edit Profile");
//            System.out.println("0. Logout");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//            switch (choice) {
//                case 1:
//                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
//                    for (RestockSuggestion r : rs) {
//                        System.out.println("Ingredient: " + r.getIngredientId() + ", Qty: " + r.getSuggestedQuantity() + ", Price: " + r.getPriceQuote().getPrice());
//                    }
//                    break;
//                case 2:
//                    List<RestockSuggestion> suggestions = InventoryService.suggestRestock();
//                    PurchaseOrder po = PurchaseOrderService.createPurchaseOrder(suggestions);
//                    PurchaseOrderService.sendOrder(po);
//                    System.out.println("PO Sent: " + po.getOrderId());
//                    break;
//                case 3:
//                    PurchaseOrder summaryPo = PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock());
//                    System.out.println("Purchase Order ID: " + summaryPo.getOrderId());
//                    for (PurchaseItem item : summaryPo.getItems()) {
//                        System.out.println(item.getIngredientId() + " - Qty: " + item.getQuantity() + " - Price: " + item.getPrice());
//                    }
//                    System.out.println("Total Amount: " + summaryPo.getTotalAmount());
//                    break;
//                case 4:
//                    System.out.print("New Name: "); String nn = scanner.nextLine();
//                    System.out.print("New Email: "); String ne = scanner.nextLine();
//                    System.out.print("New Phone: "); String np = scanner.nextLine();
//                    Application.updateUserProfile(nn, ne, np);
//                    System.out.println(Application.getProfileUpdateMessage());
//                    break;
//                case 0:
//                    return;
//            }
//        }
//    }
//}