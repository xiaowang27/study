# MyBatis学习笔记
**hibernote框架：** 全自动，要优化sql语句的话得学好HQL
**MyBatis框架：** 搬自动，将sql的编写提出来交给程序员去编写，不自动化生成。

---
### 初次使用MyBatis
&emsp;步骤
1. 创建数据表
``` sql
-- 创建员工表
create table emp(
id int(11) primary key auto_increment,
emp_name varchar(255),
gender char(1),
email varchar(255)
)

-- 插入数据
INSERT INTO `mybatis_study`.`emp` (`id`, `emp_name`, `gender`, `email`) VALUES (1, 'jack', '0', 'jack@123.com');
```
2. 创建工程
3. 在pom文件中；引入mysql、mybatis、jUnit的依赖
``` xml
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.0</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.37</version>
        </dependency>
        
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>
        </dependency>
```
4. 创建实体类
``` java
package bean;

public class Employee {
    private Integer id;
    private String empName;
    private String gender;
    private String email;

    成员属性的get And set 方法
    
    @Override
    public String toString() {
        ... ...
    }
}

```
5. 编写mybatis配置文件，设置数据库地址等信息
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis_study"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 将写好的sql映射文件注册到全局配置文件中 -->
    <mappers>
        <mapper resource="mapper/EmployeeMapper.xml"/>
    </mappers>
</configuration>
```
6. 创建mybatis工具类，用以获取sqlSession对象
``` java
public class MyBatisUtil {

    /**
     * 1. 根据XML配置文件，创建一个sqlSessionFactory对象
     * @throws IOException mybatis配置文件路径异常
     */
    @Test
    public void sqlSessionFactory() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession实力，能够指向已经映射的sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 第一个参数是sql语句的唯一标识符(最好是命名空间+id)，第二个参数是sql语句要执行的参数
        Employee emp = sqlSession.selectOne("com.study.mybatis.employeeMapper.selectEmpList",1);
        System.out.println(emp);

        // 3. 释放资源
        sqlSession.close();
    }
}

```
7. 编写mapper映射文件，编写sql语句
``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--
    namespace 名称空间 一般是接口的全限定类名
    id 唯一标识
    resultType 返回值类型
    #{id} 从传递来的参数中取出id值
-->
<mapper namespace="com.study.mybatis.employeeMapper">
    <select id="selectEmpList" resultType="bean.Employee">
        select id,emp_name empName,gender,email from emp where id = #{id}
    </select>
    <!-- 当实体类的成员属性与数据表字段名不一致是，就会返回null，解决方法时在sql中为不同名的列名起别名-->
    <select id="selectEmpList02" resultType="bean.Employee">
        select * from emp where id = #{id}
    </select>
</mapper>
```
8. 将mapper映射文件在mybatis全局配置文件中注册
``` xml
    <mappers>
        <mapper resource="mapper/EmployeeMapper.xml"/>
    </mappers>
```

---
### 接口式编程
1. 创建接口，编写数据库操作的方法
``` java
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}

```
2. 将接口和mapper文件绑定。在mapper文件中，将命名空间改为接口的全限定类名
``` xml
<mapper namespace="dao.EmployeeMapper">
```
3. 在测试方法中通过接口获取实现类对象
``` java
    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "config/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void test01() throws IOException{
        // 1. 获取SqlSession对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取接口实现类对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee emp = mapper.getEmpById(1);
        System.out.println(emp);
        sqlSession.close();
    }
```

&emsp;**总结：**
1. 以前都是一个DaoImpl对应一个接口的实现类。用mybatis后一个接口对应一个mapper配置文件。 
2. SqlSession表示和数据库的一次对话，用完必须关闭。SqlSession和Connection一样，都不是线程安全了，每次使用都应该去获取新的对象。
3. mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象(前提是两者进行绑定了)。
4. 两个重要的配置文件：
   <br>
   &emsp;mybatis全局配置文件：包括数据库连接池信息、事物管理器信息等
   <br>
   &emsp;sql映射文件：保存了每一个sql语句的映射信息，通过映射文件将sql抽取出来。

