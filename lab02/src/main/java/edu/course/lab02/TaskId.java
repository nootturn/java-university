package edu.course.lab02;

public record TaskId(String value) {

    public TaskId{
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("tasl id must not be null or blank");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
