package com.example.graduate.user.service;

import com.example.graduate.entity.UserEntity;

import java.util.List;

public interface SearchService {

    // 根据用户名搜索用户
    List<UserEntity> getByUserName(String userName);
}
