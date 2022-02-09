package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author 王俊杰
 * @CreateDate 2022/1/26 11:23 MyBatisUtil
 */
public class MyBatisUtil {
    public static SqlSession getSqlSesion(){
        String resource = "config/mybatis-config.xml";
        SqlSession sqlSession = null;
        try {
            InputStream input = Resources.getResourceAsStream(resource);
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(input);
            sqlSession = build.openSession(true);   // 设置true就是默认提交
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return sqlSession;
        }
    }
}
