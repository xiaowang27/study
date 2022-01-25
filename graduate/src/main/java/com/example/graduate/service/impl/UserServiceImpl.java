package com.example.graduate.service.impl;

import com.example.graduate.dao.UserDao;
import com.example.graduate.entity.UserEntity;
import com.example.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/25 15:33 UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /*根据用户账号查找账号是否存在*/
    @Override
    public int getByUserAccount(String userAccount) {
        return userDao.getByUserAccount(userAccount);
    }

    /*验证账号密码是否对应*/
    @Override
    public int login(String userAccount, String userPassword) {
        return userDao.Login(userAccount,userPassword);
    }

    /*根据用户id查找用户*/
    @Override
    public String getByUserId(Integer userId) {
        UserEntity userEntity = userDao.getByUserId(userId);
        System.out.println(userEntity);
        String userJOSN = "用户id："+userEntity.getUserId()+"\t用户名"+userEntity.getUserName()+"\t用户状态："+userEntity.getUserStatus();
        return userJOSN;
    }
}
