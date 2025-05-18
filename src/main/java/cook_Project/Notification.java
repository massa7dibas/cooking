package cook_Project;



public class Notification {
    private String chefName;
    private String taskId;
    private String message;
    public Notification(String chefName, String taskId, String message) {
        this.chefName = chefName;
        this.taskId = taskId;
        this.message = message;
    }

    public String getChefName() { return chefName; }
    public String getTaskId() { return taskId; }
    public String getMessage() { return message; }}