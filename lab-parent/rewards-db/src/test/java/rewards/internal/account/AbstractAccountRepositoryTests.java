package rewards.internal.account;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.common.money.Percentage;
import io.spring.learning.entity.Account;
import io.spring.learning.entity.Beneficiary;
import io.spring.learning.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract  class AbstractAccountRepositoryTests {

    @Autowired
    protected AccountRepository accountRepository;

    public abstract void testProfile();

    @Test
    @Transactional
    public void findByCreditCard() {
        Account account = accountRepository
                .findByCreditCard("1234123412341234");

        // assert the returned account contains what you expect given the state
        // of the database
        assertNotNull(account, "account should never be null");
        assertEquals(Long.valueOf(1), account.getEntityId(), "wrong entity id");
        assertEquals("123456789", account.getNumber(), "wrong account number");
        assertEquals("Keith and Keri Donald", account.getName(), "wrong name");
        assertEquals(2, account
                .getBeneficiaries().size(), "wrong beneficiary collection size");

        Beneficiary b1 = account.getBeneficiary("Annabelle");
        assertNotNull(b1, "Annabelle should be a beneficiary");
        assertEquals(MonetaryAmount.valueOf("0.00"),
                b1.getSavings(), "wrong savings");
        assertEquals(Percentage.valueOf("50%"),
                b1.getAllocationPercentage(), "wrong allocation percentage");

        Beneficiary b2 = account.getBeneficiary("Corgan");
        assertNotNull(b2, "Corgan should be a beneficiary");
        assertEquals(MonetaryAmount.valueOf("0.00"),
                b2.getSavings(), "wrong savings");
        assertEquals(Percentage.valueOf("50%"),
                b2.getAllocationPercentage(), "wrong allocation percentage");
    }
}
