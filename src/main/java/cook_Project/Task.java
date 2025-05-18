package cook_Project;

import java.time.ZonedDateTime;

public class Task {
    private final String taskId;
    private final String description;
    private final int expertiseRequired;
    private final ZonedDateTime scheduledTime;
    private String assignedChefId;
    public Task(String taskId, String description, int expertiseRequired, ZonedDateTime scheduledTime,String assignedChefId) {
        this.taskId = taskId;
        this.description = description;
        this.expertiseRequired = expertiseRequired;
        this.scheduledTime = scheduledTime;
        this.assignedChefId=assignedChefId;

    }

    public String getTaskId() { return taskId; }
    public String getDescription() { return description; }
    public int getExpertiseRequired() { return expertiseRequired; }
    public ZonedDateTime getScheduledTime() { return scheduledTime; }
    public String getAssignedChefId() { return assignedChefId; }
    public void setAssignedChefId(String assignedChefId) { this.assignedChefId = assignedChefId; }}