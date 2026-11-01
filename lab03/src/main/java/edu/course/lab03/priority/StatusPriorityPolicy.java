package edu.course.lab03.priority;

import edu.course.lab03.priority.task.ProjectTask;

public class StatusPriorityPolicy implements PriorityPolicy{
    @Override
    public int calculatePriority(ProjectTask task) {
        return switch (task.getStatus()) {
            case DONE -> 1;
            case IN_PROGRESS -> 2;
            case TODO -> 3;
        };
    }
}
