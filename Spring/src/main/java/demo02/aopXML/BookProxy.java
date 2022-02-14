package demo02.aopXML;

import org.aspectj.lang.ProceedingJoinPoint;

public class BookProxy {
    public void before(){
        System.out.println("前置通知-before()...");
    }
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕通知-around() 前...");
        point.proceed();
        System.out.println("环绕通知-around() 后...");
    }
}
