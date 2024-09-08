package rewards.internal.reward;

import io.spring.learning.config.AppConfig;
import io.spring.learning.config.DbConfig;
import io.spring.learning.repository.JdbcRewardRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("jpa")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, DbConfig.class})
public class JdbcRewardRepositoryIntegerationTests extends AbstractRewardRepositoryTests{
    @Override
    public void testProfile() {
        assertTrue(
                rewardRepository.getInfo().equals(JdbcRewardRepository.TYPE),
                "JDBC expected but found " + rewardRepository.getInfo());
    }
}
