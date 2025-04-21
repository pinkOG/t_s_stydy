package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherCoursesRepository extends JpaRepository<TeacherCourse,Integer> {
//    TeacherCourse findById();
//    List<TeacherCourse> findByCourseName();
    Optional<TeacherCourse> findByEntryNumber(String code);

    List<TeacherCourse> findByTeacherId(int teacherId);

    List<TeacherCourse> findByCourseIdIn(List<Integer> courseIdList);

    TeacherCourse findByCourseId(int courseId);

    //选课人数
    // 查找主键最大的值
    @Query("SELECT MAX(tc.courseId) FROM TeacherCourse tc")
    Optional<Integer> findMaxId();

}
