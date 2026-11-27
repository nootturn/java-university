package edu.course.lab07;

import edu.course.lab07.projectTask.*;

import java.util.*;
import java.util.stream.Collectors;

public class DataAnalytics {

    public List<ProjectTask> filterByStatus(List<ProjectTask> tasks, TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public List<String> normalizedSorted(List<ProjectTask> tasks) {
        return tasks.stream()
                .map(DataAnalytics::normalizedCategories)
                .sorted()
                .toList();
    }

    public Map<String, Long> countByCategory(List<ProjectTask> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        DataAnalytics::normalizedCategories,
                        Collectors.counting()
                ));
    }

    public Optional<ProjectTask> mostExpensiveTask(List<ProjectTask> tasks) {
        return tasks.stream()
                .max(Comparator.comparingInt(ProjectTask::getEstimatedHours));
    }

    public List<ProjectTask> sortedTasks(List<ProjectTask> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparingInt(ProjectTask::getEstimatedHours)
                        .thenComparing(ProjectTask::getTitle))
                .toList();
    }

    public static String normalizedCategories(ProjectTask task) {
        return task.getCategory().strip().toLowerCase(Locale.ROOT);
    }
}
