# 前言

**Spring5的学习内容：**

* spring

* IOC

* AOP

* JdbcTemplate

* 事务管理

* spring5的新特性

----



# 1. spring概述

## 1.1 spring的概念

轻量级javaee开源框架。核心部分：IOC和AOP。用于简化开发。

**IOC：**控制反转；将创建对象的过程交给spring。

**AOP：**面向切面；不修改源代码的情况下修改或添加功能。

**特点：**

1. 方便解耦，简化开发
2. 对AOP编程的支持
3. 方便程序的测试
4. 方便集成其他框架
5. 降低javaeeapi使用难度
6. 方便进行事务管理



## 1.2 入门案例：

1. 下载jar包并导入或添加maven依赖
2. 创建一个ordinary class，在class中创建一个ordinary method
3. 创建spring的配置文件，在配置文件中注册需要创建的对象
4. 编写测试代码，测试类中定义的方法

```java
// ordinary java class
public class People {
    public void sleep(){
        System.out.println("sleep sleep...");
    }
}
```

```xml
<!-- spring config file-->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="people" class="demo01.People">

    </bean>
</beans>
```

```java
// test class
public class People {
    public void sleep(){
        // 1.加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("demo01.xml");
        // 2.创建对象
        People people = context.getBean("people", People.class);
        people.sleep();
    }
}
```



# 2. IOC容器

* IOC底层原理

* IOC接口(BeanFactory)

* IOC操作：Bean管理(基于xml或注解)

## 2.1 什么是IOC

&emsp;控制反转，把对象的创建和对象之间的调用过程，交给spring进行管理。使用Ioc是为了减低耦合。ioc的底层原理涉及到xml解析、工厂模式、反射。

**工厂模式：**

&emsp;需要在A类中调用B类的eat()方法，最原始的方法就是在A类中new一个B对象，然后用这个对象调用eat方法。

&emsp;使用工厂模式就是：创建一个工厂类，在工厂类中定义一个获取B类对象的方法。在A类中调用工厂类的静态方法去获取B类的对象，而不是直接去new。

```java
class ClassFactory{
    public static B getB(){
        return new B();
    }
}
```

**IOC的模式：**

1. 创建xml配置文件
2. 创建工厂类解析XML文件，得到```<bean>```标签中定义的类的信息，通过反射得到类对象
3. 返回目标对象

## 2.2 IOC接口

&emsp;IOC思想基于IOC容器完成，IOC容器的底层就是工厂模式。spring提供IOC容器有两种方式：

1. BeanFactory：IOC容器基本使用，是spring内部使用的接口。加载配置文件时，不会创建配置文件中的对象，在获取对象或使用对象时，才会创建对象。

2. ApplicationContext：BeanFactory接口的子接口，提供了更多的功能。加载配置文件时，就会创建配置文件中的所有对象。它有两个实现类：

   1）FileSystemXmlApplicationContext：xml配置文件在系统中的盘符路径

   2）ClassPathXmlApplicationContext：项目相对路径



## 2.3 Bean管理

&emsp;**spring中存在两种bean：**

1. BeanFactory(工厂bean)：在配置文件中定义的bean类型可以和返回类型不一样
2. 普通bean：在配置文件中定义的bean类型就是返回类型

&emsp;**bean管理：**

1. Spring创建对象：通过IOC去创建对象
2. Spring注入属性：在创建对象的过程中，对对象的属性赋值

&emsp;**IOC操作bean管理**有<font color=#54FF9F>基于xml配置文件实现</font>和<font color=#54FF9F>基于注解方式实现</font>的两种方式

<font color=#ff7700>DI：依赖注入，就是用来实现属性赋值。有set方法注入和有参构造器注入两种方法</font>



## 2.4 基于xml的方式管理

**示例：**

1. 创建对象

   ```xml
   <!-- 基于xml的方式创建bean对象-->
   <bean id="people" class="demo01.People"></bean>
   ```

   在spring的配置文件中，使用bean标签，并在标签中添加对应属性就可以实现对象创建，spring默认采用无参构造器创建对象。bean标签常用属性有：

   ```id属性：```唯一标识

   ```class属性:```目标bean的全限定类名

