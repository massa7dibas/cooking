package cook_Project;

import java.util.*;
import java.util.Comparator;

import static cook_Project.Application.getAllTasks;

public class SchedulerService {
    public static List<Task> scheduleTasks() {
        List<Chef> chefs = new ArrayList<>();
        List<Task> tasks =getAllTasks();

        tasks.sort(Comparator.comparing(Task::getScheduledTime));
        for(int i=0;i<tasks.size();i++){

        Task task=  tasks.get(i);
            Chef best = chefs.stream()
                    .filter(c -> c.getExperienceLevel() >= task.getExpertiseRequired())
                    .min(Comparator.comparingInt(Chef::getincrementLoad))
                    .orElse(null);
            if (best != null) {
                task.setAssignedChefId(best.id);
                best.getincrementLoad();
                NotificationService.queueNotification(new Notification(
                        best.getName(),
                        task.getTaskId(),
                        "Reminder: You have task " + task.getTaskId() + " scheduled at " + task.getScheduledTime() + "."
                ));
            }
        }
        return tasks;
    }}