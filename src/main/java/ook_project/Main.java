//package cook_Project;
//
//import java.time.ZonedDateTime;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Application.initializeSystem();
//        while (true) {
//            System.out.println("\n--- Special Cook Project Management ---");
//            System.out.println("1. Register User");
//            System.out.println("2. Login");
//            System.out.println("3. Edit Profile");
//            System.out.println("4. Add Ingredient");
//            System.out.println("5. List Ingredients");
//            System.out.println("6. Add Recipe");
//            System.out.println("7. List Recipes");
//            System.out.println("8. Suggest Recipes (AI)");
//            System.out.println("9. Validate Menu");
//            System.out.println("10. Suggest Substitutions");
//            System.out.println("11. Add Inventory Item");
//            System.out.println("12. Suggest Restock");
//            System.out.println("13. Create Purchase Order");
//            System.out.println("14. Send Purchase Order");
//            System.out.println("15. Add Task");
//            System.out.println("16. Schedule Tasks");
//            System.out.println("17. View Notifications");
//            System.out.println("18. Clear Notifications");
//            System.out.println("19. Place Order");
//            System.out.println("20. View Order History");
//            System.out.println("21. Generate Invoice");
//            System.out.println("22. Generate Daily Report");
//            System.out.println("23. Analyze Sales");
//            System.out.println("24. Add Allergy");
//            System.out.println("0. Exit");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    System.out.print("Role (customer/supplier/admin/chef/kitchenManager): ");
//                    String role = scanner.nextLine();
//                    System.out.print("ID: "); String id = scanner.nextLine();
//                    System.out.print("Name: "); String name = scanner.nextLine();
//                    System.out.print("Email: "); String email = scanner.nextLine();
//                    System.out.print("Password: "); String pwd = scanner.nextLine();
//                    System.out.print("Phone: "); String phone = scanner.nextLine();
//                    Application.registerUser(id,name,email,pwd,phone,role);
//                    System.out.println(Application.getConfirmationMessage());
//                    break;
//                case 2:
//                    System.out.print("Email: "); String le = scanner.nextLine();
//                    System.out.print("Password: "); String lp = scanner.nextLine();
//                    Application.loginUser(le,lp);
//                    if (Application.isUserLoggedIn()) System.out.println("Logged in as " + Application.main_User.getRole());
//                    else System.out.println(Application.getLoginErrorMessage());
//                    break;
//                case 3:
//                    System.out.print("New Name: "); String nn = scanner.nextLine();
//                    System.out.print("New Email: "); String ne = scanner.nextLine();
//                    System.out.print("New Phone: "); String np = scanner.nextLine();
//                    Application.updateUserProfile(nn,ne,np);
//                    System.out.println(Application.getProfileUpdateMessage());
//                    break;
//                case 4:
//                    System.out.print("Ingredient ID: "); String iid = scanner.nextLine();
//                    System.out.print("Name: "); String iname = scanner.nextLine();
//                    System.out.print("Available (true/false): "); boolean avail = Boolean.parseBoolean(scanner.nextLine());
//                    System.out.print("Dietary tags (comma): "); List<String> tags = Arrays.asList(scanner.nextLine().split(","));
//                    Application.addIngredient(new Ingredient(iid, iname, avail, tags));
//                    break;
//                case 5:
//                    for (Ingredient ing: Application.getAllIngredients())
//                        System.out.println(ing.getId()+": " + ing.getName()+" ["+ing.isAvailable()+"] " + ing.getDietaryTags());
//                    break;
//                case 6:
//                    System.out.print("Recipe ID: "); String rid = scanner.nextLine();
//                    System.out.print("Name: "); String rname = scanner.nextLine();
//                    System.out.print("Prep Time (min): "); int ptime = Integer.parseInt(scanner.nextLine());
//                    System.out.print("Ingredients (comma ids): "); List<String> ring = Arrays.asList(scanner.nextLine().split(","));
//                    System.out.print("Dietary tags (comma): "); List<String> rtags = Arrays.asList(scanner.nextLine().split(","));
//                    Recipe rec = new Recipe(rid, rname, ptime, ring, rtags);
//                    Application.addRecipe(rec);
//                    break;
//                case 7:
//                    for (Recipe r: Application.getAllRecipes()) System.out.println(r.getRecipeId()+" - " + r.getName());
//                    break;
//                case 8:
//                    System.out.print("Available ingredients (comma ids): "); List<String> aing = Arrays.asList(scanner.nextLine().split(","));
//                    System.out.print("Dietary prefs (comma): "); List<String> dp = Arrays.asList(scanner.nextLine().split(","));
//                    System.out.print("Max prep time (or blank): "); String mp = scanner.nextLine(); Integer mpt = mp.isEmpty()?null:Integer.parseInt(mp);
//                    List<Recipe> sug = AISuggestionService.suggestRecipes(new RecipeRequest(aing,dp,mpt));
//                    for (Recipe r: sug) System.out.println(r.getName());
//                    break;
//                case 9:
//                    System.out.print("Recipe ID to validate: "); String vrid = scanner.nextLine();
//                    Recipe vr = Application.getAllRecipes().stream().filter(r->r.getRecipeId().equals(vrid)).findFirst().orElse(null);
//                    if (vr!=null) System.out.println(MenuCustomizationService.validateIngredients(vr.getIngredients(),Arrays.asList(scanner.nextLine().split(","))));
//                    break;
//                case 10:
//                    System.out.print("Recipe ID to substitute: "); String srid = scanner.nextLine();
//                    Recipe sr = Application.getAllRecipes().stream().filter(r->r.getRecipeId().equals(srid)).findFirst().orElse(null);
//                    if (sr!=null) {
//                        List<Substitution> subs = MenuCustomizationService.suggestSubstitutions(sr.getIngredients(), Arrays.asList(scanner.nextLine().split(",")));
//                        for (Substitution s: subs)
//                            System.out.println(s.getOriginal().getName()+" -> "+s.getSubstitute().getName());
//                    }
//                    break;
//                case 11:
//                    System.out.print("Inv Item ingredient ID: "); String iiid = scanner.nextLine();
//                    System.out.print("QOH: "); int qoh = Integer.parseInt(scanner.nextLine());
//                    System.out.print("Threshold: "); int th = Integer.parseInt(scanner.nextLine());
//                    System.out.print("Reorder Qty: "); int rq = Integer.parseInt(scanner.nextLine());
//                    Application.addItem(new InventoryItem(iiid,qoh,th,rq));
//                    break;
//                case 12:
//                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
//                    for (RestockSuggestion rcs: rs)
//                        System.out.println(rcs.getIngredientId()+": qty="+rcs.getSuggestedQuantity()+" price="+rcs.getPriceQuote().getPrice());
//                    break;
//                case 13:
//                    PurchaseOrder po = PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock());
//                    System.out.println(po!=null?po.getOrderId():"No PO created");
//                    break;
//                case 14:
//                    System.out.print("PO ID to send: "); String poid = scanner.nextLine();
//
//                    PurchaseOrderService.sendOrder(PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock()));
//                    break;
//                case 15:
//                    System.out.print("Task ID: "); String tid = scanner.nextLine();
//                    System.out.print("Description: "); String td = scanner.nextLine();
//                    System.out.print("Expertise Required: "); int exp = Integer.parseInt(scanner.nextLine());
//                    System.out.print("Scheduled ISO time: "); ZonedDateTime zt = ZonedDateTime.parse(scanner.nextLine());
//                    Application.addTask(new Task(tid,td,exp,zt,null));
//                    break;
//                case 16:
//                    List<Task> sched = SchedulerService.scheduleTasks();
//                    for (Task t: sched) System.out.println(t.getTaskId()+" -> Chef: "+t.getAssignedChefId());
//                    break;
//                case 17:
//                    for (Notification n: NotificationService.getNotifications())
//                        System.out.println(n.getChefName()+": "+n.getMessage());
//                    break;
//                case 18:
//                    NotificationService.sendAll();
//                    break;
//                case 19:
//                    if (Application.main_User instanceof Customer) {
//                        List<FoodItem> fis = new ArrayList<>();
//                        fis.add(new FoodItem("fi1","TestItem",Arrays.asList("ing1"),10.0));
//                        Order o = new Order("o1",new Date(),fis,10.0,"NEW");
//                        ((Customer)Application.main_User).addOrder(o);
//                        System.out.println("Order placed: " + o.getOrderId());
//                    }
//                    break;
//                case 20:
//                    if (Application.main_User instanceof Customer)
//                        for (Order o: ((Customer)Application.main_User).getOrderHistory())
//                            System.out.println(o.getOrderId()+" - "+o.getStatus());
//                    break;
//                case 21:
//                    if (Application.main_User instanceof Customer) {
//                        Order last = ((Customer)Application.main_User).getOrderHistory().get(0);
//                        Invoice inv = InvoiceGenerator.generateInvoice(last);
//                        System.out.println("Invoice " + inv.getInvoiceId()+" Total: "+inv.getTotal());
//                        EmailService.sendInvoice(inv,Application.main_User.getEmail());
//                    }
//                    break;
//                case 22:
//                    if (Application.main_User instanceof Customer) {
//                        DailyFinancialReport dr = FinancialReportService.generateDailyReport(((Customer)Application.main_User).getOrderHistory());
//                        System.out.println("Revenue: " + dr.getTotalRevenue());
//                    }
//                    break;
//                case 23:
//                    Map<String,Integer> sd = new HashMap<>();
//                    sd.put("A",5); sd.put("B",3);
//                    System.out.println("Top: " + SalesAnalyzer.topPerformingProduct(sd));
//                    break;
//                case 24:
//                    if (Application.main_User instanceof Customer) {
//                        System.out.print("Allergy Type: "); String at = scanner.nextLine();
//                        System.out.print("Desc: "); String ad = scanner.nextLine();
//                        ((Customer)Application.main_User).addAllergy(new Allergy(at,ad));
//                    }
//                    break;
//                case 0:
//                    scanner.close();
//                    return;
//            }
//        }
//    }
//}
package ook_project;