2. 进行属性注入

   &emsp;**使用set注入**

   ```xml
       <!-- 使用set方法进行依赖注入 -->
       <bean id="bookSetDI" class="demo01.Book">
           <!-- name：属性名    value：属性值 -->
           <property name="bookName" value="spring实战"/>
       </bean>
   ```

   ```java
       // 基于xml管理bean-set方法实现依赖注入
       @org.junit.Test
       public void bookTesting(){
           // 1. 加载配置文件
           ApplicationContext context = new ClassPathXmlApplicationContext("demo01.xml");
   
           // 2. 通过spring配置文件创建对象
           Book book = context.getBean("bookSetDI", Book.class);
           System.out.println(book.getBookName());
       }
   ```

   &emsp;**使用有参构造注入**

   ```java
       // 在bean类中添加有参构造方法
       public Book(String bookName) {
           this.bookName = bookName;
       }
   ```

   ```xml
       <!-- 使用有参构造器进行依赖注入 -->
       <bean id="bookConstructDI" class="demo01.Book">
           <constructor-arg name="bookName" value="Spring从入门到放弃"/>
       </bean>
   ```

**<font color=#ff7700>构造器有多个参数时，就需要使用ref了</font>**

**注入null和特殊字符**

```xml
<property name="">
    <null/>
</property>

<property name="">
    <value>
        <![CDATA[value的值]]>
    </value>
</property>
```

**注入属性-外部bean** <font color=ff7700>使用ref属性，属性值为外部bean的id值</font>

```java
// service实现类
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    // 还是采用的set方法注入，所以必须有目标外部bean属性的set方法
    public void setBookDao(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public void add() {
        bookDao.delete();
        System.out.println("service add...");
    }
}

// dao实现类
public class BookDaoImpl implements BookDao {

    @Override
    public void delete() {
        System.out.println("DAO delete ...");
    }
}
```

```xml
    <bean id="BookDao" class="demo01.dao.impl.BookDaoImpl"/>

    <bean id="BookService" class="demo01.service.impl.BookServiceImpl">
        <!--
            name属性：类中属性名
            ref属性：外部bean的bean标签id
        -->
        <property name="bookDao" ref="BookDao"/>
    </bean>
```



**注入属性-内部bean和级联赋值**

1. 创建两个实体类

   ```java
   // EMP类
   public class Emp {
       private String empName;
       private String gender;
       // 表示一个员工只属于一个部门
       private Dept dept;
   
       public void setEmpName(String empName) {
           this.empName = empName;
       }
   
       public void setGender(String gender) {
           this.gender = gender;
       }
   
       public void setDept(Dept dept) {
           this.dept = dept;
       }
   
       // 要使用外部bean的级联赋值的方式，就必须先定义一个获取外部bean的方法，用于赋值
       public Dept getDept() {
           return dept;
       }
   
       @Override
       public String toString() {
           return "Emp{" +
                   "empName='" + empName + '\'' +
                   ", gender='" + gender + '\'' +
                   ", dept=" + dept +
                   '}';
       }
   }
   
   // DEPT类
   public class Dept {
       private String DeptName;
   
       public void setDeptName(String deptName) {
           DeptName = deptName;
       }
   
       @Override
       public String toString() {
           return "Dept{" +
                   "DeptName='" + DeptName + '\'' +
                   '}';
       }
   }
   ```

   

2. 在spring的配置文件中进行配置，使用IOC对其管理

   ```xml
       <!-- 注入属性之内部bean -->
       <bean id="empBean" class="demo01.bean.Emp">
           <property name="empName" value="张三"/>
           <property name="gender" value="男"/>
           <property name="dept">
               <bean id="deptBean" class="demo01.bean.Dept">
                   <!-- 内部bean的级联赋值 -->
                   <property name="deptName" value="开发部"/>
               </bean>
           </property>
       </bean>
   
   
       <!-- 外部bean的级联赋值的方法 -->
       <bean id="empBean2" class="demo01.bean.Emp">
           <property name="empName" value="张三"/>
           <property name="gender" value="男"/>
           <property name="dept" ref="deptBean2"/>
           <property name="dept.deptName" value="测试部"></property>
       </bean>
       <bean id="deptBean2" class="demo01.bean.Dept"/>
   
   ```

   

