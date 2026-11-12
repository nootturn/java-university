package edu.course.lab04;

import edu.course.lab04.projectTask.ImportanceTaskStatus;
import edu.course.lab04.projectTask.ProjectTask;
import edu.course.lab04.projectTask.TaskStatus;
import edu.course.lab04.storage.IssueId;
import edu.course.lab04.storage.IssueRegistry;
import edu.course.lab04.storage.MutableIssueId;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class IssueRegistryTest {

    private final ProjectTask task1 = new ProjectTask("t1", "Fix bug", TaskStatus.TODO, ImportanceTaskStatus.MEDIUM, 10);
    private final ProjectTask task2 = new ProjectTask("t2", "Add feature", TaskStatus.IN_PROGRESS, ImportanceTaskStatus.HIGH, 5);

    @Test
    void twoEqualKeys_shouldFindSameValue() {
        IssueRegistry registry = new IssueRegistry();
        IssueId id1 = new IssueId("PROJ", 1);
        IssueId id2 = new IssueId("PROJ", 1);

        registry.put(id1, task1);

        assertEquals(task1, registry.getTaskById(id2));
        assertTrue(registry.contains(id2));
    }

    @Test
    void putWithEqualKey_shouldReplaceValueAndNotIncreaseSize() {
        IssueRegistry registry = new IssueRegistry();
        IssueId id1 = new IssueId("PROJ", 1);
        IssueId id2 = new IssueId("PROJ", 1);

        registry.put(id1, task1);
        assertEquals(1, registry.size());

        registry.put(id2, task2);
        assertEquals(1, registry.size());
        assertEquals(task2, registry.getTaskById(id1));
    }

    @Test
    void differentKeys_shouldKeepDifferentValues() {
        IssueRegistry registry = new IssueRegistry();
        IssueId id1 = new IssueId("AA", 1);
        IssueId id2 = new IssueId("BB", 1);

        registry.put(id1, task1);
        registry.put(id2, task2);

        assertEquals(2, registry.size());
        assertEquals(task1, registry.getTaskById(id1));
        assertEquals(task2, registry.getTaskById(id2));
    }

    @Test
    void remove_shouldDeleteEntry() {
        IssueRegistry registry = new IssueRegistry();
        IssueId id = new IssueId("PROJ", 5);

        registry.put(id, task1);
        assertEquals(task1, registry.deleteTaskById(id));
        assertFalse(registry.contains(id));
        assertEquals(0, registry.size());
    }

    @Test
    void mutableKey_afterChange_searchStopsWorking() {
        Map<MutableIssueId, ProjectTask> map = new HashMap<>();
        MutableIssueId key = new MutableIssueId("PROJ", 10);

        map.put(key, task1);
        assertEquals(task1, map.get(key));

        // меняем поле участвующее в equals/hashCode
        key.setNumber(99);

        // поиск перестаёт работать
        assertNull(map.get(key));
        assertNull(map.get(new MutableIssueId("PROJ", 10)));
    }
}
