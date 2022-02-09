package com.example.graduate.dao;


import com.example.graduate.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    UserEntity getByUserName(String userName);

    // 验证账号是否存在
    int getByUserAccount(String userAccount);

    // 验证账号密码是否输入正确
    int findAccountAndPsd(String userAccount,String userPassword);
    int login(String userAccount,String userPassword);

    // 根据id查询用户基本信息
    UserEntity getByUserId(Integer id);

    /**站长功能--start**/
    //查询账号权限级别
    Integer findAccountLevel(String userAccount);

    //设置账号权限
    Integer manageUserCategory(String userAccount,Integer category);

    //管理账号状态
    Integer manageUserStatus(String userAccount,Integer userStatus);

    //查询所有用户
    List<UserEntity> findAllUser(Integer category);

    //
    /**站长功能--end**/
}