3. 试验

   ```java
       // 内部bean和级联赋值
       @org.junit.Test
       public void InBean(){
           ApplicationContext context = new ClassPathXmlApplicationContext("demo01.xml");
           Emp empBean = context.getBean("empBean", Emp.class);
           System.out.println(empBean);
       }
   ```



### 2.4.1 基于xml注入集合类型属性

* 注入数组类型
* 注入List属性
* 注入Map属性

**案例实现**

1. 创建类

   ```java
   public class ArrayTest {
       private int[] arr;
       private List<String> list;
       private Set<String> set;
       private Map<String,String> map;
   
       public void setArr(int[] arr) {
           this.arr = arr;
       }
   
       public void setList(List<String> list) {
           this.list = list;
       }
   
       public void setSet(Set<String> set) {
           this.set = set;
       }
   
       public void setMap(Map<String, String> map) {
           this.map = map;
       }
   
       @Override
       public String toString() {
           return "ArrayTest{" +
                   "arr=" + Arrays.toString(arr) +
                   ", \nlist=" + list +
                   ", \nset=" + set +
                   ", \nmap=" + map +
                   '}';
       }
   }
   ```

   

2. 配置xml文件

   ```xml
       <!-- 对集合属性赋值 -->
       <bean id="collectionDI" class="demo01.collection.ArrayTest">
           <property name="arr">
               <array>
                   <value>2020</value>
                   <value>2021</value>
               </array>
           </property>
   
           <property name="list">
               <list>
                   <value>C语言</value>
                   <value>数据结构</value>
               </list>
           </property>
   
           <property name="map">
               <map>
                   <entry key="张三" value="1001"></entry>
                   <entry key="李四" value="1002"></entry>
               </map>
           </property>
   
           <property name="set">
               <set>
                   <value>永远相信美好的事情即将发生</value>
                   <value>数组类型的属性可以使用list标签也可以使用array标签</value>
                   <value>现在存在的两个问题</value>
                   <value>1. 若集合中存储的数据类型是自定义对象时，如何注入？</value>
                   <value>2. 在ArrayList中定义的集合，只能在当前集合中使用，如何使定义的集合在其他bean中也能使用</value>
               </set>
           </property>
       </bean>
   ```

3. 在测试类中测试

**入集合属性时，有两个问题：**

1. 若集合存储的数据类型是自定义对象时，如何实现注入？
2. 若在其他bean中也想使用这些数据进行注入，那么只能复制粘贴吗？

### 2.4.2 集合属性两个问题的解决

&emsp;问题1的解决方法：当集合中存储的属性是自定义类型时，现将自定义类型在xml中创建好并进行依赖注入，如何在集合中通过```ref```属性进行引入。

&emsp;问题二的解决方法：修改配置文件的约束，添加一条```util```约束，将注入的属性提取出来变成公共的，都可以使用。在需要使用的地方使用```ref```进行注入。

**1.   集合内存储的类型自定义时**

* 创建自定义类

  ```java
  public class Students {
      private String stuName;
      private String gender;
  
      public void setStuName(String stuName) {
          this.stuName = stuName;
      }
  
      public void setGender(String gender) {
          this.gender = gender;
      }
  
      @Override
      public String toString() {
          return "Students{" +
                  "stuName='" + stuName + '\'' +
                  ", gender='" + gender + '\'' +
                  '}';
      }
  }
  ```

  

* 在xml文件中配置

  ```xml
     <!-- 对集合属性赋值 -->
      <bean id="collectionDI" class="demo01.collection.ArrayTest">
          <!-- 其他属性就是上面的，此处不再展示 -->
          <property name="students">
              <list>
                  <ref bean="studetns1"></ref>
                  <ref bean="studetns2"></ref>
                  <ref bean="studetns3"></ref>
              </list>
          </property>
      </bean>
  
      <!-- 集合中的类型自定义-->
      <bean id="studetns1" class="demo01.collection.Students">
          <property name="stuName" value="李华"></property>
          <property name="gender" value="男"></property>
      </bean>
      <bean id="studetns2" class="demo01.collection.Students">
          <property name="stuName" value="jack"></property>
          <property name="gender" value="男"></property>
      </bean>
      <bean id="studetns3" class="demo01.collection.Students">
          <property name="stuName" value="冯七七"></property>
          <property name="gender" value="女"></property>
      </bean>
  ```

  

