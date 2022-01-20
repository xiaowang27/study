package dao;

import bean.Department;

public interface DepartmentDAO {
    Department getByDeptId(Integer deptId);
}
