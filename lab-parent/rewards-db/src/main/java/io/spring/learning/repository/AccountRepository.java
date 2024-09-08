package io.spring.learning.repository;

import io.spring.learning.dto.AccountContribution;
import io.spring.learning.entity.Account;

public interface AccountRepository {
    public String getInfo();

    public Account findByCreditCard(String creditCardNumber);

    public void updateBeneficiaries(Account account);
}
