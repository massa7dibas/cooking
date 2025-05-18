package cook_Project;

import java.security.SecureRandom;
import java.util.*;

public class CustomerMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Edit Profile");
            System.out.println("2. Add Allergy");
            System.out.println("3. Place Order");
            System.out.println("4. View Order History");
            System.out.println("5. Generate Invoice");
            System.out.println("6. Suggest Recipes");
            System.out.println("7. View Allergies");
            System.out.println("8. View Available Recipes");
            System.out.println("9. View Ingredients");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("New Name: "); String nn = scanner.nextLine();
                    System.out.print("New Email: "); String ne = scanner.nextLine();
                    System.out.print("New Phone: "); String np = scanner.nextLine();
                    Application.updateUserProfile(nn, ne, np);
                    break;
                case 2:
                    System.out.print("Allergy Type: "); String at = scanner.nextLine();
                    System.out.print("Desc: "); String ad = scanner.nextLine();
                    ((Customer)Application.main_User).addAllergy(new Allergy(at, ad));
                    break;
                case 3:
                    System.out.print("Enter Food Item Name: "); String fname = scanner.nextLine();
                    System.out.print("Enter Ingredient IDs (comma): "); List<String> ing = Arrays.asList(scanner.nextLine().split(","));
                    System.out.print("Enter Price: "); double price = Double.parseDouble(scanner.nextLine());
                    SecureRandom secureRandom = new SecureRandom();
                    String oid = "ORD" + secureRandom.nextInt(1_000_000);
                    FoodItem fi = new FoodItem("FI-" + UUID.randomUUID(), fname, ing, price);
                    Order order = new Order(oid, List.of(fi), price, "NEW");
                    ((Customer)Application.main_User).addOrder(order);
                    System.out.println("Order Placed: " + order.getOrderId());
                    break;
                case 4:
                    for (Order o : ((Customer)Application.main_User).getOrderHistory())
                        System.out.println(o.getOrderId() + " - " + o.getStatus() + " Total: " + o.getTotalPrice());
                    break;
                case 5:
                    if (!((Customer)Application.main_User).getOrderHistory().isEmpty()) {
                        Order last = ((Customer)Application.main_User).getOrderHistory().get(0);
                        Invoice inv = InvoiceGenerator.generateInvoice(last);
                        System.out.println("Invoice ID: " + inv.getInvoiceId() + " | Total: " + inv.getTotal());
                        EmailService.sendInvoice(inv, Application.main_User.getEmail());
                    }
                    break;
                case 6:
                    System.out.print("Available ingredients (comma ids): "); List<String> aing = Arrays.asList(scanner.nextLine().split(","));
                    System.out.print("Dietary prefs (comma): "); List<String> dp = Arrays.asList(scanner.nextLine().split(","));
                    System.out.print("Max prep time (or blank): "); String mp = scanner.nextLine(); Integer mpt = mp.isEmpty() ? null : Integer.parseInt(mp);
                    List<Recipe> suggestions = AISuggestionService.suggestRecipes(new RecipeRequest(aing, dp, mpt));
                    for (Recipe r : suggestions) System.out.println(r.getName());
                    break;
                case 7:
                    for (Allergy allergy : ((Customer)Application.main_User).getAllergies()) {
                        System.out.println(allergy.getType() + ": " + allergy.getDescription());
                    }
                    break;
                case 8:
                    for (Recipe r : Application.getAllRecipes())
                        System.out.println(r.getRecipeId() + " - " + r.getName());
                    break;
                case 9:
                    for (Ingredient i : Application.getAllIngredients())
                        System.out.println(i.getId() + ": " + i.getName() + " Available: " + i.isAvailable());
                    break;
                case 0:
                    return;
            }
        }
    }
}
