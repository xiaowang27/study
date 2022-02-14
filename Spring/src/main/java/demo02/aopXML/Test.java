package demo02.aopXML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringXMLDemo02/Demo02.xml");
        Book bean = context.getBean(Book.class);
        bean.buy();
    }
}
