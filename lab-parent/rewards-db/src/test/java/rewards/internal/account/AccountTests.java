package rewards.internal.account;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.common.money.Percentage;
import io.spring.learning.dto.AccountContribution;
import io.spring.learning.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {

    private Account account = new Account("1", "Keith and Keri Donald");

    @Test
    public void accountIsValid(){
        account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
        account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
        assertTrue(account.isValid());
    }

    @Test
    public void accountIsInValidWithNoBeneficiaries(){
        assertFalse(account.isValid());
    }

    @Test
    public void accountIsInvalidWhenBeneficiaryAllocationsAreUnder100(){
        account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
        account.addBeneficiary("Corgan", Percentage.valueOf("25%"));
        assertFalse(account.isValid());
    }

    @Test
    public void makeContribution() {
        account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
        account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
        AccountContribution contribution = account.makeContribution(MonetaryAmount.valueOf("100.00"));
        assertEquals(contribution.getAmount(), MonetaryAmount.valueOf("100.00"));
        assertEquals(MonetaryAmount.valueOf("50.00"), contribution.getDistribution("Annabelle").getAmount());
        assertEquals(MonetaryAmount.valueOf("50.00"), contribution.getDistribution("Corgan").getAmount());
    }


}
