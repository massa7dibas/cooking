package cook_Project;

import java.util.*;

public class ChefMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Chef Menu ---");
            System.out.println("1. View Assigned Tasks");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Notifications");
            System.out.println("4. Edit Profile");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    for (Task t : Application.getAllTasks()) {
                        if (t.getAssignedChefId() != null && t.getAssignedChefId().equals(Application.main_User.getId())) {
                            System.out.println(t.getTaskId() + " - " + t.getDescription() + " Scheduled: " + t.getScheduledTime());
                        }
                    }
                    break;
                case 2:
                    for (Task t : Application.getAllTasks()) {
                        System.out.println(t.getTaskId() + ": " + t.getDescription() + " Assigned Chef: " + t.getAssignedChefId());
                    }
                    break;
                case 3:
                    for (Notification n : NotificationService.getNotifications()) {
                        if (n.getChefName().equals(Application.main_User.getName())) {
                            System.out.println("From: " + n.getChefName() + " - " + n.getMessage());
                        }
                    }
                    break;
                case 4:
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
