package com.example.finalwork02.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "teacher_details")
public class TeacherDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int teacherId;


    @Column(name = "user_id")
    private int userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree")
    private Degree degree;

    @Column(name = "graduate_in")
    private String graduateIn;

    @Column(name = "work_in")
    private String workIn;


    public TeacherDetails() {
        // 无参构造函数
    }

    // 枚举类型
    public enum Degree {
        HIGH_SCHOOL("HIGH SCHOOL"),
        JUNIOR_COLLEGE("JUNIOR COLLEGE"),
        BACHELOR("BACHELOR"),
        MASTER("MASTER"),
        DOCTORATE("DOCTORATE");

        private String value;

        Degree(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    // Getter和Setter方法

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getGraduateIn() {
        return graduateIn;
    }

    public void setGraduateIn(String graduateIn) {
        this.graduateIn = graduateIn;
    }

    public String getWorkIn() {
        return workIn;
    }

    public void setWorkIn(String workIn) {
        this.workIn = workIn;
    }
}