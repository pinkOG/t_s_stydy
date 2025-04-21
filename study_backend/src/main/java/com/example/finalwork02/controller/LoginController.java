package com.example.finalwork02.controller;


import com.example.finalwork02.Service.StudentService;
import com.example.finalwork02.Service.TeacherService;
import com.example.finalwork02.Service.UserService;
import com.example.finalwork02.config.Role;
import com.example.finalwork02.pojo.StudentDetails;
import com.example.finalwork02.pojo.TeacherDetails;
import com.example.finalwork02.pojo.User;
import com.example.finalwork02.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     *
     * @param loginData
     * @return 用户详细数据
     */
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        String role = loginData.get("role");
        System.out.println(username);
        Map<String, Object> response = new HashMap<>();
        // 将传过来的包装成枚举类
        Role myrole = Role.valueOf(role.toUpperCase());

        Optional<User> userOptional = userService.findByUsernameAnAndRole(username, myrole);

        System.out.println(userOptional.isPresent());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            boolean isAuthenticated = user.getPassword().equals(HashUtil.getMD5Hash(password)); // 使用MD5哈希进行比较

            response.put("success", isAuthenticated);
            if (isAuthenticated) {
                response.put("role", user.getRole());
                response.put("user", user);
                if (user.getRole() == Role.STUDENT) {
                    StudentDetails details = studentService.findByUserId(user.getUserId());
                    response.put("studentDetails", details);
                } else {
                    TeacherDetails details = teacherService.findByUserId(user.getUserId());
                    response.put("teacherDetails", details);
                }
            }
        } else {
            response.put("success", false);
            User user = userService.findByUsername(username);
        }
        System.out.println("我进来了哦");
        return response;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> register(@RequestBody Map<String, String> registerData) {
        String username = registerData.get("username");
        String password = registerData.get("password");
        String name = registerData.get("name");
        String email = registerData.get("email");
        String roleString = registerData.get("role");

        Role role = Role.valueOf(roleString.toUpperCase());

        Map<String, Object> response = new HashMap<>();

        Optional<User> existingUserOptional = userService.findByUsernameAnAndRole(username, role);

        if (existingUserOptional.isPresent()) {
            response.put("success", false);
            response.put("message", "用户名已存在");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(HashUtil.getMD5Hash(password)); // 使用MD5哈希
            user.setName(name);
            user.setEmail(email);
            user.setRole(role);

            User saveUser = userService.save(user);
            if (user.getRole() == Role.STUDENT) {
                StudentDetails details = new StudentDetails();
                details.setUserId(user.getUserId());
                studentService.saveStudentDetails(details);
            } else {
                TeacherDetails details = new TeacherDetails();
                details.setUserId(user.getUserId());
                teacherService.saveTeacherDetails(details);
            }

            response.put("success", true);
        }
        System.out.println("我进来了哦");
        return response;
    }

    @RequestMapping("/test")
    public void mytest(String id) {
        System.out.println("hello");
    }
}