package com.example.finalwork02.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "over_courses")
public class OverCourse {

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "entry_code")
    private String entryCode;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "teacher_id")
    private Integer teacherId;

    // Getters and Setters

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
