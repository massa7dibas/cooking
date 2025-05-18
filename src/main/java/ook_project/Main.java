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
//            logger.info("\n--- Special Cook Project Management ---");
//            logger.info("1. Register User");
//            logger.info("2. Login");
//            logger.info("3. Edit Profile");
//            logger.info("4. Add Ingredient");
//            logger.info("5. List Ingredients");
//            logger.info("6. Add Recipe");
//            logger.info("7. List Recipes");
//            logger.info("8. Suggest Recipes (AI)");
//            logger.info("9. Validate Menu");
//            logger.info("10. Suggest Substitutions");
//            logger.info("11. Add Inventory Item");
//            logger.info("12. Suggest Restock");
//            logger.info("13. Create Purchase Order");
//            logger.info("14. Send Purchase Order");
//            logger.info("15. Add Task");
//            logger.info("16. Schedule Tasks");
//            logger.info("17. View Notifications");
//            logger.info("18. Clear Notifications");
//            logger.info("19. Place Order");
//            logger.info("20. View Order History");
//            logger.info("21. Generate Invoice");
//            logger.info("22. Generate Daily Report");
//            logger.info("23. Analyze Sales");
//            logger.info("24. Add Allergy");
//            logger.info("0. Exit");
//            logger.info("Choose an option: ");
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    logger.info("Role (customer/supplier/admin/chef/kitchenManager): ");
//                    String role = scanner.nextLine();
//                    logger.info("ID: "); String id = scanner.nextLine();
//                    logger.info("Name: "); String name = scanner.nextLine();
//                    logger.info("Email: "); String email = scanner.nextLine();
//                    logger.info("Password: "); String pwd = scanner.nextLine();
//                    logger.info("Phone: "); String phone = scanner.nextLine();
//                    Application.registerUser(id,name,email,pwd,phone,role);
//                    logger.info(Application.getConfirmationMessage());
//                    break;
//                case 2:
//                    logger.info("Email: "); String le = scanner.nextLine();
//                    logger.info("Password: "); String lp = scanner.nextLine();
//                    Application.loginUser(le,lp);
//                    if (Application.isUserLoggedIn()) logger.info("Logged in as " + Application.main_User.getRole());
//                    else logger.info(Application.getLoginErrorMessage());
//                    break;
//                case 3:
//                    logger.info("New Name: "); String nn = scanner.nextLine();
//                    logger.info("New Email: "); String ne = scanner.nextLine();
//                    logger.info("New Phone: "); String np = scanner.nextLine();
//                    Application.updateUserProfile(nn,ne,np);
//                    logger.info(Application.getProfileUpdateMessage());
//                    break;
//                case 4:
//                    logger.info("Ingredient ID: "); String iid = scanner.nextLine();
//                    logger.info("Name: "); String iname = scanner.nextLine();
//                    logger.info("Available (true/false): "); boolean avail = Boolean.parseBoolean(scanner.nextLine());
//                    logger.info("Dietary tags (comma): "); List<String> tags = Arrays.asList(scanner.nextLine().split(","));
//                    Application.addIngredient(new Ingredient(iid, iname, avail, tags));
//                    break;
//                case 5:
//                    for (Ingredient ing: Application.getAllIngredients())
//                        logger.info(ing.getId()+": " + ing.getName()+" ["+ing.isAvailable()+"] " + ing.getDietaryTags());
//                    break;
//                case 6:
//                    logger.info("Recipe ID: "); String rid = scanner.nextLine();
//                    logger.info("Name: "); String rname = scanner.nextLine();
//                    logger.info("Prep Time (min): "); int ptime = Integer.parseInt(scanner.nextLine());
//                    logger.info("Ingredients (comma ids): "); List<String> ring = Arrays.asList(scanner.nextLine().split(","));
//                    logger.info("Dietary tags (comma): "); List<String> rtags = Arrays.asList(scanner.nextLine().split(","));
//                    Recipe rec = new Recipe(rid, rname, ptime, ring, rtags);
//                    Application.addRecipe(rec);
//                    break;
//                case 7:
//                    for (Recipe r: Application.getAllRecipes()) logger.info(r.getRecipeId()+" - " + r.getName());
//                    break;
//                case 8:
//                    logger.info("Available ingredients (comma ids): "); List<String> aing = Arrays.asList(scanner.nextLine().split(","));
//                    logger.info("Dietary prefs (comma): "); List<String> dp = Arrays.asList(scanner.nextLine().split(","));
//                    logger.info("Max prep time (or blank): "); String mp = scanner.nextLine(); Integer mpt = mp.isEmpty()?null:Integer.parseInt(mp);
//                    List<Recipe> sug = AISuggestionService.suggestRecipes(new RecipeRequest(aing,dp,mpt));
//                    for (Recipe r: sug) logger.info(r.getName());
//                    break;
//                case 9:
//                    logger.info("Recipe ID to validate: "); String vrid = scanner.nextLine();
//                    Recipe vr = Application.getAllRecipes().stream().filter(r->r.getRecipeId().equals(vrid)).findFirst().orElse(null);
//                    if (vr!=null) logger.info(MenuCustomizationService.validateIngredients(vr.getIngredients(),Arrays.asList(scanner.nextLine().split(","))));
//                    break;
//                case 10:
//                    logger.info("Recipe ID to substitute: "); String srid = scanner.nextLine();
//                    Recipe sr = Application.getAllRecipes().stream().filter(r->r.getRecipeId().equals(srid)).findFirst().orElse(null);
//                    if (sr!=null) {
//                        List<Substitution> subs = MenuCustomizationService.suggestSubstitutions(sr.getIngredients(), Arrays.asList(scanner.nextLine().split(",")));
//                        for (Substitution s: subs)
//                            logger.info(s.getOriginal().getName()+" -> "+s.getSubstitute().getName());
//                    }
//                    break;
//                case 11:
//                    logger.info("Inv Item ingredient ID: "); String iiid = scanner.nextLine();
//                    logger.info("QOH: "); int qoh = Integer.parseInt(scanner.nextLine());
//                    logger.info("Threshold: "); int th = Integer.parseInt(scanner.nextLine());
//                    logger.info("Reorder Qty: "); int rq = Integer.parseInt(scanner.nextLine());
//                    Application.addItem(new InventoryItem(iiid,qoh,th,rq));
//                    break;
//                case 12:
//                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
//                    for (RestockSuggestion rcs: rs)
//                        logger.info(rcs.getIngredientId()+": qty="+rcs.getSuggestedQuantity()+" price="+rcs.getPriceQuote().getPrice());
//                    break;
//                case 13:
//                    PurchaseOrder po = PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock());
//                    logger.info(po!=null?po.getOrderId():"No PO created");
//                    break;
//                case 14:
//                    logger.info("PO ID to send: "); String poid = scanner.nextLine();
//
//                    PurchaseOrderService.sendOrder(PurchaseOrderService.createPurchaseOrder(InventoryService.suggestRestock()));
//                    break;
//                case 15:
//                    logger.info("Task ID: "); String tid = scanner.nextLine();
//                    logger.info("Description: "); String td = scanner.nextLine();
//                    logger.info("Expertise Required: "); int exp = Integer.parseInt(scanner.nextLine());
//                    logger.info("Scheduled ISO time: "); ZonedDateTime zt = ZonedDateTime.parse(scanner.nextLine());
//                    Application.addTask(new Task(tid,td,exp,zt,null));
//                    break;
//                case 16:
//                    List<Task> sched = SchedulerService.scheduleTasks();
//                    for (Task t: sched) logger.info(t.getTaskId()+" -> Chef: "+t.getAssignedChefId());
//                    break;
//                case 17:
//                    for (Notification n: NotificationService.getNotifications())
//                        logger.info(n.getChefName()+": "+n.getMessage());
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
//                        logger.info("Order placed: " + o.getOrderId());
//                    }
//                    break;
//                case 20:
//                    if (Application.main_User instanceof Customer)
//                        for (Order o: ((Customer)Application.main_User).getOrderHistory())
//                            logger.info(o.getOrderId()+" - "+o.getStatus());
//                    break;
//                case 21:
//                    if (Application.main_User instanceof Customer) {
//                        Order last = ((Customer)Application.main_User).getOrderHistory().get(0);
//                        Invoice inv = InvoiceGenerator.generateInvoice(last);
//                        logger.info("Invoice " + inv.getInvoiceId()+" Total: "+inv.getTotal());
//                        EmailService.sendInvoice(inv,Application.main_User.getEmail());
//                    }
//                    break;
//                case 22:
//                    if (Application.main_User instanceof Customer) {
//                        DailyFinancialReport dr = FinancialReportService.generateDailyReport(((Customer)Application.main_User).getOrderHistory());
//                        logger.info("Revenue: " + dr.getTotalRevenue());
//                    }
//                    break;
//                case 23:
//                    Map<String,Integer> sd = new HashMap<>();
//                    sd.put("A",5); sd.put("B",3);
//                    logger.info("Top: " + SalesAnalyzer.topPerformingProduct(sd));
//                    break;
//                case 24:
//                    if (Application.main_User instanceof Customer) {
//                        logger.info("Allergy Type: "); String at = scanner.nextLine();
//                        logger.info("Desc: "); String ad = scanner.nextLine();
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

