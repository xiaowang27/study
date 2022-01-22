package demo01.comment.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void add(){
        System.out.println("dao add...");
    }
}
