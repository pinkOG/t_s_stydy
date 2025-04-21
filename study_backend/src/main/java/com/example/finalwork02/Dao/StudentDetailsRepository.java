package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer> {
    StudentDetails findByUserId(int userId);
//    @Query("select new com.example.finalwork02.pojo.StudentDetails from ")
//    StudentDetails updateDetails(StudentDetails studentDetails);
    // 你可以在这里添加自定义查询方法
}