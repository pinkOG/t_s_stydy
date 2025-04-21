package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.AssignmentsTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentsTasksRepository extends JpaRepository<AssignmentsTasks, Integer> {

    List<AssignmentsTasks> findByCourseId(int courseId);


}