package dao;

import bean.Employee;
import org.junit.Test;

/**初次使用mybatis**/
public interface Demo01Dao {
    // 通过emp_id查询员工
    Employee getByEmpId(Integer empId);
}
