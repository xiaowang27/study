package dao;

import bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Demo02Dao {
    /**增删改语句 start**/
    /*插入员工信息*/
    int insertEmp(Employee emp);

    /*删除员工信息*/
    int deleteEmp(Employee emp);

    /*修改员工信息*/
    int updateEmp(Employee empl);
    /**增删改语句 end**/


    /**参数处理 start**/
    //单个参数
    Employee singleParam(Integer empId);

    //多个参数
    List<Employee> manyParam(@Param("gender") String gender, @Param("deptId") Integer deptId);

    //参数类型为Map
    List<Employee> paramCategoryIsMap(Map<String,Object> map);
    /**参数处理 end**/


    /** 查询语句-返回值类型 start**/
    //返回值类型为Java的基本类型
    Integer getByEmpDeptId(Integer empId);

    //返回值类型为Java的String类型
    String getByEmpName(Integer empId);

    //返回值类型为collection集合类型
    List<Employee> getByEmpList();

    //查询语句-返回值类型为map类型
    Map<String,Object> getByEmpMap(Integer empId);
    /**返回值类型 end**/
}
