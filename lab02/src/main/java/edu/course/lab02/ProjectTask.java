package edu.course.lab02;

public class ProjectTask {
    private TaskId id;
    private String title;
    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(String id, String title, TaskStatus status, int estimatedHours) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }

        if (status == null) {
            throw new IllegalArgumentException("status must not be blank");
        }

        if (estimatedHours <= 0) {
            throw new IllegalArgumentException("hours must not be negative");
        }

        this.id = new TaskId(id);
        this.title = title;
        this.status = status;
        this.estimatedHours = estimatedHours;
    }

    public void changeStatus(TaskStatus newStatus) {

        if (newStatus == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.status = newStatus;
    }

    public boolean isCompleted() {

        return this.status == TaskStatus.DONE;
    }

    public void addEstimateHours(int addHours) {

        if (addHours <= 0) {
            throw new IllegalArgumentException("hours must be positive");
        }
        this.estimatedHours += addHours;
    }

    public String getId() {
        return this.id.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public int getEstimatedHours() {
        return this.estimatedHours;
    }

}
