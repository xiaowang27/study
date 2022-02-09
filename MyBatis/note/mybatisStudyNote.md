# MyBatis学习笔记

**hibernote框架：** 全自动，要优化sql语句的话得学好HQL
**MyBatis框架：** 半自动，将sql的编写提出来交给程序员去编写，不自动化生成。

---

### 初次使用MyBatis

&emsp;步骤

1. 创建数据表

``` sql
-- 创建员工表
-- mybatis_study.emp definition

CREATE TABLE `emp` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) NOT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `fk_emp_dept` (`dept_id`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

2. 添加数据

   ```sql
   INSERT INTO mybatis_study.emp
   (emp_id, emp_name, gender, email, dept_id)
   VALUES(1, '张三', '男', 'zhangsan@123.com', 1001);
   INSERT INTO mybatis_study.emp
   (emp_id, emp_name, gender, email, dept_id)
   VALUES(2, '李四', '男', 'lisi@123.com', 1002);
   INSERT INTO mybatis_study.emp
   (emp_id, emp_name, gender, email, dept_id)
   VALUES(3, '王梅', '女', 'wangmei@123.com', 1002);
   INSERT INTO mybatis_study.emp
   (emp_id, emp_name, gender, email, dept_id)
   VALUES(4, '夏于', '女', 'xiayu@123.com', 1002);
   ```

   

3. 创建工程

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
    private Integer empId;
    private String empName;
    private String gender;
    private String email;
    private Integer deptId;
    private Department dept;

    /**一些get&set方法**/
    @Override
    public String toString() {...}
}


```

