package com.example.finalwork02.Dao;


import com.example.finalwork02.pojo.ReviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRecordRepository extends JpaRepository<ReviewRecord, Integer> {
    List<ReviewRecord> findByTaskIdAndStudentId(int taskId,int studentId);

    List<ReviewRecord> findByTaskIdAndAssignedTo(int taskId, int studentId);

    List<ReviewRecord> findByTaskId(int taskId);
    // You can add custom query methods here if needed
}
