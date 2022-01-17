package dao;

import bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    // 查询员工方法
    public Employee getEmpById(Integer id);

    // 增加员工方法
    public Integer addEmp(Employee employee);

    // 更新员工信息方法
    public Integer updateEmp(Employee employee);

    // 删除员工方法
    public Boolean deleteEmp(Employee employee);

    // 员工列表
    public List<Employee> getEmpList();

    // mybatis多个参数处理
    public Employee mybatisParas(@Param("id") int id, @Param("empName") String empName);

    // 多参数处理之POJO
    Employee getEmpByPOJO(Employee employee);

    // 多参数处理之Map
    Employee getEmpByMap(Map<String,Object> map);
}
