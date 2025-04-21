package com.example.finalwork02.pojo;

public class TeacherCourseAndCountDTO {
    private TeacherCourse teacherCourse;
    private  int count;

    private String UriLink;

    public String getUriLink() {
        return UriLink;
    }

    public void setUriLink(String uriLink) {
        UriLink = uriLink;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public TeacherCourse getTeacherCourse() {
        return teacherCourse;
    }

    public void setTeacherCourse(TeacherCourse teacherCourse) {
        this.teacherCourse = teacherCourse;
    }


}