---

# 1. 全局配置文件
&emsp;MyBatis的配置文件包含了影响MyBatis行为甚深的**设置**和**属性**信息。文档的顶层结构如下：
* configuration配置<br>
&emsp; -properties 属性<br>
&emsp; -settings 设置 <br>
&emsp; -typeAliases 类型命名 <br>
&emsp; -typeHandlers 类型处理器 <br>
&emsp; -objectFactory 对象工厂 <br>
&emsp; -plugins 插件 <br>
&emsp; -environments 环境 <br>
&emsp;&emsp;&emsp; -environment 环境变量 <br>
&emsp;&emsp;&emsp; -transactionManager 事务管理器 <br>
&emsp;&emsp;&emsp; -dataSource 数据源 <br>
&emsp; -databaseIdProvider 数据库厂商标识 <br>
&emsp; -mappers 映射器 <br>
  
## 1.1 properties标签
&emsp;1. mybatis可以使用properties来引入外部的properties文件的内容。
```xml
    <!-- url: 引入网络路径或磁盘路径下的资源 -->
    <properties url=""></properties>
```
``` xml
    <!-- resource: 引入类路径下的资源 -->
   <properties resource=""></properties>
```

&emsp;<font color=#ff7700>**尝试：改变mybatis-config.xml文件，使数据库连接等信息都是从另一个文件读取而来的**</font>
``` xml
<!-- 最开始的mybatis-config.xml文件内容 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis_study"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
    
<!-- 创建yml文件存储数据库连接的信息 -->
driver: com.mysql.jdbc.Driver
url: jdbc:mysql://localhost:3306/mybatis_study
username: root
password: 123456

<!-- 在mybatis-config.xml文件中使用这些信息 -->
   <!-- 引入类路径下资源 -->
    <properties resource="config/dbconfig.yml"></properties>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!-- 读取类路径下资源的内容 -->
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
```
## 1.2 setting标签
| 设置参数 | 描述 | 有效值 | 默认值 |
| ---- | ---- | ---- | ---- |
| cacheEnabled | 该配置影响的所有映射器中配置的缓存全局开关 | true 或者 false | TRUE
| lazyLoadingEnabled | 延迟加载的全局开关，当开启时，所有关联对象都会延迟加载。特定关联关系中可以通过设置fetchType属性来覆盖该项的开关状态。 | true 或者 false | FALSE |
| useColumnLabel | 使用列标签代替列名。不同的驱动在这方面会有不同的表现。具体可参考相关驱动文档或通过测试者两种不同的模式来观察所用驱动的结果 | true 或者 false | TRUE |
| defaultStatementTimeout| 设置超时时间，它决定驱动等待数据库响应的秒数。 | Any positive integer | Not Set(null) |
| mapUnderscoreToCamelCase | 是否开启自动驼峰命名规则映射，即从经典数据库列名A_COLUMN到经典Java数据名aColumn的类似映射 | true 或者 false | FALSE |
| ...(还有更多) | ... | ... | ... |
&emsp;开启自动峰命令规则映射：
``` xml
<!-- mapper1文件中的查询 -->
    <select id="getEmpById" resultType="bean.Employee">
        select id,emp_name empName,gender,email from emp where id = #{id}
    </select>
```
&emsp;开启前的输出
>Employee{id=1, empName='null', gender='0', email='jack@123.com'}

&emsp;开启后的输出
>Employee{id=1, empName='jack', gender='0', email='jack@123.com'}

&emsp;因为在实体类Employee中时empName，而在数据表emp中则是emp_name

## 1.3 typeHandlers标签
&emsp;类型处理器标签。Java数据类型和数据库类型映射的桥梁。


## 1.4 plugins标签
&emsp;插件标签。

