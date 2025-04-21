package com.example.finalwork02.pojo;

import java.util.List;

public class CourseListAndTaskId {
    private TeacherCourse teacherCourse;
    private List<Integer> listId;

    public TeacherCourse getTeacherCourse() {
        return teacherCourse;
    }

    public void setTeacherCourse(TeacherCourse teacherCourse) {
        this.teacherCourse = teacherCourse;
    }

    public List<Integer> getListId() {
        return listId;
    }

    public void setListId(List<Integer> listId) {
        this.listId = listId;
    }
}
