package ook_project;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static final ArrayList<Notification> notifications = new ArrayList<>();
    public static void queueNotification(Notification notification) { notifications.add(notification); }
    public static List<Notification> getNotifications() { return new ArrayList<>(notifications); }
    public static void sendAll() { notifications.clear(); }


}