package DataSync.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.yml")
@ComponentScan(value = {"DataSync"})
public class MybatisPlusConfig {
    //todo 此处可配置查询分页
}
