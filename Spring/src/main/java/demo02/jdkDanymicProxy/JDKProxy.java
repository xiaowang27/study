package demo02.jdkDanymicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    // 创建接口实现类代理对象
    public static void main(String[] args) {
        // 1.newProxyInstance方法的三个参数
        ClassLoader loader = JDKProxy.class.getClassLoader();
        Class[] interfaces = {User.class};
        /**
         * 第三个参数的内部类写法
         *         new InvocationHandler() {
         *             @Override
         *             public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         *                 return null;
         *             }
         *         };
         */

        // 第三个参数的外部实现类写法
        User user = new UserImpl();
        EnhanceLogic h = new EnhanceLogic(user);

        // 2.使用Proxy的方法创建代理对象
        User proxyInstance = (User)Proxy.newProxyInstance(loader, interfaces, h);

        // 3.调用方法测试
        int add = proxyInstance.add(3, 10);
        System.out.println("add()方法的结果是："+add);

        StringBuilder id = new StringBuilder("2022");
        StringBuilder update = proxyInstance.update(id);
        System.out.println("update()方法的结果是："+update);
    }
}
