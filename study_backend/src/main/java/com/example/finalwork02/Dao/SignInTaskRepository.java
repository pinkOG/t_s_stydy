package com.example.finalwork02.Dao;




import com.example.finalwork02.pojo.SignInTask;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SignInTaskRepository extends JpaRepository<SignInTask, Integer> {
    List<SignInTask> findByTeacherId(int teacherId);


    List<SignInTask> findBySignCode(int signcode);

}