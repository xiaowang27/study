package com.example.graduate.service;

import com.example.graduate.entity.UserEntity;

public interface UserService {
    // 验证账号是否存在
    int getByUserAccount(String userAccount);

    // 验证账号密码是否对应且存在
    int login(String userAccount,String userPassword);

    // 根据用户id查找用户
    String getByUserId(Integer userId);
}
