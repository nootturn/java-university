package edu.course.lab04.storage;

import java.util.Objects;

/**
 * Учебный изменяемый ключ.
 * Используется ТОЛЬКО в тесте, чтобы показать проблему.
 */
public class MutableIssueId {

    private String projectCode;
    private int number;

    public MutableIssueId(String projectCode, int number) {
        this.projectCode = projectCode;
        this.number = number;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MutableIssueId that)) return false;
        return number == that.number && Objects.equals(projectCode, that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, number);
    }
}
