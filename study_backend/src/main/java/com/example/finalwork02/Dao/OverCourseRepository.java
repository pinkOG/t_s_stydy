package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.OverCourse;
import com.example.finalwork02.pojo.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OverCourseRepository extends JpaRepository<OverCourse, Integer> {
    // You can define custom query methods here if needed
}
