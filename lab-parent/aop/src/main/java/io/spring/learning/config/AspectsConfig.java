package io.spring.learning.config;

import com.jamonapi.MonitorFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "io.spring.learning.aspects")
@EnableAspectJAutoProxy
public class AspectsConfig {

    public MonitorFactory monitorFactory() {
        return null;
    }
}
