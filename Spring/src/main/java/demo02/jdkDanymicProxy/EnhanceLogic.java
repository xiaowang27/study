package demo02.jdkDanymicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EnhanceLogic implements InvocationHandler {

    private Object invocation;

    // 有参构造,将目标对象传入
    public EnhanceLogic(Object invocation){
        this.invocation = invocation;
    }

    /**
     * 增强的逻辑写入此方法中
     * @param proxy 代理对象
     * @param method 代理对象的方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.目标方法执行之前要做的事情
        System.out.println(method.getName()+"方法要执行了");

        // 2.被增强的方法开始执行，根据不同的方法执行不同的操作
        if (method.getName().equals("add")){
            for(Object o : args){
                System.out.println("方法参数："+o);
            }
        }else{
            System.out.println("当前id是："+args[0]);
        }
        Object invoke = method.invoke(invocation, args);

        // 3.目标方法执行之后要做的事情
        System.out.println(method.getName()+"方法执行完毕");

        // 4.返回值
        return invoke;
    }
}
