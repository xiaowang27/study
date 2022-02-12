package com.example.graduate.user.controller;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 王俊杰
 * @CreateDate 2022/2/11 9:57 LoginController
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService service;
    /**
     * 注册
     * @param userEntity 用户实体类
     * @return 0表失败，1表成功
     */
    @RequestMapping("/register")
    public Integer userRegister(UserEntity userEntity){
        return service.userRegister(userEntity);
    }

    // 登陆功能
    public void userLogin(String userAccount,String userPassword){

    }

    // 忘记密码
    public Integer forgetPassword(){
        return null;
    }


    /**
     * 修改密码
     * @param userAccount 当前用户账号
     * @param previouslyPsd 当前密码
     * @param nowPsd 修改后的密码
     * @return 0表示修改失败，1表示修改成功
     */
    public Integer updatePassword(String userAccount,String previouslyPsd,String nowPsd){
        return null;
    }
}
