package utils;

import bean.Department;
import bean.Employee;
import dao.AutoEmpSQL;
import dao.DepartmentDAO;
import dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        AutoEmpSQL autoEmpSQL = sqlSession.getMapper(AutoEmpSQL.class);
        List<Employee> employees = autoEmpSQL.queryEmpList();
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

    // mybatis参数处理之多个参数  key获取
    @Test
    public void mybatisParas() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee jack = mapper.mybatisParas(1, "jack");
        System.out.println(jack);
    }

    // 多参数处理之POJO
    @Test
    public void getEmpByPOJO() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee  emp = new Employee();
        emp.setId(220112);
        emp.setEmpName("张三");
        Employee empByPOJO = mapper.getEmpByPOJO(emp);
        System.out.println(empByPOJO);
        sqlSession.close();
    }

    // 多参数处理之Map
    @Test
    public void getEmpByMap() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee  emp = new Employee();
        Map<String,Object> map = new HashMap<>();
        map.put("empName","张三");
        map.put("id",220112);
        Employee empByMap = mapper.getEmpByMap(map);
        System.out.println(empByMap);
        sqlSession.close();
    }

    // 查询语句-select元素
    @Test
    public void selectElement() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> empByLastnameLike = mapper.getEmpByLastnameLike();
        for(Employee e:empByLastnameLike){
            System.out.println(e);
        }

        // 返回值是一条记录且类型为Map
        Map<String, Object> empByLastNameMap = mapper.getEmpByLastNameMap(1);
        System.out.println(empByLastNameMap);

        // 返回值是多条记录且类型为Map
        Map<Integer, Employee> empByReturnMaps = mapper.getEmpByReturnMaps();
        System.out.println(empByReturnMaps);
        sqlSession.close();
    }

    // 查询语句-resultMap
    @Test
    public void selectElementResultMap() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee byIdResultMap = mapper.getByIdResultMap(1);
        System.out.println(byIdResultMap);
        sqlSession.close();
    }


    // 关联查询之[级联属性查询]、[association联合查询]、[association分步查询]
    @Test
    public void getEmpAndDept() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee byIdResultMap = mapper.getEmpAndDept(1);
        System.out.println(byIdResultMap);

        // 分步查询
        Employee byEmpIdSelectDeptName = mapper.getByEmpIdSelectDeptName(4);
        System.out.println("分步查询的结果");
        System.out.println(byEmpIdSelectDeptName);
        System.out.println(byEmpIdSelectDeptName.getDept());
        sqlSession.close();
    }

    // 关联查询1对多，查部门时，将部门内的员工全部查出来
    @Test
    public void getByDeptIdPlus() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        DepartmentDAO mapper = sqlSession.getMapper(DepartmentDAO.class);
        Department dept = mapper.getByDeptIdPlus(1002);
        System.out.println(dept);
    }
    // 关联查询1对多，先查部门，再差部门内所有员工
    @Test
    public void getByDeptIdMax() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        DepartmentDAO mapper = sqlSession.getMapper(DepartmentDAO.class);
        Department dept = mapper.getByDeptIdMax(1002);
        System.out.println(dept.getDeptName());
        System.out.println(dept.getEmpList());
    }
}
