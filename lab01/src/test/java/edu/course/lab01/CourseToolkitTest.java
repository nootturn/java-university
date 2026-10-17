package edu.course.lab01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnErrorForAverageNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CourseToolkit.average(null);
        });
    }

    @Test
    void checkNegativeAverageNumber() {
        int[] arr = {-1, -2, -6};
        double result = CourseToolkit.average(arr);

        assertEquals(result, -3.0);
    }

    @Test
    void checkPositiveAverageNumber() {
        int[] arr = {1, 2, 6};
        double result = CourseToolkit.average(arr);

        assertEquals(result, 3.0);
    }

    @ParameterizedTest
    @CsvSource({
            "level, true",
            "apple, false",
            "radar, true",
            "deed, true"
    })
    void checksWordIsPalindrome(String word, boolean result) {
        boolean actual = CourseToolkit.isPalindrome(word);

        assertEquals(result, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "-1, false",
            "0, false",
            "1, false",
            "2, true",
            "4, false",
            "7, true",
    })
    void checksNumberIsPrime(int number, boolean result) {
        boolean actual = CourseToolkit.isPrime(number);

        assertEquals(result, actual);
    }

    @Test
    void returnMinValueInArray(){
        int[] arr = {8, 4, 5, -1, 7};

        int result = CourseToolkit.minValueInIntegerArray(arr);

        assertEquals(result, -1);
    }

    @Test
    void checkMaxValueInArray() {
        int[] arr = {8, 4, 5, -1, 7};

        int result = CourseToolkit.maxValueInIntegerArray(arr);

        assertEquals(result, 8);
    }
}
