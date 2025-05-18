package ook_project;

import java.util.*;

import static ook_project.Application.logger;

public class AdminMenu {
    public static void show(Scanner scanner) {
        while (true) {
            logger.info("\n--- Admin Menu ---\n");
            logger.info("1. Add Ingredient\n");
            logger.info("2. Add Recipe\n");
            logger.info("3. List Ingredients\n");
            logger.info("4. List Recipes\n");
            logger.info("5. Edit Profile\n");
            logger.info("6. Generate Daily Report\n");
            logger.info("7. Analyze Sales\n");
            logger.info("8. List Users by Role\n");
            logger.info("9. List Tasks\n");
            logger.info("10. List Inventory Items\n");
            logger.info("0. Logout\n");
            logger.info("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    logger.info("Ingredient ID: "); String iid = scanner.nextLine();
                    logger.info("Name: "); String iname = scanner.nextLine();
                    logger.info("Available (true/false): "); boolean avail = Boolean.parseBoolean(scanner.nextLine());
                    logger.info("Dietary tags (comma): "); List<String> tags = Arrays.asList(scanner.nextLine().split(","));
                    Application.addIngredient(new Ingredient(iid, iname, avail, tags));
                    break;
                case 2:
                    logger.info("Recipe ID: "); String rid = scanner.nextLine();
                    logger.info("Name: "); String rname = scanner.nextLine();
                    logger.info("Prep Time: "); int time = Integer.parseInt(scanner.nextLine());
                    logger.info("Ingredients (comma ids): "); List<String> ingredients = Arrays.asList(scanner.nextLine().split(","));
                    logger.info("Dietary tags (comma): "); List<String> dietary = Arrays.asList(scanner.nextLine().split(","));
                    Application.addRecipe(new Recipe(rid, rname, time, ingredients, dietary));
                    break;
                case 3:
                    for (Ingredient ing : Application.getAllIngredients())
                        logger.info(ing.getId() + ": " + ing.getName() + " (Available: " + ing.isAvailable() + ") Tags: " + ing.getDietaryTags());
                    break;
                case 4:
                    for (Recipe r : Application.getAllRecipes())
                        logger.info(r.getRecipeId() + " - " + r.getName() + " Time: " + r.getPrepTime() + " mins");
                    break;
                case 5:
                    logger.info("New Name: "); String nn = scanner.nextLine();
                    logger.info("New Email: "); String ne = scanner.nextLine();
                    logger.info("New Phone: "); String np = scanner.nextLine();
                    Application.updateUserProfile(nn, ne, np);
                    logger.info(Application.getProfileUpdateMessage());
                    break;
                case 6:
                    List<Order> allOrders = new ArrayList<>();
                    for (Customer c : Application.getCustomerList()) {
                        allOrders.addAll(c.getOrderHistory());
                    }
                    DailyFinancialReport report = FinancialReportService.generateDailyReport(allOrders);
                    logger.info("Revenue: " + report.getTotalRevenue());
                    break;
                case 7:
                    Map<String, Integer> allSales = new HashMap<>();
                    for (Customer c : Application.getCustomerList()) {
                        for (Order o : c.getOrderHistory()) {
                            for (FoodItem fi : o.getItems()) {
                                allSales.put(fi.getName(), allSales.getOrDefault(fi.getName(), 0) + 1);
                            }
                        }
                    }
                    logger.info("Top Product: " + SalesAnalyzer.topPerformingProduct(allSales));
                    break;
                case 8:
                    logger.info("Admins:");
                    for (Admin a : Application.getAdminList()) logger.info(a.getId() + " - " + a.getName());
                    logger.info("Customers:");
                    for (Customer c : Application.getCustomerList()) logger.info(c.getId() + " - " + c.getName());
                    logger.info("Suppliers:");
                    for (Supplier s : Application.getSupplierList()) logger.info(s.getId() + " - " + s.getName());
                    logger.info("Chefs:");
                    for (Chef ch : Application.getChefList()) logger.info(ch.getId() + " - " + ch.getName());
                    logger.info("Kitchen Managers:");
                    for (KitchenManager km : Application.getKitchenManagerList()) logger.info(km.getId() + " - " + km.getName());
                    break;
                case 9:
                    for (Task t : Application.getAllTasks()) {
                        logger.info(t.getTaskId() + ": " + t.getDescription() + " (Chef ID: " + t.getAssignedChefId() + ")");
                    }
                    break;
                case 10:
                    for (InventoryItem i : Application.getAllItems()) {
                        logger.info(i.getIngredientId() + ": QOH=" + i.getQuantityOnHand() + ", Threshold=" + i.getReorderThreshold());
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}