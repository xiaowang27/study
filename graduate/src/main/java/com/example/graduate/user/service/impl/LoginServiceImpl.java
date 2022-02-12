package com.example.graduate.user.service.impl;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.user.dao.LoginDao;
import com.example.graduate.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 王俊杰
 * @CreateDate 2022/2/11 10:19 LoginServiceImpl
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Override
    public Integer userRegister(UserEntity userEntity) {
        return loginDao.userRegister(userEntity);
    }

    public String userLogin(String userAccount, String userPassword, HttpServletRequest request){
        Integer i= loginDao.userLogin(userAccount, userPassword);
        if(i==1){
            return "正确";
        }else{
            return "错误";
        }
    }
}
