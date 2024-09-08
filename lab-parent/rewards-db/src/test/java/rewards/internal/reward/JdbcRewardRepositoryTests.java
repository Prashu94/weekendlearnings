package rewards.internal.reward;

import io.spring.learning.repository.JdbcRewardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JdbcRewardRepositoryTests extends  AbstractRewardRepositoryTests{

    @BeforeEach
    public void setUp(){
        dataSource = createTestDataSource();
        rewardRepository = new JdbcRewardRepository(dataSource);
    }

    @Test
    @Override
    public void testProfile() {
        assertTrue(rewardRepository instanceof JdbcRewardRepository, "JDBC Expected");
    }

    private  DataSource createTestDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springlearning?serverTimezone=UTC");
        dataSource.setUsername("prashant");
        dataSource.setPassword("admin123@");
        return dataSource;
    }
}
