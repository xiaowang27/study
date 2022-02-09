package dao;

import bean.Department;

import java.util.List;

public interface DepartmentDAO {
    Department getByDeptId(Integer deptId);

    // 查询部门的同时，查询部门内的所有员工
    Department getByDeptIdPlus(Integer deptId);

    // 查询部门，在查询部门内的所有员工
    Department getByDeptIdMax(Integer deptId);

    // resultMap标签的子标签discriminator
    List<Department> discriminatorTest();
}
