package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.ScoreAnalysisInitial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ScoreAnalysisInitialRepository extends JpaRepository<ScoreAnalysisInitial, Integer> {

 @Modifying
 @Transactional
 @Query(value = "update score_analysis_initial set pass_rate = ?1 where task_id = ?2", nativeQuery = true)
 int updatePassRateByTaskId(BigDecimal passRate, int tempTaskId);
 @Transactional
 @Query(value = "select final_score from score_analysis_initial where student_id = ?1 and task_id = ?2", nativeQuery = true)
 BigDecimal queryByTaskIdAndStudentId(int studentId, int taskId);
 @Transactional
 @Query(value = "select final_score from score_analysis_initial where task_id = ?1", nativeQuery = true)
 List<BigDecimal> findScoreListByTaskId(int taskId);

 List<ScoreAnalysisInitial> findByTaskId(int taskId);

 @Modifying
 @Transactional
 @Query(value = "update score_analysis_initial set highest_score = ?1, lowest_score = ?2, average_score = ?3, pass_rate = ?4 where task_id = ?5", nativeQuery = true)
 int saveDetails(BigDecimal tempMaxValue, BigDecimal tempMinValue, BigDecimal averageScore, BigDecimal passRate, int taskId);

 @Modifying
 @Transactional
 void deleteByTaskId(int taskId);
@Transactional
 List<ScoreAnalysisInitial> findByTaskIdAndStudentId(int taskId, int studentId);
 @Transactional
 List<ScoreAnalysisInitial> findByTaskIdIn(List<Integer> taskIdList);

 List<ScoreAnalysisInitial> findByStudentId( int studentId);
}
