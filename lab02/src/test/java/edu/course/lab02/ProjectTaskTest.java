package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProjectTaskTest {

    @Test
    void testInitTask(){

        ProjectTask task = new ProjectTask("uuid:1", "test", TaskStatus.TODO, 5);

        assertEquals("uuid:1", task.getId());
        assertEquals("test", task.getTitle());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(5, task.getEstimatedHours());
        assertFalse(task.isCompleted());
    }

    @Test
    void returnErrorWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectTask(null, "test", TaskStatus.TODO, 5);
        });
    }

    @Test
    void returnErrorWhenIdIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectTask("   ", "test", TaskStatus.TODO, 5);
        });
    }

    @Test
    void returnErrorWhenTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectTask("uuid:1", null, TaskStatus.TODO, 5);
        });
    }

    @Test
    void returnErrorWhenTitleIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectTask("uuid:1", "  ", TaskStatus.TODO, 5);
            });
    }

    @Test
    void returnErrorWhenEstimatedHoursIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectTask("uuid:1", "test", TaskStatus.TODO, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectTask("uuid:1", "test", TaskStatus.TODO, -3);
        });
    }

    @Test
    void testChangeStatus() {
        ProjectTask task = new ProjectTask("uuid:1", "test", TaskStatus.TODO, 5);

        task.changeStatus(TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertFalse(task.isCompleted());
    }

    @Test
    void returnTrueWhenTaskIsCompleted() {
        ProjectTask task = new ProjectTask("uuid:1", "test", TaskStatus.TODO, 5);

        task.changeStatus(TaskStatus.DONE);

        assertTrue(task.isCompleted());
    }

    @Test
    void testAddEstimatedHours() {
        ProjectTask task = new ProjectTask("uuid:1", "test", TaskStatus.TODO, 5);

        task.addEstimateHours(3);

        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void returnErrorWhenAddEstimateWithZeroOrNegative() {
        ProjectTask task = new ProjectTask("uuid:1", "test", TaskStatus.TODO, 5);

        assertThrows(IllegalArgumentException.class, () -> task.addEstimateHours(0));
        assertThrows(IllegalArgumentException.class, () -> task.addEstimateHours(-2));
    }
}
