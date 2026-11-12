package edu.course.lab04;

import edu.course.lab04.storage.IssueId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IssueIdTest {

    @Test
    void equalIds_shouldHaveSameHashCode() {
        IssueId id1 = new IssueId("PROJ", 42);
        IssueId id2 = new IssueId("PROJ", 42);

        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void differentIds_shouldNotBeEqual() {
        IssueId id1 = new IssueId("PROJ", 42);
        IssueId id2 = new IssueId("PROJ", 43);

        assertNotEquals(id1, id2);
    }

    @Test
    void equals_shouldReturnFalseForNull() {
        IssueId id = new IssueId("PROJ", 42);
        assertFalse(id.equals(null));
    }

    @Test
    void equals_shouldReturnFalseForDifferentType() {
        IssueId id = new IssueId("PROJ", 42);
        assertFalse(id.equals("PROJ-42"));
    }
}
