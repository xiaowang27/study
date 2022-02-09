package com.example.graduate.service.impl;

import com.example.graduate.dao.UserDao;
import com.example.graduate.entity.UserEntity;
import com.example.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/25 15:33 UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getByUserName(String userName) {
        return userDao.getByUserName(userName);
    }
    /*根据用户账号查找账号是否存在*/

    /**
     *
     * @param userAccount 被查找的用户账号
     * @return 查询结果
     */
    @Override
    public int getByUserAccount(String userAccount) {
        return userDao.getByUserAccount(userAccount);
    }


    /**
     * 验证账号密码是否对应
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 对应用户权限页面
     */
    @Override
    public String login(String userAccount, String userPassword) {
        // 若对应则根据不同的账号权限跳转到不同的页面中去
        int exist = userDao.findAccountAndPsd(userAccount, userPassword);
        if (exist == 1) {
            System.out.println("发送");
            Integer category = userDao.login(userAccount, userPassword);
            System.out.println(category);
            if (category == 0) {
                return "普通用户";
            } else if (category == 1) {
                return "管理员";
            } else if (category == 2) {
                return "超级管理员";
            } else if (category == 3) {
                return "站长";
            }
        } else if (exist == 0) {
            return "无该用户";
        }
        return "密码错误";
    }


    /**
     * 根据用户id查找用户
     * @param userId 用户id
     * @return 查找结果
     */
    @Override
    public String getByUserId(Integer userId) {
        UserEntity userEntity = userDao.getByUserId(userId);
        System.out.println(userEntity);
        String userJOSN = "用户id：" + userEntity.getUserId() + "\t用户名" + userEntity.getUserName() + "\t用户状态：" + userEntity.getUserStatus();
        return userJOSN;
    }

    /**站长功能 start**/
    /**
     * 查询账号权限级别
     * @param userAccount 需要查询的用户账号
     * @return 账号权限级别
     */
    @Override
    public Integer findAccountLevel(String userAccount) {
        return userDao.findAccountLevel(userAccount);
    }

    /**
     *判断修改者等级和目标者等级
     * @param account1 当前页面的用户
     * @param account2 目标用户
     * @return 1-当前用户等级高于目标用户；0-当前用户等级低于目标用户
     */
    public int comparisonLevel(String account1,String account2){
        int user1 = findAccountLevel(account1);
        int user2 = findAccountLevel(account2);

        if(user1<user2){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 管理账号权限
     * @param userAccount 被修改的用户账号
     * @param nowUserAccount 当前页面的用户账号
     * @param category 被修改账号后的账号等级
     * @return 返回修改信息
     */
    @Override
    public String manageUserCategory(String userAccount, String nowUserAccount, Integer category) {
        // 判断当前账号与目标账号的等级
        System.out.println(userAccount);
        System.out.println(nowUserAccount);
        int result = comparisonLevel(userAccount, nowUserAccount);
        System.out.println("进行判断...");
        System.out.println("result:"+result);
        if (result==1) {
            userDao.manageUserCategory(userAccount, category);
            return "修改成功";
        } else {
            return "当前账号权限低于目标账号权限";
        }
    }

    /**
     * 管理用户账号状态
     * @param userAccount 目标账号
     * @param userStatus 修改后的账号状态
     * @return 修改结果
     */
    @Override
    public String manageUserStatus(String userAccount, String nowUserAccount,Integer userStatus) {
        int i = comparisonLevel(userAccount, nowUserAccount);
        if(i==1){
            Integer result = userDao.manageUserStatus(userAccount, userStatus);
            if (result==1){
                return "修改成功";
            }else {
                return "修改失败";
            }
        }else {
            return "当前账号权限不够...";
        }
    }

    /**
     * 查询所有用户
     * @param category 查询用户的级别
     * @return 用户列表
     */
    @Override
    public List<UserEntity> findAllUser(Integer category) {
        return userDao.findAllUser(category);
    }
    /**站长功能 end**/
}
