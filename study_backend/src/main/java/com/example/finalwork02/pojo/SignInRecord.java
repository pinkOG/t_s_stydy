package com.example.finalwork02.pojo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 实体类代表一个签到记录
 */
@Entity
@Table(name = "sign_in_records")
public class SignInRecord {

    /**
     * 签到记录的唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;

    /**
     * 与签到任务的多对一关系
     */
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private SignInTask signInTask;

    /**
     * 学生的唯一标识
     */
    @Column(name = "student_id", nullable = false)
    private int studentId;

    /**
     * 学生签到的时间
     */
    @Column(name = "sign_in_time")
    private LocalDateTime signInTime;

    /**
     * 签到状态，可以是准时，迟到或缺席
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ABSENT;



    /**
     * 枚举类型代表签到状态
     */
    public enum Status {
        ON_TIME,
        LATE,
        ABSENT
    }


    // Getters and Setters
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public SignInTask getSignInTask() {
        return signInTask;
    }

    public void setSignInTask(SignInTask signInTask) {
        this.signInTask = signInTask;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(LocalDateTime signInTime) {
        this.signInTime = signInTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}