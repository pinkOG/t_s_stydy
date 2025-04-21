package com.example.finalwork02.Dao;


import com.example.finalwork02.config.Role;
import com.example.finalwork02.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndRole(String username, Role role);
    User findByUsername(String username);




//
//    // 使用 Spring Data JPA 提供的 findAllById 方法
    List<User> findByUserIdIn(List<Integer> ids);
}