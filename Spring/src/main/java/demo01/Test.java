package demo01;

import demo01.bean.Emp;
import demo01.bean.Orders;
import demo01.collection.ArrayTest;
import demo01.service.BookService;
import demo01.service.impl.BookServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static ApplicationContext getContext(){
        return new ClassPathXmlApplicationContext("SpringXMLDemo01/demo01.xml");
    }
    @org.junit.Test
    public void peopleTesting(){
        // 1. 加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringXMLDemo01/demo01.xml");

        // 2. 通过spring配置文件创建对象
        People people = context.getBean("people", People.class);

        // 3. 使用对象
        people.sleep();
    }

    // 基于xml管理bean-set方法实现依赖注入
    @org.junit.Test
    public void bookTesting(){
        // 1. 加载配置文件
        ApplicationContext context = getContext();
        // 2. 通过spring配置文件创建对象
        Book book = context.getBean("bookSetDI", Book.class);
        System.out.println(book.getBookName());
    }

    // 基于xml管理bean-有参构造实现依赖注入
    @org.junit.Test
    public void bookXmlManagerConstruct(){
        ApplicationContext context = getContext();
        Book book = context.getBean("bookConstructDI", Book.class);
        System.out.println(book.getBookName());

    }

    // 外部bean属性注入
    @org.junit.Test
    public void exteriorBean(){
        ApplicationContext context = getContext();
        BookServiceImpl bookService = context.getBean("BookService",BookServiceImpl.class);
        bookService.add();
    }

    // 内部bean和级联赋值
    @org.junit.Test
    public void InBean(){
        ApplicationContext context = getContext();
        Emp empBean = context.getBean("empBean", Emp.class);
        System.out.println(empBean);
    }

    // 注入集合属性
    @org.junit.Test
    public void collectionDI(){
        ApplicationContext context = getContext();
        ArrayTest collectionDI = context.getBean("collectionDI", ArrayTest.class);
        System.out.println(collectionDI);
    }

    // 工厂bean，在xml中定义的时FactoryBeanTest，在工厂类中返回的是Emp
    @org.junit.Test
    public void FactoryBeanTest(){
        ApplicationContext context = getContext();
        Object factoryBeanTest = context.getBean("factoryBeanTest");
        System.out.println(factoryBeanTest);
    }

    // bean的生命周期
    @org.junit.Test
    public void beanOrders(){
        // 因为close方法是在ApplicationContext的实现类ClasPathXmlApplicationContext中实现了
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringXMLDemo01/demo01.xml");
        Orders beanOrders = context.getBean("beanOrders", Orders.class);
        beanOrders.useBeanMethod();
        context.close();
    }

    // 自动装配
    @org.junit.Test
    public void autoWrite(){
        ApplicationContext context = getContext();
        demo01.autowire.Emp autowireEmp = context.getBean("autoWireEmp", demo01.autowire.Emp.class);
        System.out.println(autowireEmp);
    }

    // 映入外部属性文件
    public void tt(){
        ApplicationContext context = getContext();
    }

    //
    @org.junit.Test
    public void parasTest(){
        ApplicationContext context = getContext();
        Object factoryBeanTest = context.getBean("paras");
        System.out.println(factoryBeanTest);
    }
}
