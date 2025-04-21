package com.example.finalwork02.DTO;

import com.example.finalwork02.pojo.AssignmentsTasks;

import java.util.Objects;

public class AssignmentsTasksAndUri {
    AssignmentsTasks assignmentsTasks;
    String uriLink;

    public AssignmentsTasks getAssignmentsTasks() {
        return assignmentsTasks;
    }

    public void setAssignmentsTasks(AssignmentsTasks assignmentsTasks) {
        this.assignmentsTasks = assignmentsTasks;
    }

    public String getUriLink() {
        return uriLink;
    }

    public void setUriLink(String uriLink) {
        this.uriLink = uriLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignmentsTasksAndUri)) return false;
        AssignmentsTasksAndUri that = (AssignmentsTasksAndUri) o;
        return Objects.equals(getAssignmentsTasks(), that.getAssignmentsTasks()) && Objects.equals(getUriLink(), that.getUriLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssignmentsTasks(), getUriLink());
    }
}
