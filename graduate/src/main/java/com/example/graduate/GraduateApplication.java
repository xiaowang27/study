package com.example.graduate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.graduate.dao")
@MapperScan("com/example/graduate/user/dao")
@SpringBootApplication
public class GraduateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateApplication.class, args);
    }

}
