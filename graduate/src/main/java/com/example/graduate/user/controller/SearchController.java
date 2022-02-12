package com.example.graduate.user.controller;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.user.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 王俊杰
 * @CreateDate 2022/2/11 16:07 SearchController
 */
@RequestMapping("/search")
@RestController
public class SearchController {
    @Autowired
    SearchService service;

    @RequestMapping("/getByUserName")
    public List<UserEntity> getByUserName(String userName){
        return service.getByUserName(userName);
    }

    @RequestMapping("/hello")
    public String hello(String userName){
        return "Hello";
    }
}
