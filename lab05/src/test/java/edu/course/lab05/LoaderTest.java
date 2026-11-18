package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {
    private static final String HEADER =
            "id;title;category;estimatedHours;status";

    @TempDir
    Path dir;

    private Path write(String... lines) throws IOException {
        Path file = dir.resolve("tasks.txt");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void preparatoryReadAndConvert() throws IOException {
        Path file = write("10", "bad", "20");
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        assertEquals(List.of("10", "bad", "20"), lines);

        List<Integer> values = new ArrayList<>();
        List<String> invalid = new ArrayList<>();

        for (String line : lines) {
            try {
                values.add(Integer.parseInt(line));
            } catch (NumberFormatException ex) {
                invalid.add(line);
            }
        }

        assertEquals(List.of(10, 20), values);
        assertEquals(List.of("bad"), invalid);
    }

    @Test
    void badNumberThrows() {
        assertThrows(NumberFormatException.class,
                () -> Integer.parseInt("bad"));
    }

    @Test
    void loadsUnicode() throws IOException {
        Path file = write(HEADER, "T-1;Релиз;backend;8;READY");

        LoadResult result = new SampleFileLoader().load(file);

        assertTrue(result.getErrors().isEmpty());
        assertEquals(1, result.getTasks().size());
        assertEquals("Релиз", result.getTasks().get(0).getTitle());
    }

    @Test
    void errorsDoNotDiscardGoodRows() throws IOException {
        Path file = write(
                HEADER,
                "T-1;Good;backend;8;READY",
                "T-2;;backend;8;READY",
                "T-3;Bad number;backend;bad;READY",
                "T-4;Bad status;backend;8;UNKNOWN",
                "T-5;Too few;backend;8",
                "T-6;Negative;backend;-1;READY",
                "T-7;Last;frontend;2;READY"
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(2, result.getTasks().size());
        assertEquals(5, result.getErrors().size());

        for (int i = 0; i < 5; i++) {
            assertEquals(i + 3, result.getErrors().get(i).getLineNumber());
        }

        assertEquals("title", result.getErrors().get(0).getField());
        assertEquals("estimatedHours", result.getErrors().get(1).getField());
        assertEquals("status", result.getErrors().get(2).getField());
    }

    @Test
    void preservesTrailingEmptyStatus() throws IOException {
        Path file = write(HEADER, "T-2;Title;backend;1;");

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void validatesHeaderSeparately() throws IOException {
        SampleFileLoader loader = new SampleFileLoader();

        LoadResult empty = loader.load(write());
        assertEquals("header", empty.getErrors().get(0).getField());

        LoadResult wrong = loader.load(write(
                "wrong", "T-1;X;C;1;READY"));
        assertEquals(1, wrong.getErrors().size());
        assertTrue(wrong.getTasks().isEmpty());

        LoadResult onlyHeader = loader.load(write(HEADER, "", "   "));
        assertTrue(onlyHeader.getErrors().isEmpty());
        assertTrue(onlyHeader.getTasks().isEmpty());
    }

    @Test
    void reportsPhysicalLineNumbersAndAcceptsBom() throws IOException {
        Path file = write(
                "\uFEFF" + HEADER,
                "",
                "   ",
                "T-1;Title;backend;2147483648;READY"
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(1, result.getErrors().size());
        assertEquals(4, result.getErrors().get(0).getLineNumber());
        assertEquals("estimatedHours", result.getErrors().get(0).getField());
    }

    @Test
    void reportsEmptyFieldAndExtraColumn() throws IOException {
        Path file = write(
                HEADER,
                "T-1;;backend;1;READY",
                "T-2;Title;backend;1;READY;extra"
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(2, result.getErrors().size());
        assertEquals("title", result.getErrors().get(0).getField());
        assertEquals("row", result.getErrors().get(1).getField());
    }

    @Test
    void preservesIoCause() {
        Path missingFile = dir.resolve("missing.txt");

        DataLoadingException ex = assertThrows(
                DataLoadingException.class,
                () -> new SampleFileLoader().load(missingFile)
        );

        assertInstanceOf(IOException.class, ex.getCause());
    }
}