* 测试



**2. 将注入集合的数据提取出来，使之可以复用，能注入到别的bean中**

* 修改配置文件的约束

  ``` xml
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns：uitl="http://www.springframework.org/schema/util"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/util
          https://www.springframework.org/schema/util/spring-util.xsd
  ">
  ```

* 提取属性的注入

  ```xml
      <!-- 提取注入的集合，使之变为公共的-->
      <util:list id = "list">
          <value>1998</value>
          <value>1997</value>
          <value>1996</value>
      </util:list>
      <!-- 对集合属性赋值 -->
      <bean id="collectionDI" class="demo01.collection.ArrayTest">
          <property name="arr" ref="list">
          </property>
      </bean>
  ```

* 测试



### 2.4.3 工厂bean

1. 创建类，该类实现接口FactoryBean，使之成为工厂类
2. 实现接口中的方法，在getObject()方法中定义返回bean的类型
3. 在xml中注册该bean
4. 测试

工厂类：

```java
public class FactoryBeanTest implements FactoryBean {
    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Emp getObject() throws Exception {
        return new Emp();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
```

<font color=red>发现另一问题，当注册的是工厂bean，返回的是另一类型时，那如何对返回类型对象进行以来注入呢？</font>



### 2.3.6 Bean的作用域

&emsp;在xml中使用bean标签创建bean时，可以是单例对象也可以是多例对象，默认是单例的。**bean的作用域就是bean是单例还是多例。**验证默认是单例，可以对一个对象多次获取去比较地址。

&emsp;在spring中。使用bean标签的```scope```属性设置bean是单实例还是多实例，它的属性值：

1. singleton：默认值，表示单例。加载spring配置文件时，单例对象就被创建了。
2. prototype：表示多例。在调用getBean()方法时，才会创建对象。

示例：

```xml
    <bean id="xxx" class="com.xxx.xxx" scope="singleton">
    </bean>
```



### 2.3.7 Bean的生命周期

&emsp;生命周期：从对象创建到对象销毁的过程。

&emsp;**bean的生命周期：**

1. 通过构造器创建bean实例
2. 为bean的属性赋值和对其他bean引用(set方法)
3. 将bean的实例传递给bean后置处理器的方法postProcessBeforeInitialization()
3. 调用bean的初始化方法
4. 将bean的实例传递给bean后置处理器的方法postProcessAfterInitialization()
5. bean可以被使用
6. 当容器关闭的时候，调用bean的销毁方法(需要配置销毁方法)

*不添加后置处理器是不会有第三步和第五步的*

**示例**

&emsp;创建一个bean

```java
public class Orders {
    private String start;

    public Orders() {
        System.out.println("第一步：从构造器创建bean实例");
    }

    public void setStart(String start) {
        this.start = start;
        System.out.println("第二步：为bean属性赋值和对其他bean引用");
    }
    
    public void initMethod(){
        System.out.println("第三步：调用bean的初始化方法");
    }

    public void useBeanMethod(){
        System.out.println("第四步：bean可以被使用");
    }

    public void closeBean(){
        System.out.println("第五步：容器关闭时，调用bean的销毁方法");
    }
}
```

&emsp;在xml中进行配置

```xml
    <!-- bean的生命周期 -->
    <bean id="beanOrders" class="demo01.bean.Orders" init-method="initMethod" destroy-method="closeBean">
        <property name="start" value="now"/>
    </bean>
```

&emsp;测试

```java
    // bean的生命周期
    @org.junit.Test
    public void beanOrders(){
        // 因为close方法是在ApplicationContext的实现类ClasPathXmlApplicationContext中实现了
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("demo01.xml");
        Orders beanOrders = context.getBean("beanOrders", Orders.class);
        beanOrders.useBeanMethod();
        context.close();
    }
```

**演示添加后置处理器后的效果：**

&emsp; 创建类实现接口``` BeanPostProcessor```，并实现接口方法

```java
public class OrdersPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化之前执行");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化之后执行");
        return bean;
    }
}
```

&emsp;在xml文件中配置后置处理

```xml
    <!-- 添加后置处理器
         当加载配置文件时，会创建bean和后置处理器，并为所有的bean都添加后置处理器
         因为后置处理器类实现了BeanPostProcessor接口，spring就会将它作为后置处理器执行
    -->
    <bean id="OrdersPost" class="demo01.bean.OrdersPost"/>
```



