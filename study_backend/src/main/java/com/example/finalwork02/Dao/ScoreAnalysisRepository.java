package com.example.finalwork02.Dao;

import com.example.finalwork02.pojo.ScoreAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreAnalysisRepository extends JpaRepository<ScoreAnalysis, Integer> {
    // 可以在这里定义自定义的查询方法
}
