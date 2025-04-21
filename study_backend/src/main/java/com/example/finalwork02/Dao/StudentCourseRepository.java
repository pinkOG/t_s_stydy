package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
    // 这里你可以定义其他自定义的查询方法

    //查询已选课人数

    @Query(value = "select count(distinct student_id,course_id ) from  student_course where course_id= ?1",nativeQuery = true)
    int findCourseSumByCourseId(int courseId);

    @Query(value = "select course_id from student_course where student_id=?1",nativeQuery = true)
    List<Integer> findByStudentId(int studentId);
}
