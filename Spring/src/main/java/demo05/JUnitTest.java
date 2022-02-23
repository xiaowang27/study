package demo05;

import demo04.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 指定单元测试框架
@ContextConfiguration("classpath:SpringXmlDemo04/demo01.xml") // 加载配置文件
public class JUnitTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        userService.accountMoney(2023,2022,400);
    }
}
