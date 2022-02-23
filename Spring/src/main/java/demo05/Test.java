package demo05;

import org.springframework.context.support.GenericApplicationContext;

public class Test {
    @org.junit.Test
    public  void GenericApplicationContextTest(){
        // 1.创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        // 2.调用context的方法注册对象
        context.refresh();
        context.registerBean("user",User.class,() -> new User()); // 指定bean的id，和需要创建对象类型的class，然后使用Lambda表达式new对象
        // 3.获取在spring注册的对象
        Object bean = context.getBean("user");
        System.out.println(bean);
    }
}
