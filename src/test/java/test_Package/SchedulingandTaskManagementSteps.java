package test_Package;
import static ook_project.Application.*;
import static org.junit.Assert.*;
import ook_project.Chef;
import ook_project.Task;
import ook_project.SchedulerService;
import ook_project.Notification;
import ook_project.NotificationService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.*;
import java.time.ZonedDateTime;


public class SchedulingandTaskManagementSteps {
    private List<Task> scheduledTasks=new ArrayList<>();
    private List<Notification> notifications;

    @Given("the following chefs exist:")
    public void the_following_chefs_exist(DataTable dataTable) {
        clearCheiflist();
     clearTasks();
        NotificationService.sendAll();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            chefListpush(new Chef(
                    row.get("Chef ID"),
                    row.get("Name"),
                    Integer.parseInt(row.get("Experience Level")),
                    Integer.parseInt(row.get("Current Load"))
            ));
        }
    }

    @Given("the following tasks are created:")
    public void the_following_tasks_are_created(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {

            Task t=new Task(
                    row.get("Task ID"),
                    row.get("Description"),
                    Integer.parseInt(row.get("Expertise Required")),
                    ZonedDateTime.parse(row.get("Scheduled Time")),
                    row.get("assignedChefId").toString()
            );

            addTask(t);
        }
    }

    @When("the system schedules tasks")
    public void the_system_schedules_tasks() {
        NotificationService.sendAll();

        scheduledTasks = SchedulerService.scheduleTasks();
    }

    @Then("tasks should be assigned as follows:")
    public void tasks_should_be_assigned_as_follows(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String taskId = row.get("Task ID");
            String expectedChefName = row.get("Assigned Chef");

            Task t = scheduledTasks.stream()
                    .filter(x -> x.getTaskId().equals(taskId))
                    .findFirst().orElse(null);


            assertNotNull(t);
            String actualChefId = t.getAssignedChefId();
            Chef assigned = getChefList().stream()
                    .filter(c -> c.getId().equals(actualChefId))
                    .findFirst().orElse(null);

            assertNotNull(assigned);

        }
    }

    @Then("notifications should be sent to each assigned chef")
    public void notifications_should_be_sent_to_each_assigned_chef() {
        notifications = NotificationService.getNotifications();

        assertTrue(notifications.isEmpty());
    }

    @Given("the following scheduled tasks:")
    public void the_following_scheduled_tasks(DataTable dataTable) {
        NotificationService.sendAll();
        System.out.println(dataTable.asMaps(String.class, String.class));
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
Notification n=new Notification(
        row.get("Assigned Chef"),
        row.get("Task ID"),
        row.get("Message")
);

            NotificationService.queueNotification(n);
        }
    }

    @When("the notification service sends reminders")
    public void the_notification_service_sends_reminders() {
        notifications = NotificationService.getNotifications();
    }

    @Then("the following notifications should be generated:")
    public void the_following_notifications_should_be_generated(DataTable dataTable) {
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        assertEquals(expected.size(), notifications.size());

        for (int i = 0; i < expected.size(); i++) {
            Map<String, String> row = expected.get(i);
            Notification n = notifications.get(i);



            assertEquals(row.get("Chef"), n.getChefName());
            assertEquals(row.get("Task ID"), n.getTaskId());
            assertEquals(row.get("Message"), n.getMessage());
        }
    }
}
