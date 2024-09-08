package io.spring.learning.service;

import io.spring.learning.common.money.Percentage;
import io.spring.learning.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JpaAccountManager extends AbstractAccountManager{
    
    private EntityManager entityManager;

    public JpaAccountManager(){}

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {

        List<Account> l = entityManager.createQuery("select a from Account a LEFT JOIN FETCH a.beneficiaries")
                .getResultList();

        List<Account> result = new ArrayList<>();

        l.forEach(a -> {
            if(!result.contains(a))
                result.add(a);
        });

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        Account account = (Account) entityManager.find(Account.class, id);

        if(account!=null){
            account.getBeneficiaries().size();
        }

        return account;
    }

    @Override
    @Transactional
    public Account save(Account account) {
        entityManager.persist(account);
        return account;
    }

    @Override
    @Transactional
    public void update(Account account) {
        entityManager.merge(account);
    }

    @Override
    @Transactional
    public void updateBeneficaryAllocationPercentages(Long accountId, Map<String, Percentage> allocationPercentages) {
        Account account = getAccount(accountId);
        for(Map.Entry<String, Percentage> entry: allocationPercentages.entrySet()){
            account.getBeneficiary(entry.getKey()).setAllocationPercentage(entry.getValue());
        }
    }

    @Override
    @Transactional
    public void addBeneficiary(Long accountId, String beneficiaryName) {
        getAccount(accountId).addBeneficiary(beneficiaryName, Percentage.zero());
    }

    @Override
    @Transactional
    public void removeBeneficiary(Long accountId, String beneficiaryName, Map<String, Percentage> allocationPercentages) {
        getAccount(accountId).removeBeneficiary(beneficiaryName);
        if(allocationPercentages!=null){
            updateBeneficaryAllocationPercentages(accountId, allocationPercentages);
        }
    }
}
