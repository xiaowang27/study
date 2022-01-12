package dao;

import bean.Employee;

import java.util.List;

public interface EmployeeMapper {
    // 查询员工方法
    public Employee getEmpById(Integer id);

    // 增加员工方法
    public void addEmp(Employee employee);

    // 更新员工信息方法
    public void updateEmp(Employee employee);

    // 删除员工方法
    public void deleteEmp(Employee employee);

    // 员工列表
    public List<Employee> getEmpList();
}
