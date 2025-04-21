package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentsRepository extends JpaRepository<Assignments, Integer> {
    List<Assignments> findByTaskId(int taskId);

    Assignments findByTaskIdAndStudentId(int taskId,int studentId);
}