package edu.course.lab03;

import edu.course.lab03.priority.EffortPriorityPolicy;
import edu.course.lab03.priority.ImportancePriorityPolicy;
import edu.course.lab03.priority.StatusPriorityPolicy;
import edu.course.lab03.priority.TaskPrioritizer;
import edu.course.lab03.priority.task.ProjectTask;
import edu.course.lab03.priority.task.TaskStatus;
import edu.course.lab03.priority.task.ImportanceTaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrioritizerTest {

    private final ProjectTask lowImportance = new ProjectTask("1", "Low task", TaskStatus.TODO, ImportanceTaskStatus.LOW, 5);

    private final ProjectTask mediumImportance = new ProjectTask("2", "Medium task", TaskStatus.TODO, ImportanceTaskStatus.MEDIUM, 5);

    private final ProjectTask highImportance = new ProjectTask("3", "High task", TaskStatus.TODO, ImportanceTaskStatus.HIGH, 5);

    private final ProjectTask easyTask = new ProjectTask("4", "Easy", TaskStatus.TODO, ImportanceTaskStatus.MEDIUM, 2);

    private final ProjectTask hardTask = new ProjectTask("5", "Hard", TaskStatus.TODO, ImportanceTaskStatus.MEDIUM, 10);

    private final ProjectTask todoTask = new ProjectTask("6", "Todo", TaskStatus.TODO, ImportanceTaskStatus.MEDIUM, 5);

    private final ProjectTask doneTask = new ProjectTask("7", "Done", TaskStatus.DONE, ImportanceTaskStatus.MEDIUM, 5);



    @Test
    void effortPolicy_shouldPreferTaskWithMoreHours() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());

        ProjectTask result = prioritizer.chooseHigherPriority(easyTask, hardTask);

        assertEquals(hardTask, result);
    }

    @Test
    void effortPolicy_shouldReturnFirst_whenPrioritiesEqual() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());

        ProjectTask sameHours1 = new ProjectTask("a", "A", TaskStatus.TODO, ImportanceTaskStatus.LOW, 5);
        ProjectTask sameHours2 = new ProjectTask("b", "B", TaskStatus.TODO, ImportanceTaskStatus.HIGH, 5);

        ProjectTask result = prioritizer.chooseHigherPriority(sameHours1, sameHours2);

        assertEquals(sameHours1, result);
    }

    @Test
    void statusPolicy_shouldPreferTodoOverDone() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());

        ProjectTask result = prioritizer.chooseHigherPriority(todoTask, doneTask);

        assertEquals(todoTask, result);
    }

    @Test
    void statusPolicy_shouldPreferInProgressOverDone() {
        ProjectTask inProgress = new ProjectTask("8", "In progress", TaskStatus.IN_PROGRESS, ImportanceTaskStatus.MEDIUM, 5);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());

        ProjectTask result = prioritizer.chooseHigherPriority(inProgress, doneTask);

        assertEquals(inProgress, result);
    }

    @Test
    void importancePolicy_shouldPreferHighOverLow() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new ImportancePriorityPolicy());

        ProjectTask result = prioritizer.chooseHigherPriority(lowImportance, highImportance);

        assertEquals(highImportance, result);
    }

    @Test
    void importancePolicy_shouldPreferHighOverMedium() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new ImportancePriorityPolicy());

        ProjectTask result = prioritizer.chooseHigherPriority(mediumImportance, highImportance);

        assertEquals(highImportance, result);
    }

    @Test
    void importancePolicy_shouldPreferMediumOverLow() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new ImportancePriorityPolicy());

        ProjectTask result = prioritizer.chooseHigherPriority(lowImportance, mediumImportance);

        assertEquals(mediumImportance, result);
    }

    @Test
    void shouldAllowReplacingPolicy() {
        TaskPrioritizer byEffort = new TaskPrioritizer(new EffortPriorityPolicy());
        assertEquals(hardTask, byEffort.chooseHigherPriority(easyTask, hardTask));

        TaskPrioritizer byImportance = new TaskPrioritizer(new ImportancePriorityPolicy());
        ProjectTask result = byImportance.chooseHigherPriority(easyTask, hardTask);
        assertNotNull(result);
    }

    @Test
    void shouldThrow_whenFirstTaskIsNull() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());

        assertThrows(IllegalArgumentException.class,
                () -> prioritizer.chooseHigherPriority(null, hardTask));
    }

    @Test
    void shouldThrow_whenSecondTaskIsNull() {
        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());

        assertThrows(IllegalArgumentException.class,
                () -> prioritizer.chooseHigherPriority(hardTask, null));
    }

    @Test
    void shouldThrow_whenPolicyIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new TaskPrioritizer(null));
    }
}