package com.example.finalwork02.pojo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 实体类代表一个签到任务
 */
@Entity
@Table(name = "sign_in_tasks")
public class SignInTask {

    /**
     * 签到任务的唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    /**
     * 教师的唯一标识
     */
    @Column(name = "teacher_id", nullable = false)
    private int teacherId;

    /**
     * 签到任务的开始时间
     */
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    /**
     * 签到任务的结束时间
     */
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    /**
     * 签到任务的创建时间，默认值为当前时间
     */
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column (name = "sign_code",nullable = false)
    private int signCode;
    public int getSignCode() {
        return signCode;
    }

    public void setSignCode(int signCode) {
        this.signCode = signCode;
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "SignInTask{" +
                "taskId=" + taskId +
                ", teacherId=" + teacherId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createdAt=" + createdAt +
                ", signCode=" + signCode +
                '}';
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}