### 2.3.8 基于XML的自动装配

**手动装配：**就是通过bean标签的子标签property对属性值进行手动配置。

**手自动装配：**根据指定的装配规则(属性名称或属性类型)，spring自动将匹配的属性值进行注入。

**示例-手动装配**

```java
package demo01.autowire;

public class Emp {
    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }

    public void test(){
        System.out.println(dept);
    }
}

package demo01.autowire;

public class Dept {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

```xml
    <!-- 手动装配 -->
    <bean id="autoWireEmp" class="demo01.autowire.Emp">
        <property name="dept" ref="autoWireDept"/>
    </bean>
    <bean id="autoWireDept" class="demo01.autowire.Dept">
        <property name="deptName" value="财务部"></property>
	</bean>
```

```
输出
Emp{dept=Dept{name='财务部'}}
```



**示例-自动装配**

```xml
    <!-- 自动装配
         使用bean标签的autowire属性配置自动装配，autowire常用的两个属性值：
         byName: 根据属性名注入，bean的属性名，必须和注入值bean的id相同。
                 比如Emp有属性名为dept，那么在xml中注册Dept的bean时，必须为dept
         byType: 根据属性类型注入,根据属性的类型，在xml中找到目标类型自动注入
                 当同一类型在xml中定义多个时，byType找不到目标类型，会报错
    -->
    <bean id="autoWireEmp" class="demo01.autowire.Emp" autowire="byName">
    </bean>
    <bean id="dept" class="demo01.autowire.Dept">
        <property name="name" value="技术部"></property>
    </bean>
```

```
输出
Emp{dept=Dept{name='技术部'}}

去掉autowire属性后的输出
Emp{dept=null}
```

&emsp;<font color=#DCACCA>在基于xml管理bean中使用```autowire```属性进行自动装配，但是在实际中，都采用注解的方式进行自动装配。</font>



### 2.3.9 映入外部属性文件

&emsp;当bean中的属性过多时，按照上面的方法写入spring配置文件中。这样不是不方便，且在属性值发生变化时，还需要去修改xml1文件。在实际中都会将其写入一个外部文件中，然后将外部文件引入到xml中。比如对数据库操作时，数据库的相关信息。





**示例-写入外部文件后，在xml中引入**

### 2.3.10 基于注解的方式

&emsp;注解时代码特殊标记，注解可以用在类、属性、方法上面。使用注解的目的是简化xml配置。

&emsp;spring针对bean管理中创建对象提供的注解有以下四种：

1. @Component：表示spring容器中普通的组件
2. @Service：一般用在业务逻辑层
3. @Controller：一般用在web层
4. @Repository：一般用到dao层

*上述四个注解功能是一样的，都可以用来创建bean实例*

**示例——基于注解创建bean对象**

1. 引入依赖```spring-aop```

2. 开启组件扫描。在配置文件中引入名称空间。

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                              http://www.springframework.org/schema/beans/spring-beans.xsd
                              http://www.springframework.org/schema/context
                              https://www.springframework.org/schema/context/spring-context.xsd ">
       <!-- 开启包扫描
        如果扫描多个包，用逗号将包的全限定类名分割，或者直接扫描包的上层目录
   -->
       <context:component-scan base-package="demo01.comment.service"></context:component-scan>
   </beans>
   <!--
    xmlns:context="http://www.springframework.org/schema/context"
   -->
   ```

   ```java
   // 注解的value可以不写，默认是类名且首字母小写
   @Service(value = "userService") // 等于<bean id="userService class="..."/>
   public class UserService {
       public void add(){
           System.out.println("comment service add...");
       }
   }
   ```

3. 测试

### 2.3.11 组件扫描配置

扫描注解有两种方式：

1. 使用xml配置
2. 使用注解

**示例1:** 只扫描带@Service注解的类

```xml
    <!-- 包扫描配置-示例1
         use-default-filters="false" 表示现在不使用默认的filter，使用自己配置filter
         context:include-filter 设置扫描那些内容
         type="annotation" 扫描注解
         expression="org.springframework.stereotype.Service" 扫描Service注解
    -->
    <context:component-scan base-package="demo01.comment.service" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
    </context:component-scan>
```



