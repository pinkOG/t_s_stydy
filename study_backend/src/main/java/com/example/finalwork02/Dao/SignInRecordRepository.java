package com.example.finalwork02.Dao;


import com.example.finalwork02.pojo.SignInRecord;
import com.example.finalwork02.pojo.SignInRecordWithUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SignInRecordRepository extends JpaRepository<SignInRecord, Integer> {
    List<SignInRecord> findBySignInTask_TaskId(int taskId);
    List<SignInRecord> findByStudentId(int studentId);



    @Query("SELECT new com.example.finalwork02.pojo.SignInRecordWithUsername(s, u.username) " +
            "FROM SignInRecord s JOIN User u ON s.studentId = u.userId " +
            "WHERE s.signInTask.taskId = :taskId")
    List<SignInRecordWithUsername> findSignInRecordsWithUsernamesByTaskId(@Param("taskId") int taskId);
    @Query("SELECT new com.example.finalwork02.pojo.SignInRecordWithUsername(s, u.username) " +
            "FROM SignInRecord s JOIN User u ON s.studentId = u.userId " +
            "WHERE u.username = :username")
    List<SignInRecordWithUsername> findSignInRecordsWithUsernamesByUsername(@Param("username") String username);

    @Query("SELECT new com.example.finalwork02.pojo.SignInRecordWithUsername(s, u.username) " +
            "FROM SignInRecord s JOIN User u ON s.studentId = u.userId " +
            "WHERE s.studentId = :studentId")
    List<SignInRecordWithUsername> findSignInRecordsWithUsernamesByStudentId(@Param("studentId") int studentId);


}