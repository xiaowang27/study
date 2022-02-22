package demo04.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int addMoney(int money,int userId);
    int reduceMoney(int money,int userId);
}
