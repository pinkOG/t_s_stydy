package com.example.finalwork02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.finalwork02.Dao")
public class Finalwork02Application {

    public static void main(String[] args) {
        SpringApplication.run(Finalwork02Application.class, args);
    }

}
