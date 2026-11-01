package edu.course.lab03.priority.task;

public class ProjectTask {
    private TaskId id;
    private String title;
    private TaskStatus status;
    private ImportanceTaskStatus importance; // новый статус у таска
    private int estimatedHours;

    public ProjectTask(String id, String title, TaskStatus status, ImportanceTaskStatus importance, int estimatedHours) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }

        if (status == null) {
            throw new IllegalArgumentException("status must not be blank");
        }

        if (importance == null){
            throw new IllegalArgumentException("importance status must not be null");
        }

        if (estimatedHours <= 0) {
            throw new IllegalArgumentException("hours must not be negative");
        }

        this.id = new TaskId(id);
        this.title = title;
        this.status = status;
        this.importance = importance;
        this.estimatedHours = estimatedHours;
    }

    public void changeStatus(TaskStatus status) {

        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.status = status;
    }
    // новый статус важности таска
    public void changeImportanceStatus(ImportanceTaskStatus importance) {
        if (importance == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.importance = importance;
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
    // геттер для сложности таска
    public ImportanceTaskStatus getImportanceStatus() {
        return this.importance;
    }

    public int getEstimatedHours() {
        return this.estimatedHours;
    }

}
