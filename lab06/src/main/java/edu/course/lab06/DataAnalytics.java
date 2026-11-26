package edu.course.lab06;

import java.util.*;

import edu.course.lab06.projectTask.*;

public class DataAnalytics {
    public Map<TaskStatus, Integer> countByStatus(List<ProjectTask> tasks) {
        Map<TaskStatus, Integer> counts = new EnumMap<>(TaskStatus.class);

        for (ProjectTask task : tasks){
            TaskStatus status = task.getStatus();
            counts.put(status, counts.getOrDefault(status, 0) + 1);
        }
        return counts;
    }

    public Set<String> uniqueCategories(List<ProjectTask> tasks){
        Set<String> categories = new LinkedHashSet<>();

        for (ProjectTask task : tasks){
            categories.add(task.getCategory());
        }

        return categories;
    }

    public List<ProjectTask> sortedTasks(List<ProjectTask> tasks) {
        List<ProjectTask> copy = new ArrayList<>(tasks);

        copy.sort(
                Comparator.comparingInt(ProjectTask::getEstimatedHours)
                        .thenComparing(ProjectTask::getId)
        );

        return copy;
    }
}
