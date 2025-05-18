Feature: Scheduling and Task Management

Scenario: Assign tasks to chefs based on experience and workload
Given the following chefs exist:
| Chef ID | Name    | Experience Level | Current Load |
| c1      | Ahmad   | 5                | 2            |
| c2      | Sara    | 3                | 1            |
| c3      | Youssef | 7                | 4            |
And the following tasks are created:
| Task ID | Description            | Expertise Required | Scheduled Time       |assignedChefId|
| t1      | Prepare appetizers     | 2                  | 2025-05-09T08:00:00Z |c1            |
| t2      | Cook main course       | 5                  | 2025-05-09T09:00:00Z |c2            |
| t3      | Bake desserts          | 4                  | 2025-05-09T10:00:00Z |c3            |
When the system schedules tasks
Then tasks should be assigned as follows:
| Task ID | Assigned Chef |
| t1      | Sara          |
| t2      | Ahmad         |
| t3      | Youssef       |
And notifications should be sent to each assigned chef

Scenario: Send notifications for scheduled tasks
Given the following scheduled tasks:
| Task ID | Assigned Chef | Scheduled Time       |Message                                                            |
| t1      | Sara          | 2025-05-09T08:00:00Z |"Reminder: You have task t1 scheduled at 2025-05-09T08:00:00Z."   |
| t2      | Ahmad         | 2025-05-09T09:00:00Z |"Reminder: You have task t2 scheduled at 2025-05-09T09:00:00Z."   |
When the notification service sends reminders
Then the following notifications should be generated:
| Chef    | Task ID | Message                                                            |
| Sara    | t1      | "Reminder: You have task t1 scheduled at 2025-05-09T08:00:00Z."   |
| Ahmad   | t2      | "Reminder: You have task t2 scheduled at 2025-05-09T09:00:00Z."   |