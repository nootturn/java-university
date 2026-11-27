package edu.course.lab07;

import org.junit.jupiter.api.Test;
import edu.course.lab07.projectTask.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;


class DataAnalyticsTest {

    private final DataAnalytics analytics = new DataAnalytics();

    @Test
    void handlesEmptyInput() {
        List<ProjectTask> tasks = List.of();

        assertTrue(analytics.filterByStatus(tasks, TaskStatus.DONE).isEmpty());
        assertTrue(analytics.normalizedSorted(tasks).isEmpty());
        assertTrue(analytics.countByCategory(tasks).isEmpty());
        assertTrue(analytics.mostExpensiveTask(tasks).isEmpty());
        assertTrue(analytics.sortedTasks(tasks).isEmpty());
    }

    @Test
    void handlesSingleTask() {
        ProjectTask only = new ProjectTask(
                "T-1", "Login", " Backend ", 3, TaskStatus.TODO
        );
        List<ProjectTask> tasks = List.of(only);

        assertEquals(List.of(only),
                analytics.filterByStatus(tasks, TaskStatus.TODO));
        assertTrue(analytics.filterByStatus(tasks, TaskStatus.DONE).isEmpty());
        assertEquals(List.of("backend"), analytics.normalizedSorted(tasks));
        assertEquals(Map.of("backend", 1L), analytics.countByCategory(tasks));
        assertEquals(Optional.of(only), analytics.mostExpensiveTask(tasks));
        assertEquals(List.of(only), analytics.sortedTasks(tasks));
    }

    @Test
    void filtersByStatusAndKeepsInputOrder() {
        ProjectTask first = new ProjectTask(
                "T-1", "Login", "backend", 5, TaskStatus.DONE
        );
        ProjectTask second = new ProjectTask(
                "T-2", "Menu", "ui", 2, TaskStatus.TODO
        );
        ProjectTask third = new ProjectTask(
                "T-3", "Tests", "qa", 3, TaskStatus.DONE
        );

        List<ProjectTask> result = analytics.filterByStatus(
                List.of(first, second, third), TaskStatus.DONE
        );

        assertEquals(List.of(first, third), result);
    }

    @Test
    void normalizesCategoriesAndCountsTasksInEachGroup() {
        ProjectTask first = new ProjectTask(
                "T-1", "Menu", " UI ", 2, TaskStatus.TODO
        );
        ProjectTask second = new ProjectTask(
                "T-2", "Login", "Backend", 4, TaskStatus.DONE
        );
        ProjectTask third = new ProjectTask(
                "T-3", "API", " BACKEND ", 3, TaskStatus.READY
        );
        List<ProjectTask> tasks = List.of(first, second, third);

        assertEquals(
                List.of("backend", "backend", "ui"),
                analytics.normalizedSorted(tasks)
        );
        assertEquals(
                Map.of("backend", 2L, "ui", 1L),
                analytics.countByCategory(tasks)
        );
    }

    @Test
    void findsTaskWithMostEstimatedHours() {
        ProjectTask small = new ProjectTask(
                "T-1", "Menu", "ui", 2, TaskStatus.TODO
        );
        ProjectTask large = new ProjectTask(
                "T-2", "Login", "backend", 8, TaskStatus.READY
        );
        ProjectTask medium = new ProjectTask(
                "T-3", "Tests", "qa", 4, TaskStatus.DONE
        );

        Optional<ProjectTask> result =
                analytics.mostExpensiveTask(List.of(small, large, medium));

        assertEquals(Optional.of(large), result);
    }

    @Test
    void sortsByHoursThenTitleWithoutChangingInput() {
        ProjectTask beta = new ProjectTask(
                "T-1", "Beta", "backend", 5, TaskStatus.TODO
        );
        ProjectTask zeta = new ProjectTask(
                "T-2", "Zeta", "ui", 1, TaskStatus.DONE
        );
        ProjectTask alpha = new ProjectTask(
                "T-3", "Alpha", "qa", 5, TaskStatus.READY
        );

        List<ProjectTask> input = new ArrayList<>(List.of(beta, zeta, alpha));
        List<ProjectTask> before = new ArrayList<>(input);

        List<ProjectTask> result = analytics.sortedTasks(input);

        assertEquals(List.of(zeta, alpha, beta), result);
        assertEquals(before, input);
    }
}