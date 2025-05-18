package ook_project;

import java.util.*;

import static ook_project.Application.logger;

public class ChefMenu {
    public static void show(Scanner scanner) {
        while (true) {
            logger.info("\n--- Chef Menu ---");
            logger.info("1. View Assigned Tasks");
            logger.info("2. View All Tasks");
            logger.info("3. View Notifications");
            logger.info("4. Edit Profile");
            logger.info("0. Logout");
            logger.info("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    for (Task t : Application.getAllTasks()) {
                        if (t.getAssignedChefId() != null && t.getAssignedChefId().equals(Application.mainUser.getId())) {
                            logger.info(t.getTaskId() + " - " + t.getDescription() + " Scheduled: " + t.getScheduledTime());
                        }
                    }
                    break;
                case 2:
                    for (Task t : Application.getAllTasks()) {
                        logger.info(t.getTaskId() + ": " + t.getDescription() + " Assigned Chef: " + t.getAssignedChefId());
                    }
                    break;
                case 3:
                    for (Notification n : NotificationService.getNotifications()) {
                        if (n.getChefName().equals(Application.mainUser.getName())) {
                            logger.info("From: " + n.getChefName() + " - " + n.getMessage());
                        }
                    }
                    break;
                case 4:
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
