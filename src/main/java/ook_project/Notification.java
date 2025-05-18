package ook_project;



public class Notification {
    private final String chefName;
    private final String taskId;
    private final String message;
    public Notification(String chefName, String taskId, String message) {
        this.chefName = chefName;
        this.taskId = taskId;
        this.message = message;
    }

    public String getChefName() { return chefName; }
    public String getTaskId() { return taskId; }
    public String getMessage() { return message; }}