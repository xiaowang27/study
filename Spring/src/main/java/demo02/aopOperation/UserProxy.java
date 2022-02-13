package demo02.aopOperation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 增强的类
@Component
@Aspect // 注解表示要生成代理对象
public class UserProxy {

    @Pointcut("execution(* demo02.aopOperation.User.add(..))")
    public void  pointDemo02(){}
    // 前置通知
    @Before("pointDemo02()")
    public void before() {
        System.out.println("before... 前置通知...");
    }

    // 后置通知
    @After("execution(* demo02.aopOperation.User.add(..))")
    public void after() {
        System.out.println("after... 后置通知...");
    }

    // 后置通知 返回值之后执行
    @AfterReturning("execution(* demo02.aopOperation.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning... 后置通知，在返回值之后执行...");
    }

    /**
     * 环绕通知
     *
     * @param point
     */
    @Around("execution(* demo02.aopOperation.User.add(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("around...   环绕之前...");
        // proceed()表示被增强的方法
        point.proceed();
        System.out.println("around...   环绕之后...");
    }

    // 异常通知
    @AfterThrowing("execution(* demo02.aopOperation.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing... 异常通知...");
    }
}
