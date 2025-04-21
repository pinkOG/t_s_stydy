package com.example.finalwork02.pojo;



import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "learning_materials")
public class LearningMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int materialId;
    @Column(name = "course_id")
    private int courseId;
    private String title;
    private String description;

    @Column(name = "file_path")
    private String filePath;
    @Enumerated(EnumType.STRING)
    @Column(name = "file_type")

    private FileTpye fileType;
    @Column(name = "created_at")
    private Timestamp createdAt;
    //userId
    @Column(name = "user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getters and setters

    public enum FileTpye {
        VIDEO,
        DOCUMENT,
        ASSIGNMENT,
        RESOURCE,
        SASSIGNMENT
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileTpye getFileType() {
        return fileType;
    }

    public void setFileType(FileTpye fileType) {
        this.fileType = fileType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


}
