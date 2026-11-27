package edu.course.lab07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordPreparationTest {
    @Test
    void preparesWordsWithoutChangingInput() {
        List<String> input = new ArrayList<>(List.of("java", "", "git", "maven", "api"));

        List<String> before = new ArrayList<>(input);

        List<String> result = WordPreparation.prepare(input);

        assertEquals(List.of("API", "GIT", "JAVA", "MAVEN"), result);
        assertEquals(before, input);
    }
}
