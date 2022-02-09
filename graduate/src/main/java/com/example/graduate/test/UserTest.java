package com.example.graduate.test;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/25 15:51 UserTest
 */
@RestController
@RequestMapping("/userTest")
public class UserTest {
    @Autowired
    UserService userService;

    // 根据用户id查找用户
    @RequestMapping("/0001")
    public String getByUserId(Integer userId){
        return userService.getByUserId(22011701);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello graduate";
    }

    @RequestMapping("/0002")
    public String login(String account,String psd){
        return userService.login(account,psd);
    }

    /**站长功能 start**/
    // 查询账号权限级别
    @RequestMapping("/1001")
    Integer findAccountLevel(String userAccount){
        Integer accountLevel = userService.findAccountLevel(userAccount);
        System.out.println(accountLevel);
        return accountLevel;
    }


    /**
     * 管理账号权限
     * @param userAccount 目标
     * @param nowUserAccount 当前
     * @param category 结果
     */
    @RequestMapping("/1002")
    String manageUserCategory(String userAccount,String nowUserAccount,Integer category){
        String s = userService.manageUserCategory(userAccount, nowUserAccount, category);
        System.out.println(s);
        return s;
    }
    //
    // //

    /**
     * 管理账号状态
     * @param userAccount 目标账号
     * @param nowUserAccount 当前账号
     * @param userStatus 被修改后的状态
     * @return 修改结果
     */
    String manageUserStatus(String userAccount,String nowUserAccount,Integer userStatus){
        return userService.manageUserStatus(userAccount,nowUserAccount,userStatus);
    }

    /**
     * 查询所有用户
     * @param category 目标用户群体的等级
     * @return 用户列表
     */
    List<UserEntity> findAllUser(Integer category){
        return userService.findAllUser(category);
    }
    /**站长功能 end**/
}
