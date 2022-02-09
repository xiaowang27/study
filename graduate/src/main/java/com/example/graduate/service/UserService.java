package com.example.graduate.service;

import com.example.graduate.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity getByUserName(String userName);
    // 验证账号是否存在
    int getByUserAccount(String userAccount);

    // 验证账号密码是否对应且存在
    String login(String userAccount,String userPassword);

    // 根据用户id查找用户
    String getByUserId(Integer userId);

    /**管理员功能 start**/
    // 查询所有用户

    /**管理员功能 end**/

    /**站长功能 start**/
    // 查询账号权限级别
    Integer findAccountLevel(String userAccount);

    // 管理账号权限
    String manageUserCategory(String userAccount,String nowUserAccount,Integer category);

    //管理账号状态
    String manageUserStatus(String userAccount,String nowUserAccount,Integer userStatus);

    //查询所有用户
    List<UserEntity> findAllUser(Integer category);
    /**站长功能 end**/
}
