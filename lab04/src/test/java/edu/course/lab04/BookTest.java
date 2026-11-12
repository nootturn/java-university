package edu.course.lab04;

import edu.course.lab04.book.Book;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void booksWithSameIsbn_shouldBeEqual() {
        Book book1 = new Book("978-3-16-148410-0", "Clean Code");
        Book book2 = new Book("978-3-16-148410-0", "Another Title");

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void booksWithDifferentIsbn_shouldNotBeEqual() {
        Book book1 = new Book("978-3-16-148410-0", "Clean Code");
        Book book2 = new Book("978-0-13-235088-4", "Clean Code");

        assertNotEquals(book1, book2);
    }

    @Test
    void valuePutByOneBook_shouldBeFoundByAnotherWithSameIsbn() {
        Map<Book, String> map = new HashMap<>();
        Book book1 = new Book("978-3-16-148410-0", "Clean Code");
        Book book2 = new Book("978-3-16-148410-0", "Different Title");

        map.put(book1, "stored-value");

        assertEquals("stored-value", map.get(book2));
    }
}