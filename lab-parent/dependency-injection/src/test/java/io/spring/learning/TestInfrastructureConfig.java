package io.spring.learning;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RewardsConfig.class)
public class TestInfrastructureConfig {
}