**示例2:**  不扫描带@Controller注解的类

```xml
    <!-- 包扫描配置-示例2
     context:exclude-filter 设置不扫描那些内容
     type="annotation" 扫描注解
     expression="org.springframework.stereotype.Service" 不扫描Service注解
-->
    <context:component-scan base-package="demo01.comment.service" use-default-filters="false">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
```



**示例3：**使用注解来配置

​	配置类

```java
@Configuration  // 标记为配置类，代替xml文件
@ComponentScan(basePackages = {"demo01.comment"})
public class SpringConfig {
}
```

​	测试(userService在2.3.12中看)

```java
    // 使用@Configration配置类
    @Test
    public void testConfigration(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService bean = context.getBean("userService", UserService.class);
        bean.add();
    }
}
```





### 2.3.12 基于注解注入属性

&emsp;spring提供了@AutoWired、@Qualifier、@resource三个注解实现属性注入。

**@AutoWired：**根据属性类型进行自动装配

**@Qualifier：**根据属性名进行注入

**@Resource：**可以根据属性类型注入也可以根据属性名注入

**@Value：** 注入普通类型属性



**示例1：**使用@AutoWired在service中注入dao对象

1. 在dao和service的实现类上都标记不同的注解，并在service中添加dao对象属性，且不需要在创建set()方法

```java
@Repository
public class UserDao {
    public void add(){
        System.out.println("dao add...");
    }
}

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void add(){
        userDao.add();
        System.out.println("comment service add...");
    }
}
```



**示例2：**使用@Qualifier在service中注入dao对象。@Qualifier需要和@AutoWired一起使用，因为若一个接口有多个实现类，通过类型注入就无法知道要找哪个实现类了。

```java
@Service
public class UserService {
    @Autowired
    @Qualifier("userDao")	// 若一个接口有多个实现类，通过类型注入就无法知道要找哪个实现类了
    private UserDao userDao;
    public void add(){
        userDao.add();
        System.out.println("comment service add...");
    }
}
```

 

**示例3：**使用@Resource注解实现示例1和2。@Resource注解是javax提供的。

```java
@Service
public class UserService {
    // @Resource
    @Resource(name="userDao")
    private UserDao userDao;
    public void add(){
        userDao.add();
        System.out.println("comment service add...");
    }
}
```



**示例4：**使用@Value注解，对普通属性赋值

```java
@Service
public class UserService {

    @Value("张三")
    private String name;

    @Resource(name="userDao")
    private UserDao userDao;
    public void add(){
        userDao.add();
        System.out.println("comment service add...");
        System.out.println("name="+name);
    }
}
```

<font color=red>那么@Value可以对集合属性注入吗？</font>



# 3. AOP

**AOP官方解释：** Aspect Oriented Programming缩写面向切面编程。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各个部分之间的耦合度降低，提高程序的**可重用性**，同时提高了开发效率。

**AOP的功能：** 不修改源代码，新增新功能。《Spring实战》骑士和游吟诗人的关系。

**AOP低层原理：** 动态代理，有两种情况

1. 有接口的情况：使用JDK动态代理，创建接口实现类代理对象，增强类的方法

   ```java
   // 接口
   interface UserDao(){
       void login();
   }
   
   // 实现类
   class UserDaoImpl implements UserDao{
       public void login(){
           // 实现过程
       }
   }
   
   // 使用jdk动态代理
   /* 创建UserDao接口实现类代理对象，通过代理对象增强UserDaoImpl的功能*/
   ```

   

2. 无接口的情况：使用CGLB动态代理，创建子类的代理对象，增强类的方法

   ```java
   class User(){
       public void add(){}
   }
   
   // CGLB动态代理
   /* 创建当前类子类的代理对象*，通过代理对象增强User的功能/
   ```



## 3.1 JDK动态代理

&emsp;使用jdk动态代理要用到```Proxy```类的```newProxyInstance(ClassLoader loader,Class<?>[] interface,InvocationHandler h)```方法。改方法的三个参数意义为：

1. ```ClassLoader loader``` 类加载器
2. ```Class<?>[] interface``` 增强方法所在的类实现的接口，可以有多个
3. ```InvocationHandler h``` ：一个接口，创建一个类去编写要增强的逻辑并实现此接口

