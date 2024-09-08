package io.spring.learning;

import io.spring.learning.config.RewardsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RewardsConfig.class)
public class TestInfrastructureConfig {
}
