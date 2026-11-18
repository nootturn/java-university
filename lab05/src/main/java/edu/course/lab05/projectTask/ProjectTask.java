package edu.course.lab05.projectTask;

public final class ProjectTask {
    private final String id;
    private final String title;
    private final String category;
    private final int estimatedHours;
    private final TaskStatus status;

    public ProjectTask(String id, String title, String category,
                       int estimatedHours, TaskStatus status) {
        if (id == null || id.isBlank() || title == null || title.isBlank()
                || category == null || category.isBlank()
                || estimatedHours < 0 || status == null) {
            throw new IllegalArgumentException("Invalid task fields");
        }
        this.id = id;
        this.title = title;
        this.category = category;
        this.estimatedHours = estimatedHours;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public TaskStatus getStatus() {
        return status;
    }
}