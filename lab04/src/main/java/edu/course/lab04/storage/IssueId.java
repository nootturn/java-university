package edu.course.lab04.storage;

import java.util.Objects;

public final class IssueId {
    private String projectCode;
    private int projectNumber;

    public IssueId(String projectCode, int projectNumber){
        if (projectCode == null || projectCode.isBlank()){
            throw new IllegalArgumentException("code must not be null or blank");
        }

        if (projectNumber <= 0){
            throw new IllegalArgumentException("Number must be greater than zero");
        }

        this.projectCode = projectCode;
        this.projectNumber = projectNumber;
    }

    public String getProjectCode(){
        return projectCode;
    }

    public int getProjectNumber(){
        return projectNumber;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueId issueId)) {
            return false;
        }
        return projectNumber == issueId.projectNumber && projectCode.equals(issueId.projectCode);
    }

    @Override
    public int hashCode(){
        return Objects.hash(projectCode, projectNumber);
    }

    @Override
    public String toString() {
        return projectCode + "-" + projectNumber;
    }
}