5. 编写mybatis配置文件，设置数据库地址等信息

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.o·rg//DTD Config 3.0//EN"
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
    public static SqlSession getSqlSesion(){
        String resource = "config/mybatis-config.xml";
        SqlSession sqlSession = null;
        try {
            InputStream input = Resources.getResourceAsStream(resource);
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(input);
            sqlSession = build.openSession(true);   // 设置true就是默认提交
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return sqlSession;
        }
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
    namespace 名称空间
    id 唯一标识
    resultType 返回值类型
    #{id} 从传递来的参数中取出id值
-->
<mapper namespace="dao.EmployeeMapper">
    <!--初次使用mybatis-->
    <!--根据员工id查询员工
        id：唯一标识符
        resultType：查询结果类型
        #{empId}：传入参数名
    -->
    <select id="getByEmpId" resultType="bean.Employee">
        select *
        from emp
        where emp_id=#{empId}
    </select>
</mapper>
```

8. 将mapper映射文件在mybatis全局配置文件中注册

   ```xml
       <mappers>
           <mapper resource="mapper/EmployeeMapper.xml"/>
       </mappers>
   ```

9. 编写mapper对应的接口(也可以不写接口，直接使用mapper文件)

   ```java
   public interface EmployeeMapper {
   
       // 通过emp_id查询员工
       public Employee getByEmpId(Integer empId);
   ```

10. 编写测试方法进行测试

    ```java
    public class Demo01 {
        /**初次使用MyBatis**/
        /*根据员工id查询员工*/
        @Test
        public void getByEmpId(){
            // 通过mybatis工具类获取sqlsession对象
            SqlSession sqlSession = MyBatisUtil.getSqlSesion();
            // 通过sqlsession对象获取到mapper文件的接口
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 调用dao接口的方法
            // Employee emp1 = mapper.getByEmpId(1);
            // System.out.println(emp1);
    
            // 不写接口，直接使用mapper文件
            Employee emp2 = sqlSession.selectOne("dao.EmployeeMapper.getByEmpId", 2);
            System.out.println(emp2);
    
            // 释放资源
            sqlSession.close();
        }
    }
    ```

    

---

### 接口式编程

1. 创建接口，编写数据库操作的方法

``` java
public interface 接口名 {
    public 返回值类型 方法名(形参类型 形参名 );
}

```

2. 将接口和mapper文件绑定。在mapper文件中，将命名空间改为接口的全限定类名

``` xml
<mapper namespace="目标接口全类型类名">
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
        EmployeeMapper mapper = sqlSession.getMapper(接口.class);
        Employee emp = mapper.接口方法(形参);
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

| 设置参数                       | 描述                                                         | 有效值               | 默认值        |
| ------------------------------ | ------------------------------------------------------------ | -------------------- | ------------- |
| cacheEnabled                   | 该配置影响的所有映射器中配置的缓存全局开关                   | true 或者 false      | TRUE          |
| lazyLoadingEnabled             | 延迟加载的全局开关，当开启时，所有关联对象都会延迟加载。特定关联关系中可以通过设置fetchType属性来覆盖该项的开关状态。 | true 或者 false      | FALSE         |
| useColumnLabel                 | 使用列标签代替列名。不同的驱动在这方面会有不同的表现。具体可参考相关驱动文档或通过测试者两种不同的模式来观察所用驱动的结果 | true 或者 false      | TRUE          |
| defaultStatementTimeout        | 设置超时时间，它决定驱动等待数据库响应的秒数。               | Any positive integer | Not Set(null) |
| mapUnderscoreToCamelCase       | 是否开启自动驼峰命名规则映射，即从经典数据库列名A_COLUMN到经典Java数据名aColumn的类似映射 | true 或者 false      | FALSE         |
| ...(还有更多)                  | ...                                                          | ...                  | ...           |
| &emsp;开启自动峰命令规则映射： |                                                              |                      |               |

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
        mybatis可以配置多种环境，default指定使用某种环境，可以达到快速切换环境
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

**mapper文件的常用标签：**

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
<!-- 插入员工信息 -->
    <insert id="insertEmp" >
        insert into emp(emp_id,emp_name,gender,email,dept_id)
        values(#{empId},#{empName},#{gender},#{email},#{deptId})
    </insert>

    <!-- 删除员工信息 -->
    <delete id="deleteEmp">
        delete from emp
        where emp_id=#{empId}
    </delete>

    <!-- 修改员工信息 -->
    <update id="updateEmp">
        update emp
        set emp_name=#{empName},gender=#{gender},email=#{email},dept_id=#{deptId}
    </update>
```

**dao接口**

``` java
    /*插入员工信息*/
    int insertEmp(Employee emp);

    /*删除员工信息*/
    int deleteEmp(Employee emp);

    /*修改员工信息*/
    int updateEmp(Employee empl);
```

**测试方法**

``` java
    @Test
    public void insertEmp(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(dao.Demo02Dao.class);

        Employee employee = new Employee();
        employee.setEmpName("雪落");
        employee.setGender("女");
        employee.setEmail("xueluo@123.com");
        employee.setDeptId(1002);

        int i = mapper.insertEmp(employee);
        if(i==1){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        sqlSesion.close();
    }

    /*修改员工信息*/
    @Test
    public void updateEmp(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Employee employee = new Employee();
        employee.setEmpId(5);
        employee.setEmpName("李淳刚");
        employee.setGender("男");
        employee.setEmail("licg@123.com");
        employee.setDeptId(1001);

        int i = mapper.updateEmp(employee);
        if(i==1){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }

        sqlSesion.close();
    }

    /*删除员工信息*/
    @Test
    public void deleteEmp(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Employee employee = new Employee();
        employee.setEmpId(5);

        int i = mapper.deleteEmp(employee);
        if(i==1){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

        sqlSesion.close();
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

&emsp;在使用mybatis进行增删改后，有个疑问：那就是方法中传递给dao接口方法的参数，mapper文件是如何进行读取的呢？

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

&emsp;多个参数mybatis会做特殊处理，多个参数会被封转成一个map，#{}就是从map中获取指定的key值。多个参数处理有两种方法：

1. 在mapper1文件中使用#{param1}去读取参数
2. 在dao接口的方法使用```@param("mapper文件读取的参数名")```对参数注解

以下面为例子，就会报错

```java
List<Employee> manyParam(String gender, Integer deptId);
```

``` xml
    <!--多个参数-->
    <!--
        由员工性别和部门id查找员工信息
    -->
    <select id="manyParam" resultType="bean.Employee">
        select *
        from emp
        where gender=#{gender} and dept_id=#{deptId}
    </select>
```

``` java
    // 多个参数
    @Test
    public void manyParam(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);
        List<Employee> emps = mapper.manyParam("男", 1001);
        for(Employee e : emps){
            System.out.println(e);
        }
    }
```

&emsp;map是[param1:value1,param2:value2...]，所以多个参数取值的话可以使用#{param1}...或[索引]。

&emsp;xml文件取值方式是```#{param1}、#{param2}...```，如下

``` xml
    <select id="manyParam" resultType="bean.Employee">
        select *
        from emp
        where gender=#{param1} and dept_id=#{param2}
    </select>
```

&emsp;使用<font color=#ff700>命名参数</font>的方法，使用方法如下：

``` java
// dao接口的方法
List<Employee> manyParam(@Param("gender") String gender, @Param("deptId") Integer deptId);
```

&emsp;命名参数：明确指定封装参数时map的key，使用注解@Param("参数名""),多个参数会被封装成一个map



### 2.2.3 参数为POJO

&emsp;如果多个参数恰好是实体类的属性，那么可以直接传入POJO。格式为：#{属性名}：取出POJO的属性值。

### 2.2.4 参数为Map

&emsp;如果多个参数不是POJO，那么也可以传入map，使用#{key}取出对应的值。

``` xml
    <!--参数类型为Map-->
    <select id="paramCategoryIsMap" resultType="bean.Employee">
        select *
        from emp
        where gender=#{gender} and dept_id=#{deptId}
    </select>
```

``` java
    List<Employee> paramCategoryIsMap(Map<String,Object> map);
```

``` java
    @Test
    public void paramCategoryIsMap(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Map<String,Object> map = new HashMap<>();
        map.put("gender","女");
        map.put("deptId","1002");

        List<Employee> emps = mapper.paramCategoryIsMap(map);

        for (Employee e : emps){
            System.out.println(e);
        }
    }
```

&emsp;如果多个参数不是实体类属性，如果不是经常使用，可以选择传Map。但是如果经常使用，推荐编写一个TO(Transfer Object)数据传输对象。

### 2.2.5 mybatis参数处理源码分析





### 2.2.6 #和$取值的区别

**两种取值方式的区别：***

&emsp;${}：是以预编译的形式，将参数设置到sql语句中；PreparedStatement；可以防止sql注入。在使用${}，时，若参数时中文的，需要加上单引号，不然会报错。示例：```'${gender}'```

&emsp;#{}：取出的值直接拼装在sql语句中，会有安全问题

&emsp;mapper文件中的sql语句

```xml
    <select id="paramCategoryIsMap" resultType="bean.Employee">
        select *
        from emp
        where gender='${gender}' and dept_id=#{deptId}
    </select>
```

&emsp;mybatis转换后的sql语句

```sql
select * from emp where gender='女' and dept_id=? 
```

**两种取值方法分别对应的情景：**

&emsp;在大多数情况下，取值都应该去使用#{}。在分库分表的情况下(原生jdbc不支持占位符的地方)，，就可以使用${}进行取值，比如取不同年份的工资表数据：

```sql
select * from ${year}_salary where xxx;
```

&emsp;再比如排序

```sql
select * from tb1_emp order_by ${f_name} ${order}
```

&emsp;**#{}更丰富的用法：**

&emsp;&emsp;规定参数的一些规则：

&emsp;&emsp;javaType、jdbcType、mode(存储过程)、numericScale、resultType、typeHandler、jdbcTypeName、expression(未来准备支持的功能)

&emsp;jdbcType通常需要再某种特定条件下被设置：比如再我们传递的参数为null时，有些数据库可能不能识别mybatis对null的默认处理，比如oracle环境下时，会报错```JdbcType OTHER```无效的类型。因为mybatis对所有的null都映射的是原生jdbc的other类型，oracle不能正确处理。 在mybatis的全局配置文件中：```JdbcTypeForNull=OTHER；```，oracle不支持OTHER类型。解决方法：

```xml
// 1. 影响当前语句的JdbcType
select * from emp where id=#{id} and emp_name=#{empName JdbcType=OTHER}

// 2. 改变全局配置，在mybatis配置文件的settings标签下的setting标签
JdbcTypeForNull=NULL
```



## 2.3 查询语句——select元素

* 返回值为Java基本类型
* 返回值为String类型
* 返回值为collection结婚类型
* 返回值为map类型

### 2.3.1 返回值类型为Java基本类型

&emsp;mybatis做了类型转换，为包装类的首字母小写，如Integer就是integer。

```xml
    <select id="getByEmpDeptId" resultType="integer">
        select dept_id
        from emp
        where emp_id=#{empId}
    </select>
```



### 2.3.2 返回值类型为String

&emsp;和基本类型一样。

```xml
    <select id="getByEmpName" resultType="string">
        select emp_name
        from emp
        where emp_id=#{empId}
    </select>
```



### 2.3.3 返回值类型为集合

&emsp;当dao接口方法的返回值是集合时，mapper文件中select元素的resultType属性的值应该是集合内的元素的类型。例如：

```xml
    <select id="getByEmpList" resultType="bean.Employee">
        select *
        from emp
    </select>
```

### 2.3.4 返回值类型为Map

&emsp;返回一条记录的map，Key就是列名，值就是对应的值

```xml
    <select id="getByEmpMap" resultType="map">
        select emp_name
        from emp
        where emp_id=#{empId}
    </select>
```

```java
    @Test
    public void getByEmpMap(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Map<String, Object> byEmpMap = mapper.getByEmpMap(2);
        System.out.println("key值"+byEmpMap.keySet());   // key就是字段名
        System.out.println("value值"+byEmpMap.values()); // value就是字段值
        sqlSesion.close();
    }
/*输出：
	key值[emp_name]
	value值[李四]
*/
```

&emsp;查询多条记录，并将其封装成一个Map：Map<Integer,Employee>，键是这条记录的主键，值是封装后的javabean对象



## 2.4 resultMap

&emsp;resultMap用于自定义某个javaBean的封装规则，type表示自定义的Java类型，id是唯一的，方便引用。示例如下

```xml
    <!-- resultMap自定义封装规则 -->
    <resultMap id="MyEmp" type="bean.Employee">
        <!--
            id标签用于定义逐渐列的封装规则，定义逐渐底层有优化
            column指定那一列的字段为逐渐
            property指定对象的javaBean属性
        -->
        <id column="id" property="id"/>

        <!--定义普通列封装规则-->
        <result column="emp_name" property="empName"/>
        <!-- 其他不指定的列会自动封装，但是推荐只要写resultMap，就将全部的映射规则都写上，方便检查-->
    </resultMap>
    
    <select id="getByIdResultMap" resultMap="MyEmp">
        select * from emp where id=#{id}
    </select>
```



### 2.4.1 级联属性封装结果

&emsp;需求：查询员工的同时查询它的部门

**数据库准备**：

```sql
create table dept(
    id int(11) primary key auto_increment
    dept_name varchar(40) not null
);

alter table emp add column dept_id int(11);

alter table emp 
add constraint fk_emp_dept 
foreign key(dept_id) references dept(id)
-- alter table 从表
-- add constraint 约束名 foreign key (关联字段) references 主表(关联字段)
```

**mapper文件：**

```xml
    <!-- 关联查询 -->
    <resultMap id="EmpAndDept" type="bean.Employee">
        <id column="id" property="id"></id>
        <result column="emp_name" property="empName"/>
        <result column="gender" property="gender"/>
        <result column="email" property="email"/>
        <result column="dept_id" property="dept.deptId"/>
        <result column="dept_name" property="dept.deptName"/>
    </resultMap>
    <select id="getEmpAndDept" resultMap="EmpAndDept">
        select t1.id,t1.emp_name,t1.gender,t1.email,t1.dept_id,t2.dept_name
        from emp t1,dept t2
        where t1.dept_id=t2.id and t1.id=#{id}
    </select>
```

**javaBean：**

```java
// 准备一个与dept对应的entity
// 在emp对应的entity中添加一个属性
private Department dept;
```



### 2.4.2 association定义联合属性

&emsp;在resultMap标签中通过<association>标签去定义其他实体类的属性，来进行联合查询。

**mapper文件：**

``` xml
    <select id="getEmpAndDept" resultMap="EmpAndDept">
        select t1.id,t1.emp_name,t1.gender,t1.email,t1.dept_id,t2.dept_name
        from emp t1,dept t2
        where t1.dept_id=t2.dept_id and t1.id=#{id}
    </select>

    <!-- 关联查询之使用association标签 -->
    <resultMap id="EmpAndDept" type="bean.Employee">
        <id column="id" property="id"></id>
        <result column="emp_name" property="empName"/>
        <result column="gender" property="gender"/>
        <result column="email" property="email"/>

        <!--
            association可以指定联合的JavaBean对象
            property指定哪个属性是联合对象，比如emp对象中有个属性叫做dept，是另一个实体类对象
            javaType指定这个属性对象的类型，比如dept的实体类是Department
        -->
        <association property="dept" javaType="bean.Department">
            <id column="dept_id" property="deptId"/>
            <result column="dept_name" property="deptName"/>
        </association>
    </resultMap>
```



### 2.4.3 association进行分步查询

&emsp;在2.4.2中，可以将分两步执行，第一步查员工的部门id。第二部通过部门id查找部门名称。使用association可以进行分步查询。

创建部门表的查询方法，DepartmentMapper.xml文件方法

```xml
    <select id="getByDeptId>" resultType="bean.Department">
        select dept_name from dept where dept_id=#{deptId}
    </select>
```

EmployeeMapper.xml文件方法

```xml
    <!-- association分步查询 -->
    <resultMap id="resultEmpIdAndDeptId" type="bean.Employee">
        <id column="id" property="id"/>
        <result column="emp_name" property="empName"/>
        <result column="gender" property="gender"/>
        <result column="email" property="email"/>
        <result column="dept_id" property="deptId"/>


        <!--
            1. 先根据员工id查找员工信息
            2. 根据员工信息中的dept_id去dept表查部门信息
            3. 将部门信息设置到员工中

            select表明当前属性是调用select指定的方法查出结果
            column指定将那一列的值传递给这个方法
        -->
        <association property="dept" select="dao.DepartmentDAO.getByDeptId" column="dept_id"/>

    </resultMap>
    <select id="getByEmpIdSelectDeptName" resultMap="resultEmpIdAndDeptId">
        select * from emp where id=#{id}
    </select>
```

DepartmentMapper.xml

```xml
<mapper namespace="dao.DepartmentDAO">
    <select id="getByDeptId" resultType="bean.Department">
        select * from dept where dept_id=#{deptId}
    </select>
</mapper>
```



### 2.4.4 association延迟加载

&emsp;当一个实体类中存在其他实体类属性，进行查询时就会进行关联查询，或者不查询。关联查询的缺点就是耗费资源。association可以做到**延迟加载**，即当需要查询其他实体属性的内容时，才进行关联查询。

&emsp;在association分步查询的前提上，在mybatis的配置文件配置文件中对以下两个属性进行配置，即可进行延迟加载。

```xml
        <!-- 延迟加载 -->
        <!--
            lazyLoadingEnabled：
            延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。
            特定关联关系中可通过设置 fetchType 属性来覆盖该项的开关状态。
			默认时开启
            aggressiveLazyLoading：
            开启时，任一方法的调用都会加载该对象的所有延迟加载属性。
            否则，每个延迟加载属性会按需加载
			默认开启
        -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
```



### 2.4.5 collection关联查询

需求：对Department实体类添加属性 ```List<Employee>```。在对部门进行查询时，将部门下的所有员工也查询出来。

```xml
    <!-- 需求：查出部门的同时，查出该部门下的所有员工 -->
    <select id="getByDeptIdPlus" resultMap="deptAndEmp">
        select t1.dept_id ,t1.dept_name ,t2.id ,t2.emp_name 
        from dept t1,emp t2
        where t1.dept_id = t2.dept_id and t1 .dept_id =#{deptId};
    </select>
    
    <resultMap id="deptAndEmp" type="bean.Department">
        <id column="dept_id" property="deptId"/>
        <result column="dept_name" property="deptName"/>
        <!--
            collection定义关联集合类型的属性的封装规则
            ofType指定集合内元素的类型
        -->
        <collection property="empList" ofType="bean.Employee">
            <id column="id" property="id"/>
            <result column="emp_name" property="empName"/>
        </collection>
    </resultMap>
```



### 2.4.6 collection分步查询&延迟加载

&emsp;在之前多对一查询时，使用了association标签进行分布查询。步骤如下

1. 定义查询emp的方法
2. 定义根据emp的字段查询dept的方法
3. 使用resultMap封装返回值
4. 使用association标签对其他bean属性进行封装
5. 使用association标签的```select```属性指定查询dept的sql
6. 使用association标签的```column```属性，将查询dept所需要的参数传递过去



&emsp;再一对多的查询中，也可以这样做，之不过不是使用association标签，而是使用collection标签。

```xml
    <select id="getByDeptIdMax" resultMap="associationOneAndMany">
        select dept_name,dept_id from dept where dept_id = #{deptId}
    </select>

    <resultMap id="associationOneAndMany" type="bean.Department">
        <id column="dept_id" property="deptId"/>
        <result column="dept_name" property="deptName"/>
        <collection property="empList" ofType="bean.Employee" select="getByEmpId" column="dept_id" fetchType="lazy">
            <id column="id" property="id"/>
            <result column="emp_name" property="empName"/>
            <result column="email" property="email"/>
        </collection>

    </resultMap>

    <select id="getByEmpId" resultType="bean.Employee">
        select id,emp_name ,email from emp where dept_id=#{deptId}
    </select>
```



&emsp;不论是association定义单个属性还是collection定义多个属性，都是使用```column```进行传值。那么<font color=red>需要传递多个值的时候怎么做呢？</font>

&emsp;当需要column传递多个参数时，可以将多列的值封装map再传递，column取值就是```ccolumn={key1=value1,key2=value2}```。其次，即使再mybatis配置文件中开启的全局延迟加载，也可以在局部进行关闭，不用关闭整个配置文件的。那就是使用```fetchType``属性来管理，它的属性值有：

1. lazy：延迟加载
2. eager：立即加载



### 2.4.7 discriminator鉴别器

&emsp;discriminator(鉴别器)可以判断根据某列的不同值，去选择不同的封装行为。

**示例：** 查询部门表，当部门人数大于2时，查出部门下所有员工，部门人数小于2时，查出部门下员工的人数。





## 2.5 动态SQL

