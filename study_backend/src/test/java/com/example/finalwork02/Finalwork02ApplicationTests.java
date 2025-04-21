package com.example.finalwork02;

import com.example.finalwork02.Dao.UserRepository;
import com.example.finalwork02.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Finalwork02ApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("guanhao");
        user.setEmail("124365142365");

    }

}
