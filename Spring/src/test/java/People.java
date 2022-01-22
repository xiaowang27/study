import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring初体验的测试方法
 * @Author 王
 * @CreateDate 2022/1/21 16:03 People
 */


public class People {
    @Test
    public void sleep(){
        // 1.加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("demo01.xml");
        // 2.创建对象
        demo01.People people = context.getBean("people", demo01.People.class);
        people.sleep();
    }
}
