package com.example.graduate.user.dao;

import com.example.graduate.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {

    Integer userRegister(UserEntity userEntity);

    Integer userLogin(String userAccount,String userPassword);

    Integer forgerPassword();

    Integer updatePassword(String userAccount,String previouslyPassword,String newPassword);
}
