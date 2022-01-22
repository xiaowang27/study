package demo01.comment;

import demo01.comment.config.SpringConfig;
import demo01.comment.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo01Comment {
    public static ApplicationContext getContext(){
       return new ClassPathXmlApplicationContext("SpringXMLDemo01/demo01_comment.xml");
    }

    // 测试包自动扫描
    @Test
    public void testComment(){
        ApplicationContext context = getContext();
        UserService bean = context.getBean("userService", UserService.class);
        bean.add();
    }

    // 测试@AutoWired注解
    @Test
    public void testAutoWired(){
        ApplicationContext context = getContext();
        UserService bean = context.getBean("userService", UserService.class);
        bean.add();

    }

    // 使用@Configration配置类
    @Test
    public void testConfigration(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService bean = context.getBean("userService", UserService.class);
        bean.add();
    }
}
