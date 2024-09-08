package io.spring.learning.repository;

import io.spring.learning.common.money.Percentage;
import io.spring.learning.entity.Account;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.util.HashMap;
import java.util.Map;

public class StubAccountRepository implements AccountRepository{
    public static final String TYPE = "Stub";

    private Map<String, Account> accountsByCreditCard = new HashMap<String, Account>();

    public StubAccountRepository() {
        Account account = new Account("123456789", "Keith and Keri Donald");
        account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
        account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
        accountsByCreditCard.put("1234123412341234", account);
    }

    @Override
    public String getInfo() {
        return TYPE;
    }

    public Account findByCreditCard(String creditCardNumber) {
        Account account = accountsByCreditCard.get(creditCardNumber);
        if (account == null) {
            throw new ObjectRetrievalFailureException(Account.class, creditCardNumber);
        }
        return account;
    }

    public void updateBeneficiaries(Account account) {
        // nothing to do, everything is in memory
    }
}