## 1.5 environments标签
&emsp;环境标签。
```xml
    <!--
        mybatis可以配置多种环境，default指定使用某种黄金，可以达到快速切换环境
        environment：配置一个具体的环境信息，必须有两个标签，id表示当前环境的唯一标识
            transactionManager：事务管理器
                type：事物管理器的类型。有两种类型：JDBC|MANAGED
                      自定义事物管理器：实现TransactionFactory接口，type指定为全类名
            dataSource：数据源类型。UNPOOLED|POOLED|JNDI
                        自定义数据源：实现DataSourceFactory接口，type指定全类名
     -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
```

## 1.6 databaseIdProvider标签
&emsp;mybatis可以支持不同数据库厂商，执行不同的sql语句。
&emsp;在mybatis的配置文件中配置
``` xml
    <!--
        databaseIdProvider支持多数据库厂商
        type = "DB_VENDOR": VenderDatabaseIdProvider
            作用就是得到数据库厂商的标识(驱动自带的，getDatabaseProductName()得到)
            mybatis就能工具数据库厂商标识来执行不同的sql
            MySQL、Oracle、SQL Server
    -->
    <databaseIdProvider type="DB_VENDOR">
        <!-- 为不同的数据库厂商起别名 -->
        <property name="MySQL" value="mysql"/>
        <property name="Oracle" value="oracle"/>
    </databaseIdProvider>
```

&emsp;在mapper文件中，告诉sql标签
``` xml
    <select id="selectEmpList02" resultType="bean.Employee" 
    databaseId="mysql">
        select * from emp where id = #{id}
    </select>
```

## 1.7 mapper标签
&emsp;sql映射标签。将sql映射注册到全局中。
``` xml
    <!-- 将写好的sql映射文件注册到全局配置文件中 -->
    <mappers>
        <!--
            mapper：注册一个sql映射
                resource：引用类路径下的sql映射文件
                url：引用网络路径下或磁盘路径下的sql映射文件
                class：直接引用接口。如果要注册接口,sql映射文件必须和接口同名且二者放在同一目录下。
                      一般引用接口，都是利用注解将sql写在接口上，没有映射文件。
         -->
        <mapper resource="mapper/EmployeeMapper.xml"/>
        <mapper class="dao.AutoEmpSQL"/>
        <!-- 批量注册
            name：包名
            mapper文件必须和接口同名且放在同一包下，可以在resource下建立一个文件夹，和接口包同名，将mapper文件放入其中
            这是一种视觉上让工程更加有条理的方法，实际上还是在一个包内。
        -->
        <package name=""/>
    </mappers>
```

&emsp;接口写法：
``` java
public interface EmpMapper {
    @Select("select * from emp")
    public List<Employee> queryEmpList();
}
```

# 2. mybatis映射文件
&emsp;映射文件指导着mybatis如何进行数据库增删改查。
* cache 命名空间的二级缓存设置
* cache-ref 其他密码空间缓存配置的引用
* resultMap 自定义结果集映射
* parameterMap已废弃 老式风格的参数映射
* sql 抽取刻重用语句块
* insert 映射插入语句
* update 映射更新语句
* delete 映射删除语句
* select 映射查询语句

## 2.1 增删改语句
**mapper文件**
``` xml
 <!-- 增加员工方法 -->
    <insert id="addEmp" parameterType="bean.Employee">
        insert into emp (id, emp_name, gender, email)
        values (#{id}, #{empName}, #{gender}, #{email})
    </insert>

    <!-- 更新员工方法 -->
    <update id="updateEmp">
        update emp
        set id=#{id},emp_name=#{empName},gender=#{gender},email=#{email}
        where id=#{id}
    </update>

    <!-- 删除员工方法 -->
    <delete id="deleteEmp">
        delete from emp where id=#{id}
    </delete>
```
**dao接口**
``` java
    // 增加员工方法
    public Integer addEmp(Employee employee);

    // 更新员工信息方法
    public Integer updateEmp(Employee employee);

    // 删除员工方法
    public Boolean deleteEmp(Employee employee);

```

