package com.example.finalwork02.Service;

import com.example.finalwork02.config.Role;
import com.example.finalwork02.pojo.User;

import java.util.Optional;

public interface UserService {
//    Optional<User> findByUser(User user);
    User save(User user);

    Optional<User> findByUsernameAnAndRole(String username, Role role);
    User findByUsername(String username);


}