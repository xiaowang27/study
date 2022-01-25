package com.example.graduate.test;

import com.example.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/25 15:51 UserTest
 */
@RestController
@RequestMapping("/user")
public class UserTest {
    @Autowired
    UserService userService;

    // 根据用户id查找用户
    @RequestMapping("/getByUserId")
    public String getByUserId(Integer userId){
        return userService.getByUserId(22011701);
    }

    @RequestMapping("hello")
    public String hello(){
        return "Hello graduate";
    }
}
