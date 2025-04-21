package com.example.finalwork02.Service;

import com.example.finalwork02.Dao.UserRepository;
import com.example.finalwork02.config.Role;
import com.example.finalwork02.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsernameAnAndRole(String username, Role role) {
        return userRepository.findByUsernameAndRole(username,role);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }




}