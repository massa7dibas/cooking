package ook_project;

import java.time.ZonedDateTime;
import java.util.*;

public class KitchenManagerMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Kitchen Manager Menu ---");
            System.out.println("1. Add Inventory Item");
            System.out.println("2. Suggest Restock");
            System.out.println("3. Schedule Tasks");
            System.out.println("4. Add Task");
            System.out.println("5. View Inventory Items");
            System.out.println("6. View Tasks");
            System.out.println("7. Edit Profile");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Ingredient ID: "); String iid = scanner.nextLine();
                    System.out.print("QOH: "); int qoh = Integer.parseInt(scanner.nextLine());
                    System.out.print("Threshold: "); int th = Integer.parseInt(scanner.nextLine());
                    System.out.print("Reorder Qty: "); int rq = Integer.parseInt(scanner.nextLine());
                    Application.addItem(new InventoryItem(iid, qoh, th, rq));
                    break;
                case 2:
                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
                    for (RestockSuggestion r : rs)
                        System.out.println(r.getIngredientId() + ": Qty = " + r.getSuggestedQuantity() + ", Price: " + r.getPriceQuote().getPrice());
                    break;

                case 3:
                    List<Task> tasks = SchedulerService.scheduleTasks();
                    for (Task t : tasks)
                        System.out.println(t.getTaskId() + " assigned to Chef: " + t.getAssignedChefId());
                    break;
                case 4:
                    System.out.print("Task ID: "); String tid = scanner.nextLine();
                    System.out.print("Description: "); String desc = scanner.nextLine();
                    System.out.print("Expertise Required: "); int exp = Integer.parseInt(scanner.nextLine());
                    System.out.print("Scheduled Time (ISO): "); ZonedDateTime time = ZonedDateTime.parse(scanner.nextLine());
                    Application.addTask(new Task(tid, desc, exp, time, null));
                    break;
                case 5:
                    for (InventoryItem item : Application.getAllItems())
                        System.out.println(item.getIngredientId() + ": QOH = " + item.getQuantityOnHand() + ", Threshold = " + item.getReorderThreshold());
                    break;
                case 6:
                    for (Task t : Application.getAllTasks())
                        System.out.println(t.getTaskId() + ": " + t.getDescription() + ", Chef: " + t.getAssignedChefId());
                    break;
                case 7:
                    System.out.print("New Name: "); String nn = scanner.nextLine();
                    System.out.print("New Email: "); String ne = scanner.nextLine();
                    System.out.print("New Phone: "); String np = scanner.nextLine();
                    Application.updateUserProfile(nn, ne, np);
                    System.out.println(Application.getProfileUpdateMessage());
                    break;
                case 0:
                    return;
            }
        }
    }
}