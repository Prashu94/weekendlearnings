package io.spring.learning.service;

import io.spring.learning.common.money.Percentage;
import io.spring.learning.entity.Account;

import java.util.List;
import java.util.Map;

public interface AccountManager {

    public String getInfo();
    
    public List<Account> getAllAccounts();

    public Account getAccount(Long id);

    public Account save(Account account);

    public void update(Account account);

    public void updateBeneficaryAllocationPercentages(Long accountId,
                                                      Map<String, Percentage> allocationPercentages);
    
    public void addBeneficiary(Long accountId, String beneficiaryName);

    public void removeBeneficiary(Long accountId, String beneficiaryName,
                                  Map<String, Percentage> allocationPercentages);
}
