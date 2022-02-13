package demo02.aopOperation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {

    }

    @org.junit.Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringXMLDemo02/Demo02.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }
}
