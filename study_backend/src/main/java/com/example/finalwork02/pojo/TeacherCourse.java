package com.example.finalwork02.pojo;

import javax.persistence.*;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_courses")
public class TeacherCourse {
    @Id

    @Column(name = "course_id")
    private  int courseId;
    @Column(name = "class_name")
    private  String className;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "entry_code")
    private String entryNumber;
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "teacher_id")
    private int teacherId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public TeacherCourse() {

    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }



    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


}
