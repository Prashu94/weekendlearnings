package rewards.internal.account;

import io.spring.learning.repository.JpaAccountRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import io.spring.learning.config.DbConfig;
import io.spring.learning.config.AppConfig;

@ActiveProfiles("jpa")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class, DbConfig.class })
public class JpaAccountRepositoryIntegrationTests extends AbstractAccountRepositoryTests{
    @Override
    public void testProfile() {
        assertTrue(accountRepository.getInfo().equals(JpaAccountRepository.INFO), "JPA expected but found " + accountRepository.getInfo());
    }
}
