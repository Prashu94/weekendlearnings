package io.spring.learning;

import io.spring.learning.config.DbConfig;
import io.spring.learning.repository.*;
import io.spring.learning.service.RewardNetwork;
import io.spring.learning.service.RewardNetworkImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@Import(DbConfig.class)
public class RewardsConfig {

    private DataSource dataSource;

    private EntityManagerFactory entityManagerFactory;

    private PlatformTransactionManager transactionManager;

    @Bean
    public RewardNetwork rewardNetwork(){
        return new RewardNetworkImpl(
                accountRepository(),
                restaurantRepository(),
                rewardRepository()
        );
    }

    public RewardsConfig(DataSource dataSource, EntityManagerFactory entityManagerFactory, PlatformTransactionManager transactionManager) {
        this.dataSource = dataSource;
        this.entityManagerFactory = entityManagerFactory;
        this.transactionManager = transactionManager;
    }

    @Bean
    public AccountRepository accountRepository(){
        JpaAccountRepository jpaAccountRepository = new JpaAccountRepository();
        jpaAccountRepository.setEntityManager(entityManagerFactory.createEntityManager());
        return jpaAccountRepository;
    }

    @Bean
    public RestaurantRepository restaurantRepository(){
        JpaRestaurantRepository restaurantRepository = new JpaRestaurantRepository();
        restaurantRepository.setEntityManager(entityManagerFactory.createEntityManager());
        return restaurantRepository;
    }

    @Bean
    public RewardRepository rewardRepository(){
        return new JdbcRewardRepository(dataSource);
    }
}
