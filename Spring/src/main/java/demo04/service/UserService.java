package demo04.service;

import demo04.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  // 可以加在类上，也可以加在方法上
public class UserService {
    @Autowired
    private UserDao userDao;

    public int accountMoney(int userId1,int userId2,int money){
        // 1.开启事务

        // 2.进行业务操作
        int i = userDao.addMoney(money, userId1);
        // 模拟异常
        int m = 10/0;
        int j = userDao.reduceMoney(money, userId2);

        // 3.没有产生异常-事务提交
        // 3.产生异常-事务回滚

        return i+j;
    }
}
