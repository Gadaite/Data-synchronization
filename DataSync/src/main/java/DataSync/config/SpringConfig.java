package DataSync.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:application.yml")
@ComponentScan(value = {"DataSync"})
@Configuration
@Import(value = {SwaggerConfig.class})
public class SpringConfig {
}
