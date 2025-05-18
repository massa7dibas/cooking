package ook_project;

import java.security.SecureRandom;
import java.util.*;

import static ook_project.Application.logger;

public class CustomerMenu {
    public static void show(Scanner scanner) {
        while (true) {
            logger.info("\n--- Customer Menu ---");
            logger.info("1. Edit Profile");
            logger.info("2. Add Allergy");
            logger.info("3. Place Order");
            logger.info("4. View Order History");
            logger.info("5. Generate Invoice");
            logger.info("6. Suggest Recipes");
            logger.info("7. View Allergies");
            logger.info("8. View Available Recipes");
            logger.info("9. View Ingredients");
            logger.info("0. Logout");
            logger.info("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    logger.info("New Name: "); String nn = scanner.nextLine();
                    logger.info("New Email: "); String ne = scanner.nextLine();
                    logger.info("New Phone: "); String np = scanner.nextLine();
                    Application.updateUserProfile(nn, ne, np);
                    break;
                case 2:
                    logger.info("Allergy Type: "); String at = scanner.nextLine();
                    logger.info("Desc: "); String ad = scanner.nextLine();
                    ((Customer)Application.mainUser).addAllergy(new Allergy(at, ad));
                    break;
                case 3:
                    logger.info("Enter Food Item Name: "); String fname = scanner.nextLine();
                    logger.info("Enter Ingredient IDs (comma): "); List<String> ing = Arrays.asList(scanner.nextLine().split(","));
                    logger.info("Enter Price: "); double price = Double.parseDouble(scanner.nextLine());
                    SecureRandom secureRandom = new SecureRandom();
                    String oid = "ORD" + secureRandom.nextInt(1_000_000);
                    FoodItem fi = new FoodItem("FI-" + UUID.randomUUID(), fname, ing, price);
                    Order order = new Order(oid, List.of(fi), price, "NEW");
                    ((Customer)Application.mainUser).addOrder(order);
                    logger.info("Order Placed: " + order.getOrderId());
                    break;
                case 4:
                    for (Order o : ((Customer)Application.mainUser).getOrderHistory())
                        logger.info(o.getOrderId() + " - " + o.getStatus() + " Total: " + o.getTotalPrice());
                    break;
                case 5:
                    if (!((Customer)Application.mainUser).getOrderHistory().isEmpty()) {
                        Order last = ((Customer)Application.mainUser).getOrderHistory().get(0);
                        Invoice inv = InvoiceGenerator.generateInvoice(last);
                        logger.info("Invoice ID: " + inv.getInvoiceId() + " | Total: " + inv.getTotal());
                        EmailService.sendInvoice(inv, Application.mainUser.getEmail());
                    }
                    break;
                case 6:
                    logger.info("Available ingredients (comma ids): "); List<String> aing = Arrays.asList(scanner.nextLine().split(","));
                    logger.info("Dietary prefs (comma): "); List<String> dp = Arrays.asList(scanner.nextLine().split(","));
                    logger.info("Max prep time (or blank): "); String mp = scanner.nextLine(); Integer mpt = mp.isEmpty() ? null : Integer.parseInt(mp);
                    List<Recipe> suggestions = AISuggestionService.suggestRecipes(new RecipeRequest(aing, dp, mpt));
                    for (Recipe r : suggestions) logger.info(r.getName());
                    break;
                case 7:
                    for (Allergy allergy : ((Customer)Application.mainUser).getAllergies()) {
                        logger.info(allergy.getType() + ": " + allergy.getDescription());
                    }
                    break;
                case 8:
                    for (Recipe r : Application.getAllRecipes())
                        logger.info(r.getRecipeId() + " - " + r.getName());
                    break;
                case 9:
                    for (Ingredient i : Application.getAllIngredients())
                        logger.info(i.getId() + ": " + i.getName() + " Available: " + i.isAvailable());
                    break;
                case 0:
                    return;
            }
        }
    }
}
