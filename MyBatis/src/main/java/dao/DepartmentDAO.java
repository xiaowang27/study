package dao;

import bean.Department;

public interface DepartmentDAO {
    Department getByDeptId(Integer deptId);

    // 查询部门的同时，查询部门内的所有员工
    Department getByDeptIdPlus(Integer deptId);

    // 查询部门，在查询部门内的所有员工
    Department getByDeptIdMax(Integer deptId);
}
