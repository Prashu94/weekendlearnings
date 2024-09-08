package io.spring.learning.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.spring.learning")
@Import(DbConfig.class)
public class RewardsConfig {
}