**测试方法**
``` java
   @Test
    public void crudTest() throws IOException {
        // 1. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2. 获取SqlSession对象    无参方法不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取接口实现类对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        // 前提准备，创建一个emp对象
        Employee employee = new Employee();
        employee.setId(220113);
        employee.setEmpName("张三");
        employee.setGender("男");
        employee.setEmail("zhangsan@123.com");

        // 4. 执行crud
        // addEmp(mapper, employee);
        // employee.setGender("女");
        // updateEmp(mapper,employee);
        // deleteEmp(mapper,employee);
        getEmpList(mapper,employee);

        // 提交
        sqlSession.commit();
    }
    
    // 添加员工
    public void addEmp(EmployeeMapper employeeMapper, Employee employee) {
        System.out.println("添加员工");
        employeeMapper.addEmp(employee);
    }

    // 删除员工
    public void deleteEmp(EmployeeMapper employeeMapper, Employee employee) {
        System.out.println("删除员工");
        employeeMapper.deleteEmp(employee);
    }

    // 修改信息
    public void updateEmp(EmployeeMapper employeeMapper, Employee employee) {
        System.out.println("修改信息");
        Integer integer = employeeMapper.updateEmp(employee);
        if(integer==1){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
```
&emsp;**<font color=#54ff9f>1. 语句提交</font>。当openSession()方法参数是true时，自动提交** 
``` java
SqlSession sqlSession = sqlSessionFactory.openSession(true);
```
&emsp;**<font color=#54ff9f>2. insert获取自增主键。</font>** mysql支持自增主键，在jdbc中获取自增主键是使用statement.getGenreatedKesy()。
在mybatis也是利用的这个方法。使用步骤是
* 在&lt;insert&gt;标签中属性设置为
<font color=#ff7700>useGeneratedKeys=true</font>，使用自增主键获取主键值策略
* <font color=#ff7700>keyProperty=</font>指定对应的主键属性(就是对应的实体类的属性)
``` xml
<!-- mapper文件设置 -->
    <!-- 增加员工方法 -->
    <insert id="addEmp" parameterType="bean.Employee" useGeneratedKeys="true" keyProperty="id">
        insert into emp (id, emp_name, gender, email)
        values (#{id}, #{empName}, #{gender}, #{email})
    </insert>
```


## 2.2 参数处理
### 2.2.1 单个参数

单个参数mybatis不会做特殊处理，形式：#{参数名}。参数名写什么都可以。
``` xml
    <select id="getEmpById" resultType="bean.Employee">
        select *
        from emp
        where id = #{id}
    </select>
```

### 2.2.2 多个参数
多个参数mybatis会做特殊处理，多个参数会被封转成一个map，#{}就是从map中获取指定的key值。以下面为例
``` xml
    <!-- mybatis多个参数处理 -->
    <select id="mybatisParas" resultType="bean.Employee">
        select * from emp where id=#{id} and emp_name=#{empName}
    </select>
```

``` java
    // mybatis参数处理之多个参数
    @Test
    public void mybatisParas() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee jack = mapper.mybatisParas(1, "jack");
        System.out.println(jack);
    }
    
    /*
      报错：
      Cause: org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
    */
```
&emsp;map是[param1:value1,param2:value2...]，所以多个参数取值的话可以使用#{param1}...或[索引]。
更改xml文件的取值如下，就不会报错了
``` xml
    <!-- mybatis多个参数处理 -->
    <select id="mybatisParas" resultType="bean.Employee">
        select * from emp where id=#{param1} and emp_name=#{param2}
    </select>
```

&emsp;采用param1、param2...这种方法不够直观看出参数，所以可以使用<font color=#ff700>命名参数</font>的方法，使用方法如下：
``` java
// dao接口的方法
public Employee mybatisParas(@Param("id") int id, @Param("empName") String empName);
```
&emsp;命名参数：明确指定封装参数时map的key，使用注解@Param("参数名""),多个参数会被封装成一个map