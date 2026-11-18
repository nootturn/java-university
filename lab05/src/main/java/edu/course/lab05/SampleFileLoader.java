package edu.course.lab05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import edu.course.lab05.projectTask.*;

public final class SampleFileLoader {
    private static final String HEADER =
            "id;title;category;estimatedHours;status";

    private static final class InvalidField extends IllegalArgumentException {
        final String field;

        InvalidField(String field, String message) {
            super(message);
            this.field = field;
        }
    }

    public LoadResult load(Path path) {
        Objects.requireNonNull(path);

        List<ProjectTask> tasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String header = reader.readLine();

            // некоторые UTF-8-файлы начинаются с метки BOM
            if (header != null && header.startsWith("\uFEFF")) {
                header = header.substring(1);
            }

            if (!HEADER.equals(header)) {
                errors.add(new ValidationError(
                        1, "header", "Expected header: " + HEADER));
                return new LoadResult(tasks, errors);
            }

            String line;
            int number = 1;

            while ((line = reader.readLine()) != null) {
                number++;

                if (line.isBlank()) {
                    continue;
                }

                // лимит -1 для того чтобы он не отбрасывал последнее поле при условии, что оно пустое
                String[] fields = line.split(";", -1);

                if (fields.length != 5) {
                    errors.add(new ValidationError(number, "row", "Expected 5 fields"));
                    continue;
                }

                try {
                    tasks.add(toTask(fields));
                } catch (InvalidField ex) {
                    errors.add(new ValidationError(number, ex.field, ex.getMessage()));
                }
            }

            return new LoadResult(tasks, errors);
        } catch (IOException ex) {
            throw new DataLoadingException("cannot read " + path, ex);
        }
    }

    private ProjectTask toTask(String[] fields) {
        String id = required(fields[0], "id");
        String title = required(fields[1], "title");
        String category = required(fields[2], "category");

        int hours;
        try {
            hours = Integer.parseInt(fields[3].strip());
        } catch (NumberFormatException ex) {
            throw new InvalidField("estimatedHours", "expected integer");
        }

        if (hours < 0) {
            throw new InvalidField("estimatedHours", "must be non-negative");
        }

        TaskStatus status;
        try {
            status = TaskStatus.valueOf(fields[4].strip());
        } catch (IllegalArgumentException ex) {
            throw new InvalidField("status", "unknown status");
        }

        return new ProjectTask(id, title, category, hours, status);
    }

    private String required(String value, String field) {
        String result = value.strip();

        if (result.isEmpty()) {
            throw new InvalidField(field, "must not be blank");
        }

        return result;
    }
}