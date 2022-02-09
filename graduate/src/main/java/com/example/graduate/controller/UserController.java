package com.example.graduate.controller;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/30 13:47 UserController
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    /** 用户通用功能---start **/
    /**
     * 通过用户账号查找用户信息
     * @param account 用户账号
     * @return 用户信息
     */
    public String findUserAccount(String account){
        return "";
    }
    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    @RequestMapping("00001")
    public UserEntity getByUserName(String userName){
        return userService.getByUserName(userName);
    }
    /** 用户通用功能---end **/

    /**管理员功能---start**/

    /**管理员功能---end**/
}
