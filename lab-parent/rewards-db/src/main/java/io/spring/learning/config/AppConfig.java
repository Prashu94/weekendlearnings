package io.spring.learning.config;

import io.spring.learning.repository.*;
import io.spring.learning.service.AccountManager;
import io.spring.learning.service.JpaAccountManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public AccountManager accountManager(){
        return new JpaAccountManager();
    }

    @Bean
    public AccountRepository accountRepository(){
        return new JpaAccountRepository();
    }

    @Bean
    public RestaurantRepository restaurantRepository(){
        return new JpaRestaurantRepository();
    }

    @Bean
    public RewardRepository rewardRepository(DataSource dataSource){
        return new JdbcRewardRepository(dataSource);
    }
}
