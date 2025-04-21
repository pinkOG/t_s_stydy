package com.example.finalwork02.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "score_analysis_initial")
public class ScoreAnalysisInitial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "highest_score", precision = 5, scale = 2)
    private BigDecimal highestScore;

    @Column(name = "lowest_score", precision = 5, scale = 2)
    private BigDecimal lowestScore;

    @Column(name = "average_score", precision = 5, scale = 2)
    private BigDecimal averageScore;

    @Column(name = "pass_rate", precision = 5, scale = 2)
    private BigDecimal passRate;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "final_score", precision = 5, scale = 2)
    private BigDecimal finalScore;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreAnalysisInitial)) return false;
        ScoreAnalysisInitial that = (ScoreAnalysisInitial) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTaskId(), that.getTaskId()) && Objects.equals(getHighestScore(), that.getHighestScore()) && Objects.equals(getLowestScore(), that.getLowestScore()) && Objects.equals(getAverageScore(), that.getAverageScore()) && Objects.equals(getPassRate(), that.getPassRate()) && Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getFinalScore(), that.getFinalScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskId(), getHighestScore(), getLowestScore(), getAverageScore(), getPassRate(), getStudentId(), getFinalScore());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public BigDecimal getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(BigDecimal highestScore) {
        this.highestScore = highestScore;
    }

    public BigDecimal getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(BigDecimal lowestScore) {
        this.lowestScore = lowestScore;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public BigDecimal getPassRate() {
        return passRate;
    }

    public void setPassRate(BigDecimal passRate) {
        this.passRate = passRate;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public void setLowerestScore(BigDecimal tempMinValue) {
        this.lowestScore =tempMinValue;
    }
}
