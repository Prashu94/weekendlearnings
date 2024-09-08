package rewards;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;
import io.spring.learning.repository.*;
import io.spring.learning.rewards.RewardNetworkImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import utils.DataManagementSetup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RewardNetworkImplTests {

    private RewardNetworkImpl rewardNetwork;
    
    private PlatformTransactionManager transactionManager;

    private TransactionStatus transactionStatus;


    @BeforeEach
    public void setUp() throws Exception {

        DataManagementSetup dataManagementSetup = new DataManagementSetup();

        // create stubs to facilitate fast in-memory testing with dummy data and no external dependencies
//        AccountRepository accountRepo = new StubAccountRepository();
//        RestaurantRepository restaurantRepo = new StubRestaurantRepository();
//        RewardRepository rewardRepo = new StubRewardRepository();

        JpaAccountRepository accountRepo = new JpaAccountRepository();
        accountRepo.setEntityManager(dataManagementSetup.createEntityManager());

        JpaRestaurantRepository restaurantRepo = new JpaRestaurantRepository();
        restaurantRepo.setEntityManager(dataManagementSetup.createEntityManager());

        JdbcRewardRepository rewardRepo = new JdbcRewardRepository(dataManagementSetup.getDataSource());

        // being a transaction
        transactionManager = dataManagementSetup.getTransactionManager();
        transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        // setup the object being tested by handing what it needs to work
        rewardNetwork = new RewardNetworkImpl(accountRepo, restaurantRepo, rewardRepo);
    }

    @AfterEach
    public void tearDown() throws Exception {
        if(transactionManager != null) {
            transactionManager.rollback(transactionStatus);
        }
    }

    @Test
    public void testRewardForDining() {
        // create a new dining of 100.00 charged to credit card '1234123412341234' by merchant '123457890' as test input
        Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");

        // call the 'rewardNetwork' to test its rewardAccountFor(Dining) method
        RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);

        // assert the expected reward confirmation results
        assertNotNull(confirmation);
        assertNotNull(confirmation.getConfirmationNumber());

        // assert an account contribution was made
        AccountContribution contribution = confirmation.getAccountContribution();
        assertNotNull(contribution);

        // the account number should be '123456789'
        assertEquals("123456789", contribution.getAccountNumber());

        // the total contribution amount should be 8.00 (8% of 100.00)
        assertEquals(MonetaryAmount.valueOf("8.00"), contribution.getAmount());

        // the total contribution amount should have been split into 2 distributions
        assertEquals(2, contribution.getDistributions().size());

        // each distribution should be 4.00 (as both have a 50% allocation)
        assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Annabelle").getAmount());
        assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Corgan").getAmount());
    }

}
