package edu.course.lab06;

import edu.course.lab06.projectTask.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsTest {

    @Test
    void boxesKeepTheirTypes() {
        Box<String> text = new Box<>("Java");
        text.setContent("Git");
        assertEquals("Git", text.getContent());

        Box<Integer> number = new Box<>(10);
        number.setContent(20);
        assertEquals(20, number.getContent());

    }

    @Test
    void stackWorksWithStringsAndIntegers() {
        Stack<String> stack = new Stack<>();

        assertTrue(stack.isEmpty());
        assertThrows(NoSuchElementException.class, stack::peek);
        assertThrows(NoSuchElementException.class, stack::pop);
        assertThrows(NullPointerException.class, () -> stack.push(null));

        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());
        assertEquals(2, stack.size());

        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertTrue(stack.isEmpty());

        Stack<Integer> numbers = new Stack<>();
        numbers.push(1);
        numbers.push(2);

        assertEquals(2, numbers.pop());
        assertEquals(1, numbers.peek());
    }

    private ProjectTask task(String id, String category,
                             int hours, TaskStatus status) {
        return new ProjectTask(id, "Task " + id, category, hours, status);
    }

    @Test
    void analyticsPreservesInputAndSortsTies() {
        ProjectTask b = task("B", "backend", 2, TaskStatus.TODO);
        ProjectTask a = task("A", "frontend", 2, TaskStatus.DONE);
        ProjectTask c = task("C", "backend", 1, TaskStatus.TODO);

        List<ProjectTask> input = new ArrayList<>(List.of(b, a, c));
        List<ProjectTask> before = new ArrayList<>(input);
        DataAnalytics analytics = new DataAnalytics();

        assertEquals(
                Map.of(TaskStatus.TODO, 2, TaskStatus.DONE, 1),
                analytics.countByStatus(input)
        );

        assertEquals(
                List.of("backend", "frontend"),
                new ArrayList<>(analytics.uniqueCategories(input))
        );

        // у C меньше часов ,а у A и B часы равны, поэтому сравниваются id
        assertEquals(List.of(c, a, b), analytics.sortedTasks(input));
        assertEquals(before, input);
    }

    @Test
    void analyticsHandlesEmptyAndSingle() {
        DataAnalytics analytics = new DataAnalytics();

        assertTrue(analytics.countByStatus(List.of()).isEmpty());
        assertTrue(analytics.uniqueCategories(List.of()).isEmpty());
        assertTrue(analytics.sortedTasks(List.of()).isEmpty());

        ProjectTask large = task(
                "A", "backend", Integer.MAX_VALUE, TaskStatus.READY
        );

        assertEquals(List.of(large), analytics.sortedTasks(List.of(large)));

        ProjectTask zero = task("B", "other", 0, TaskStatus.TODO);

        assertEquals(
                List.of(zero, large),
                analytics.sortedTasks(List.of(large, zero))
        );
    }
}