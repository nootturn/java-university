package edu.course.lab03.priority;

import edu.course.lab03.priority.task.ProjectTask;

public class TaskPrioritizer {
    private PriorityPolicy policy;

    public TaskPrioritizer (PriorityPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("Policy must not be null");
        }
        this.policy = policy;
    }

    public ProjectTask chooseHigherPriority (ProjectTask firstTask, ProjectTask secondTask) {
        if (firstTask == null || secondTask == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }
        int priorityFirst = policy.calculatePriority(firstTask);
        int prioritySecond = policy.calculatePriority(secondTask);

        return priorityFirst >= prioritySecond ? firstTask : secondTask;
    }
}
