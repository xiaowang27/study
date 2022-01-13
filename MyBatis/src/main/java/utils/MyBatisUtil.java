package utils;

import bean.Employee;
import dao.EmpMapper;
import dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisUtil {

    /**
     * 1. 根据XML配置文件，创建一个sqlSessionFactory对象
     *
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
        Employee emp = sqlSession.selectOne("selectEmpList", 1);
        System.out.println(emp);

        // 3. 释放资源
        sqlSession.close();
    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void test01() throws IOException {
        // 1. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取接口实现类对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee emp = mapper.getEmpById(1);
        System.out.println(emp);

        // 使用mapper标签的class属性绑定接口，利用注解将sql写在方法上
        System.out.println("注解写sql");
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Employee> employees = empMapper.queryEmpList();
        for (Employee e : employees) {
            System.out.println(e);
        }
        sqlSession.close();
    }

    /**
     * 测试增删改查方法
     *
     * @throws IOException sqlSession对象获取异常
     */
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

    // 查询员工信息
    public void getEmpList(EmployeeMapper employeeMapper, Employee employee) {
        System.out.println("查询所有员工");
        List<Employee> empList = employeeMapper.getEmpList();
        for(Employee e : empList){
            System.out.println(e);
        }
    }
}
