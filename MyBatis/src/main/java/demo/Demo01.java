package demo;

import bean.Employee;
import dao.AutoEmpSQL;
import dao.Demo01Dao;
import dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.MyBatisUtil;

import java.util.List;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/26 11:30 Demo01
 */
public class Demo01 {
    /**初次使用MyBatis**/
    /*根据员工id查询员工*/
    @Test
    public void getByEmpId(){
        // 通过mybatis工具类获取sqlsession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSesion();
        // 通过sqlsession对象获取到mapper文件的接口
        Demo01Dao mapper = sqlSession.getMapper(Demo01Dao.class);
        // 调用dao接口的方法
        // Employee emp1 = mapper.getByEmpId(1);
        // System.out.println(emp1);

        // 不写接口，直接使用mapper文件
        Employee emp2 = sqlSession.selectOne("dao.Demo01Dao.getByEmpId", 2);
        System.out.println(emp2);

        // 释放资源
        sqlSession.close();
    }
}
