package io.spring.learning.repository;

import io.spring.learning.entity.Account;
import io.spring.learning.entity.Beneficiary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaAccountRepository implements AccountRepository{
    public static final String ACCOUNT_BY_CC_QUERY = "select ACCOUNT_ID from " +
            "T_ACCOUNT_CREDIT_CARD where number = :ccn";
    public static final String INFO = "JPA";

    private static final Logger LOGGER = LoggerFactory.getLogger("config");

    private EntityManager entityManager;

    public JpaAccountRepository(){
        LOGGER.info("Created JpaAccountManager");
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public Account findByCreditCard(String creditCardNumber) {
        Integer accountId =
                (Integer) entityManager.createNativeQuery(ACCOUNT_BY_CC_QUERY).setParameter("ccn"
                        , creditCardNumber).getSingleResult();

        Account account = (Account) entityManager.find(Account.class, accountId.longValue());

        account.getBeneficiaries().size();

        return account;
    }

    @Override
    public void updateBeneficiaries(Account account){
        for(Beneficiary b: account.getBeneficiaries()){
            entityManager.persist(b);
        }
    }

}
