package edu.course.lab04;

import edu.course.lab04.chainedStorage.ChainedHashTable;
import edu.course.lab04.projectTask.ImportanceTaskStatus;
import edu.course.lab04.projectTask.ProjectTask;
import edu.course.lab04.projectTask.TaskStatus;
import edu.course.lab04.storage.IssueId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainedHashTableTest {

    private ChainedHashTable table;
    private ProjectTask task1;
    private ProjectTask task2;
    private ProjectTask task3;

    @BeforeEach
    void setUp() {
        table = new ChainedHashTable();
        task1 = new ProjectTask("t1", "Fix bug", TaskStatus.TODO, ImportanceTaskStatus.MEDIUM, 4);
        task2 = new ProjectTask("t2", "Add feature", TaskStatus.IN_PROGRESS, ImportanceTaskStatus.HIGH, 8);
        task3 = new ProjectTask("t3", "Refactor", TaskStatus.DONE, ImportanceTaskStatus.LOW, 3);
    }

    @Test
    void putAndGet_shouldReturnSameValue() {
        IssueId id = new IssueId("PROJ", 1);

        assertNull(table.put(id, task1));
        assertEquals(task1, table.get(id));
        assertEquals(1, table.size());
    }

    @Test
    void putWithEqualKey_shouldReplaceValueAndReturnOld() {
        IssueId id1 = new IssueId("PROJ", 10);
        IssueId id2 = new IssueId("PROJ", 10); // другой экземпляр, но логически равный

        assertNull(table.put(id1, task1));
        ProjectTask old = table.put(id2, task2);

        assertEquals(task1, old);
        assertEquals(task2, table.get(id1));
        assertEquals(1, table.size());
    }

    @Test
    void get_nonExistentKey_shouldReturnNull() {
        assertNull(table.get(new IssueId("NONE", 999)));
    }

    @Test
    void containsKey_shouldWorkCorrectly() {
        IssueId id = new IssueId("PROJ", 5);

        assertFalse(table.containsKey(id));
        table.put(id, task1);
        assertTrue(table.containsKey(id));
        assertTrue(table.containsKey(new IssueId("PROJ", 5))); // равный ключ
    }

    @Test
    void remove_shouldDeleteAndReturnValue() {
        IssueId id = new IssueId("PROJ", 7);

        table.put(id, task1);
        assertEquals(task1, table.remove(id));
        assertNull(table.get(id));
        assertFalse(table.containsKey(id));
        assertEquals(0, table.size());
    }

    @Test
    void remove_nonExistentKey_shouldReturnNull() {
        assertNull(table.remove(new IssueId("NONE", 1)));
        assertEquals(0, table.size());
    }

    @Test
    void differentKeys_shouldStoreDifferentValues() {
        IssueId id1 = new IssueId("AA", 1);
        IssueId id2 = new IssueId("BB", 1);
        IssueId id3 = new IssueId("CC", 1);

        table.put(id1, task1);
        table.put(id2, task2);
        table.put(id3, task3);

        assertEquals(3, table.size());
        assertEquals(task1, table.get(id1));
        assertEquals(task2, table.get(id2));
        assertEquals(task3, table.get(id3));
    }

    @Test
    void put_nullValue_shouldThrowException() {
        IssueId id = new IssueId("PROJ", 1);
        assertThrows(NullPointerException.class, () -> table.put(id, null));
    }

    @Test
    void put_nullKey_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> table.put(null, task1));
    }

    @Test
    void size_shouldBeZeroOnEmptyTable() {
        assertEquals(0, table.size());
    }

    @Test
    void multipleOperations_shouldKeepCorrectState() {
        IssueId id1 = new IssueId("PROJ", 1);
        IssueId id2 = new IssueId("PROJ", 2);
        IssueId id3 = new IssueId("PROJ", 3);

        table.put(id1, task1);
        table.put(id2, task2);
        table.put(id3, task3);

        assertEquals(3, table.size());

        table.remove(id2);
        assertEquals(2, table.size());
        assertNull(table.get(id2));

        // замена
        table.put(id1, task3);
        assertEquals(task3, table.get(id1));
        assertEquals(2, table.size());
    }
}
