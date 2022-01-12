package dao;

import bean.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmpMapper {
    @Select("select * from emp")
    public List<Employee> queryEmpList();
}
