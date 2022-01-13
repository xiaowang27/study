package dao;

import bean.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AutoEmpSQL {
    @Select("select * from emp")
    public List<Employee> queryEmpList();
}
