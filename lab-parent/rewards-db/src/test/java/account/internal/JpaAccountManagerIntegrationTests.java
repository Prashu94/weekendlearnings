package account.internal;

import io.spring.learning.config.AppConfig;
import io.spring.learning.config.DbConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("jpa")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, DbConfig.class})
public class JpaAccountManagerIntegrationTests extends AbstractDatabaseAccountManagerTests{

    @Test
    @Override
    public void testProfile(){
        assertTrue(accountManager.getInfo().equals("JPA"), "JPA expected but found " + accountManager.getInfo());
    }
}
