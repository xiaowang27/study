package demo02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"demo02.aopOperation"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
}
