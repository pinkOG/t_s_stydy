package com.example.finalwork02.pojo;

public class TeacherCourseAndCount {
    TeacherCourse teacherCourse;
    int count;

    public TeacherCourseAndCount(TeacherCourse teacherCourse, int count) {
        this.teacherCourse = teacherCourse;
        this.count = count;
    }

    public TeacherCourse getTeacherCourse() {
        return teacherCourse;
    }

    public void setTeacherCourse(TeacherCourse teacherCourse) {
        this.teacherCourse = teacherCourse;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
