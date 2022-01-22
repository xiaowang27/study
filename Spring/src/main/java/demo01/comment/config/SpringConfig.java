package demo01.comment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // 标记为配置类，代替xml文件
@ComponentScan(basePackages = {"demo01.comment"})
public class SpringConfig {
}
