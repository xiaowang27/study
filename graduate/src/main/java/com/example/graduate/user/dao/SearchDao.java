package com.example.graduate.user.dao;

import com.example.graduate.entity.UserEntity;

import java.util.List;

public interface SearchDao {

    // 根据id搜索用户
    UserEntity getByUserId(Integer userId);

    // 根据用户账号搜索用户
    UserEntity getByUserAccount(String userAccount);

    // 根据用户名查找用户
    List<UserEntity> getByUserName(String userName);

    // 根据帖子标题搜索帖子

}
