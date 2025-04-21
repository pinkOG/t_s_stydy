package com.example.finalwork02.DTO;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

public class IdAndHomework {
    //批改人I
    List<Map<Integer,Resource>> studentIdAndResource;

    public List<Map<Integer, Resource>> getStudentIdAndResource() {
        return studentIdAndResource;
    }

    public void setStudentIdAndResource(List<Map<Integer, Resource>> studentIdAndResource) {
        this.studentIdAndResource = studentIdAndResource;
    }
}
