package demo04.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addMoney(int money,int userId) {
        String sql = "update t_account set user_money = user_money-? where user_id=?";
        return jdbcTemplate.update(sql, money,userId);
    }

    @Override
    public int reduceMoney(int money,int userId) {
        String sql = "update t_account set user_money = user_money+? where user_id=?";
        return jdbcTemplate.update(sql, money,userId);
    }
}
