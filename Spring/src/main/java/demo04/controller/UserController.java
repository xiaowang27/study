package demo04.controller;

import demo04.config.TxConfig;
import demo04.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    @Test
    public void testAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringXmlDemo04/demo01.xml");
        UserService userService = context.getBean("userService", UserService.class);
        int i = userService.accountMoney(2022, 2023, 300);
        if (i == 2) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringXmlDemo04/demo02.xml");
        UserService userService = context.getBean("userService", UserService.class);
        int i = userService.accountMoney(2023, 2022, 600);
        if (i == 2) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }

    @Test
    public void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        int i = userService.accountMoney(2022, 2023, 300);
        if (i == 2) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }
}
