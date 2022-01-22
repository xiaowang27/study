import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring初体验的测试方法
 * @Author 王
 * @CreateDate 2022/1/21 16:03 People
 */

public class People {
    public void sleep(){
        // 1.加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("demo01.xml");
        // 2.创建对象
        People people = context.getBean("people", People.class);
        people.sleep();
    }
}
