package cook_Project;

import java.util.*;

public class AdminMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Ingredient");
            System.out.println("2. Add Recipe");
            System.out.println("3. List Ingredients");
            System.out.println("4. List Recipes");
            System.out.println("5. Edit Profile");
            System.out.println("6. Generate Daily Report");
            System.out.println("7. Analyze Sales");
            System.out.println("8. List Users by Role");
            System.out.println("9. List Tasks");
            System.out.println("10. List Inventory Items");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Ingredient ID: "); String iid = scanner.nextLine();
                    System.out.print("Name: "); String iname = scanner.nextLine();
                    System.out.print("Available (true/false): "); boolean avail = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Dietary tags (comma): "); List<String> tags = Arrays.asList(scanner.nextLine().split(","));
                    Application.addIngredient(new Ingredient(iid, iname, avail, tags));
                    break;
                case 2:
                    System.out.print("Recipe ID: "); String rid = scanner.nextLine();
                    System.out.print("Name: "); String rname = scanner.nextLine();
                    System.out.print("Prep Time: "); int time = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingredients (comma ids): "); List<String> ingredients = Arrays.asList(scanner.nextLine().split(","));
                    System.out.print("Dietary tags (comma): "); List<String> dietary = Arrays.asList(scanner.nextLine().split(","));
                    Application.addRecipe(new Recipe(rid, rname, time, ingredients, dietary));
                    break;
                case 3:
                    for (Ingredient ing : Application.getAllIngredients())
                        System.out.println(ing.getId() + ": " + ing.getName() + " (Available: " + ing.isAvailable() + ") Tags: " + ing.getDietaryTags());
                    break;
                case 4:
                    for (Recipe r : Application.getAllRecipes())
                        System.out.println(r.getRecipeId() + " - " + r.getName() + " Time: " + r.getPrepTime() + " mins");
                    break;
                case 5:
                    System.out.print("New Name: "); String nn = scanner.nextLine();
                    System.out.print("New Email: "); String ne = scanner.nextLine();
                    System.out.print("New Phone: "); String np = scanner.nextLine();
                    Application.updateUserProfile(nn, ne, np);
                    System.out.println(Application.getProfileUpdateMessage());
                    break;
                case 6:
                    List<Order> allOrders = new ArrayList<>();
                    for (Customer c : Application.getCustomerList()) {
                        allOrders.addAll(c.getOrderHistory());
                    }
                    DailyFinancialReport report = FinancialReportService.generateDailyReport(allOrders);
                    System.out.println("Revenue: " + report.getTotalRevenue());
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
                    System.out.println("Top Product: " + SalesAnalyzer.topPerformingProduct(allSales));
                    break;
                case 8:
                    System.out.println("Admins:");
                    for (Admin a : Application.getAdminList()) System.out.println(a.getId() + " - " + a.getName());
                    System.out.println("Customers:");
                    for (Customer c : Application.getCustomerList()) System.out.println(c.getId() + " - " + c.getName());
                    System.out.println("Suppliers:");
                    for (Supplier s : Application.getSupplierList()) System.out.println(s.getId() + " - " + s.getName());
                    System.out.println("Chefs:");
                    for (Chef ch : Application.getChefList()) System.out.println(ch.getId() + " - " + ch.getName());
                    System.out.println("Kitchen Managers:");
                    for (KitchenManager km : Application.getKitchenManagerList()) System.out.println(km.getId() + " - " + km.getName());
                    break;
                case 9:
                    for (Task t : Application.getAllTasks()) {
                        System.out.println(t.getTaskId() + ": " + t.getDescription() + " (Chef ID: " + t.getAssignedChefId() + ")");
                    }
                    break;
                case 10:
                    for (InventoryItem i : Application.getAllItems()) {
                        System.out.println(i.getIngredientId() + ": QOH=" + i.getQuantityOnHand() + ", Threshold=" + i.getReorderThreshold());
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}