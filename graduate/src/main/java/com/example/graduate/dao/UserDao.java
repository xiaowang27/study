package com.example.graduate.dao;


import com.example.graduate.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    // 验证账号是否存在
    int getByUserAccount(String userAccount);

    // 验证账号密码是否输入正确
    int Login(String userAccount,String userPassword);

    // 根据id查询用户基本信息
    UserEntity getByUserId(Integer id);
}
