package rewards.internal.restaurant;

import io.spring.learning.repository.JpaRestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.DataManagementSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JpaRestaurantRepositoryTests extends AbstractRestaurantRepositoryTests{

    private PlatformTransactionManager transactionManager;

    private TransactionStatus transactionStatus;

    @BeforeEach
    public void setUp() throws Exception {
        DataManagementSetup dataManagementSetup = new DataManagementSetup();

        JpaRestaurantRepository restaurantRepository = new JpaRestaurantRepository();
        restaurantRepository.setEntityManager(dataManagementSetup.createEntityManager());
        this.restaurantRepository = restaurantRepository;

        // begin a transaction
        transactionManager = dataManagementSetup.getTransactionManager();
        transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @Test
    @Override
    public void testProfile() {
        assertTrue(restaurantRepository instanceof JpaRestaurantRepository, "JPA expected");
    }

    @AfterEach
    public void tearDown() throws Exception {
        // rollback the transaction to avoid corrupting other tests
        if (transactionManager != null)
            transactionManager.rollback(transactionStatus);
    }
}
