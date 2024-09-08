package account.internal;

import utils.DataManagementSetup;
import io.spring.learning.service.JpaAccountManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JpaAccountManagerManualIntegrationTests extends AbstractDatabaseAccountManagerTests{
    DataManagementSetup dataManagementSetup = new DataManagementSetup();

    public JpaAccountManagerManualIntegrationTests() {
        setupForTest();
    }

    @Test
    @Override
    public void testProfile() {
        assertTrue(accountManager instanceof JpaAccountManager, "JPA expected");
        logger.info("JPA with Hibernate");
    }

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        transactionUtils.beginTransaction();
    }

    @AfterEach
    public void tearDown() throws Exception {
        transactionUtils.rollbackTransaction();
    }

    private void setupForTest() {
        dataSource = dataManagementSetup.getDataSource();

        JpaAccountManager accountManager = new JpaAccountManager();
        accountManager.setEntityManager(dataManagementSetup.createEntityManager());
        this.accountManager = accountManager;
        transactionManager = dataManagementSetup.getTransactionManager();
    }
}