**步骤：**

1. 创建示例接口，以```User```示例
2. 创建接口实现类，以```UserImpl```示例
3. 创建```InvacationHandler```接口的实现类，以```EnhanceLogic```示例
   * 创建有参构造，将目标接口实现类对象传入进来，比如这里要对```User```接口的方法进行增强，就需要传入```UserImpl```
   * 在实现接口的```invoke```方法中，编写需要增强的逻辑
4. 创建测试类，使用```Proxy```类创建接口代理对象
   * 使用```Proxy```类的```newProxyInstance()```方法，创建接口代理对象
   * 对代理对象进行强转，调用目标方法

```java
// User接口
public interface User {
    int add(int a,int b);
    StringBuilder update(StringBuilder id);
}
```

```java
// User接口实现类
public class UserImpl implements User {
    @Override
    public int add(int a,int b) {
        return a+b;
    }

    @Override
    public StringBuilder update(StringBuilder id) {
        return id.append("...");
    }
}
```

```java
// InvocationHandler接口实现类
public class EnhanceLogic implements InvocationHandler {
    private Object invocation;

    // 有参构造,将目标对象传入
    public EnhanceLogic(Object invocation){
        this.invocation = invocation;
    }

    /**
     * 增强的逻辑写入此方法中
     * @param proxy 代理对象
     * @param method 代理对象的方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.目标方法执行之前要做的事情
        System.out.println(method.getName()+"方法要执行了");

        // 2.被增强的方法开始执行，根据不同的方法执行不同的操作
        if (method.getName().equals("add")){
            for(Object o : args){
                System.out.println("方法参数："+o);
            }
        }else{
            System.out.println("当前id是："+args[0]);
        }
        Object invoke = method.invoke(invocation, args);

        // 3.目标方法执行之后要做的事情
        System.out.println(method.getName()+"方法执行完毕");

        // 4.返回值
        return invoke;
    }
}
```

```java
// 测试类
public class JDKProxy {
    // 创建接口实现类代理对象
    public static void main(String[] args) {
        // 1.newProxyInstance方法的三个参数
        ClassLoader loader = JDKProxy.class.getClassLoader();
        Class[] interfaces = {User.class};
        /**
         * 第三个参数的内部类写法
         *         new InvocationHandler() {
         *             @Override
         *             public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         *                 return null;
         *             }
         *         };
         */

        // 第三个参数的外部实现类写法
        User user = new UserImpl();
        EnhanceLogic h = new EnhanceLogic(user);

        // 2.使用Proxy的方法创建代理对象
        User proxyInstance = (User)Proxy.newProxyInstance(loader, interfaces, h);

        // 3.调用方法测试
        int add = proxyInstance.add(3, 10);
        System.out.println("add()方法的结果是："+add);

        StringBuilder id = new StringBuilder("2022");
        StringBuilder update = proxyInstance.update(id);
        System.out.println("update()方法的结果是："+update);
    }
}
```



## 3.2 AOP术语

**常见术语：**

1. 连接点：哪些方法可以增强，就称为连接点。例如上面例子的add方法、update方法
2. 切入点：实际被增强的方法就称为切入点，比如只增强的add方法，那么add就是切入点，add和update是连接点
3. 通知(增强)：实际增强的逻辑部分。通知的五种类型：
   * 前置通知：发生前执行
   * 后置通知：发生后执行
   * 环绕通知：发生前和发生后都执行
   * 异常通知：发生异常时执行
   * 最终通知：相当于finally
4. 切面：将通知应用到切入点的过程。切面是一个动作



## 3.3 AOP操作

&emsp;将IOC和AOP的概念解释后，接下来就是在spring框架中完成AOP的具体操作。在spring中实现AOP有多种实现方式，实现AOP都是基于```AspectJ```。

&emsp;<font color=#ff7700>AspectJ</font>不是Spring的租出部分，是一个独立的AOP框架。一般把**AspectJ**和Spring框架一起使用，进行AOP操作。



### 3.3.1 基于AspectJ在spring中实现AOP操作

**基于AspectJ实现AOP操作的两种方式：**

1. 基于xml配置文件实现
2. 基于注解方式实现（常用）

