package com.example.graduate.user.service.impl;

import com.example.graduate.entity.UserEntity;
import com.example.graduate.user.dao.SearchDao;
import com.example.graduate.user.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王俊杰
 * @CreateDate 2022/2/11 16:03 SearchServiceImpl
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    SearchDao searchDao;
    @Override
    public List<UserEntity> getByUserName(String userName) {
        System.out.println(userName);
        char[] chars = userName.toCharArray();
        StringBuilder name = new StringBuilder();
        for(char c : chars){
            name.append('%');
            name.append(c);
        }
        name.append('%');
        System.out.println(name);
        List<UserEntity> userList = searchDao.getByUserName(name.toString());
        if(userList.size()==0){
            System.out.println("没有找到");
        }else{
            System.out.println("有");
        }
        return userList;
    }
}
