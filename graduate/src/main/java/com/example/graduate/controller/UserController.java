package com.example.graduate.controller;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/30 13:47 UserController
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    /** 用户通用功能---start **/
    /**
     * 查找账号是否存在
     * @param userAccount 目标账户
     * @return 存在返回1，不存在返回0
     */
    @RequestMapping("/00001")
    public Integer getByUserAccount(String userAccount){
        return userService.getByUserAccount(userAccount);
    }

    /**
     * 通过用户账号查找用户信息
     * @param account 用户账号
     * @return 用户信息：账号、id、用户名
     */
    @RequestMapping("/00002")
    public String findUserInfo(String account){
        return "";
    }

    /**
     * 通过用户名查找用户
     * @param userName 用户名
     * @return 用户信息：账号、id、用户名
     */
    @RequestMapping("/00003")
    public UserEntity getByUserName(String userName){
        return userService.getByUserName(userName);
    }

    /**
     * 关注用户
     * @param followersUserAccount 关注者用户账号
     * @param byFollowersUserAccount 被关注者用户账号
     * @return 操作结果信息：1-关注成功，0-关注失败
     */
    @RequestMapping("/00004")
    public Integer followUser(String followersUserAccount,String byFollowersUserAccount){
        return null;
    }

    /**
     * 取消关注
     * @param followersUserAccount 关注者账号
     * @param byFollowersUserAccount 被关注者账号
     * @return 操作结果信息：1-关注成功，0-关注失败
     */
    @RequestMapping("/00005")
    public Integer unfollow(String followersUserAccount,String byFollowersUserAccount){
        return null;
    }

    /**
     * 用户搜索信息
     * @param infoKeyword 目标信息关键字
     * @return 所有符合搜索条件的信息
     */
    public String[] findInfo(String infoKeyword){
        return null;
    }
    /** 用户通用功能---end **/

    /**管理员功能---start**/

    /**管理员功能---end**/
}
