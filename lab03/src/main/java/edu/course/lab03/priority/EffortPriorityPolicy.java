package edu.course.lab03.priority;

import edu.course.lab03.priority.task.ProjectTask;

public class EffortPriorityPolicy  implements PriorityPolicy{
    @Override
    public int calculatePriority(ProjectTask task) {
        return task.getEstimatedHours();
    }
}
