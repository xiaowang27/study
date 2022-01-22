package demo01.comment.service;

import demo01.comment.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// 注解的value可以不写，默认是类名且首字母小写
@Service(value = "userService") // 等于<bean id="userService class="..."/>
public class UserService {

    @Value("张三")
    private String name;
    // @Autowired
    // @Qualifier("userDao")   // 若一个接口有多个实现类，通过类型注入就无法知道要找哪个实现类了
    // @Resource    // 是javax提供的
    @Resource(name="userDao")
    private UserDao userDao;
    public void add(){
        userDao.add();
        System.out.println("comment service add...");
        System.out.println("name="+name);
    }
}
