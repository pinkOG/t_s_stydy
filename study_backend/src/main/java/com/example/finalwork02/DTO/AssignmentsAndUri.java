package com.example.finalwork02.DTO;

import com.example.finalwork02.pojo.Assignments;

public class AssignmentsAndUri {
    Assignments assignments;
    String uriLink;

    public Assignments getAssignments() {
        return assignments;
    }

    public void setAssignments(Assignments assignments) {
        this.assignments = assignments;
    }

    public String getUriLink() {
        return uriLink;
    }

    public void setUriLink(String uriLink) {
        this.uriLink = uriLink;
    }
}