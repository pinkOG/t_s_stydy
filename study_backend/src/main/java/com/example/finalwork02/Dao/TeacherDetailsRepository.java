package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDetailsRepository extends JpaRepository<TeacherDetails,Integer> {

        TeacherDetails findByUserId(int userId);
}