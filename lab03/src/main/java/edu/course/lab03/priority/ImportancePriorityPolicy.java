package edu.course.lab03.priority;

import edu.course.lab03.priority.task.ProjectTask;

public class ImportancePriorityPolicy implements PriorityPolicy{
    @Override
    public int calculatePriority(ProjectTask task) {
        return switch (task.getImportanceStatus()) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> 3;
        };
    }
}
