package com.example.graduate.entity;

import java.util.Date;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/25 15:16 UserEntity
 */
public class UserEntity {
    private Integer userId;
    private String userAccount;
    private String userPassword;
    private String userName;
    private Integer gender;     // 0-女 1-男
    private Integer userStatus; // 0-未激活 1-正常 2-封禁 3-注销
    private Integer category;   // 0-用户 1-管理员 2-超级管理员 3-站长
    private Date createTime;
    private Date updateTime;
    private String userEmail;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", userStatus=" + userStatus +
                ", category=" + category +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}