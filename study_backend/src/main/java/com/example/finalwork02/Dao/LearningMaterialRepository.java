package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {

    List<LearningMaterial> findByCourseIdAndFileType(int courseId,LearningMaterial.FileTpye fileTpye);
    LearningMaterial  findByCourseId(int courseId);
    @Query("SELECT lm FROM LearningMaterial lm WHERE lm.courseId = :courseId AND (lm.fileType = :fileType1 OR lm.fileType = :fileType2)")
    List<LearningMaterial> findByCourseIdAndFileTypes(@Param("courseId") int courseId,
                                                      @Param("fileType1") LearningMaterial.FileTpye fileType1,
                                                      @Param("fileType2") LearningMaterial.FileTpye fileType2);

}