**进行AOP操作的准备工作步骤：**

1. **引入依赖**

   * spring-aspects
   * springsource.net.sf.cglib
   * aopaliance
   * aspect.weaver

2. **切入点表达式**

   切入点表达式作用：表示对哪个类的哪个方法进行增强

   语法结构：execution([权限修饰符] [返回类型] [全限定类名] [方法名称] [参数列表])

   例子1：对com.dao.BookDao类的add方法进行增强

   ```execution(*com.dao.BookDao.add(..))``` ：第一个```*```表示所有修饰符，返回值可以不写，最后```(..)```表示参数

   例子2：对com.dao.BookDao类的所有方法进行增强

   ```execution(*com.dao.BookDao.*(..))``` 

   例子三：对com.dao包类所有类的方法都就进行增强

   ```execution(*com.dao.*.*(..))```



### 3.3.2 基于AspectJ的注解的方式

**步骤：**

1. 创建被增强类User，在类中定义方法

2. 创建增强类UserProxy，在类中编写增强逻辑，让不同的方法代表不同的通知类型

3. 进行通知的配置
   * 在spring配置文件中开启注解扫描
   
     ```xml
     <context:component-scan base-package="demo02.aopOperation"></context:component-scan>
     ```
   
   * 使用注解创建User和UserProxy对象
   
   * 在增强类上添加注解```@Aspect```
   
   * 在spring配置文件中开启生成代理对象
   
   * 配置不同类型的通知
   
     在增强类中，对通知方法使用相应的注解，结合切入点表达式进行配置
   
   * 你好

**代码实例：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation=
               "http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!-- 开启注解扫描-->
    <context:component-scan base-package="demo02.aopOperation"></context:component-scan>

    <!-- 开启Aspect生成代理对象 当类有标注@Aspect注解，那就将改类的对象生成代理对象-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>
```

```java
// 被增强的类
@Component
public class User {
    public void add(){
        System.out.println("add()方法执行了... ...");
    }
}
```

```java
// 增强的类
@Component
@Aspect // 注解表示要生成代理对象
public class UserProxy {
    // 前置通知
    @Before("execution(* demo02.aopOperation.User.add(..))")
    public void before() {
        System.out.println("before... 前置通知...");
    }

    // 后置通知
    @After("execution(* demo02.aopOperation.User.add(..))")
    public void after() {
        System.out.println("after... 后置通知...");
    }

    // 后置通知 返回值之后执行
    @AfterReturning("execution(* demo02.aopOperation.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning... 后置通知，在返回值之后执行...");
    }

    /**
     * 环绕通知
     *
     * @param point
     */
    @Around("execution(* demo02.aopOperation.User.add(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("around...   环绕之前...");
        // proceed()表示被增强的方法
        point.proceed();
        System.out.println("around...   环绕之后...");
    }

    // 异常通知
    @AfterThrowing("execution(* demo02.aopOperation.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing... 异常通知...");
    }
}
```

```java
    @org.junit.Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringXMLDemo02/Demo02.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }
```

控制台输出：

```
around...   环绕之前...
before... 前置通知...
add()方法执行了... ...
around...   环绕之后...
after... 后置通知...
afterReturning... 后置通知，在返回值之后执行...
```



&emsp;异常通知只有在发生异常后在触发，手动为add()方法产生异常后，控制台输出

```
around...   环绕之前...
before... 前置通知...
after... 后置通知...
afterThrowing... 异常通知...
```

&emsp;从输出可以看到，after通知无论有无异常都会触发，所以异步称after为最终通知，afterReturning为后置通知

**问题：**<font color=red>使用注解的话，如何像实现接口那样获取方法名等操作呢</font>



**使用细节：**

1. **重用切入点**

   ```java
   @Pointcut("execution(* demo02.aopOperation.User.add(..))")
   public void  pointDemo02(){}
   // 前置通知
   @Before("pointDemo02()")
   public void before() {
       System.out.println("before... 前置通知...");
   }
   ```

2. 有多个增强类对同一方法进行增强，**设置优先级**。在增强类上面添加注解```@Order(int类型值)```，值越小，优先级越高



### 3.3.3 基于AspectJ的XML配置文件的方式





# 4. JDBCTemplate





# 5. 事务管理







# 6. spring5新特性

















