package io.spring.learning.repository;

import io.spring.learning.entity.Account;

public interface AccountRepository {
    public Account findByCreditCard(String creditCardNumber);
    public void updateBeneficiaries(Account account);
}