import static ook_project.Application.logger;
import static ook_project.EmailService.sendNotification;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("\n--- Special Cook Project Management ---");
            logger.info("1. Register User");
            logger.info("2. Login");
            logger.info("0. Exit");
            logger.info("Choose an option: ");

            String choice =  scanner.nextLine();


            switch (choice) {
                case "1":
                    logger.info("Role (customer/supplier/admin/chef/kitchenManager): ");
                    String role = scanner.nextLine();
                    logger.info("ID: "); String id = scanner.nextLine();
                    logger.info("Name: "); String name = scanner.nextLine();
                    logger.info("Email: "); String email = scanner.nextLine();
                    logger.info("Password: "); String pwd = scanner.nextLine();
                    logger.info("Phone: "); String phone = scanner.nextLine();
                    SecureRandom random = new SecureRandom();
                    int verificationCode = 10000 + random.nextInt(90000);

sendNotification(email,  "Your code is " + verificationCode + "\nPlease don't share this code with anyone");
                    logger.info("We have sent a verification code to your email\nPlease write it here");

               for(int i=0;i<3;i++){
                   int code=scanner.nextInt();
                   if(code==verificationCode){
                       Application.registerUser(id, name, email, pwd, phone, role);
                       logger.info(Application.getConfirmationMessage());
                       break;
                   }else{
                       logger.info("Invalid Verification Code\nTry again!");

                   }
               }


                    break;
                case "2":
                    logger.info("Email: "); String le = scanner.nextLine();
                    logger.info("Password: "); String lp = scanner.nextLine();
                    Application.loginUser(le, lp);
                    if (Application.isUserLoggedIn()) {
                        logger.info("Logged in as " + Application.mainUser.getRole());
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
                                logger.info("Unknown role.");
                                break;
                        }
                    } else {
                        logger.info(Application.getLoginErrorMessage());
                    }
                    break;
                case "0":
                    scanner.close();
                    return;
            }
        }
    }
}
