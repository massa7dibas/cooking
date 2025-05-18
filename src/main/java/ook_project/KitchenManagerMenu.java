package ook_project;

import java.time.ZonedDateTime;
import java.util.*;

import static ook_project.Application.logger;

public class KitchenManagerMenu {
    public static void show(Scanner scanner) {
        while (true) {
            logger.info("\n--- Kitchen Manager Menu ---");
            logger.info("1. Add Inventory Item");
            logger.info("2. Suggest Restock");
            logger.info("3. Schedule Tasks");
            logger.info("4. Add Task");
            logger.info("5. View Inventory Items");
            logger.info("6. View Tasks");
            logger.info("7. Edit Profile");
            logger.info("0. Logout");
            logger.info("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    logger.info("Ingredient ID: "); String iid = scanner.nextLine();
                    logger.info("QOH: "); int qoh = Integer.parseInt(scanner.nextLine());
                    logger.info("Threshold: "); int th = Integer.parseInt(scanner.nextLine());
                    logger.info("Reorder Qty: "); int rq = Integer.parseInt(scanner.nextLine());
                    Application.addItem(new InventoryItem(iid, qoh, th, rq));
                    break;
                case 2:
                    List<RestockSuggestion> rs = InventoryService.suggestRestock();
                    for (RestockSuggestion r : rs)
                        logger.info(r.getIngredientId() + ": Qty = " + r.getSuggestedQuantity() + ", Price: " + r.getPriceQuote().getPrice());
                    break;

                case 3:
                    List<Task> tasks = SchedulerService.scheduleTasks();
                    for (Task t : tasks)
                        logger.info(t.getTaskId() + " assigned to Chef: " + t.getAssignedChefId());
                    break;
                case 4:
                    logger.info("Task ID: "); String tid = scanner.nextLine();
                    logger.info("Description: "); String desc = scanner.nextLine();
                    logger.info("Expertise Required: "); int exp = Integer.parseInt(scanner.nextLine());
                    logger.info("Scheduled Time (ISO): "); ZonedDateTime time = ZonedDateTime.parse(scanner.nextLine());
                    Application.addTask(new Task(tid, desc, exp, time, null));
                    break;
                case 5:
                    for (InventoryItem item : Application.getAllItems())
                        logger.info(item.getIngredientId() + ": QOH = " + item.getQuantityOnHand() + ", Threshold = " + item.getReorderThreshold());
                    break;
                case 6:
                    for (Task t : Application.getAllTasks())
                        logger.info(t.getTaskId() + ": " + t.getDescription() + ", Chef: " + t.getAssignedChefId());
                    break;
                case 7:
                    logger.info("New Name: "); String nn = scanner.nextLine();
                    logger.info("New Email: "); String ne = scanner.nextLine();
                    logger.info("New Phone: "); String np = scanner.nextLine();
                    Application.updateUserProfile(nn, ne, np);
                    logger.info(Application.getProfileUpdateMessage());
                    break;
                case 0:
                    return;
            }
        }
    }
}