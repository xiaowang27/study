package demo;

import bean.Employee;
import dao.Demo02Dao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.MyBatisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mapper映射文件
 *
 * @Author 王俊杰
 * @CreateDate 2022/1/26 14:15 Demo02
 */
public class Demo02 {

    /**增删改语句 start**/
    //插入员工信息
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
    //修改员工信息
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

    //删除员工信息
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

    /**增删改语句 end**/


    /**参数处理 strat**/
    // 单个参数
    @Test
    public void singleparam(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Employee employee = mapper.singleParam(1);
        System.out.println(employee);

    }

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

    // 参数类型为Map
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
    /**参数处理 end**/


    /**查询语句-返回值类型 start**/
    // 返回值类型为Java基本类型
    @Test
    public void getByEmpDeptId(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Integer byEmpDeptId = mapper.getByEmpDeptId(1);
        System.out.println("部门id为"+byEmpDeptId);
        sqlSesion.close();
    }

    //返回值类型为Java的String类型
    @Test
    public void getByEmpName(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        String byEmpName = mapper.getByEmpName(2);
        System.out.println(byEmpName);
    }

    //返回值类型为collection集合类型
    @Test
    public void getByEmpList(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        List<Employee> byEmpList = mapper.getByEmpList();

        for(Employee e : byEmpList){
            System.out.println(e);
        }
    }

    //查询语句-返回值类型为map类型
    @Test
    public void getByEmpMap(){
        SqlSession sqlSesion = MyBatisUtil.getSqlSesion();
        Demo02Dao mapper = sqlSesion.getMapper(Demo02Dao.class);

        Map<String, Object> byEmpMap = mapper.getByEmpMap(2);
        System.out.println("key值"+byEmpMap.keySet());   // key就是字段名
        System.out.println("value值"+byEmpMap.values()); // value就是字段值
        sqlSesion.close();
    }
    /**查询语句-返回值类型 end**/

}