import java.security.SecureRandom;
import java.util.Scanner;

import static ook_project.EmailService.sendNotification;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Application.initializeSystem();
        while (true) {
            System.out.println("\n--- Special Cook Project Management ---");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice =  scanner.nextLine();


            switch (choice) {
                case "1":
                    System.out.print("Role (customer/supplier/admin/chef/kitchenManager): ");
                    String role = scanner.nextLine();
                    System.out.print("ID: "); String id = scanner.nextLine();
                    System.out.print("Name: "); String name = scanner.nextLine();
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("Password: "); String pwd = scanner.nextLine();
                    System.out.print("Phone: "); String phone = scanner.nextLine();
                    SecureRandom random = new SecureRandom();
                    int verificationCode = 10000 + random.nextInt(90000);

sendNotification(email,  "Your code is " + verificationCode + "\nPlease don't share this code with anyone");
                    System.out.println("We have sent a verification code to your email\nPlease write it here");

               for(int i=0;i<3;i++){
                   int code=scanner.nextInt();
                   if(code==verificationCode){
                       Application.registerUser(id, name, email, pwd, phone, role);
                       System.out.println(Application.getConfirmationMessage());
                       break;
                   }else{
                       System.out.println("Invalid Verification Code\nTry again!");

                   }
               }


                    break;
                case "2":
                    System.out.print("Email: "); String le = scanner.nextLine();
                    System.out.print("Password: "); String lp = scanner.nextLine();
                    Application.loginUser(le, lp);
                    if (Application.isUserLoggedIn()) {
                        System.out.println("Logged in as " + Application.mainUser.getRole());
                        String roleLoggedIn = Application.mainUser.getRole();
                        switch (roleLoggedIn) {
                            case "Admin":
                                AdminMenu.show(scanner);
                                break;
                            case "Customer":
                                CustomerMenu.show(scanner);
                                break;
                            case "Chef":
                                ChefMenu.show(scanner);
                                break;
                            case "KitchenManager":
                                KitchenManagerMenu.show(scanner);
                                break;
                            case "Supplier":
                                SupplierMenu.show(scanner);
                                break;
                            default:
                                System.out.println("Unknown role.");
                                break;
                        }
                    } else {
                        System.out.println(Application.getLoginErrorMessage());
                    }
                    break;
                case "0":
                    scanner.close();
                    return;
            }
        }
    }
}
